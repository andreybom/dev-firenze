# Backlog detalhado de execução

**Estimativas:** iniciais, em dias-pessoa (dp), sujeitas ao refinamento arquitetural.  
**Status inicial:** todas as tarefas estão `Não iniciadas`.  
**Responsável:** função executora; o nome será preenchido no kickoff.

## Resumo de esforço

| Frente | Tarefas | Esforço estimado |
|---|---:|---:|
| Mobilização e gestão | 8 | 5,5 dp |
| Arquitetura e fundação | 14 | 20,5 dp |
| Segurança, identidade e auditoria | 12 | 30,5 dp |
| Cadastros estruturais | 11 | 25 dp |
| Clientes e corretores | 8 | 17 dp |
| Reservas, alertas e bloqueios | 15 | 34 dp |
| Contratos e documentos | 17 | 48,5 dp |
| Financeiro e relatórios | 15 | 39,5 dp |
| Migração operacional | 10 | 25 dp |
| Infraestrutura e continuidade | 12 | 23 dp |
| Qualidade e segurança final | 12 | 41 dp |
| Homologação, treinamento e implantação | 21 | 34,5 dp |
| **Total da linha de base** | **155** | **344 dp** |
| **Total com reserva de 15%** | — | **396 dp** |

## 1. Mobilização e gestão

| ID | Tarefa | Responsável | Dependência | Est. | Saída/critério de aceite |
|---|---|---|---|---:|---|
| GOV-001 | Aprovar plano, escopo do MVP e itens futuros | Aprovador final | — | 0,5 | Aprovação e data registradas |
| GOV-002 | Registrar autorização explícita para iniciar implementação | Aprovador final | GOV-001 | 0,5 | Autorização inequívoca no registro de decisões |
| GOV-003 | Nomear equipe técnica e disponibilidade semanal | Diretoria/TI | GOV-001 | 0,5 | Nomes, funções e capacidade registrados |
| GOV-004 | Criar quadro de acompanhamento com todos os IDs deste backlog | Gestão do projeto | GOV-001 | 1 | Quadro contém status, responsável, prazo e dependências |
| GOV-005 | Definir calendário de reuniões, demonstrações e marcos | Gestão do projeto | GOV-003 | 0,5 | Agenda recorrente comunicada |
| GOV-006 | Baselinear escopo, esforço e cronograma | Gestão do projeto | GOV-002/004 | 1 | Versão-base aprovada e protegida contra alteração informal |
| GOV-007 | Criar registros de risco, impedimento, decisão e mudança | Gestão do projeto | GOV-004 | 1 | Modelos disponíveis e responsáveis definidos |
| GOV-008 | Definir processo de aceite e assinatura dos marcos | Gestão/Produto | GOV-005 | 0,5 | Fluxo e aprovadores documentados |

## 2. Arquitetura e fundação técnica

| ID | Tarefa | Responsável | Dependência | Est. | Saída/critério de aceite |
|---|---|---|---|---:|---|
| ARC-001 | Inventariar código, dependências, builds e configurações atuais | Líder Técnico | GOV-002 | 1 | Relatório do estado inicial reproduzível |
| ARC-002 | Validar Java 25, Spring Boot 4.1, Angular 22, Node e PostgreSQL | Líder Técnico | ARC-001 | 2 | Matriz de compatibilidade e decisão de versões |
| ARC-003 | Corrigir `groupId`, pacotes, nomes e metadados do produto | Backend | ARC-002 | 1 | Artefatos usam identificação definitiva |
| ARC-004 | Definir arquitetura modular e limites dos domínios | Líder Técnico | ARC-002 | 2 | Diagrama e decisões arquiteturais aprovados |
| ARC-005 | Definir padrão de API, versionamento, paginação e filtros | Backend/Frontend | ARC-004 | 1,5 | Convenção documentada e exemplo funcional |
| ARC-006 | Padronizar erros, validações e mensagens ao usuário | Backend/Frontend | ARC-005 | 2 | Contrato de erro testado no frontend e backend |
| ARC-007 | Definir estratégia de datas, horários, moeda e fuso | Backend/Frontend | ARC-004 | 1 | Padrão America/Sao_Paulo e moeda testados |
| ARC-008 | Implantar migrações versionadas do banco | Backend | ARC-002 | 2 | Banco vazio criado apenas pelas migrações |
| ARC-009 | Remover criação/alteração automática de esquema em produção | Backend | ARC-008 | 0,5 | Configuração de produção valida o esquema sem alterá-lo |
| ARC-010 | Externalizar configurações e segredos por ambiente | Backend/DevOps | ARC-002 | 1,5 | Nenhuma senha real no repositório; ambientes isolados |
| ARC-011 | Configurar lint, formatação e convenções de commit/revisão | Líder Técnico | ARC-001 | 1 | Verificações executadas automaticamente |
| ARC-012 | Criar pipeline de build e testes para frontend/backend | DevOps/QA | ARC-011 | 2 | Pipeline falha em erro de compilação ou teste |
| ARC-013 | Definir estratégia para armazenamento de PDFs e comprovantes | Líder Técnico/Infra | ARC-004 | 1,5 | Persistência, acesso, backup e retenção aprovados |
| ARC-014 | Definir serviço de e-mail e comportamento em falta de internet | Líder Técnico/Infra | ARC-004 | 1,5 | Fila, tentativas, falhas e monitoramento documentados |

