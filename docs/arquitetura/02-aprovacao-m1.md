# Aprovação do marco M1 — arquitetura

**Data de elaboração:** 12/10/2026

**Responsável pela decisão:** Andrey Bom

**Status:** Aprovado

Este formulário contém somente as decisões que bloqueiam o início da modelagem física e da primeira migração Flyway. As informações operacionais de SMTP, servidor, DNS e certificados serão tratadas nas tarefas de infraestrutura e não bloqueiam esta aprovação.

## M1-001 — Arquitetura da aplicação

**Opção A — Aprovar (recomendada):** monólito modular, frontend Angular separado, API REST `/api/v1`, autenticação por sessão/cookie em mesma origem e os limites de módulos descritos na [proposta de arquitetura](README.md).

**Opção B — Solicitar ajustes:** registrar abaixo quais limites, padrões de API ou estratégia de autenticação devem mudar.

**Resposta:** A

**Ajustes/observações:** Sem ajustes.

## M1-002 — Armazenamento de documentos no MVP

**Opção A — Volume local com abstração (recomendada):** arquivos em volume dedicado do servidor, metadados e hash no PostgreSQL, acesso sempre mediado pela aplicação e interface preparada para futura migração a MinIO/S3. Reduz a complexidade inicial sem prender o domínio ao sistema de arquivos.

**Opção B — MinIO desde o início:** adiciona um serviço de armazenamento compatível com S3 ao ambiente. Facilita expansão futura, mas aumenta instalação, monitoramento, backup e recuperação do MVP.

**Resposta:** A

**Ajustes/observações:** Sem ajustes.

## M1-003 — Modelo conceitual inicial

**Opção A — Aprovar (recomendada):** aceitar as entidades, relações, regras transversais e ordem de migrações do [modelo conceitual inicial](01-modelo-conceitual-inicial.md). A aprovação autoriza somente a primeira migração do núcleo necessário; detalhes dos módulos continuam sujeitos às tarefas específicas do backlog.

**Opção B — Solicitar ajustes:** registrar abaixo as entidades ou relações que precisam mudar antes da primeira migração.

**Resposta:** A

**Ajustes/observações:** Sem ajustes.

## Registro da aprovação

**Decisão final:** Aprovado

**Aprovador:** Andrey Bom

**Data:** 12/10/2026

**Observações gerais:** Aprovação registrada nesta conversa; horário não informado. A primeira migração do núcleo está autorizada.

## Itens operacionais não bloqueantes para o M1

Estes dados serão solicitados antes das tarefas indicadas, sem impedir a aprovação arquitetural:

| Informação | Necessária antes de | Tratamento provisório |
|---|---|---|
| Servidor SMTP, porta, remetente e credenciais | ARC-014/RES-008 | Interface, outbox e variáveis de ambiente já definidas conceitualmente |
| Sistema operacional, capacidade e estratégia de processo/contêiner | OPS-002/OPS-004 | Builds independentes do ambiente e configuração externa |
| DNS e certificados | disponibilização de homologação | HTTPS obrigatório em ambientes compartilhados |
| Política final de retenção de documentos e e-mails | ARC-013/ARC-014 | Retenção configurável, sem exclusão automática até aprovação |
