# Matriz de rastreabilidade

Esta matriz conecta as decisões da Fase 0 às tarefas e às evidências necessárias. Ela deve ser atualizada quando uma tarefa, requisito ou decisão mudar.

| Requisito/decisão | Resultado esperado | Tarefas principais | Evidência de aceite | Marco |
|---|---|---|---|---|
| DEC-001/002/014 | Vários loteamentos com escopo por usuário | SEG-005/006, CAD-003 | Testes de isolamento e demonstração | M3 |
| DEC-003 | Perfis e múltiplos perfis por usuário | SEG-001/004/008 | Matriz aprovada e testes por perfil | M2 |
| DEC-004 | Corretor restrito à própria carteira | SEG-007/011, CLI-007 | Testes de acesso cruzado negado | M3 |
| DEC-005/020 | Aprovação de operação sensível e segregação | SEG-008, FIN-009/010, CON-015 | Testes sem autoaprovação | M5/M6 |
| DEC-006 | Reserva exclusiva e auditada | RES-002/003/012 | Teste concorrente e trilha | M4 |
| DEC-007 | Prazo configurável de reserva | RES-004/005 | Cenários de configuração/prorrogação | M4 |
| DEC-008/016 | Estados fixos e auxiliares configuráveis | CAD-002/008 | Matriz de transições testada | M3 |
| DEC-009 | Venda após assinatura e entrada | CON-016 | Testes das combinações de pré-condição | M5/M6 |
| DEC-010 | Ciclo de vida contratual | CON-001/011/015 | Jornadas e transições aprovadas | M5 |
| DEC-011 | Pagamentos manuais e estorno auditado | FIN-004–010 | Reconciliação e histórico imutável | M6 |
| DEC-013 | Recorte do MVP e itens futuros | GOV-006/007 | Baseline e registro de mudanças | M0 |
| DEC-015 | Conversão gera contrato rascunho | RES-011/CON-002 | Conversão única e rastreável | M4/M5 |
| DEC-017/018 | Prazo de contratação e pagamento sem assinatura | CON-012–014 | Cenários temporais e encaminhamento | M5/M6 |
| DEC-019 | Retorno do vendido somente via distrato | CON-015 | Roteiro jurídico/financeiro aprovado | M5/M6 |
| DEC-021 | Servidor local e operação sem internet externa | OPS-001–012, QAS-010 | Teste de rede/internet e runbook | M9 |
| DEC-022/031, ESC-001 | Mapa fora do MVP | GOV-006, comunicação de escopo | Termo de homologação não exige mapa | M0/M8 |
| DEC-023/030 | Migração operacional e duas simulações | MIG-001–010 | Relatórios de carga e reconciliação | M7 |
| DEC-024 | Assinatura manual e PDF armazenado | CON-009/010 | PDF gerado, assinado e recuperável | M5 |
| DEC-025 | E-mails e destaque de vencimento | RES-008–010 | Evidência de envio, falha e interface | M4 |
| DEC-026/027/034 | Bloqueio exclusivo do Diretor e justificado | BLQ-001–003 | Permissão, motivo, anexo e auditoria | M4 |
| DEC-028 | Modalidades e participantes do MVP | CON-001–005/015 | Roteiros PF/PJ, múltiplos e distrato | M5 |
| DEC-029 | Templates contratuais versionados | CON-006–010 | Histórico, publicação e PDF por versão | M5 |
| DEC-032 | Porte médio e dispositivos | CAD-009/010, QAS-008 | Teste de capacidade e responsividade | M8 |
| DEC-033 | Titular e substituto por área | HOM-001, GOV-003 | Relação nominal aprovada | M8 |
| DEC-035 | Dados e nomes adiados com gates | MIG-001, HOM-001 | Gates atendidos antes de M7/M8 | M7/M8 |
| Auditoria do MVP | Operações sensíveis rastreáveis | SEG-009 e tarefas de domínio | Consulta com autor, data, motivo e alteração | M8 |
| Backup e recuperação | Banco e documentos recuperáveis | OPS-009/010, QAS-011 | Relatório de restauração | M9 |
| Relatório financeiro básico | Visão filtrada e reconciliada | FIN-011–015 | Massa conhecida e totais aprovados | M6 |

## Controle das evidências

Para cada linha, registrar durante a execução:

| Campo | Preenchimento esperado |
|---|---|
| Versão testada | Identificador imutável do build |
| Ambiente | Teste, homologação ou produção |
| Data | Data e hora da execução |
| Executor | Pessoa que executou |
| Revisor | Segunda pessoa quando exigida |
| Resultado | Aprovado, reprovado ou aprovado com ressalva |
| Evidência | Link para relatório, teste, captura ou log controlado |
| Defeitos | IDs dos problemas encontrados |
| Aceite funcional | Nome e data do responsável da área |

## Regras de consistência

- Requisito sem tarefa associada não pode entrar no marco.
- Tarefa crítica sem evidência não pode ser considerada concluída.
- Decisão substituída deve apontar para a decisão vigente.
- Mudança aprovada deve atualizar backlog, testes, riscos e esta matriz.
- Evidências não devem conter senha, token ou dados pessoais desnecessários.

