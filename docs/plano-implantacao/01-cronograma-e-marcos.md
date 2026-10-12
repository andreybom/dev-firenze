# Cronograma, dependências e marcos

**Horizonte de referência:** 20 semanas após autorização formal  
**Unidade de esforço:** dia-pessoa (dp)  
**Regra:** datas reais serão calculadas no kickoff, conforme equipe disponível.

## 1. Sequência de execução

| Etapa | Semanas | Dependências | Entregas | Marco |
|---|---:|---|---|---|
| Mobilização e linha de base | 0–1 | Aprovação do plano | Backlog priorizado, responsáveis funcionais, ambientes solicitados | M0 |
| Arquitetura e segurança de base | 1–2 | M0 | Arquitetura, stack validada, modelo de ameaças, decisões técnicas | M1 |
| Fundação técnica | 2–3 | M1 | CI, configuração externa, banco versionado, tratamento de erros, observabilidade inicial | M2 |
| Identidade, acesso e auditoria | 3–5 | M2 | Login, usuários, perfis, loteamentos atribuídos e auditoria | M2/M3 |
| Cadastros centrais | 4–6 | Modelo de acesso | Empresa, loteamento, quadra, lote e estados | M3 |
| Clientes e corretores | 5–7 | Cadastros e acesso | PF/PJ, contatos, cônjuge, corretores e carteiras | M3 |
| Reservas e bloqueios | 6–9 | Lotes, clientes, usuários | Concorrência, prazos, alertas, expiração, bloqueios e auditoria | M4 |
| Contratos e documentos | 8–12 | Reservas, clientes, lotes | Modelos versionados, geração PDF, assinatura manual e ciclo contratual | M5 |
| Parcelas e pagamentos | 10–14 | Contratos | Plano financeiro, baixas manuais, estornos, comprovantes e relatório | M6 |
| Migração operacional | 13–16 | Dados reais, modelo estabilizado | Inventário, transformações, duas cargas e reconciliação | M7 |
| Operação e infraestrutura | 13–17 | Fundação técnica | Servidor, banco, backups, restauração, monitoramento e runbooks | M7/M9 |
| Testes integrados e segurança | 15–17 | M4–M6 | Regressão, concorrência, segurança, desempenho e recuperação | M8 |
| Homologação e treinamento | 17–18 | Dados e responsáveis nominais | UAT, manuais, treinamento e aceite | M8 |
| Corte de produção | 19 | M8 e M9 | Carga final, publicação, smoke test e decisão Go/No-Go | M9 |
| Estabilização assistida | 19–20 | Produção | Monitoramento, correções críticas e transferência para operação | M10 |

## 2. Caminho crítico

O caminho crítico de referência é:

`Aprovação → Arquitetura → Fundação → Acesso/Auditoria → Cadastros → Reservas → Contratos → Financeiro → Testes integrados → Homologação → Go-Live`

Dois gates externos podem interromper esse caminho:

1. ausência dos dados reais até o início da migração;
2. ausência dos titulares e substitutos antes da homologação.

## 3. Ondas funcionais

### Onda 1 — Administração e cadastros

Abrange autenticação, perfis, acesso por loteamento, cadastros estruturais, clientes e corretores. Pode ser demonstrada ao final da semana 7.

### Onda 2 — Operação comercial

Abrange reservas, conversão, expiração, avisos e bloqueios. Pode ser demonstrada ao final da semana 9.

### Onda 3 — Jurídico e documentos

Abrange contratos, modelos, versionamento, PDF, assinatura manual e distrato. Pode ser demonstrada ao final da semana 12.

### Onda 4 — Financeiro

Abrange parcelas, entrada, pagamentos, complementos, estornos e relatório financeiro. Pode ser demonstrada ao final da semana 14.

### Onda 5 — Preparação para produção

Abrange migração, segurança final, operação, homologação, treinamento, corte e estabilização.

## 4. Critério de passagem entre etapas

Uma etapa somente avança quando:

- todas as tarefas críticas previstas estiverem concluídas;
- testes automatizados e manuais aplicáveis estiverem aprovados;
- defeitos bloqueadores estiverem zerados;
- defeitos altos tiverem correção ou aceite formal documentado;
- documentação e evidências estiverem anexadas;
- responsável funcional tiver validado a entrega quando aplicável;
- riscos residuais estiverem registrados com proprietário e tratamento.

## 5. Reserva de contingência

O cronograma contém paralelismo, mas não inclui mudança substancial de escopo. Recomenda-se reservar:

- 15% do esforço técnico para correções e incertezas;
- até uma semana antes do Go-Live para defeitos de homologação;
- uma janela de retorno integral durante o corte;
- uma semana de estabilização assistida após a implantação.

## 6. Replanejamento

O cronograma será recalculado quando ocorrer qualquer uma destas condições:

- mudança aprovada superior a 5 dias-pessoa;
- atraso maior que 5 dias úteis em atividade do caminho crítico;
- indisponibilidade de membro essencial por mais de 3 dias úteis;
- falha de segurança ou integridade financeira;
- reprovação de marco pelo responsável funcional;
- ausência de dados no gate da migração;
- indisponibilidade do servidor ou da infraestrutura necessária.

## 7. Calendário de marcos

| Marco | Semana-alvo | Quem valida | Evidência mínima |
|---|---:|---|---|
| M0 | 1 | Aprovador final | Plano aprovado e autorização registrada |
| M1 | 2 | Diretoria/TI | Registro arquitetural e riscos aceitos |
| M2 | 3 | Líder Técnico/QA | Pipeline, ambiente e testes básicos |
| M3 | 7 | Negócio/Comercial | Demonstração e roteiro de cadastros aprovado |
| M4 | 9 | Comercial/Diretoria | Cenários de reserva e bloqueio aprovados |
| M5 | 12 | Jurídico | Modelos, versões e documentos aprovados |
| M6 | 14 | Financeiro | Reconciliação dos cenários financeiros |
| M7 | 16 | Dados/Financeiro | Segundo ensaio de migração reconciliado |
| M8 | 18 | Áreas usuárias | Termo de homologação |
| M9 | 19 | Diretoria/TI | Checklist Go/No-Go assinado |
| M10 | 20 | Aprovador final | Encerramento da estabilização |

