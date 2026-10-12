# Questionário 01 — Escopo e operação

**Projeto:** Sistema de gestão de loteamento Firenze  
**Etapa:** Fase 0 — Descoberta  
**Status:** Aprovado com pendências externas e definições complementares  
**Respondido por:**  
**Data:**  

## Orientação

Preencha os campos **Resposta** e, quando necessário, **Observações**. As recomendações são propostas iniciais e poderão ser alteradas.

---

## 1. Estrutura organizacional

Qual estrutura o sistema deverá atender?

- **A.** Um único loteamento.
- **B.** Vários loteamentos pertencentes à mesma empresa. **Recomendação inicial.**
- **C.** Várias empresas independentes, operando como um produto SaaS multiempresa.

**Resposta:**  
Seguir sugestão.

**Observações:**
Cada usuário terá acesso à loteamento específico determinado pelo administrador ou Diretor, e cada usuário pode ter acesso à varios loteamentos desde que atribuídos pelo administrador ou Diretor.  

Se a resposta for B ou C, os usuários poderão acessar todos os loteamentos ou o acesso deverá ser concedido individualmente por loteamento?

**Resposta:**  
Será concedido individulamente por administrador ou diretor
---

## 2. Usuários e perfis de acesso

Marque os perfis necessários e descreva diferenças relevantes:

- [x] Administrador
- [x] Diretoria ou gestor
- [x] Comercial
- [x] Financeiro
- [x] Jurídico
- [x] Corretor
- [x] Consulta ou auditoria
- [ ] Outro: 

**Resposta/observações:**
Diretoria ou gestor deve possuir acesso à todos os módulos do sistema depois de realizada a identificação, Login, o mesmo vale para o Administrador.  

O corretor terá acesso direto ao sistema?

- **A.** Não; a equipe interna registra as operações.
- **B.** Sim; acessa somente seus clientes, reservas e vendas.
- **C.** Sim; acessa todas as informações comerciais permitidas.

**Resposta:**  
B sim, somente seus clientes e afins

Uma mesma pessoa poderá acumular mais de um perfil?

**Resposta:**  
Sim, o mesmo usuário pode possuir mais de um perfil

Existe alguma ação que deverá exigir aprovação de uma segunda pessoa, como cancelamento de contrato, estorno ou alteração financeira?

**Resposta:**  
Sim, todo cancelamento financeiro deve ser aprovado por um diretor.
---

## 3. Processo de reserva

Confirme ou ajuste as regras propostas:

- [x] Um lote não pode possuir duas reservas ativas ao mesmo tempo.
- [x] A reserva deve identificar o interessado ou cliente.
- [x] A reserva deve identificar o corretor ou responsável.
- [x] A reserva possui data e hora de validade.
- [x] Ao expirar, o lote volta automaticamente a ficar disponível.
- [x] Somente usuários autorizados podem prorrogar a reserva.
- [x] O cancelamento exige motivo.
- [x] A conversão da reserva inicia a venda ou o contrato.
- [x] Criação, prorrogação, conversão e cancelamento ficam auditados.

**Resposta/ajustes:**  
Converter a reserva criará um contrato em rascunho e colocará o lote em `Em contratação`.

Qual será o prazo padrão da reserva?

**Resposta:**
prazo para reserva dever ser ajustada no sistema, dependendo da circunstância economica no momento, pode variar de 3 dias a 15 dias, mas deve ser configurado no sistema por um diretor.  

Será permitido configurar prazos diferentes por loteamento, lote ou tipo de negociação?

**Resposta:**  
Sim, os prazos terão um padrão inicial, mas poderão ser ajustados

É necessário avisar alguém antes do vencimento? Em caso positivo, quem, por qual canal e com quanta antecedência?

**Resposta:**  
Encaminhar aviso por e-mail ao corretor e ao cliente, com antecedência configurável por diretor. No sistema, destacar graficamente o lote quando a reserva estiver próxima do vencimento e quando estiver vencida.

---

## 4. Situações do lote

