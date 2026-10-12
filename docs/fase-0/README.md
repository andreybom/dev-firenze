# Fase 0 — Descoberta e decisões

Esta pasta reúne as perguntas, respostas e decisões que precisam ser fechadas antes do início da implementação do sistema de gestão de loteamento Firenze.

## Objetivos

- delimitar o MVP;
- documentar as regras de negócio;
- formalizar as regras financeiras;
- conhecer os dados e arquivos que serão migrados;
- decidir a arquitetura e a operação do sistema;
- estabelecer critérios objetivos para autorizar o início da codificação.

## Como responder

1. Abra o arquivo do questionário desejado.
2. Preencha cada campo iniciado por **Resposta:**.
3. Quando ainda não houver uma decisão, escreva **Pendente** e indique quem poderá esclarecê-la.
4. Quando concordar com a proposta apresentada, escreva **Seguir recomendação**.
5. Não apague as alternativas: elas ajudam a registrar o contexto da decisão.

Respostas curtas são suficientes nesta primeira passagem. Exemplos:

> **Resposta:** B — vários loteamentos da mesma empresa.

> **Resposta:** Pendente. Validar com o setor financeiro.

## Arquivos

- [01-escopo-e-operacao.md](01-escopo-e-operacao.md): primeira rodada, sobre usuários, lotes, reservas, contratos, pagamentos, dados e implantação.
- [01a-conciliacao-escopo-e-operacao.md](01a-conciliacao-escopo-e-operacao.md): requisitos consolidados, conflitos e lacunas encontrados na primeira rodada.
- [registro-de-decisoes.md](registro-de-decisoes.md): consolidação das decisões aprovadas e das pendências.
- [Plano detalhado de implementação e implantação](../plano-implantacao/README.md): cronograma, backlog, governança, qualidade, riscos e corte de produção.

Novos questionários serão acrescentados após a primeira rodada:

1. regras financeiras;
2. dados, documentos e migração;
3. arquitetura e integrações;
4. segurança, auditoria e operação;
5. critérios de aceite do MVP.

## Situação da Fase 0

- [x] Escopo e operação aprovados; mapa geográfico transferido para versão futura
- [ ] Regras financeiras aprovadas
- [ ] Fontes de dados entregues; estratégia de migração já definida e entrega adiada até o gate de migração
- [ ] Arquitetura técnica aprovada
- [ ] Segurança e matriz de permissões aprovadas
- [x] MVP e itens posteriores separados em nível preliminar
- [ ] Titulares e substitutos nominais das áreas definidos; nomeação adiada até antes da homologação
- [x] Autorização explícita para iniciar a implementação — registrada em 12/10/2026

> A autorização explícita para iniciar a implementação foi registrada em 12/10/2026. Os gates de arquitetura, segurança e regras financeiras continuam válidos antes das respectivas entregas.
