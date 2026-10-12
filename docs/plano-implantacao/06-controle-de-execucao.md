# Controle de execução

**Última atualização:** 12/10/2026  
**Situação geral:** Em execução — plano aprovado e início autorizado  
**Próximo marco:** M1 — arquitetura aprovada

Este é o painel vivo do projeto. O [backlog detalhado](02-backlog-detalhado.md) contém a linha de base completa. Todas as tarefas são consideradas `Não iniciadas` até aparecerem neste controle com outro estado.

## 1. Progresso por frente

| Frente | Tarefas | Esforço base | Concluído | Em execução | Bloqueado | Situação |
|---|---:|---:|---:|---:|---:|---|
| Mobilização e gestão | 8 | 5,5 dp | 18,2% (1/5,5 dp) | 0 | 0 | Em execução |
| Arquitetura e fundação | 14 | 20,5 dp | 4,9% (1/20,5 dp) | 4 | 0 | Compilações base concluídas; conexão PostgreSQL e migração de domínio pendentes |
| Segurança, identidade e auditoria | 12 | 30,5 dp | 0% | 0 | 0 | Não iniciada |
| Cadastros estruturais | 11 | 25 dp | 0% | 0 | 0 | Não iniciada |
| Clientes e corretores | 8 | 17 dp | 0% | 0 | 0 | Não iniciada |
| Reservas, alertas e bloqueios | 15 | 34 dp | 0% | 0 | 0 | Não iniciada |
| Contratos e documentos | 17 | 48,5 dp | 0% | 0 | 0 | Não iniciada |
| Financeiro e relatórios | 15 | 39,5 dp | 0% | 0 | 0 | Não iniciada |
| Migração operacional | 10 | 25 dp | 0% | 0 | 0 | Gate PEN-013 futuro |
| Infraestrutura e continuidade | 12 | 23 dp | 0% | 0 | 0 | Não iniciada |
| Qualidade e segurança final | 12 | 41 dp | 0% | 0 | 0 | Não iniciada |
| Homologação, treinamento e implantação | 21 | 34,5 dp | 0% | 0 | 0 | Gate PEN-017 futuro |
| **Total** | **155** | **344 dp** | **0,6% (2/344 dp)** | **4** | **0** | **Execução iniciada** |

## 2. Tarefas ativas ou fora do estado padrão

| ID | Status | Responsável nominal | Início | Prazo | Avanço | Evidência/próxima ação |
|---|---|---|---|---|---:|---|
| GOV-001 | Concluída | Aprovador final | 12/10/2026 | — | 100% | Plano, escopo e itens futuros aprovados nesta conversa |
| GOV-002 | Concluída | Aprovador final | 12/10/2026 | — | 100% | Autorização explícita de início nesta conversa |
| ARC-001 | Concluída | Líder Técnico (função) | 12/10/2026 | — | 100% | [Inventário inicial](07-inventario-inicial.md) |
| ARC-002 | Em execução | Líder Técnico (função) | 12/10/2026 | — | 90% | TypeScript ajustado; Maven 3.10.0/JDK25 e psql 18.1 confirmados; PostgreSQL Server 18.1 aprovado, ambiente ainda a preparar |
| ARC-008 | Em execução | Backend | 12/10/2026 | — | 40% | Flyway configurado com suporte PostgreSQL e migrations em `classpath:db/migration`; primeira migration depende do modelo aprovado no M1 |
| ARC-009 | Em execução | Backend | 12/10/2026 | — | 70% | Hibernate valida o esquema e não o altera; confirmar inicialização conectada ao PostgreSQL Server 18.1 |
| ARC-010 | Em execução | Backend/DevOps | 12/10/2026 | — | 75% | Credenciais externas e perfil `local` para URL/CORS; configuração de produção e segregação operacional pendentes |
| ARC-011 | Em execução | Líder Técnico/Frontend | 12/10/2026 | — | 30% | Builders atualizados para `@angular/build`; runner migrado para Vitest e audit limpo; lint, formatação e revisão automática pendentes |

Quando uma tarefa começar, remover a linha indicativa e incluir a tarefa. Ao concluir, manter a linha até o fechamento do marco correspondente, com a evidência vinculada.

## 3. Impedimentos

| ID | Tarefa afetada | Impedimento | Desde | Responsável pela remoção | Próxima ação | Prazo |
|---|---|---|---|---|---|---|
| — | — | Nenhum impedimento bloqueante registrado | — | — | Prosseguir com ARC-002 e completar mobilização | — |

## 4. Gates futuros já conhecidos

| Gate | Necessidade | Limite | Situação |
|---|---|---|---|
| PEN-013 | Fontes reais, amostra e validador dos dados | Antes de MIG-002 | Adiada conforme DEC-035 |
| PEN-017 | Titulares e substitutos nominais | Antes de HOM-001 | Adiada conforme DEC-035 |

## 5. Marcos

| Marco | Semana-alvo | Situação | Data prevista | Data real | Aprovador | Evidência |
|---|---:|---|---|---|---|---|
| M0 — Plano aprovado | 1 | Aprovado | — | 12/10/2026 | Aprovador final | Aprovação e autorização registradas nesta conversa |
| M1 — Arquitetura aprovada | 2 | Em preparação | A definir | — | Diretoria/TI | — |
| M2 — Base segura pronta | 3 | Não iniciado | A definir | — | Líder Técnico/QA | — |
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
| Progresso ponderado | 0,6% | Conforme cronograma após M0 |
| Tarefas concluídas | 3/155 | — |
| Tarefas em execução | 4 | Limitadas à capacidade definida |
| Tarefas bloqueadas | 0 | Escalar críticas após 2 dias úteis |
| Defeitos bloqueadores | 0 | Zero para qualquer marco |
| Riscos altos/críticos sem tratamento | 0 | Zero |
| Marcos aprovados | 1/11 | — |

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

## 8. Procedimento de atualização

Ao final de cada dia com execução:

1. atualizar tarefas ativas, percentuais e impedimentos;
2. registrar evidências das tarefas concluídas;
3. recalcular progresso pelo esforço concluído, não apenas pela quantidade;
4. atualizar o marco impactado;
5. registrar decisão ou mudança de escopo associada;
6. adicionar uma linha ao histórico quando houver mudança relevante.

