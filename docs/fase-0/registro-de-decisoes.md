# Registro de decisões da Fase 0

Este arquivo consolida apenas decisões efetivamente aprovadas. As alternativas e discussões permanecem nos questionários de origem.

## Como registrar

- Use um identificador sequencial: `DEC-001`, `DEC-002` etc.
- Uma decisão deve indicar sua origem e quem a aprovou.
- Se uma decisão mudar, não apague o histórico: marque-a como substituída e crie outra decisão.

## Decisões

| ID | Data | Área | Decisão | Motivo | Aprovada por | Origem | Status |
|---|---|---|---|---|---|---|---|
| DEC-001 | 20/09/2026 | Organização | Atender vários loteamentos de uma mesma empresa. | Escopo selecionado no questionário. | Andrey Bom | Q01, seção 1 | Aprovada |
| DEC-002 | 20/09/2026 | Acesso | Conceder acesso individual por loteamento; administrador ou diretor gerencia as atribuições. | Restringir dados conforme responsabilidade do usuário. | Andrey Bom | Q01, seção 1 | Aprovada |
| DEC-003 | 20/09/2026 | Acesso | Adotar os perfis Administrador, Diretoria/Gestor, Comercial, Financeiro, Jurídico, Corretor e Consulta/Auditoria; permitir múltiplos perfis por usuário. | Cobrir as áreas operacionais informadas. | Andrey Bom | Q01, seção 2 | Aprovada |
| DEC-004 | 20/09/2026 | Acesso | Restringir o corretor aos próprios clientes, reservas e vendas. | Preservar separação da carteira comercial. | Andrey Bom | Q01, seção 2 | Aprovada |
| DEC-005 | 20/09/2026 | Financeiro | Exigir aprovação de diretor para cancelamento financeiro. | Aplicar controle de segunda pessoa em operação sensível. | Andrey Bom | Q01, seção 2 | Aprovada |
| DEC-006 | 20/09/2026 | Reserva | Impedir duas reservas ativas no mesmo lote e auditar criação, prorrogação, conversão e cancelamento. | Evitar conflito comercial e preservar rastreabilidade. | Andrey Bom | Q01, seção 3 | Aprovada |
| DEC-007 | 20/09/2026 | Reserva | Usar prazo configurável por diretor, normalmente entre 3 e 15 dias, permitindo ajustes a partir de um padrão inicial. | Adaptar a reserva à condição comercial. | Andrey Bom | Q01, seção 3 | Aprovada |
| DEC-008 | 20/09/2026 | Lote | Usar o fluxo-base Disponível, Reservado, Em contratação e Vendido, além de Bloqueado, Permutado, Uso interno e Indisponível. | Representar as situações operacionais indicadas. | Andrey Bom | Q01, seção 4 | Aprovada |
| DEC-009 | 20/09/2026 | Venda | Considerar o lote vendido somente após assinatura do contrato e confirmação da entrada. | Evitar venda juridicamente incompleta e conflito de disponibilidade. | Andrey Bom | Conciliação C4 | Aprovada |
| DEC-010 | 20/09/2026 | Contrato | Usar os estados Rascunho, Aguardando assinatura, Aguardando entrada, Ativo, Quitado, Cancelado, Rescindido/Distratado e Renegociado. | Representar o ciclo de vida contratual. | Andrey Bom | Q01, seção 5 | Aprovada |
| DEC-011 | 20/09/2026 | Pagamento | Incluir no MVP registro manual, pagamentos parciais, complementos, comprovantes, alocação em parcelas e estorno auditado; deixar boleto, Pix e integrações bancárias para etapa posterior. | Manter integridade financeira com um primeiro escopo controlado. | Andrey Bom | Conciliação/PEN-012 | Aprovada |
| DEC-012 | 20/09/2026 | Mapa | Exibir situações por cor; pesquisar, destacar e filtrar lotes; iniciar reserva; mostrar medidas, área e confrontações; usar referência geográfica real. | Interações selecionadas no questionário. | Andrey Bom | Q01, seção 9 | Substituída pela DEC-031 quanto ao MVP |
| DEC-013 | 20/09/2026 | MVP | Adotar o recorte preliminar do MVP e manter automação de índices, DataJud, renegociação avançada, notificações multicanal e relatórios sofisticados para etapa posterior. | Controlar o escopo da primeira entrega. | Andrey Bom | Q01, seção 11 | Aprovada |
| DEC-014 | 20/09/2026 | Acesso | Administradores terão acesso global; diretores terão todos os módulos apenas nos loteamentos atribuídos, salvo escopo global explícito. | Aplicar menor privilégio sem impedir administração central. | Andrey Bom | Conciliação C1 | Aprovada |
| DEC-015 | 20/09/2026 | Reserva | Converter uma reserva criará contrato em rascunho e colocará o lote em Em contratação. | Eliminar ambiguidade entre reserva e formalização da venda. | Andrey Bom | Conciliação C2 | Aprovada |
| DEC-016 | 20/09/2026 | Lote | Manter estados e transições estruturais fixos; permitir somente motivos, etiquetas e categorias auxiliares configuráveis. | Preservar regras, filtros, relatórios e automações. | Andrey Bom | Conciliação C3 | Aprovada |
| DEC-017 | 20/09/2026 | Contratação | Permitir que diretor configure o prazo por loteamento; sem pagamento, expirar contrato e liberar lote automaticamente; com pagamento, exigir tratamento manual. | Evitar bloqueio indefinido e proteger operações com movimento financeiro. | Andrey Bom | Conciliação C4 | Aprovada |
| DEC-018 | 20/09/2026 | Contratação | Com entrada paga e assinatura pendente, manter lote Em contratação e encaminhar para análise do Financeiro/Jurídico. | Evitar liberação indevida ou venda incompleta. | Andrey Bom | Conciliação C4 | Aprovada |
| DEC-019 | 20/09/2026 | Distrato | Liberar lote vendido somente após cancelamento/distrato, acerto financeiro, documentos, aprovação de diretor e auditoria. | Preservar integridade jurídica e financeira. | Andrey Bom | Conciliação C5 | Aprovada |
| DEC-020 | 20/09/2026 | Controle interno | Comercial prepara negociação; Financeiro valida valores e pagamentos; Jurídico valida contratos; Diretor aprova operações sensíveis; ninguém aprova a própria solicitação. | Segregar funções e reduzir fraude e erro. | Andrey Bom | Conciliação C6 | Aprovada |
| DEC-021 | 20/09/2026 | Implantação | Executar aplicação e PostgreSQL em servidor local pela rede interna; não implementar banco por dispositivo nem sincronização offline no MVP. | Garantir operação sem internet externa com complexidade controlada. | Andrey Bom | Conciliação C7 | Aprovada |
| DEC-022 | 20/09/2026 | Mapa | Priorizar DWG/DXF, GeoJSON ou levantamento oficial; PDF/imagem será apenas fundo visual sem validade geométrica até validação técnica. | Evitar tratar desenho impreciso como geometria legal. | Andrey Bom | Conciliação C8 | Aprovada |
| DEC-023 | 20/09/2026 | Migração | Inventariar fontes, mapear campos, medir inconsistências, reconciliar totais e executar ao menos duas migrações de teste. | Reduzir risco de perda ou divergência de dados. | Andrey Bom | PEN-013 | Aprovada |
| DEC-024 | 20/09/2026 | Assinatura | No MVP, usar assinatura manual com armazenamento do PDF assinado; integração eletrônica fica para etapa posterior. | Evitar dependência prematura de fornecedor. | Andrey Bom | PEN-010 | Aprovada |
| DEC-025 | 20/09/2026 | Reserva | Avisar corretor e cliente por e-mail antes do vencimento, com antecedência configurável por diretor, e destacar graficamente no sistema lotes com reserva próxima do vencimento ou vencida. | Reduzir expirações não percebidas e tornar o risco visível na operação. | Andrey Bom | PEN-003 | Aprovada |
| DEC-026 | 20/09/2026 | Bloqueio | Autorizar o perfil Diretor a retirar bloqueios somente com motivo obrigatório e registro de auditoria. | Controlar a liberação de lotes e preservar rastreabilidade. | Andrey Bom | PEN-004 | Aprovada |
| DEC-027 | 21/09/2026 | Bloqueio | Somente o perfil Diretor poderá aplicar e retirar bloqueios administrativos. O bloqueio não terá prazo de validade nem desbloqueio automático; o Diretor poderá retirá-lo a qualquer tempo, observadas as exigências de motivo e auditoria da DEC-026. | Definir a autoridade e evitar liberação automática de lotes bloqueados. | Andrey Bom | PEN-004 | Aprovada |
| DEC-028 | 10/10/2026 | Contrato | Incluir no MVP vendas à vista e parceladas, sinal/entrada inclusive parcelada, um ou mais compradores pessoa física ou jurídica, cônjuge/companheiro e distrato. Preparar o modelo de dados para fiador, cessão e permuta sem implementar seus fluxos completos; manter renegociação avançada para versão futura. | Evitar redesenho estrutural sem ampliar excessivamente os fluxos do MVP. | Andrey Bom | PEN-009, opção B | Aprovada |
| DEC-029 | 10/10/2026 | Contrato | Adotar modelos contratuais versionados por finalidade: modelos-base para pessoa física e jurídica, com cláusulas ou anexos específicos por loteamento e seleção conforme modalidade e comprador. O Jurídico publica versões; o Comercial gera documentos sem alterar cláusulas aprovadas; contratos preservam a versão usada. | Equilibrar flexibilidade comercial, governança jurídica e rastreabilidade. | Andrey Bom | PEN-010, opção B | Aprovada |
| DEC-030 | 10/10/2026 | Migração | Adotar migração operacional: migrar cadastros, clientes e corretores ativos, contratos ativos, parcelas abertas, pagamentos necessários à composição dos saldos e documentos essenciais; manter dados encerrados em arquivo histórico consultável. Executar inventário, duas simulações e reconciliação antes da carga definitiva. | Concentrar o MVP nos dados operacionais sem perder acesso ao legado. | Andrey Bom | PEN-013, opção B | Aprovada |
| DEC-031 | 10/10/2026 | Mapa | Adiar o material cartográfico, o mapa interativo e a representação geográfica real para versão futura. O MVP manterá os cadastros de loteamentos, quadras e lotes sem assumir precisão ou representação cartográfica. | Não existe atualmente informação ou material cartográfico que permita validar a solução. | Andrey Bom | PEN-014 | Aprovada |
| DEC-032 | 10/10/2026 | Capacidade e dispositivos | Dimensionar inicialmente para até 200 usuários cadastrados, 50 simultâneos e 20 loteamentos em três anos. Suportar a operação completa em computador e tablet; no celular, limitar o compromisso do MVP a consultas básicas. | Adotar a faixa intermediária e priorizar as telas adequadas aos fluxos completos. | Andrey Bom | PEN-015, opção B | Aprovada |
| DEC-033 | 10/10/2026 | Governança | Designar um titular e um substituto para cada área decisória, permitindo que uma pessoa acumule áreas, mas exigindo responsável identificável por decisão. Andrey Bom permanece como aprovador final. | Evitar decisões sem proprietário e manter continuidade durante ausências. | Andrey Bom | PEN-017, opção B | Aprovada |
| DEC-034 | 10/10/2026 | Bloqueio | Exigir categoria padronizada e justificativa obrigatória para aplicar e retirar bloqueios. Categorias iniciais: restrição judicial/jurídica; irregularidade documental/registral; impedimento técnico/urbanístico/ambiental; divergência financeira/contratual; revisão interna da Diretoria; e Outro. Permitir anexos e exigi-los quando houver documento formal que fundamente a restrição. | Padronizar relatórios sem perder o contexto necessário à auditoria. | Andrey Bom | PEN-018, opção B | Aprovada |
| DEC-035 | 10/10/2026 | Planejamento | Adiar a entrega dos arquivos de migração e a identificação nominal de titulares/substitutos, sem bloquear a elaboração do plano nem as etapas iniciais. Os dados deverão estar disponíveis antes dos ensaios de migração e os responsáveis nominais antes da homologação. | Permitir o avanço do planejamento preservando marcos obrigatórios antes da entrada em produção. | Andrey Bom | PEN-013/PEN-017 | Aprovada |
| DEC-036 | 12/10/2026 | Banco de dados | Adotar PostgreSQL Server 18.1 como versão-alvo do MVP; referências observadas às versões 17 e 15 ficam superadas e não são alvo de implantação. | Padronizar a versão do banco solicitada para a implantação. | Aprovador final | Orientação nesta conversa | Aprovada |