## 3. Segurança, identidade e auditoria

| ID | Tarefa | Responsável | Dependência | Est. | Saída/critério de aceite |
|---|---|---|---|---:|---|
| SEG-001 | Elaborar matriz de permissões por perfil e operação | Segurança/Produto | ARC-004 | 2 | Matriz aprovada por áreas funcionais |
| SEG-002 | Definir política de senha, sessão, bloqueio e recuperação | Segurança/TI | SEG-001 | 1 | Política documentada e casos de teste definidos |
| SEG-003 | Implementar autenticação e encerramento de sessão | Backend/Frontend | SEG-002 | 3 | Login/logout e expiração testados |
| SEG-004 | Implementar usuários, ativação, desativação e múltiplos perfis | Backend/Frontend | SEG-003 | 3 | Ciclo do usuário e múltiplos perfis funcionais |
| SEG-005 | Implementar atribuição de usuários por loteamento | Backend/Frontend | SEG-004 | 3 | Escopo aplicado em todas as consultas e mutações |
| SEG-006 | Implementar acesso global do Administrador e escopo do Diretor | Backend/Frontend | SEG-005 | 2 | Testes positivos e negativos por perfil |
| SEG-007 | Restringir Corretor à própria carteira | Backend/Frontend | SEG-005 | 2 | Acesso cruzado impedido inclusive por API direta |
| SEG-008 | Implementar autorização no backend para cada operação | Backend | SEG-001/005 | 4 | Nenhuma proteção depende somente da interface |
| SEG-009 | Implementar trilha de auditoria imutável | Backend | ARC-008 | 4 | Autor, data, ação, entidade, antes/depois e motivo consultáveis |
| SEG-010 | Proteger dados sensíveis em logs e respostas | Backend/DevOps | SEG-003 | 1,5 | Testes não encontram senha, token ou documento completo em log |
| SEG-011 | Criar testes automatizados da matriz de acesso | QA/Backend | SEG-006/007/008 | 3 | Todos os perfis cobertos por testes de permissão |
| SEG-012 | Executar revisão de ameaças e controles mínimos | Segurança/Líder Técnico | SEG-009/010 | 2 | Riscos críticos tratados ou formalmente aceitos |

## 4. Cadastros estruturais