Fluxo principal proposto:

`Disponível → Reservado → Em contratação → Vendido`

Marque os estados adicionais utilizados na operação:

- [x] Bloqueado
- [x] Permutado
- [x] Uso interno
- [x] Indisponível
- [ ] Em distrato
- [ ] Outro: 

**Resposta/observações:**  
Os estados estruturais e suas transições serão fixos. Usuários autorizados poderão configurar motivos, etiquetas e categorias auxiliares.

Quem poderá bloquear ou desbloquear um lote? O bloqueio deverá exigir motivo e data de validade?

**Resposta:**  
Reserva, bloqueio administrativo e contratação serão situações diferentes. Somente o perfil Diretor poderá aplicar e retirar o bloqueio administrativo. O bloqueio não terá prazo de validade nem desbloqueio automático; o Diretor poderá retirá-lo a qualquer tempo, sempre informando um motivo, com registro da operação na auditoria. Os motivos aceitos para aplicar o bloqueio ainda serão definidos.

Um lote vendido poderá voltar diretamente para disponível ou deverá passar por um processo formal de distrato/cancelamento?

**Resposta:**  
Não diretamente. O retorno exigirá cancelamento ou distrato, acerto financeiro, documentos, aprovação de diretor e auditoria antes da liberação.
---

## 5. Momento da venda

Quando o lote deverá ser considerado vendido e deixar de estar disponível?

- **A.** Ao criar o contrato.
- **B.** Ao assinar o contrato.
- **C.** Ao pagar a entrada. **Recomendação preliminar.**
- **D.** Somente após assinatura e pagamento da entrada.
- **E.** Outra regra.

**Resposta:**  
D — considerar vendido somente após assinatura do contrato e confirmação da entrada, conforme decisão conciliada.

Se a assinatura ou a entrada não ocorrer no prazo, o que deverá acontecer com o contrato e com o lote?

**Resposta:**
O prazo será configurável por loteamento. Sem pagamento, o contrato expirará e o lote será liberado automaticamente. Havendo pagamento sem assinatura, o lote permanecerá `Em contratação` e seguirá para análise do Financeiro/Jurídico.

Quais estados do contrato são necessários? Proposta inicial:

- [x] Rascunho
- [x] Aguardando assinatura
- [x] Aguardando entrada
- [x] Ativo
- [x] Quitado
- [x] Cancelado
- [x] Rescindido ou distratado
- [x] Renegociado
- [ ] Outro: 

**Resposta/ajustes:**  

---

## 6. Modalidades e participantes do contrato

Marque o que deverá ser atendido pelo MVP:

- [x] Venda à vista
- [x] Venda parcelada
- [x] Sinal ou entrada
- [x] Entrada parcelada
- [x] Mais de um comprador no mesmo contrato
- [x] Cônjuge ou companheiro no contrato
- [ ] Fiador ou avalista
- [x] Pessoa jurídica como compradora
- [x] Distrato
- [ ] Cessão de direitos
- [ ] Renegociação
- [ ] Permuta
- [ ] Outra modalidade: 

**Resposta/observações:**  
O MVP atenderá vendas à vista e parceladas, sinal ou entrada inclusive parcelada, um ou mais compradores pessoa física ou jurídica, cônjuge/companheiro e distrato. O modelo de dados será preparado para fiador, cessão e permuta, mas seus fluxos completos ficarão para versões futuras. Renegociação avançada também ficará para versão futura.

Existe um modelo único de contrato ou modelos diferentes por loteamento, modalidade ou condição comercial?

**Resposta:**  
Serão adotados modelos contratuais versionados por finalidade, com modelos-base para pessoa física e jurídica e cláusulas ou anexos específicos por loteamento. A seleção observará a modalidade e o tipo de comprador. O Jurídico publicará as versões; o Comercial poderá gerar documentos sem alterar cláusulas aprovadas; cada contrato manterá vínculo com a versão utilizada.

Quem poderá elaborar, revisar, aprovar e cancelar um contrato?

