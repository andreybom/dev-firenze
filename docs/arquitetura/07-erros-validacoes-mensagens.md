# Erros, validações e mensagens

**Tarefa:** ARC-006
**Status:** Implementado em 12/10/2026

## 1. Contrato único

Toda falha HTTP da API usa `Content-Type: application/problem+json` e o seguinte corpo:

```json
{
  "type": "validation_error",
  "title": "Dados inválidos",
  "status": 400,
  "detail": "Revise os campos informados.",
  "instance": "/api/v1/lots",
  "correlationId": "request-12345678",
  "errors": [
    {
      "field": "code",
      "code": "required",
      "message": "Código é obrigatório"
    }
  ]
}
```

| Campo | Regra |
|---|---|
| `type` | Código estável da categoria; pode orientar o fluxo da interface |
| `title` | Resumo curto e seguro para pessoas |
| `status` | Mesmo código HTTP da resposta |
| `detail` | Orientação segura; nunca contém stack trace, SQL, segredo ou detalhe interno |
| `instance` | Caminho da requisição, sem parâmetros de consulta |
| `correlationId` | Identificador para suporte e rastreabilidade |
| `errors` | Lista de erros por campo; vazia quando a falha não é de formulário |

Clientes devem tolerar campos adicionais e tipos novos. Eles não devem decidir comportamento analisando texto livre de `title`, `detail` ou `message`.

## 2. Categorias

| HTTP | `type` | Uso |
|---:|---|---|
| 400 | `validation_error` | Campos semanticamente inválidos |
| 400 | `invalid_request` | JSON, parâmetro ou formato inválido |
| 401 | `authentication_required` | Sessão ausente ou expirada; será integrado na camada de segurança |
| 403 | `access_denied` | Usuário autenticado sem permissão; será integrado na camada de segurança |
| 404 | `resource_not_found` | Recurso inexistente ou não visível ao escopo autorizado |
| 405 | `method_not_allowed` | Método HTTP incompatível com o recurso |
| 409 | `business_conflict` | Estado, concorrência ou integridade impedem a operação |
| 415 | `unsupported_media_type` | Conteúdo enviado em formato não aceito |
| 500 | `internal_error` | Falha inesperada sem exposição de detalhes internos |

Erros de autenticação e autorização deverão preservar exatamente este contrato quando Spring Security for implantado.

## 3. Validações por campo

Os códigos são estáveis e independentes do texto apresentado:

| Código | Significado |
|---|---|
| `required` | Valor obrigatório ausente ou vazio |
| `invalid_format` | Formato não reconhecido, como e-mail ou padrão textual |
| `invalid_size` | Comprimento ou quantidade fora do permitido |
| `out_of_range` | Número ou data fora do intervalo |
| `invalid_value` | Restrição específica não coberta pelos códigos anteriores |

DTOs de entrada devem declarar mensagens em português claro. Uma validação que depende do estado atual do domínio retorna `business_conflict`, não simula um erro de campo.

## 4. Correlação e logs

- O cliente pode enviar `X-Correlation-Id` com 8 a 64 caracteres seguros: letras, números, ponto, sublinhado e hífen.
- O backend gera UUID quando o cabeçalho está ausente ou inválido.
- O mesmo valor aparece em `X-Correlation-Id`, no corpo de erro e no contexto de log da requisição.
- CORS expõe o cabeçalho para o frontend.
- Falhas inesperadas registram correlação, método, caminho e classe da exceção, sem corpo da requisição, segredo ou mensagem interna.
- O frontend pode exibir o identificador em uma área de detalhes/suporte, mas não como mensagem principal.

## 5. Backend

`ApiExceptionHandler` converte validação Jakarta, JSON inválido, parâmetros inválidos, recurso inexistente, conflito de domínio, integridade, concorrência e falhas inesperadas. Exceções de domínio que carregam texto para o usuário devem ser criadas apenas com mensagem previamente segura.

`CorrelationIdFilter` executa no início da cadeia e remove o contexto de log ao final para impedir vazamento entre requisições.

## 6. Frontend

`api-error.ts` contém o tipo equivalente, valida o corpo recebido, agrupa erros por campo e fornece uma mensagem genérica para falhas de rede ou respostas fora do contrato. Componentes não devem exibir diretamente `HttpErrorResponse.message` nem concatenar objetos recebidos do servidor.

Fluxo esperado:

1. serviço recebe a falha HTTP;
2. corpo é verificado por `isApiError`;
3. `validation_error` associa `errors` aos controles do formulário;
4. `authentication_required` direciona ao fluxo de sessão quando a segurança estiver pronta;
5. demais categorias exibem `detail` e mantêm `correlationId` disponível para suporte;
6. resposta desconhecida usa a mensagem local segura.

## 7. Evidências automatizadas

- Backend: testes HTTP cobrem validação, JSON inválido, ausência, conflito, falha inesperada, conteúdo e cabeçalho de correlação.
- Frontend: testes cobrem reconhecimento do contrato, agrupamento de campos e fallback seguro.
- O teste de falha inesperada confirma que um texto semelhante a segredo não chega à resposta.