| ID | Tarefa | Responsável | Dependência | Est. | Saída/critério de aceite |
|---|---|---|---|---:|---|
| CAD-001 | Modelar empresa, loteamento, quadra e lote | Backend/Dados | ARC-008 | 2 | Modelo revisado e migração criada |
| CAD-002 | Modelar estados fixos, etiquetas, categorias e motivos | Backend/Produto | CAD-001 | 1,5 | Transições estruturais não podem ser alteradas por usuário |
| CAD-003 | Implementar cadastro e consulta de loteamentos | Backend/Frontend | CAD-001/SEG-005 | 3 | CRUD, validações e escopo aprovados |
| CAD-004 | Implementar cadastro e consulta de quadras | Backend/Frontend | CAD-003 | 2 | Identificação única no loteamento e filtros funcionais |
| CAD-005 | Implementar cadastro e consulta de lotes | Backend/Frontend | CAD-004 | 4 | Estado, preço, área cadastral e características funcionais |
| CAD-006 | Implementar histórico de alteração do lote | Backend/Frontend | CAD-005/SEG-009 | 2 | Alterações relevantes aparecem na auditoria |
| CAD-007 | Implementar pesquisa, paginação e filtros cadastrais | Backend/Frontend | CAD-005 | 2 | Consultas performam no volume de referência |
| CAD-008 | Impedir transições inválidas de estado | Backend | CAD-002/005 | 2 | Matriz de transição coberta por testes |
| CAD-009 | Criar telas responsivas para computador e tablet | Frontend/UX | CAD-003/004/005 | 3 | Fluxos completos aprovados nos dois formatos |
| CAD-010 | Criar consultas básicas para celular | Frontend/UX | CAD-007 | 1,5 | Lista e detalhes essenciais utilizáveis em tela pequena |
| CAD-011 | Criar testes de cadastro e isolamento por loteamento | QA | CAD-006/008 | 2 | Cenários principais e negativos aprovados |

## 5. Clientes e corretores

| ID | Tarefa | Responsável | Dependência | Est. | Saída/critério de aceite |
|---|---|---|---|---:|---|
| CLI-001 | Modelar participante pessoa física e jurídica | Backend/Dados | ARC-008 | 2 | Estrutura suporta comprador e demais papéis futuros |
| CLI-002 | Definir documentos, contatos, endereços e validações | Produto/Jurídico | CLI-001 | 1 | Campos obrigatórios aprovados |
| CLI-003 | Implementar cadastro de pessoa física e cônjuge | Backend/Frontend | CLI-002 | 3 | Inclusão, edição, pesquisa e validação funcionais |
| CLI-004 | Implementar cadastro de pessoa jurídica e representantes | Backend/Frontend | CLI-002 | 3 | Inclusão, edição, pesquisa e validação funcionais |
| CLI-005 | Implementar cadastro e vínculo de corretores | Backend/Frontend | SEG-004/CLI-001 | 2 | Corretor associado a usuário e carteira |
| CLI-006 | Detectar possíveis duplicidades de CPF/CNPJ e contato | Backend/Frontend | CLI-003/004 | 2 | Sistema alerta e impede duplicidade exata |
| CLI-007 | Implementar consulta da carteira própria do corretor | Backend/Frontend | CLI-005/SEG-007 | 2 | Corretor vê somente registros permitidos |
| CLI-008 | Criar testes de clientes, representantes e carteira | QA | CLI-003–007 | 2 | Casos PF, PJ e restrição aprovados |

## 6. Reservas, alertas e bloqueios

| ID | Tarefa | Responsável | Dependência | Est. | Saída/critério de aceite |
|---|---|---|---|---:|---|
| RES-001 | Modelar reserva, validade, eventos e motivos | Backend/Dados | CAD-005/CLI-005 | 2 | Modelo e estados aprovados |
| RES-002 | Implementar criação de reserva | Backend/Frontend | RES-001 | 3 | Cliente, corretor, lote e validade obrigatórios |
| RES-003 | Garantir exclusividade sob concorrência | Backend/DB/QA | RES-002 | 3 | Duas tentativas simultâneas não reservam o mesmo lote |
| RES-004 | Implementar prazo padrão e ajuste pelo Diretor | Backend/Frontend | RES-002/SEG-006 | 2 | Prazo por loteamento e intervalo permitido testados |
| RES-005 | Implementar prorrogação autorizada | Backend/Frontend | RES-004 | 2 | Permissão, novo prazo, motivo e auditoria validados |
| RES-006 | Implementar cancelamento com motivo | Backend/Frontend | RES-002/SEG-009 | 1,5 | Lote liberado e histórico preservado |
| RES-007 | Implementar expiração automática idempotente | Backend | RES-002 | 3 | Execuções repetidas não duplicam eventos nem corrompem estado |
| RES-008 | Implementar fila e envio de aviso por e-mail | Backend/Infra | ARC-014/RES-004 | 3 | Corretor e cliente recebem aviso conforme antecedência |
| RES-009 | Implementar tentativas e registro de falha de e-mail | Backend/Infra | RES-008 | 2 | Falhas ficam visíveis e podem ser reprocessadas |
| RES-010 | Destacar reservas próximas do vencimento e vencidas | Frontend | RES-004/007 | 2 | Estilos possuem legenda e não dependem apenas de cor |
| RES-011 | Converter reserva em contrato rascunho | Backend/Frontend | RES-002/CON-001 | 2 | Conversão única coloca lote em `Em contratação` |
| BLQ-001 | Implementar aplicação de bloqueio pelo Diretor | Backend/Frontend | CAD-005/SEG-006 | 2 | Categoria, justificativa e eventual anexo obrigatórios |
| BLQ-002 | Implementar retirada de bloqueio pelo Diretor | Backend/Frontend | BLQ-001 | 1,5 | Motivo obrigatório, sem expiração automática e com auditoria |
| BLQ-003 | Implementar catálogo inicial de motivos | Backend/Frontend | BLQ-001 | 1 | Categorias da DEC-034 disponíveis e `Outro` exige descrição |
| RES-012 | Criar testes integrados de reserva, expiração e bloqueio | QA | RES-003–011/BLQ-003 | 4 | Fluxos, concorrência, permissões e auditoria aprovados |

