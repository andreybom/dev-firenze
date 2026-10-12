# Qualidade, riscos e implantação

## 1. Estratégia de testes

### Camadas

| Camada | Objetivo | Execução mínima |
|---|---|---|
| Unitário | Validar regras isoladas | A cada alteração |
| Integração | Validar banco, transações, migrações e armazenamento | No pipeline e antes de marcos |
| Contrato de API | Evitar incompatibilidade frontend/backend | No pipeline |
| Interface | Validar jornadas essenciais | No pipeline da versão candidata |
| Concorrência | Impedir reserva dupla e inconsistência financeira | Antes de M4 e M6 |
| Segurança | Validar autenticação, autorização, entrada e exposição | Antes de M8 e em mudanças sensíveis |
| Desempenho | Validar 50 usuários simultâneos e consultas principais | Antes de M8 |
| Migração | Validar transformação, rejeições e reconciliação | Duas cargas antes de M7 |
| Backup/restauração | Demonstrar recuperação integral | Antes de M9 |
| Homologação | Confirmar aderência ao negócio | Antes de M8 |
| Smoke de produção | Validar implantação sem alterar dados indevidamente | Imediatamente após o corte |

## 2. Cenários críticos obrigatórios

### Segurança e acesso

- Administrador acessa todos os loteamentos.
- Diretor acessa somente loteamentos atribuídos, salvo escopo global explícito.
- Corretor não acessa dados de outro corretor nem por URL/API direta.
- Usuário sem perfil não executa operação protegida.
- Usuário desativado perde acesso e sessões conforme política.
- Solicitante não aprova a própria operação sensível.

### Reserva e bloqueio

- Duas solicitações simultâneas não reservam o mesmo lote.
- Reserva expirada libera o lote uma única vez.
- Prorrogação exige permissão e deixa auditoria.
- E-mail é enfileirado para corretor e cliente na antecedência configurada.
- Falta de internet não impede operação interna e o e-mail pode ser reprocessado.
- Apenas Diretor aplica e retira bloqueio.
- Categoria e justificativa são obrigatórias e não há desbloqueio automático.

### Contratos

- Conversão de reserva cria um único rascunho.
- Contrato preserva o modelo e a versão usados.
- Comercial não altera cláusula publicada pelo Jurídico.
- PDF gerado possui dados consistentes com o contrato.
- PDF assinado fica armazenado, recuperável e auditado.
- Lote somente fica vendido após assinatura e confirmação da entrada.
- Sem pagamento no prazo, contrato expira e lote é liberado.
- Com entrada e sem assinatura, lote permanece `Em contratação`.
- Distrato exige acerto, documentos, aprovação do Diretor e auditoria.

### Financeiro

- Soma das parcelas corresponde ao valor contratado conforme regra de arredondamento.
- Pagamento parcial reduz corretamente o saldo.
- Complemento quita apenas o saldo restante.
- Pagamento em várias parcelas não perde nem duplica valor.
- Estorno não apaga o lançamento original.
- Diretor diferente do solicitante aprova o estorno.
- Relatório e saldo contratual refletem todos os eventos.
- Reprocessamento de uma requisição não duplica pagamento.

### Continuidade

- Sistema continua funcionando sem internet externa na rede interna.
- Backup contém banco e documentos da mesma referência operacional.
- Restauração reconstrói aplicação, dados e documentos.
- Retorno de versão preserva consistência dos dados.

## 3. Metas não funcionais iniciais

As metas deverão ser confirmadas em M1 e testadas antes de M8.

| Requisito | Meta inicial |
|---|---|
| Capacidade | 200 usuários cadastrados e 50 simultâneos |
| Disponibilidade interna | operação enquanto servidor, banco e rede local estiverem disponíveis |
| Tempo de resposta | 95% das consultas comuns em até 2 segundos na rede interna |
| Operação crítica | gravação comum em até 3 segundos, salvo geração de documento |
| Geração de PDF | até 10 segundos para modelo típico |
| Integridade | nenhuma duplicidade de reserva ou lançamento por repetição/concorrência |
| Rastreabilidade | 100% das operações sensíveis auditadas |
| Backup | execução automática e monitorada, com retenção aprovada |
| Restauração | objetivo e tempo máximo definidos após inventário da infraestrutura |
| Compatibilidade | navegadores corporativos aprovados; computador/tablet completos, celular básico |

## 4. Registro inicial de riscos

