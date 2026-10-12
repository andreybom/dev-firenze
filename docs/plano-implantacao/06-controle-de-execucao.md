# Controle de execução

**Última atualização:** 12/10/2026  
**Situação geral:** Em execução — plano aprovado e início autorizado  
**Próximo marco:** M2 — base segura pronta

Este é o painel vivo do projeto. O [backlog detalhado](02-backlog-detalhado.md) contém a linha de base completa. Todas as tarefas são consideradas `Não iniciadas` até aparecerem neste controle com outro estado.

## 1. Progresso por frente

| Frente | Tarefas | Esforço base | Concluído | Em execução | Bloqueado | Situação |
|---|---:|---:|---:|---:|---:|---|
| Mobilização e gestão | 8 | 5,5 dp | 18,2% (1/5,5 dp) | 0 | 0 | Em execução |
| Arquitetura e fundação | 14 | 20,5 dp | 53,7% (11/20,5 dp) | 1 | 0 | Configuração por ambiente, lint e formatação concluídos; pipeline criado e aguardando execução remota |
| Segurança, identidade e auditoria | 12 | 30,5 dp | 0% | 0 | 0 | Não iniciada |
| Cadastros estruturais | 11 | 25 dp | 8% (2/25 dp) | 0 | 0 | Modelo físico do núcleo concluído; implementação dos cadastros ainda não iniciada |
| Clientes e corretores | 8 | 17 dp | 0% | 0 | 0 | Não iniciada |
| Reservas, alertas e bloqueios | 15 | 34 dp | 0% | 0 | 0 | Não iniciada |
| Contratos e documentos | 17 | 48,5 dp | 0% | 0 | 0 | Não iniciada |
| Financeiro e relatórios | 15 | 39,5 dp | 0% | 0 | 0 | Não iniciada |
| Migração operacional | 10 | 25 dp | 0% | 0 | 0 | Gate PEN-013 futuro |
| Infraestrutura e continuidade | 12 | 23 dp | 0% | 0 | 0 | Não iniciada |
| Qualidade e segurança final | 12 | 41 dp | 0% | 0 | 0 | Não iniciada |
| Homologação, treinamento e implantação | 21 | 34,5 dp | 0% | 0 | 0 | Gate PEN-017 futuro |
| **Total** | **155** | **344 dp** | **4,1% (14/344 dp)** | **1** | **0** | **Preparação do M2 em execução** |

## 2. Tarefas ativas ou fora do estado padrão

| ID | Status | Responsável nominal | Início | Prazo | Avanço | Evidência/próxima ação |
|---|---|---|---|---|---:|---|
| GOV-001 | Concluída | Aprovador final | 12/10/2026 | — | 100% | Plano, escopo e itens futuros aprovados nesta conversa |
| GOV-002 | Concluída | Aprovador final | 12/10/2026 | — | 100% | Autorização explícita de início nesta conversa |
| ARC-001 | Concluída | Líder Técnico (função) | 12/10/2026 | — | 100% | [Inventário inicial](07-inventario-inicial.md) |
| ARC-002 | Concluída | Líder Técnico (função) | 12/10/2026 | — | 100% | Java 25, Spring Boot 4.1, Angular 22, Node 24 e PostgreSQL major 18 validados; DEC-040 permite patch `18.x` e cobre o servidor 18.6 testado |
| ARC-003 | Concluída | Backend | 12/10/2026 | — | 100% | Coordenadas e pacotes alterados para `br.com.firenze:loteamento`; nome do produto padronizado como Firenze |
| ARC-004 | Concluída | Líder Técnico | 12/10/2026 | — | 100% | [Arquitetura modular](../arquitetura/README.md), [modelo conceitual](../arquitetura/01-modelo-conceitual-inicial.md) e [aprovação do M1](../arquitetura/02-aprovacao-m1.md) |
| ARC-008 | Concluída | Backend | 12/10/2026 | — | 100% | V1 aplicada pelo Flyway em bancos vazios H2 e PostgreSQL 18.6; criação ocorreu exclusivamente pela migração versionada |
| ARC-009 | Concluída | Backend | 12/10/2026 | — | 100% | `ddl-auto=validate`; aplicação inicializada sobre o esquema criado pelo Flyway sem alteração automática |
| ARC-010 | Concluída | Backend/DevOps | 12/10/2026 | — | 100% | Perfis `local`, `test`, `homologation` e `production`, variáveis externas, `.env.example` sem segredo e [matriz de configuração](../arquitetura/03-configuracao-de-ambientes.md) |
| ARC-011 | Concluída | Líder Técnico/Frontend | 12/10/2026 | — | 100% | EditorConfig, Spotless, ESLint/Angular ESLint, Prettier e [convenções de desenvolvimento](../arquitetura/04-convencoes-de-desenvolvimento.md) validados localmente |
| ARC-012 | Em validação | DevOps/QA | 12/10/2026 | — | 80% | Workflow `.github/workflows/ci.yml` criado; falta executar no GitHub após publicação para comprovar falha/sucesso remoto |
| CAD-001 | Concluída | Backend/Dados | 12/10/2026 | — | 100% | Modelo de empresa, loteamento, quadra e lote aprovado no M1 e materializado na migração V1, com restrições e índices testados |