## 7. Contratos e documentos

| ID | Tarefa | Responsável | Dependência | Est. | Saída/critério de aceite |
|---|---|---|---|---:|---|
| CON-001 | Modelar contrato, participantes, modalidades e estados | Backend/Dados | CLI-004/CAD-005 | 3 | Modelo cobre PF/PJ, múltiplos compradores e cônjuge |
| CON-002 | Implementar criação de contrato rascunho | Backend/Frontend | CON-001 | 3 | Origem manual autorizada ou conversão de reserva rastreável |
| CON-003 | Implementar venda à vista e parcelada | Backend/Frontend | CON-002 | 3 | Condições comerciais validadas e persistidas |
| CON-004 | Implementar sinal/entrada inclusive parcelada | Backend/Frontend | CON-003 | 3 | Entrada compõe corretamente o plano financeiro |
| CON-005 | Implementar múltiplos compradores, PF/PJ e cônjuge | Backend/Frontend | CON-002 | 3 | Papéis e percentuais/relações validados conforme regra aprovada |
| CON-006 | Modelar e implementar templates versionados | Backend/Frontend | ARC-013/CON-001 | 4 | Versões publicadas são imutáveis e possuem vigência |
| CON-007 | Restringir publicação de modelo ao Jurídico | Backend/Frontend | CON-006/SEG-008 | 1,5 | Comercial não altera cláusulas publicadas |
| CON-008 | Implementar variáveis, cláusulas e anexos por loteamento | Backend/Frontend | CON-006 | 4 | Documento usa apenas campos aprovados e acusa ausência |
| CON-009 | Gerar PDF e registrar hash/versão do documento | Backend | CON-008/ARC-013 | 3 | PDF reproduzível e vinculado ao contrato/template |
| CON-010 | Armazenar PDF assinado manualmente | Backend/Frontend | CON-009 | 2 | Upload validado, auditado e recuperável |
| CON-011 | Implementar ciclo Rascunho até Ativo/Quitado | Backend/Frontend | CON-002/FIN-001 | 3 | Transições, permissões e pré-condições testadas |
| CON-012 | Implementar prazo de contratação por loteamento | Backend/Frontend | CON-011 | 2 | Prazo configurável e eventos registrados |
| CON-013 | Liberar automaticamente quando não houver pagamento | Backend | CON-012 | 2 | Expiração libera lote somente se não houver movimento financeiro |
| CON-014 | Tratar entrada paga sem assinatura | Backend/Frontend | CON-011/FIN-005 | 2 | Lote permanece indisponível e caso vai para Financeiro/Jurídico |
| CON-015 | Implementar cancelamento e distrato controlado | Backend/Frontend | CON-011/FIN-010 | 4 | Acerto, documentos, aprovação do Diretor e auditoria obrigatórios |
| CON-016 | Garantir que venda exija assinatura e entrada confirmada | Backend | CON-010/FIN-005 | 2 | Estado `Vendido` impossível sem ambas as condições |
| CON-017 | Criar testes jurídicos e documentais | QA/Jurídico | CON-003–016 | 4 | Roteiros PF/PJ, versões, PDF, cancelamento e distrato aprovados |

## 8. Financeiro e relatórios