**Resposta:**  
Comercial prepara a negociação; Financeiro valida valores e registra pagamentos; Jurídico valida o contrato; Diretor aprova cancelamentos, distratos, estornos e ajustes sensíveis. O solicitante não aprova a própria operação sensível.

A assinatura será manual, eletrônica ou ambas? Se eletrônica, já existe um fornecedor escolhido?

**Resposta:**  
No MVP, assinatura manual com armazenamento do PDF assinado. Integração com assinatura eletrônica fica para etapa posterior.

---

## 7. Pagamentos no MVP

Proposta inicial: registrar pagamentos manualmente, permitir comprovantes e manter histórico imutável de pagamentos, complementos e estornos.

Essa proposta atende ao início da operação?

**Resposta:**
Seguir sugestão  

Marque as capacidades necessárias já no MVP:

- [x] Registro manual de pagamento
- [x] Pagamento parcial de parcela
- [x] Complemento posterior
- [x] Pagamento de várias parcelas em uma operação
- [x] Estorno com motivo e auditoria
- [x] Anexo de comprovante
- [ ] Baixa automática por arquivo de retorno bancário
- [ ] Emissão de boleto
- [ ] Cobrança por Pix
- [ ] Integração com banco
- [ ] Integração com sistema contábil ou ERP
- [ ] Outra: 

**Resposta/observações:**  
As capacidades manuais marcadas fazem parte do MVP. Boleto, Pix, retorno bancário e integrações ficam para etapa posterior. Estornos exigem aprovação de diretor e não apagam o lançamento original.

Se houver integração, informe os bancos, ERPs ou fornecedores envolvidos:

**Resposta:**
Não se aplica ao MVP. Bancos, ERPs e fornecedores serão definidos quando as integrações entrarem no escopo de uma versão futura.

---

## 8. Dados existentes e migração

Qual é a origem dos dados atuais?

- [ ] Planilha Excel
- [ ] Planilhas Google
- [ ] Sistema legado
- [ ] Documentos físicos
- [ ] Documentos digitais
- [ ] Outra: 

**Resposta/observações:**  
Será utilizada migração operacional: cadastros, clientes e corretores ativos, contratos ativos, parcelas abertas, pagamentos necessários à composição dos saldos e documentos essenciais serão migrados. Dados encerrados permanecerão em arquivo histórico consultável. A execução depende do fornecimento das fontes e de uma amostra representativa.

Informe, ainda que aproximadamente:

| Informação | Quantidade aproximada |
|---|---:|
| Loteamentos | |
| Quadras | |
| Lotes | |
| Clientes | |
| Corretores | |
| Contratos | |
| Parcelas | |
| Pagamentos | |
| Documentos digitais | |

Existem duplicidades, campos ausentes, divergências financeiras ou outros problemas já conhecidos?

**Resposta:**  
Pendentes

Quem conhece melhor os dados atuais e poderá validar a migração?

**Resposta:**  
Pendentes

---

## 9. Mapa do loteamento

Quais materiais cartográficos estão disponíveis?

- [ ] AutoCAD/DWG
- [ ] DXF
- [ ] PDF vetorial
- [ ] PDF contendo somente imagem
- [ ] Imagem PNG/JPEG
- [ ] Shapefile
- [ ] GeoJSON
- [ ] Coordenadas geográficas
- [ ] Ainda não identificado
- [ ] Outro: 

**Resposta/observações:**
Não há atualmente informação ou material cartográfico disponível. O material cartográfico, o mapa interativo e a representação geográfica real foram adiados para versão futura. O MVP manterá os cadastros de loteamentos, quadras e lotes sem assumir precisão ou representação cartográfica.

Marque as interações necessárias:

- [x] Visualizar lotes e suas situações por cor
- [ ] Clicar em um lote para abrir seus detalhes
- [x] Pesquisar e destacar um lote
- [x] Filtrar lotes por situação, preço ou características
- [x] Iniciar reserva pelo mapa
- [ ] Iniciar venda pelo mapa
- [x] Exibir medidas, área e confrontações
- [ ] Editar o desenho dos lotes pelo sistema
- [x] Utilizar coordenadas e mapa geográfico real
- [ ] Outra: 