Quando uma tarefa começar, remover a linha indicativa e incluir a tarefa. Ao concluir, manter a linha até o fechamento do marco correspondente, com a evidência vinculada.

## 3. Impedimentos

| ID | Tarefa afetada | Impedimento | Desde | Responsável pela remoção | Próxima ação | Prazo |
|---|---|---|---|---|---|---|
| — | — | Nenhum impedimento bloqueante registrado | — | — | Validar ARC-012 após a próxima publicação e iniciar ARC-005/ARC-007 | — |

## 4. Gates futuros já conhecidos

| Gate | Necessidade | Limite | Situação |
|---|---|---|---|
| PEN-013 | Fontes reais, amostra e validador dos dados | Antes de MIG-002 | Adiada conforme DEC-035 |
| PEN-017 | Titulares e substitutos nominais | Antes de HOM-001 | Adiada conforme DEC-035 |

## 5. Marcos

| Marco | Semana-alvo | Situação | Data prevista | Data real | Aprovador | Evidência |
|---|---:|---|---|---|---|---|
| M0 — Plano aprovado | 1 | Aprovado | — | 12/10/2026 | Aprovador final | Aprovação e autorização registradas nesta conversa |
| M1 — Arquitetura aprovada | 2 | Aprovado | — | 12/10/2026 | Andrey Bom | [Formulário de aprovação](../arquitetura/02-aprovacao-m1.md) e DEC-037 a DEC-039 |
| M2 — Base segura pronta | 3 | Em preparação | A definir | — | Líder Técnico/QA | Pipeline e fundação técnica em validação |
| M3 — Cadastros aceitos | 7 | Não iniciado | A definir | — | Negócio/Comercial | — |
| M4 — Comercial aceito | 9 | Não iniciado | A definir | — | Comercial/Diretoria | — |
| M5 — Contratos aceitos | 12 | Não iniciado | A definir | — | Jurídico | — |
| M6 — Financeiro aceito | 14 | Não iniciado | A definir | — | Financeiro | — |
| M7 — Migração aprovada | 16 | Não iniciado | A definir | — | Dados/Financeiro | — |
| M8 — Homologação concluída | 18 | Não iniciado | A definir | — | Áreas usuárias | — |
| M9 — Go/No-Go | 19 | Não iniciado | A definir | — | Diretoria/TI | — |
| M10 — Implantação estabilizada | 20 | Não iniciado | A definir | — | Andrey Bom | — |

## 6. Indicadores atuais

| Indicador | Valor | Meta/sinal |
|---|---:|---|
| Progresso ponderado | 4,1% | Conforme cronograma após M1 |
| Tarefas concluídas | 11/155 | — |
| Tarefas em execução | 1 | Limitadas à capacidade definida |
| Tarefas bloqueadas | 0 | Escalar críticas após 2 dias úteis |
| Defeitos bloqueadores | 0 | Zero para qualquer marco |
| Riscos altos/críticos sem tratamento | 0 | Zero |
| Marcos aprovados | 2/11 | — |