| ID | Tarefa | Responsável | Dependência | Est. | Saída/critério de aceite |
|---|---|---|---|---:|---|
| FIN-001 | Modelar plano financeiro, parcela e lançamento | Backend/Dados | CON-003/004 | 3 | Modelo diferencia obrigação, pagamento, alocação e estorno |
| FIN-002 | Formalizar regras de vencimento, arredondamento e saldo | Financeiro/Produto | FIN-001 | 2 | Exemplos de cálculo aprovados pelo Financeiro |
| FIN-003 | Gerar parcelas da venda à vista/parcelada | Backend | FIN-002 | 3 | Totais e datas reconciliam com o contrato |
| FIN-004 | Implementar registro manual de pagamento | Backend/Frontend | FIN-003 | 3 | Usuário, data, valor e origem registrados |
| FIN-005 | Implementar pagamento parcial e complemento | Backend/Frontend | FIN-004 | 3 | Saldos e situação da parcela permanecem corretos |
| FIN-006 | Implementar alocação em várias parcelas | Backend/Frontend | FIN-004 | 3 | Soma das alocações igual ao valor recebido |
| FIN-007 | Implementar anexação de comprovantes | Backend/Frontend | ARC-013/FIN-004 | 2 | Tipo, tamanho, acesso e auditoria validados |
| FIN-008 | Implementar histórico financeiro imutável | Backend | FIN-004 | 3 | Lançamentos confirmados não podem ser sobrescritos ou apagados |
| FIN-009 | Implementar solicitação de estorno | Backend/Frontend | FIN-008 | 2 | Motivo e referência ao lançamento original obrigatórios |
| FIN-010 | Implementar aprovação de estorno pelo Diretor | Backend/Frontend | FIN-009/SEG-006 | 2 | Solicitante não aprova; novo evento compensa o original |
| FIN-011 | Implementar saldo do contrato e situação das parcelas | Backend/Frontend | FIN-005/006/010 | 3 | Totais conferem após parcial, complemento e estorno |
| FIN-012 | Criar relatório financeiro básico | Backend/Frontend | FIN-011 | 3 | Filtros e totais aprovados pelo Financeiro |
| FIN-013 | Implementar exportação controlada do relatório | Backend/Frontend | FIN-012/SEG-008 | 1,5 | Arquivo respeita filtros e escopo do usuário |
| FIN-014 | Criar testes de propriedades e reconciliação financeira | QA/Backend | FIN-003–011 | 4 | Invariantes de soma, saldo e imutabilidade aprovados |
| FIN-015 | Executar homologação funcional dos cálculos | Financeiro/QA | FIN-014 | 2 | Massa conhecida reconciliada e evidência assinada |

## 9. Migração operacional

| ID | Tarefa | Responsável | Dependência | Est. | Saída/critério de aceite |
|---|---|---|---|---:|---|
| MIG-001 | Receber fontes, amostra e responsável validador | Dados/Negócio | PEN-013 | 1 | Inventário de arquivos e autorização de uso |
| MIG-002 | Medir volumes, formatos, qualidade e duplicidades | Dados | MIG-001 | 3 | Perfil de dados com problemas quantificados |
| MIG-003 | Mapear campos, regras de transformação e chaves | Dados/Backend | MIG-002/modelo estável | 4 | De-para aprovado pelas áreas |
| MIG-004 | Definir critérios para registros ativos e arquivo histórico | Dados/Financeiro/Jurídico | MIG-003 | 2 | Critérios consistentes com DEC-030 |
| MIG-005 | Construir carga repetível e relatório de rejeições | Backend/Dados | MIG-003 | 5 | Processo idempotente, versionado e auditável |
| MIG-006 | Executar primeiro ensaio de migração | Dados/QA | MIG-005 | 2 | Carga concluída e rejeições catalogadas |
| MIG-007 | Reconciliar quantidades, contratos, parcelas e saldos | Dados/Financeiro/QA | MIG-006 | 3 | Diferenças explicadas e aprovadas |
| MIG-008 | Corrigir regras e executar segundo ensaio | Dados/Backend | MIG-007 | 3 | Segundo resultado atende tolerância definida |
| MIG-009 | Validar acesso ao arquivo histórico | Jurídico/Operação | MIG-004 | 1 | Histórico encerrado localizável por critérios acordados |
| MIG-010 | Aprovar plano da carga final e congelamento | Diretoria/Dados/TI | MIG-008/009 | 1 | Janela, responsáveis, validações e retorno aprovados |

