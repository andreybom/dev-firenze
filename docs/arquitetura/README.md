# Arquitetura do MVP — marco M1

**Data:** 12/10/2026

**Status:** Aprovada

**Marco:** M1 — arquitetura aprovada

Arquitetura aprovada por **Andrey Bom em 12/10/2026**, conforme [formulário do M1](02-aprovacao-m1.md) e decisões DEC-037 a DEC-039.

Documentos complementares:

- [Configuração de ambientes](03-configuracao-de-ambientes.md)
- [Convenções de desenvolvimento](04-convencoes-de-desenvolvimento.md)
- [Padrão de API, paginação e filtros](05-padrao-api.md)
- [Datas, horários, moeda e fuso](06-datas-moeda-fuso.md)
- [Erros, validações e mensagens](07-erros-validacoes-mensagens.md)

## 1. Estilo arquitetural

O MVP será construído como **monólito modular**, com frontend Angular separado e API REST Spring Boot. Essa abordagem mantém implantação e transações simples no servidor local, mas exige limites claros entre os módulos para evitar acoplamento indevido.

```text
Navegador
   |
   | HTTPS / mesma origem em produção
   v
Frontend Angular 22
   |
   | /api/v1
   v
Backend Spring Boot 4.1 / Java 25
   |-- identidade e acesso
   |-- cadastros
   |-- clientes e corretores
   |-- reservas e bloqueios
   |-- contratos e documentos
   |-- financeiro
   |-- auditoria e notificações
   |
   +--> PostgreSQL 18.x
   +--> armazenamento de documentos
   +--> servidor SMTP
```

Não haverá microsserviços, banco por dispositivo ou sincronização offline no MVP.

## 2. Módulos do backend

| Módulo | Responsabilidade | Não deve fazer diretamente |
|---|---|---|
| `identity` | usuários, perfis, sessão e escopo por loteamento | alterar regras comerciais |
| `development` | loteamentos, quadras, lotes, estados e características | criar contratos ou pagamentos |
| `customer` | PF/PJ, contatos, cônjuge, representantes e corretores | modificar disponibilidade do lote |
| `reservation` | reserva, validade, prorrogação, conversão e bloqueio | registrar pagamento |
| `contract` | contrato, participantes, modelos, versões, PDF e distrato | sobrescrever histórico financeiro |
| `finance` | parcelas, pagamentos, alocações, saldos e estornos | alterar cláusulas contratuais |
| `document` | metadados, armazenamento, hash, acesso e retenção | decidir autorização de negócio |
| `notification` | fila local, tentativas e envio de e-mail | controlar estado da reserva |
| `audit` | eventos imutáveis de operações sensíveis | permitir alteração ou exclusão de eventos |

Cada módulo terá pacotes de API, aplicação, domínio e infraestrutura. Módulos interagem por serviços de aplicação e eventos internos, não por acesso direto às tabelas ou repositórios de outro módulo.

## 3. API

- Prefixo: `/api/v1`.
- JSON em UTF-8.
- Nomes de propriedades em `camelCase`.
- Paginação obrigatória em listagens potencialmente grandes.
- Filtros explícitos por parâmetros de consulta.
- Datas civis em `YYYY-MM-DD`.
- Instantes em ISO 8601 com offset; persistência em UTC.
- Valores monetários em decimal, nunca `float`/`double`.
- Operações sensíveis aceitam chave de idempotência quando houver risco de repetição.

Resposta de erro padronizada:

```json
{
  "type": "validation_error",
  "title": "Dados inválidos",
  "status": 400,
  "detail": "Revise os campos informados.",
  "instance": "/api/v1/reservations",
  "correlationId": "identificador",
  "errors": [
    { "field": "customerId", "message": "Campo obrigatório" }
  ]
}
```

## 4. Identidade e sessão

Proposta para o MVP:

- contas locais administradas pelo sistema;
- senhas com hash adaptativo suportado pelo Spring Security;
- autenticação por sessão e cookie `HttpOnly`, `Secure` e `SameSite`;
- proteção CSRF nas operações de escrita;
- frontend e backend servidos pela mesma origem em produção;
- CORS restrito apenas aos endereços aprovados nos ambientes de desenvolvimento;
- autorização obrigatória no backend, independentemente da interface;
- sessão invalidada para usuário desativado conforme política a ser formalizada em SEG-002.

## 5. Persistência

- PostgreSQL major 18; usar a atualização corretiva mais recente aprovada para o ambiente.
- Flyway é a única ferramenta autorizada a alterar esquemas compartilhados.
- Hibernate usa `ddl-auto=validate`.
- Identificadores internos serão UUID.
- Tabelas usam nomes em `snake_case` e timestamps `created_at`/`updated_at`.
- Alterações concorrentes usarão versionamento otimista quando aplicável.
- Reserva exclusiva terá proteção transacional e restrição no banco.
- Lançamentos financeiros e eventos de auditoria não serão atualizados nem apagados pelo fluxo normal.

## 6. Documentos

Proposta inicial:

- conteúdo fora do PostgreSQL, em diretório/volume dedicado do servidor local;
- metadados, proprietário, tipo, tamanho, hash SHA-256 e chave de armazenamento no PostgreSQL;
- nomes físicos não serão derivados diretamente do nome enviado pelo usuário;
- downloads passarão pela autorização da aplicação;
- banco e documentos participarão do mesmo plano de backup e restauração;
- uma interface de armazenamento permitirá migrar futuramente para MinIO/S3 sem alterar as regras de negócio.

A escolha entre volume local e MinIO deverá ser confirmada com a infraestrutura antes de concluir M1.

## 7. E-mail

- integração SMTP configurada por ambiente;
- mensagens persistidas em uma fila/outbox local antes do envio;
- falha de internet não desfaz a operação de negócio;
- tentativas com intervalo progressivo e limite configurável;
- falhas definitivas ficam visíveis para reprocessamento;
- nenhum segredo SMTP será armazenado no repositório.

Servidor, remetente e política de retenção ainda precisam ser informados pela infraestrutura.

## 8. Implantação

- Ambientes separados: desenvolvimento, teste, homologação e produção.
- Builds imutáveis e identificados pelo commit Git.
- Configurações e segredos externos aos artefatos.
- Banco executado com usuário de privilégios mínimos.
- HTTPS obrigatório nos ambientes compartilhados.
- Logs estruturados com identificador de correlação e sem dados sensíveis.
- Health checks para aplicação, banco, documentos e fila de e-mail.
- Backup coordenado de banco e documentos com restauração testada.

O sistema operacional, a estratégia de processos/contêineres, DNS e certificados do servidor local continuam dependentes do inventário de infraestrutura.

## 9. Qualidade

- Backend: testes unitários, integração e PostgreSQL real nos cenários de persistência.
- H2 em memória será usado somente para teste rápido de inicialização, nunca para validar SQL ou comportamento específico do PostgreSQL.
- Frontend: Vitest para componentes e serviços; jornadas críticas terão testes de interface posteriores.
- Compilação, testes e auditoria de dependências serão executados no pipeline.
- Reserva, autorização, transições e financeiro exigem cobertura automatizada de casos positivos e negativos.

## 10. Decisões aprovadas no M1

As decisões bloqueantes foram aprovadas no [formulário de aprovação do M1](02-aprovacao-m1.md):

1. arquitetura, limites modulares, API e autenticação;
2. volume local ou MinIO para documentos;
3. modelo conceitual inicial.

Servidor SMTP, remetente, sistema operacional, capacidade, DNS, certificados e retenção definitiva continuam necessários, mas serão tratados nas tarefas de infraestrutura indicadas no formulário e não bloquearam o marco M1.
