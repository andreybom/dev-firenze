# Governança e acompanhamento

## 1. Papéis

| Papel | Responsabilidades principais | Aprova |
|---|---|---|
| Aprovador final | Prioridade, orçamento, exceções, Go/No-Go e encerramento | Marcos M0, M8, M9 e M10 |
| Produto/Negócio | Escopo, critérios de aceite e ordem do backlog | Requisitos e demonstrações |
| Comercial | Clientes, corretores, reservas e operação de venda | Marco M4 |
| Financeiro | Parcelas, pagamentos, estornos, saldos e relatórios | Marco M6 e reconciliação |
| Jurídico | Modelos, cláusulas, assinatura manual, cancelamento e distrato | Marco M5 |
| Líder Técnico | Arquitetura, padrões, revisões e riscos técnicos | Marco M1/M2 |
| Frontend | Interface, acessibilidade e responsividade | Entregas de interface |
| Backend | API, domínio, banco, segurança e integrações | Entregas de serviço |
| QA | Estratégia, evidências, regressão e recomendação de qualidade | Relatório de qualidade |
| Infra/DevOps/DBA | Ambientes, publicação, logs, backup e restauração | Prontidão operacional |
| Dados/Migração | Inventário, transformação, cargas e reconciliação | Marco M7 |
| Suporte/Operação | Atendimento, monitoramento e runbooks | Encerramento da estabilização |

Até a definição dos nomes da PEN-017, o acompanhamento será feito por função. Antes da homologação, cada papel funcional deverá ter titular e substituto identificados.

## 2. Cadência

| Evento | Frequência | Participantes | Saída obrigatória |
|---|---|---|---|
| Planejamento da iteração | Quinzenal | Produto, equipe técnica e QA | Objetivo, capacidade e tarefas comprometidas |
| Reunião diária | Dias úteis, 15 minutos | Equipe executora | Progresso, próximo passo e impedimentos |
| Refinamento | Semanal | Produto, Líder Técnico, QA e área convidada | Critérios, dependências e estimativas revisados |
| Demonstração | Quinzenal | Equipe e áreas funcionais | Aceites, rejeições e observações registradas |
| Revisão de riscos | Semanal | Gestão, Líder Técnico, QA e Infra | Riscos atualizados, donos e tratamentos |
| Comitê executivo | Quinzenal ou por marco | Aprovador e responsáveis de área | Decisões de escopo, prazo, risco e orçamento |
| Go/No-Go | Antes do corte | Diretoria, TI, QA e áreas | Decisão formal e plano de ação |
| Triagem de estabilização | Diária após Go-Live | Suporte, QA e equipe | Incidentes priorizados e comunicados |

## 3. Campos obrigatórios de cada tarefa

- ID estável;
- título objetivo;
- fase e requisito relacionado;
- responsável;
- estimativa e prazo;
- dependências;
- status;
- critério de aceite;
- evidência de conclusão;
- impedimento, se houver;
- data da última atualização.

## 4. Definição de pronta para iniciar

Uma tarefa poderá mudar para `Pronta para iniciar` quando:

- possuir objetivo e critério de aceite claros;
- suas regras de negócio estiverem disponíveis;
- dependências bloqueadoras estiverem concluídas;
- telas ou contratos de API necessários estiverem definidos;
- dados de teste estiverem identificados;
- responsável e revisor estiverem atribuídos;
- estimativa tiver sido revisada pela equipe.

## 5. Definição de concluída

Uma tarefa será considerada concluída apenas quando:

- implementação ou documento tiver sido revisado;
- testes previstos estiverem aprovados;
- pipeline estiver verde;
- migração de banco for segura e versionada, quando aplicável;
- logs não expuserem dados sensíveis;
- documentação tiver sido atualizada;
- critério de aceite estiver demonstrado;
- evidência estiver anexada;
- responsável funcional tiver aprovado quando necessário.

## 6. Indicadores semanais

| Indicador | Cálculo | Sinal de atenção |
|---|---|---|
| Progresso ponderado | esforço concluído / esforço total da linha de base | desvio maior que 10% do planejado |
| Previsibilidade | esforço concluído / esforço comprometido na iteração | abaixo de 80% por duas iterações |
| Bloqueios | tarefas bloqueadas e idade do bloqueio | bloqueio crítico acima de 2 dias úteis |
| Defeitos abertos | por severidade e fase | qualquer bloqueador ou tendência crescente |
| Retrabalho | esforço de correção / esforço total | acima de 20% |
| Cobertura crítica | regras críticas com testes / regras críticas | abaixo de 100% antes da homologação |
| Aprovação de marcos | aprovados / previstos | marco atrasado sem nova data aprovada |
| Risco | exposição = probabilidade × impacto | risco alto sem proprietário/tratamento |

## 7. Relatório semanal padrão

```text
Período:
Responsável pelo relatório:

Situação geral: Verde | Amarela | Vermelha
Progresso planejado:
Progresso realizado:

Concluído na semana:
-

Planejado para a próxima semana:
-

Marcos:
- Marco / data / situação / evidência

Impedimentos:
- ID / descrição / desde / responsável / ação / prazo

Riscos:
- ID / exposição / tendência / tratamento / proprietário

Defeitos críticos e altos:
- ID / severidade / situação / previsão

Decisões necessárias:
- Questão / opções / recomendação / decisor / prazo

Mudanças de escopo:
- ID / impacto / situação da aprovação
```

## 8. Gestão de impedimentos

1. Registrar o impedimento na tarefa no mesmo dia.
2. Indicar causa, impacto, responsável pela remoção e próxima ação.
3. Escalar após 2 dias úteis em tarefa crítica ou 3 dias nas demais.
4. Replanejar o marco se o caminho crítico for afetado.
5. Não marcar tarefa como concluída com dependência ou defeito bloqueador pendente.

## 9. Controle de mudanças

Toda inclusão, retirada ou alteração relevante deverá conter:

- descrição e justificativa;
- requisito e tarefas afetadas;
- impacto em esforço, prazo, risco, dados, segurança e operação;
- alternativas consideradas;
- recomendação técnica e funcional;
- decisão e aprovador;
- atualização da linha de base após aprovação.

Mudanças emergenciais de produção poderão ser executadas pelo fluxo de incidente, mas deverão ser registradas e revisadas no próximo dia útil.

## 10. Classificação de defeitos

| Severidade | Definição | Tratamento esperado |
|---|---|---|
| Bloqueador | perda/corrupção de dados, acesso indevido, cálculo financeiro incorreto ou sistema indisponível | interromper avanço e corrigir imediatamente |
| Alta | fluxo essencial sem alternativa segura | corrigir antes do marco ou obter aceite excepcional |
| Média | falha com alternativa operacional controlada | planejar correção e avaliar impacto no marco |
| Baixa | problema visual ou inconveniência sem risco operacional | priorizar no backlog |

## 11. Regras de aprovação

- Aprovação verbal deve ser transcrita para o registro no mesmo dia.
- Silêncio não constitui aceite.
- Aceite com ressalva deve listar cada ressalva, risco, dono e prazo.
- Operações financeiras e alterações de produção exigem segunda pessoa.
- O solicitante não aprova a própria mudança sensível.

