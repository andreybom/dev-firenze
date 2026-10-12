# Plano de implementação e implantação — Firenze

**Versão:** 1.0  
**Data-base:** 10/10/2026  
**Situação:** Aprovado; execução iniciada em 12/10/2026  
**Aprovador do projeto:** Andrey Bom  

## 1. Finalidade

Este plano organiza a construção, validação, implantação e estabilização do MVP do sistema de gestão de loteamentos Firenze. Ele transforma as decisões da Fase 0 em tarefas rastreáveis, mas não autoriza sozinho o início da codificação nem a entrada em produção.

O plano deve ser usado para:

- acompanhar escopo, prazo, esforço, dependências e responsáveis;
- estabelecer critérios objetivos para concluir cada etapa;
- impedir que funcionalidades incompletas avancem silenciosamente;
- registrar riscos, decisões, defeitos e mudanças de escopo;
- preparar homologação, migração, implantação e suporte inicial.

## 2. Documentos de acompanhamento

1. [01-cronograma-e-marcos.md](01-cronograma-e-marcos.md) — sequência, duração, dependências e marcos.
2. [02-backlog-detalhado.md](02-backlog-detalhado.md) — tarefas executáveis, esforço, saída e aceite.
3. [03-governanca-e-acompanhamento.md](03-governanca-e-acompanhamento.md) — papéis, reuniões, indicadores e controle de mudanças.
4. [04-qualidade-riscos-e-implantacao.md](04-qualidade-riscos-e-implantacao.md) — testes, segurança, riscos, corte, retorno e estabilização.
5. [05-matriz-de-rastreabilidade.md](05-matriz-de-rastreabilidade.md) — ligação entre requisitos, decisões, tarefas e evidências.
6. [06-controle-de-execucao.md](06-controle-de-execucao.md) — painel vivo de progresso, tarefas ativas, bloqueios e próximos marcos.

## 3. Escopo da primeira implantação

### Incluído

- autenticação, usuários, perfis e acesso por loteamento;
- cadastros de loteamento, quadra, lote, cliente e corretor;
- reservas, expiração, prorrogação, conversão e avisos por e-mail;
- bloqueios administrativos controlados pelo Diretor;
- contratos para pessoa física e jurídica, modelos versionados e PDF;
- venda à vista ou parcelada, sinal/entrada inclusive parcelada, múltiplos compradores, cônjuge e distrato;
- parcelas, pagamentos manuais, pagamentos parciais, complementos, múltiplas parcelas e estornos;
- comprovantes e documentos essenciais;
- relatório financeiro básico;
- histórico imutável e auditoria das operações sensíveis;
- migração operacional dos dados necessários;
- aplicação e PostgreSQL no servidor local, acessíveis pela rede interna;
- backup, restauração, monitoramento, homologação, implantação e estabilização;
- operação completa em computador e tablet e consultas básicas no celular.

### Fora desta implantação

- mapa interativo, material cartográfico e representação geográfica real;
- edição gráfica de lotes;
- boleto, Pix, retorno bancário e integrações bancárias/contábeis;
- assinatura eletrônica integrada;
- automação de índices;
- DataJud;
- renegociação avançada;
- fluxo completo de fiador, cessão e permuta;
- notificações multicanal e relatórios analíticos avançados;
- banco local por dispositivo ou sincronização offline.

## 4. Base técnica provisória

O repositório contém atualmente:

- frontend Angular 22;
- backend Java 25 com Spring Boot 4.1;
- PostgreSQL Server major 18 como versão-alvo, permitindo atualização corretiva `18.x` aprovada (DEC-040, que substitui a DEC-036);
- endpoint inicial de verificação de saúde.

Essa composição será confirmada no marco arquitetural. Antes de qualquer ambiente compartilhado será obrigatório:

- validar compatibilidade, suporte e política de atualização das versões;
- remover credenciais do código e adotar configuração segura por ambiente;
- substituir `hibernate.ddl-auto=update` por migrações versionadas;
- definir identificadores reais de pacote, aplicação e artefatos;
- definir armazenamento de documentos, envio de e-mail, logs e backups;
- aprovar arquitetura, segurança e convenções de desenvolvimento.

## 5. Premissas de planejamento

- Uma equipe-base terá Líder Técnico/Backend, Desenvolvedor Frontend, QA, DevOps/Infra e participação parcial de Produto, Comercial, Financeiro e Jurídico.
- Esforço é expresso em **dias-pessoa (dp)**; duração de calendário depende da disponibilidade real da equipe.
- O cronograma de referência possui 20 semanas, com atividades paralelas controladas por dependências.
- O backlog inicial contém 155 tarefas e 344 dias-pessoa. Com reserva de 15%, a referência é 396 dias-pessoa.
- Para cumprir 20 semanas, será necessária capacidade média próxima de 4 profissionais equivalentes em tempo integral, distribuída entre as funções do projeto.
- Mudanças nas regras financeiras, contratos ou perfis depois da linha de base serão avaliadas formalmente.
- Os dados reais poderão ser entregues posteriormente, mas antes do primeiro ensaio de migração.
- Titulares e substitutos nominais poderão ser definidos posteriormente, mas antes da homologação.
- O mapa geográfico não é requisito de aceite do MVP.