**Resposta/observações:**  
As interações selecionadas serão requisitos de versão futura, condicionadas ao fornecimento e à validação do material cartográfico.

---

## 10. Implantação e dispositivos

Onde o sistema deverá funcionar?

- **A.** Servidor local da empresa.
- **B.** Nuvem.
- **C.** Ainda não decidido.

**Resposta:**  
Aplicação e PostgreSQL centralizados em servidor local, acessíveis pela rede interna.

Quantos usuários são esperados?

| Medida | Quantidade aproximada |
|---|---:|
| Usuários cadastrados | Até 200 |
| Usuários simultâneos | Até 50 |
| Loteamentos nos próximos 3 anos | Até 20 |

Quais dispositivos precisam ser suportados?

- [x] Computador
- [x] Tablet
- [x] Celular — somente consultas básicas no MVP

**Resposta/observações:**  
O MVP oferecerá operação completa em computador e tablet. No celular, o compromisso inicial será limitado a consultas básicas.

O sistema precisará funcionar sem internet ou com conexão instável?

**Resposta:**  
Sim, enquanto o servidor e a rede interna estiverem disponíveis. O MVP não terá banco em cada dispositivo nem sincronização offline.

Existe preferência ou restrição sobre provedor de nuvem, localização dos dados ou orçamento mensal de infraestrutura?

**Resposta:**  
Sem preferencia, por enquanto.

---

## 11. Limites preliminares do MVP

Proposta inicial para o MVP:

- autenticação e permissões;
- cadastro de empreendimento, quadra e lote;
- mapa interativo; **transferido para versão futura pela DEC-031**
- reservas;
- clientes e corretores;
- contratos e geração de PDF;
- parcelas e pagamentos manuais;
- relatório financeiro básico;
- importação inicial de dados;
- auditoria e backups.

Itens propostos para etapa posterior:

- automação de índices financeiros;
- integração com DataJud;
- renegociação avançada;
- notificações multicanal;
- relatórios analíticos sofisticados.

O recorte proposto atende? Liste inclusões, exclusões ou itens posteriores que precisam entrar obrigatoriamente no MVP.

**Resposta:**  
Seguir sugestão com a alteração aprovada em 10/10/2026: mapa interativo e representação geográfica real ficam para versão futura.
---

## 12. Pessoas responsáveis pelas decisões

| Área | Nome ou função do responsável | Disponibilidade/observação |
|---|---|---|
| Negócio/comercial | | |
| Financeiro | | |
| Jurídico | | |
| Dados/migração | | |
| Infraestrutura/TI | | |
| Aprovação final | Andrey Bom | Aprovação complementar registrada em 10/10/2026 às 23:02 |

**Modelo de responsabilidade aprovado:** Cada área terá um titular e um substituto. Uma pessoa poderá acumular áreas, mas cada decisão deverá possuir responsável identificável. Os nomes ainda precisam ser informados.

**Aprovação final:** Andrey Bom, em 10/10/2026 às 23:02.

## Pendências identificadas ao responder

- As pendências foram consolidadas em [01a-conciliacao-escopo-e-operacao.md](01a-conciliacao-escopo-e-operacao.md).
- Os titulares e substitutos nominais das áreas, além das datas-alvo restantes, ainda precisam ser definidos.
- Os pontos críticos estão registrados em [registro-de-decisoes.md](registro-de-decisoes.md).

## Aprovação desta rodada

- [x] Respostas revisadas pelo responsável de negócio
- [x] Pendências atribuídas a responsáveis por área
- [x] Decisões consolidadas no registro da Fase 0

**Aprovado por:** Andrey Bom  
**Data:** 20/09/2026  

**Aprovação complementar:** Andrey Bom  
**Data:** 10/10/2026 23:02  
