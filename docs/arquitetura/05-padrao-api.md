# Padrão de API, paginação e filtros

**Tarefa:** ARC-005
**Status:** Implementado em 12/10/2026

Este documento define o contrato obrigatório entre o frontend Angular e a API REST do Firenze.

## 1. Endereçamento e evolução

- A raiz da API é `/api/v1` em todos os ambientes.
- A versão no caminho representa uma versão principal incompatível. Alterações compatíveis permanecem em `v1`.
- Recursos usam substantivos no plural e `kebab-case` apenas quando o nome tiver mais de uma palavra.
- Identificadores internos são UUID e aparecem no caminho: `GET /api/v1/lots/{lotId}`.
- JSON usa UTF-8 e propriedades em `camelCase`.
- Endpoints de ação só são usados quando a operação não corresponde naturalmente a CRUD, por exemplo `POST /api/v1/reservations/{id}/cancel`.

O frontend usa `environment.apiUrl` já incluindo `/api/v1`; serviços não devem repetir nem omitir essa versão.

## 2. Métodos e respostas

| Operação | Método | Sucesso usual | Observação |
|---|---|---:|---|
| Consultar item | `GET` | `200` | Sem efeito colateral |
| Listar | `GET` | `200` | Paginação obrigatória quando a coleção puder crescer |
| Criar | `POST` | `201` | Retorna o recurso e cabeçalho `Location` |
| Substituir integralmente | `PUT` | `200` | Repetível com o mesmo conteúdo |
| Alterar parcialmente | `PATCH` | `200` | Campos aceitos são explícitos por endpoint |
| Excluir/desativar | `DELETE` | `204` | Exclusão física somente quando autorizada pelo domínio |

Requisições inválidas usam `400`; ausência, `404`; conflito de estado ou concorrência, `409`; autenticação ausente, `401`; e permissão insuficiente, `403`. Todas seguem o [contrato padronizado de erros](07-erros-validacoes-mensagens.md).

Operações financeiras ou sujeitas a repetição por reconexão deverão aceitar `Idempotency-Key`; a persistência e o prazo da chave serão definidos junto ao respectivo fluxo.

## 3. Paginação

Parâmetros comuns:

| Parâmetro | Regra |
|---|---|
| `page` | Índice iniciado em zero; padrão `0` |
| `size` | Entre `1` e `100`; padrão `20` |
| `sort` | Repetível no formato `campo,asc` ou `campo,desc` |

Cada endpoint mantém uma lista explícita de campos permitidos para ordenação. Não são aceitos nomes arbitrários de propriedade ou coluna.

Exemplo:

```http
GET /api/v1/lots?page=0&size=20&sort=status,asc&sort=code,asc
```

Resposta:

```json
{
  "items": [
    {
      "id": "018f5f10-12cc-7d19-9cb4-05a3197d18c5",
      "code": "L-001",
      "status": "AVAILABLE"
    }
  ],
  "page": {
    "number": 0,
    "size": 20,
    "totalElements": 1,
    "totalPages": 1
  }
}
```

O backend fornece `PageRequests` para validar limites e campos de ordenação e `PageResponse` para estabilizar o corpo da resposta. O frontend fornece `buildPageParams` e os tipos equivalentes. Os testes dos dois lados executam o mesmo exemplo de paginação e ordenação.

## 4. Filtros e busca

- Cada filtro é um parâmetro de consulta nomeado e documentado pelo endpoint.
- Nomes usam `camelCase`, como `developmentId`, `status`, `active` e `createdFrom`.
- Valores ausentes não filtram; string vazia não equivale a ausência e deve ser rejeitada quando não tiver significado.
- Intervalos usam sufixos `From` e `To`, com limites inclusivos salvo indicação contrária.
- Datas e instantes seguem o [padrão regional e temporal](06-datas-moeda-fuso.md).
- Filtros por estado usam o código estrutural da API, não o rótulo traduzido da tela.
- Busca textual geral, quando suportada, usa `search`; não será criada uma linguagem genérica de consulta.
- Filtros desconhecidos ou campos de ordenação não permitidos resultam em `400` com o tipo `invalid_request`.

Exemplo combinado:

```http
GET /api/v1/lots?developmentId=018f5f10-12cc-7d19-9cb4-05a3197d18c5&status=AVAILABLE&active=true&page=0&size=20&sort=code,asc
```

## 5. Compatibilidade

Uma mudança é compatível quando apenas adiciona campos opcionais, endpoints ou valores previamente preparados para extensão. Remover ou renomear campos, mudar tipos, alterar semântica ou tornar campo opcional obrigatório exige nova versão principal ou período formal de transição.

Clientes devem ignorar campos adicionais em respostas. O backend não deve depender da ordem das propriedades JSON.

## 6. Evidências automatizadas

- Backend: `PageRequestsTest` e `PageResponseTest`.
- Frontend: `api-page.spec.ts`.
- Configurações de ambiente do frontend apontam para `/api/v1`.

Os endpoints reais de lotes serão implementados nas tarefas de cadastro; os componentes transversais já estão funcionais e impedem divergência de contrato antes dessa implementação.