## 10. Infraestrutura, operação e continuidade

| ID | Tarefa | Responsável | Dependência | Est. | Saída/critério de aceite |
|---|---|---|---|---:|---|
| OPS-001 | Inventariar servidor, rede, armazenamento e capacidade | Infra/TI | GOV-003 | 1,5 | Recursos comparados com DEC-032 |
| OPS-002 | Definir ambientes de desenvolvimento, teste, homologação e produção | Infra/DevOps | ARC-002 | 2 | Ambientes isolados e acessos aprovados |
| OPS-003 | Preparar PostgreSQL com usuários e privilégios mínimos | DBA/Infra | OPS-002/ARC-010 | 2 | Aplicação não usa superusuário |
| OPS-004 | Preparar publicação reproduzível de backend e frontend | DevOps | ARC-012/OPS-002 | 3 | Implantação automatizada e versionada |
| OPS-005 | Configurar HTTPS, certificados e cabeçalhos de segurança | DevOps/Segurança | OPS-004 | 2 | Varredura confirma configuração mínima |
| OPS-006 | Configurar logs estruturados e correlação de requisições | Backend/DevOps | ARC-006 | 2 | Operação rastreável sem dados sensíveis |
| OPS-007 | Criar health checks e métricas essenciais | Backend/DevOps | OPS-004 | 1,5 | Aplicação, banco, armazenamento e e-mail monitoráveis |
| OPS-008 | Definir alertas, severidades e canais internos | Infra/Operação | OPS-007 | 1 | Alertas críticos testados |
| OPS-009 | Implementar backup do banco e documentos | DBA/Infra | OPS-003/ARC-013 | 2 | Rotina agendada, criptografada e monitorada |
| OPS-010 | Executar e documentar restauração completa | DBA/QA | OPS-009 | 2 | Restauração em ambiente isolado dentro do objetivo aprovado |
| OPS-011 | Definir retenção, descarte e capacidade de armazenamento | Diretoria/Jurídico/TI | OPS-009 | 1 | Política aprovada e aplicada |
| OPS-012 | Criar runbooks de publicação, falha, restauração e suporte | DevOps/Operação | OPS-004/008/010 | 3 | Procedimentos executáveis por pessoa diferente do autor |

## 11. Qualidade, desempenho e segurança final

| ID | Tarefa | Responsável | Dependência | Est. | Saída/critério de aceite |
|---|---|---|---|---:|---|
| QAS-001 | Elaborar plano de testes e matriz de cenários | QA/Produto | GOV-006 | 3 | Requisitos críticos possuem cenário e responsável |
| QAS-002 | Definir dados sintéticos de teste sem dados pessoais reais | QA/Dados | QAS-001 | 2 | Massa reproduzível cobre PF/PJ e financeiro |
| QAS-003 | Automatizar testes unitários dos domínios críticos | Devs/QA | Implementações | 6 | Regras financeiras, estados e permissões cobertos |
| QAS-004 | Automatizar testes de integração com PostgreSQL | Backend/QA | ARC-008 | 4 | Migrações e transações testadas em banco real de teste |
| QAS-005 | Automatizar jornadas essenciais de interface | Frontend/QA | M4–M6 | 5 | Login, reserva, contrato e pagamento cobertos |
| QAS-006 | Executar regressão funcional completa | QA | QAS-003–005 | 5 | Sem defeitos bloqueadores ou altos não aceitos |
| QAS-007 | Testar concorrência em reserva e operações financeiras | QA/Backend | RES-003/FIN-014 | 3 | Invariantes preservados sob acesso simultâneo |
| QAS-008 | Testar capacidade de 50 usuários simultâneos | QA/Infra | OPS-007 | 3 | Metas de resposta e erro aprovadas |
| QAS-009 | Executar testes de autorização e segurança | Segurança/QA | SEG-012/OPS-005 | 4 | Falhas críticas/altas corrigidas ou aceitas formalmente |
| QAS-010 | Executar teste de indisponibilidade da internet externa | QA/Infra | OPS-004 | 2 | Operação interna continua; e-mails aguardam reprocessamento |
| QAS-011 | Executar teste de backup, restauração e retorno de versão | QA/Infra | OPS-010/012 | 2 | Evidência do exercício e tempos medidos |
| QAS-012 | Produzir relatório final de qualidade | QA | QAS-006–011 | 2 | Cobertura, defeitos, riscos e recomendação Go/No-Go publicados |