Status sugeridos: `Proposta`, `Aprovada`, `Substituída` ou `Cancelada`.

## Pendências

| ID | Questão pendente | Impacto | Responsável | Data-alvo | Status |
|---|---|---|---|---|---|
| PEN-001 | Definir se administradores e diretores acessam automaticamente todos os loteamentos ou somente os atribuídos. | Alto — segurança e autorização. | A definir | 20/09/2026 | Resolvida — DEC-014 |
| PEN-002 | Definir se converter uma reserva cria contrato em rascunho ou apenas inicia negociação. | Alto — fluxo de venda e concorrência. | Negócio/comercial | 20/09/2026 | Resolvida — DEC-015 |
| PEN-003 | Definir canal e antecedência dos avisos de vencimento de reserva. | Médio — comunicação e integrações. | Negócio/comercial | 20/09/2026 | Resolvida — DEC-025 |
| PEN-004 | Definir quem pode aplicar e retirar o bloqueio administrativo e se ele terá validade. | Alto — disponibilidade dos lotes. | Negócio/comercial | 21/09/2026 | Resolvida — DEC-026/027 |
| PEN-005 | Decidir entre estados operacionais fixos e estados totalmente configuráveis pelo usuário. | Alto — modelo de dados e automações. | Negócio e TI | 20/09/2026 | Resolvida — DEC-016 |
| PEN-006 | Definir prazo e tratamento quando assinatura ou entrada não ocorrer, inclusive como e por quem o lote será liberado. | Crítico — estoque e contrato. | Comercial/financeiro/jurídico | 20/09/2026 | Resolvida — DEC-017 |
| PEN-007 | Definir o tratamento quando a entrada estiver paga, mas a assinatura não tiver ocorrido. | Crítico — venda e risco jurídico. | Financeiro/jurídico | 20/09/2026 | Resolvida — DEC-009/018 |
| PEN-008 | Formalizar o processo para devolver lote vendido à disponibilidade, incluindo distrato/cancelamento, acerto financeiro e auditoria. | Crítico — integridade jurídica e financeira. | Diretoria/jurídico/financeiro | 20/09/2026 | Resolvida — DEC-019 |
| PEN-009 | Confirmar modalidades do MVP: à vista, parcelada, sinal, múltiplos compradores, PJ, fiador, distrato, cessão, renegociação e permuta. | Alto — contrato e financeiro. | Comercial/jurídico | 10/10/2026 | Resolvida — DEC-028 |
| PEN-010 | Definir quantos modelos de contrato existirão e quando cada um será usado; assinatura do MVP já definida na DEC-024. | Alto — documentos e integrações. | Jurídico | 10/10/2026 | Resolvida — DEC-024/029 |
| PEN-011 | Rever quem elabora, revisa, aprova e cancela contratos e se haverá segregação de funções. | Crítico — controle interno. | Diretoria/jurídico/financeiro | 20/09/2026 | Resolvida — DEC-020 |
| PEN-012 | Detalhar pagamentos do MVP: parcial, complemento, múltiplas parcelas, estorno, anexos, boleto, Pix, retorno bancário e integrações. | Crítico — escopo financeiro. | Financeiro | 20/09/2026 | Resolvida — DEC-011 |
| PEN-013 | Fornecer dados existentes para inventariar volumes, qualidade e reconciliação; estratégia operacional definida nas DEC-023/030. | Crítico — modelo e planejamento da migração. | Dados/migração | Antes do primeiro ensaio de migração | Adiada — não bloqueia etapas iniciais; gate obrigatório para migração |
| PEN-014 | Definir o tratamento do material cartográfico e do mapa no MVP. | Crítico — viabilidade do mapa geográfico. | Negócio/TI | 10/10/2026 | Resolvida para o MVP — adiada pela DEC-031 |
| PEN-015 | Informar usuários cadastrados/simultâneos, loteamentos previstos e dispositivos suportados. | Médio — dimensionamento e interface. | Infraestrutura/TI | 10/10/2026 | Resolvida — DEC-032 |
| PEN-016 | Esclarecer se a aplicação também será local e o que deve funcionar sem internet: rede local ou modo offline com sincronização. | Crítico — arquitetura e operação. | Diretoria/TI | 20/09/2026 | Resolvida — DEC-021 |
| PEN-017 | Identificar titulares e substitutos nominais das áreas; modelo de responsabilidade definido na DEC-033 e aprovador final identificado como Andrey Bom. | Alto — governança da Fase 0. | Diretoria | Antes da homologação | Adiada — acompanhamento por função até a nomeação |
| PEN-018 | Definir os motivos aceitos para aplicar bloqueio administrativo e se a aplicação exigirá justificativa obrigatória. | Médio — padronização e rastreabilidade dos bloqueios. | Diretoria | 10/10/2026 | Resolvida — DEC-034 |