## 6. Fases resumidas

| Fase | Período de referência | Resultado principal |
|---|---|---|
| 0. Aprovação e mobilização | Semana 0–1 | Plano, responsáveis funcionais e ambientes autorizados |
| 1. Arquitetura e fundação | Semanas 1–3 | Base técnica segura, CI, migrações e convenções |
| 2. Segurança e auditoria | Semanas 3–5 | Autenticação, autorização, escopo e trilha auditável |
| 3. Cadastros centrais | Semanas 4–7 | Loteamentos, quadras, lotes, clientes e corretores |
| 4. Reservas e bloqueios | Semanas 6–9 | Fluxos comerciais, expiração, alertas e bloqueios |
| 5. Contratos e documentos | Semanas 8–12 | Modelos, geração, assinatura manual e ciclo contratual |
| 6. Financeiro | Semanas 10–14 | Parcelas, pagamentos, estornos e relatório básico |
| 7. Migração e preparação operacional | Semanas 13–16 | Cargas de ensaio, reconciliação, backup e operação |
| 8. Integração, segurança e homologação | Semanas 15–18 | Testes integrados, correções e aceite dos usuários |
| 9. Implantação e estabilização | Semanas 19–20 | Produção, validação pós-corte e acompanhamento assistido |

### Sensibilidade à capacidade

| Capacidade média disponível | Duração aproximada para 396 dp |
|---:|---:|
| 4 profissionais equivalentes | 20 semanas |
| 3 profissionais equivalentes | 27 semanas |
| 2,5 profissionais equivalentes | 32 semanas |
| 2 profissionais equivalentes | 40 semanas |

Esses valores são referências de capacidade, não datas prometidas. Dependências entre tarefas podem impedir o uso integral do paralelismo.

## 7. Marcos obrigatórios

| Marco | Condição para aprovação |
|---|---|
| M0 — Plano aprovado | Escopo, cronograma, governança e autorização de início registrados |
| M1 — Arquitetura aprovada | Tecnologias, ambientes, banco, documentos, e-mail e segurança definidos |
| M2 — Base segura pronta | CI, migrações, segredos, logs, testes básicos e ambientes funcionando |
| M3 — Cadastros aceitos | Dados centrais e permissões validados em homologação técnica |
| M4 — Comercial aceito | Reservas, bloqueios e concorrência validados |
| M5 — Contratos aceitos | Jurídico aprova modelos, versões, PDF e ciclo contratual |
| M6 — Financeiro aceito | Financeiro reconcilia parcelas, pagamentos, estornos e relatório |
| M7 — Migração aprovada | Duas cargas de ensaio reconciliadas e aprovadas |
| M8 — Homologação concluída | Cenários críticos aprovados e defeitos bloqueadores zerados |
| M9 — Go/No-Go | Backup, restauração, monitoramento, treinamento e retorno testados |
| M10 — Implantação estabilizada | Produção validada e período de acompanhamento encerrado |

## 8. Estados de acompanhamento

Cada tarefa deverá usar apenas um dos estados:

- `Não iniciada`;
- `Pronta para iniciar`;
- `Em execução`;
- `Em validação`;
- `Bloqueada`;
- `Concluída`;
- `Cancelada`.

Uma tarefa somente será `Concluída` quando sua evidência e seu critério de aceite estiverem registrados.

## 9. Pendências adiadas e respectivos gates

| Pendência | Pode esperar até | Consequência se não for resolvida |
|---|---|---|
| PEN-013 — fontes e amostra dos dados | Antes de MIG-002, primeiro ensaio de migração | Migração e entrada em produção ficam bloqueadas |
| PEN-017 — nomes dos titulares e substitutos | Antes de HOM-001, início da homologação | Homologação e aceite formal ficam bloqueados |

## 10. Aprovação e início da execução

O plano, o escopo do MVP e os itens futuros foram aprovados, e a implementação foi explicitamente autorizada em 12/10/2026. A aprovação foi registrada no [controle de execução](06-controle-de-execucao.md) e no [registro de decisões](../fase-0/registro-de-decisoes.md).

O início da execução não elimina os gates previstos: a arquitetura deve ser aprovada antes da fundação técnica; as regras financeiras e a matriz de permissões devem ser formalizadas antes de suas implementações; os dados de migração são necessários antes do primeiro ensaio; e os titulares/substitutos nominais antes da homologação.

## 11. Condição para início da execução

Antes de iniciar tarefas de desenvolvimento, devem existir:

- aprovação deste plano;
- autorização explícita para início da implementação;
- definição provisória das pessoas da equipe técnica;
- acesso aos ambientes e repositório;
- priorização inicial do backlog;
- aceite de que itens fora do MVP seguirão outro ciclo de planejamento.