## 12. Homologação, treinamento e implantação

| ID | Tarefa | Responsável | Dependência | Est. | Saída/critério de aceite |
|---|---|---|---|---:|---|
| HOM-001 | Nomear titulares e substitutos das áreas | Diretoria | PEN-017 | 0,5 | Todos os papéis de aceite possuem nomes |
| HOM-002 | Preparar ambiente, massa e roteiro de homologação | QA/Produto | M6/M7/HOM-001 | 2 | Ambiente estável e cenários distribuídos |
| HOM-003 | Homologar administração e cadastros | Negócio/TI | HOM-002 | 1 | Evidência e aceite registrados |
| HOM-004 | Homologar reservas e bloqueios | Comercial/Diretoria | HOM-002 | 1,5 | Evidência e aceite registrados |
| HOM-005 | Homologar contratos e documentos | Jurídico | HOM-002 | 1,5 | Evidência e aceite registrados |
| HOM-006 | Homologar parcelas, pagamentos e relatório | Financeiro | HOM-002 | 2 | Valores reconciliados e aceite registrado |
| HOM-007 | Corrigir e retestar defeitos de homologação | Devs/QA | HOM-003–006 | 5 | Defeitos bloqueadores zerados |
| HOM-008 | Emitir termo de homologação do MVP | Aprovador final | HOM-007 | 0,5 | Termo assinado com ressalvas explícitas, se houver |
| TRN-001 | Criar manual rápido por perfil | Produto/QA | Funcionalidades estáveis | 3 | Instruções revisadas pelas áreas |
| TRN-002 | Treinar Administrador, Diretor e suporte | Produto/TI | TRN-001 | 1 | Participantes e evidência registrados |
| TRN-003 | Treinar Comercial, Financeiro e Jurídico | Produto/Áreas | TRN-001 | 2 | Exercícios críticos executados |
| IMP-001 | Aprovar janela, congelamento e plano de comunicação | Diretoria/TI | HOM-008/MIG-010 | 1 | Data, participantes e canais registrados |
| IMP-002 | Executar backup pré-corte e validar restauração | DBA/Infra | IMP-001/OPS-010 | 1 | Backup íntegro e identificável |
| IMP-003 | Publicar versão candidata imutável | DevOps | IMP-001/QAS-012 | 1 | Artefatos possuem versão e checksum |
| IMP-004 | Executar carga final e reconciliação | Dados/Financeiro | IMP-002/MIG-010 | 2 | Quantidades e saldos aprovados |
| IMP-005 | Publicar aplicação em produção | DevOps | IMP-003/004 | 1 | Versão acessível pela rede interna |
| IMP-006 | Executar smoke test pós-publicação | QA/Áreas | IMP-005 | 1 | Login, consulta, reserva e registro controlado aprovados |
| IMP-007 | Realizar decisão Go/No-Go | Aprovador final | IMP-006 | 0,5 | Decisão e riscos residuais registrados |
| IMP-008 | Executar retorno se critérios críticos falharem | DevOps/DBA | IMP-007=No-Go | 1 | Versão e dados anteriores restaurados e validados |
| IMP-009 | Monitorar estabilização e triagem diária | Suporte/Equipe | IMP-007=Go | 5 | Incidentes acompanhados por severidade e prazo |
| IMP-010 | Encerrar estabilização e transferir para operação | Aprovador/TI | IMP-009 | 1 | Indicadores estáveis, documentação entregue e aceite final |

## 13. Regras de manutenção do backlog

- Nenhum ID será reutilizado.
- Tarefa removida será marcada `Cancelada`, nunca apagada.
- Mudança de escopo gera item próprio e referência à decisão aprovada.
- Dependência bloqueadora deve ser registrada no mesmo dia.
- Estimativa será revisada no refinamento, antes de a tarefa ficar `Pronta para iniciar`.
- Toda conclusão exige evidência: teste, captura, relatório, aprovação ou artefato versionado.
- Tarefas financeiras, de segurança, migração e produção exigem revisão por segunda pessoa.