## Premissas temporárias

Premissas permitem continuar a análise sem tratá-las como decisões finais.

As antigas premissas `PRE-001` a `PRE-004` foram promovidas às decisões aprovadas `DEC-016`, `DEC-021` e `DEC-019`.

| ID | Premissa | Risco se estiver incorreta | Validar com | Prazo |
|---|---|---|---|---|
| PRE-005 | A aceitação do MVP é de alto nível; itens marcados como pendentes ainda podem alterar seu conteúdo e estimativa. | O MVP pode parecer fechado antes de suas regras essenciais. | Aprovação final | Ao concluir a Fase 0 |

## Alterações de escopo

| ID | Data | Alteração | Justificativa | Impacto estimado | Aprovada por |
|---|---|---|---|---|---|
| ESC-001 | 10/10/2026 | Retirar do MVP o mapa interativo, o material cartográfico e a representação geográfica real, mantendo-os para versão futura. | Não há atualmente informação ou material cartográfico disponível para validação. | Reduz o escopo e elimina a dependência cartográfica do MVP; cadastros de loteamentos, quadras e lotes permanecem. | Andrey Bom |

## Aprovação complementar

As decisões `DEC-028` a `DEC-034` e a alteração `ESC-001` foram aprovadas por **Andrey Bom em 10/10/2026 às 23:02**. A `DEC-035` foi aprovada pela orientação posterior de 10/10/2026; o horário não foi informado.

Em **12/10/2026**, o aprovador final confirmou a aprovação do plano de implantação e autorizou explicitamente o início da implementação. O registro operacional está no marco M0 do [controle de execução](../plano-implantacao/06-controle-de-execucao.md).

Em **12/10/2026**, o aprovador final definiu PostgreSQL Server 18.1 como a versão-alvo (DEC-036), substituindo as referências anteriores a 17 e 15.

## Critério para encerramento da Fase 0

A Fase 0 estará concluída quando:

- [x] o escopo do MVP estiver aprovado;
- [ ] as regras críticas do negócio e do financeiro estiverem documentadas;
- [ ] a origem e a qualidade dos dados tiverem sido avaliadas;
- [ ] a arquitetura técnica e as integrações estiverem decididas;
- [ ] a matriz de permissões e os requisitos de auditoria estiverem aprovados;
- [ ] riscos e pendências possuírem responsáveis e tratamento;
- [ ] houver autorização explícita para iniciar a implementação.