## 7. Histórico de atualizações

| Data | Alteração | Responsável |
|---|---|---|
| 10/10/2026 | Painel criado a partir da linha de base de 155 tarefas e 344 dp | Planejamento |
| 12/10/2026 | Plano aprovado e implementação autorizada; GOV-001/002 concluídas, M0 aprovado e execução iniciada | Aprovador final |
| 12/10/2026 | Inventário inicial concluído (ARC-001); validação da stack (ARC-002) pronta para iniciar | Líder Técnico (função) |
| 12/10/2026 | Verificação refeita: Maven 3.10.0 executou com JDK 25 após definir variáveis no processo; TypeScript ~6.0.0; psql ainda não localizado e ambiente PostgreSQL requer confirmação (dados v17 vs Compose v15) | Líder Técnico (função) |
| 12/10/2026 | psql 18.1 confirmado em `C:\Program Files\pgAdmin 4\runtime`; consulta local de versão do servidor bloqueada pela permissão de rede da sessão | Líder Técnico (função) |
| 12/10/2026 | DEC-036 aprovada: PostgreSQL Server 18.1 definido como alvo; referências anteriores a 17 e 15 superadas | Aprovador final |
| 12/10/2026 | Backend preparado com Flyway para PostgreSQL, Hibernate em validação, configuração obrigatória fora do perfil local e CORS por ambiente | Backend/DevOps |
| 12/10/2026 | `mvn compile` e build de produção Angular concluídos; Angular migrado para `@angular/build` e Vitest; audit npm passou de 11 vulnerabilidades altas para zero; testes não executados | Backend/Frontend |
| 12/10/2026 | Git regularizado e commit inicial publicado em `main`; repositório local e remoto sincronizados | Líder Técnico (função) |
| 12/10/2026 | ARC-003 concluída; pacote Java migrado para `br.com.firenze`, endpoint base versionado em `/api/v1` e proposta de arquitetura/modelo do M1 documentada | Backend/Líder Técnico |
| 12/10/2026 | Testes mínimos de contexto Spring e componente Angular aprovados; empacotamento Maven e build Angular de produção concluídos | Backend/Frontend |
| 12/10/2026 | Decisões bloqueantes do M1 consolidadas; dados de SMTP e infraestrutura reclassificados como insumos posteriores não bloqueantes | Líder Técnico |
| 12/10/2026 | M1 aprovado por Andrey Bom: arquitetura, volume local abstraído para documentos e modelo conceitual aprovados (DEC-037 a DEC-039); ARC-004 concluída | Aprovador final/Líder Técnico |
| 12/10/2026 | Flyway corrigido para o starter oficial do Spring Boot 4; migração V1 do núcleo estrutural aplicada em banco vazio e três testes aprovados | Backend |
| 12/10/2026 | Migração V1 validada em PostgreSQL 18.6 usando banco temporário isolado, removido após os testes; ARC-008, ARC-009 e CAD-001 concluídas | Backend/Dados |
| 12/10/2026 | DEC-040 aprovada: PostgreSQL major 18 com atualização corretiva `18.x`; servidor 18.6 validado passa a atender ao alvo e ARC-002 é concluída | Aprovador final/Líder Técnico |
| 12/10/2026 | Perfis de ambiente, contrato de variáveis e exemplo sem segredos concluíram ARC-010; Spotless, ESLint e Prettier concluíram ARC-011 com verificações verdes | Backend/Frontend/DevOps |
| 12/10/2026 | Pipeline CI criado para backend com PostgreSQL 18.6 e frontend com lint, formatação, testes, build e auditoria; ARC-012 aguarda execução remota | DevOps/QA |

## 8. Procedimento de atualização

Ao final de cada dia com execução:

1. atualizar tarefas ativas, percentuais e impedimentos;
2. registrar evidências das tarefas concluídas;
3. recalcular progresso pelo esforço concluído, não apenas pela quantidade;
4. atualizar o marco impactado;
5. registrar decisão ou mudança de escopo associada;
6. adicionar uma linha ao histórico quando houver mudança relevante.