| ID | Risco | Prob. | Impacto | Tratamento | Proprietário funcional | Gate |
|---|---|---|---|---|---|---|
| RSK-001 | Regras financeiras incompletas gerarem saldos incorretos | Alta | Crítico | FIN-002, exemplos aprovados e testes de propriedades | Financeiro | M6 |
| RSK-002 | Dados chegarem tarde ou com baixa qualidade | Alta | Alto | Gate PEN-013, perfil de dados e duas cargas | Dados/Migração | M7 |
| RSK-003 | Falta de responsáveis nominais atrasar aceite | Média | Alto | Resolver PEN-017 antes de HOM-001 | Diretoria | M8 |
| RSK-004 | Versões técnicas atuais não serem adequadas ao servidor | Média | Alto | ARC-002 e prova de build/deploy | Líder Técnico | M1 |
| RSK-005 | Configurações atuais exporem credenciais | Alta | Crítico | ARC-010, rotação de segredos e revisão | TI/Segurança | M2 |
| RSK-006 | Atualização automática de esquema corromper produção | Média | Crítico | ARC-008/009 e restauração testada | Backend/DBA | M2/M9 |
| RSK-007 | Reserva dupla sob concorrência | Média | Crítico | Restrição transacional e RES-003 | Backend/QA | M4 |
| RSK-008 | Alteração ou exclusão indevida de histórico financeiro | Média | Crítico | FIN-008/010 e auditoria | Financeiro/Backend | M6 |
| RSK-009 | Modelos contratuais divergirem do aprovado | Média | Alto | Versionamento, publicação Jurídica e hash do PDF | Jurídico | M5 |
| RSK-010 | Falha de internet impedir avisos por e-mail | Alta | Médio | Fila local, tentativas e monitoramento | TI/Comercial | M4 |
| RSK-011 | Documentos e banco não serem restaurados no mesmo ponto | Média | Crítico | Backup coordenado e exercício de restauração | DBA/Infra | M9 |
| RSK-012 | Escopo crescer durante desenvolvimento | Alta | Alto | Baseline e controle formal de mudanças | Produto/Diretoria | Todos |
| RSK-013 | Interface de tablet/celular atrasar fluxos principais | Média | Médio | Priorizar computador/tablet e limitar celular | Produto/Frontend | M8 |
| RSK-014 | Falta de mapa ser interpretada como entrega incompleta | Média | Médio | Comunicar ESC-001 e demonstrar escopo sem mapa | Produto | M0/M8 |
| RSK-015 | Servidor local ser ponto único de falha | Média | Alto | Capacidade, backup, peças/VM e procedimento de recuperação | TI | M9 |
| RSK-016 | Atualizações de dependências reintroduzirem vulnerabilidades altas nas ferramentas frontend | Baixa | Alto | `npm audit` sem vulnerabilidades altas em 12/10/2026; repetir auditoria no CI em ARC-012 | Frontend/Segurança | M2 |

## 5. Critérios de entrada em homologação

- funcionalidades previstas para o MVP implementadas;
- pipeline aprovado;
- migrações de banco testadas;
- defeitos bloqueadores zerados;
- segurança crítica revisada;
- segundo ensaio de migração concluído ou massa de homologação formalmente aprovada;
- ambiente estável e isolado;
- titulares e substitutos identificados;
- roteiros e resultados esperados distribuídos;
- versão candidata congelada e identificada.

## 6. Critérios de saída da homologação

- todos os cenários críticos executados;
- aprovações de Comercial, Financeiro, Jurídico, Negócio e TI registradas;
- nenhum defeito bloqueador;
- nenhum defeito alto sem aceite excepcional;
- defeitos médios/baixos classificados e planejados;
- riscos residuais identificados;
- relatório final de qualidade emitido;
- termo de homologação aprovado.

## 7. Plano de implantação

### Preparação — até sete dias antes

1. Confirmar data, janela, participantes e contatos.
2. Congelar mudanças não relacionadas ao corte.
3. Publicar versão candidata imutável.
4. Confirmar capacidade de servidor, banco e armazenamento.
5. Verificar certificados, contas de serviço e segredos.
6. Executar backup e teste de restauração.
7. Confirmar plano de carga, reconciliação e retorno.
8. Comunicar indisponibilidade e orientações aos usuários.
9. Confirmar equipe de suporte e escala da estabilização.

### Corte

1. Interromper alterações no sistema/fonte anterior.
2. Registrar horário e responsáveis pelo congelamento.
3. Executar backup pré-corte.
4. Publicar banco, backend e frontend na ordem prevista.
5. Executar carga final quando aplicável.
6. Reconciliar quantidades, contratos, parcelas e saldos.
7. Executar smoke test técnico.
8. Executar smoke test funcional com cada área crítica.
9. Registrar decisão Go/No-Go.
10. Liberar acesso e comunicar usuários somente após Go.

### Validação pós-corte

- autenticação e perfis;
- acesso por loteamento;
- cadastros e pesquisas;
- reserva controlada e posterior cancelamento, se permitido;
- geração de contrato de teste autorizada;
- consulta financeira e relatório;
- upload e recuperação de documento;
- auditoria e logs;
- backup agendado;
- monitoramento e alertas.

## 8. Plano de retorno

O retorno será acionado se ocorrer:

- corrupção ou reconciliação financeira incompatível;
- acesso indevido crítico;
- indisponibilidade sem correção dentro da janela;
- falha na migração com impacto material;
- impossibilidade de recuperar documentos essenciais;
- reprovação do Aprovador no Go/No-Go.

Procedimento mínimo:

1. suspender acessos e gravações;
2. preservar logs, artefatos e evidências;
3. restaurar versão anterior da aplicação;
4. restaurar banco/documentos a partir do ponto aprovado;
5. validar consistência técnica e funcional;
6. comunicar retorno e impacto;
7. abrir análise de causa antes de nova tentativa.

## 9. Estabilização assistida

Durante a primeira semana:

- triagem diária de incidentes;
- monitoramento reforçado de erros, banco, espaço e backups;
- prioridade imediata para segurança, indisponibilidade e financeiro;
- relatório diário ao responsável do projeto;
- mudanças somente pelo fluxo emergencial;
- comparação diária dos indicadores financeiros essenciais.

A estabilização será encerrada quando:

- não houver incidente bloqueador aberto;
- não houver incidente alto sem plano aceito;
- backups estiverem executando e monitorados;
- usuários-chave confirmarem operação normal;
- documentação e runbooks tiverem sido entregues;
- responsabilidade tiver sido transferida à operação.

