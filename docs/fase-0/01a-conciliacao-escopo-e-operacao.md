# Conciliação 01 — Escopo e operação

**Documento de origem:** [01-escopo-e-operacao.md](01-escopo-e-operacao.md)  
**Data da conciliação:** 20/09/2026  
**Status:** Aprovado com dependências de dados e responsáveis nominais  

## Resultado geral

O questionário definiu o escopo geral do produto, os perfis principais, as regras básicas de reserva, o gatilho preliminar da venda, o recorte do MVP e as interações esperadas no mapa. Ainda não é possível encerrar esta rodada porque há decisões operacionais sem responsável e pontos que afetam diretamente arquitetura, segurança e integridade financeira.

As decisões iniciais foram aprovadas por Andrey Bom em 20/09/2026. As definições complementares sobre contratos, migração, mapa, capacidade, governança e bloqueios foram aprovadas em 10/10/2026 às 23:02. Permanecem abertos os itens que dependem dos arquivos de dados e dos nomes dos responsáveis.

## Requisitos conciliados

### Organização e acesso

- O sistema atenderá vários loteamentos de uma mesma empresa.
- O acesso será concedido individualmente por loteamento por um administrador ou diretor.
- Existirão os perfis Administrador, Diretoria/Gestor, Comercial, Financeiro, Jurídico, Corretor e Consulta/Auditoria.
- Um usuário poderá acumular mais de um perfil.
- O Administrador terá acesso global. A Diretoria/Gestor terá acesso funcional a todos os módulos dos loteamentos atribuídos, salvo concessão explícita de escopo global.
- O corretor acessará somente clientes, reservas e vendas vinculados a ele.
- Cancelamentos financeiros exigirão aprovação de um diretor.

### Reservas

- Um lote não poderá ter duas reservas ativas simultaneamente.
- A reserva identificará interessado/cliente e corretor/responsável.
- A reserva terá validade com data e hora.
- A expiração devolverá automaticamente o lote à disponibilidade.
- Prorrogação será restrita a usuários autorizados.
- Cancelamento exigirá motivo.
- Criação, prorrogação, conversão e cancelamento serão auditados.
- O prazo será configurável por diretor, normalmente entre 3 e 15 dias, com um padrão inicial e possibilidade de ajuste.
- Corretor e cliente serão avisados por e-mail antes do vencimento, com antecedência configurável por diretor.
- O sistema destacará graficamente o lote quando a reserva estiver próxima do vencimento e quando estiver vencida.

### Lotes e contratos

- O fluxo-base do lote será `Disponível → Reservado → Em contratação → Vendido`.
- Também existirão as situações Bloqueado, Permutado, Uso interno e Indisponível.
- Os estados e suas transições serão fixos; usuários autorizados poderão configurar motivos, etiquetas e categorias auxiliares.
- Um lote será considerado vendido somente quando o contrato estiver assinado e a entrada estiver confirmada.
- O retorno de um lote vendido à comercialização dependerá de autorização de diretor e deverá preservar histórico e auditoria.
- O contrato terá os estados Rascunho, Aguardando assinatura, Aguardando entrada, Ativo, Quitado, Cancelado, Rescindido/Distratado e Renegociado.
- O MVP atenderá vendas à vista e parceladas, sinal/entrada inclusive parcelada, um ou mais compradores pessoa física ou jurídica, cônjuge/companheiro e distrato.
- O modelo de dados será preparado para fiador, cessão e permuta, mas esses fluxos completos e a renegociação avançada ficarão para versões futuras.
- Haverá modelos contratuais versionados para pessoa física e jurídica, com cláusulas ou anexos específicos por loteamento. O Jurídico publicará versões e o Comercial gerará documentos sem alterar cláusulas aprovadas.
- Comercial preparará a negociação; Financeiro validará valores e pagamentos; Jurídico validará contratos; Diretor aprovará operações sensíveis, sem autoaprovação.

### Pagamentos

- O MVP partirá de registro manual de pagamentos.
- Comprovantes deverão ser aceitos.
- Pagamentos, complementos e estornos formarão histórico imutável; correções não deverão sobrescrever o lançamento anterior.
- O MVP aceitará pagamentos parciais, complementos, alocação em várias parcelas e estorno auditado. Baixa bancária, boleto, Pix e integrações ficarão para versões futuras.

### Mapa — versão futura

- O material cartográfico, o mapa interativo e a representação geográfica real não integrarão o MVP por falta de fonte disponível.
- O MVP manterá os cadastros de loteamentos, quadras e lotes sem assumir precisão ou representação cartográfica.
- As interações de mapa, medidas, confrontações e coordenadas reais permanecerão como requisitos de versão futura, condicionados ao fornecimento e à validação do material.

### Implantação e MVP

- A aplicação e o PostgreSQL funcionarão em servidor local pela rede interna, sem banco por dispositivo ou sincronização offline no MVP.
- O sistema continuará disponível sem internet externa enquanto o servidor e a rede local estiverem funcionando.
- O MVP será dimensionado inicialmente para até 200 usuários cadastrados, 50 simultâneos e 20 loteamentos em três anos.
- A operação completa será suportada em computador e tablet; celular terá consultas básicas.
- O recorte do MVP inclui autenticação e permissões; empreendimentos, quadras e lotes; reservas; clientes e corretores; contratos e PDF; parcelas e pagamentos manuais; relatório financeiro básico; migração operacional; auditoria e backups. O mapa foi transferido para versão futura.
- Permanecem para etapa posterior, salvo nova decisão: automação de índices, DataJud, renegociação avançada, notificações multicanal e relatórios analíticos sofisticados.

## Conflitos e lacunas que precisam de decisão

### C1 — Acesso global de administradores e diretores — Resolvido

Foi informado que todo usuário recebe acesso por loteamento e que administradores/diretores acessam todos os módulos. Falta definir se esses dois perfis enxergam automaticamente todos os loteamentos ou apenas os que lhes forem atribuídos.

**Decisão:** Administradores terão acesso global. Diretores terão acesso a todos os módulos, mas somente nos loteamentos atribuídos, salvo concessão explícita de escopo global.

### C2 — Reserva, bloqueio e contratação — Resolvido quanto ao fluxo e às permissões

“Bloqueado” foi associado inicialmente à reserva, embora o fluxo já tenha o estado “Reservado”. É necessário separar:

- reserva comercial com validade;
- bloqueio administrativo/jurídico;
- lote em contratação.

Também falta decidir se a conversão da reserva cria um contrato em rascunho ou apenas inicia uma negociação.

**Decisão:** Reserva, bloqueio administrativo e contratação serão situações distintas. Converter uma reserva criará um contrato em rascunho e colocará o lote em `Em contratação`.

**Decisão complementar:** Somente o perfil Diretor poderá aplicar e retirar bloqueios administrativos. O bloqueio não terá prazo de validade nem desbloqueio automático. O Diretor poderá retirá-lo a qualquer tempo; a retirada exigirá motivo obrigatório e ficará registrada na auditoria.

**Decisão complementar:** Aplicação e retirada exigirão categoria padronizada e justificativa obrigatória. As categorias iniciais serão: restrição judicial/jurídica; irregularidade documental/registral; impedimento técnico/urbanístico/ambiental; divergência financeira/contratual; revisão interna da Diretoria; e Outro. Documentos poderão ser anexados e serão obrigatórios quando houver documento formal que fundamente a restrição.

### C3 — Estados criados por usuários — Resolvido

Permitir estados totalmente novos pode quebrar filtros, automações e regras de disponibilidade. A proposta técnica é manter estados operacionais fixos e permitir motivos, etiquetas ou categorias configuráveis. Essa proposta precisa ser validada.

**Decisão:** Os estados estruturais e suas transições serão fixos. Usuários autorizados poderão configurar motivos, etiquetas e categorias auxiliares, sem criar transições arbitrárias.

### C4 — Entrada, assinatura e liberação do lote — Resolvido

O lote será vendido ao pagar a entrada. Entretanto, foi definido que, sem assinatura ou entrada no prazo, o contrato não é concluído e o lote permanece bloqueado. Faltam prazo, responsável, regra de cancelamento e condição para liberar o lote. Também é necessário decidir o que ocorre quando há entrada paga, mas falta assinatura.

**Decisão:** O prazo de contratação será configurável por loteamento. Sem pagamento, o contrato expirará e o lote será liberado automaticamente. Havendo pagamento sem assinatura, o lote continuará `Em contratação`, indisponível para terceiros, e seguirá para análise manual do Financeiro/Jurídico. A venda somente será efetivada após assinatura e confirmação da entrada.

### C5 — Retorno de lote vendido — Resolvido

A autorização de diretor foi definida, mas o retorno direto para “Disponível” elimina etapas jurídicas e financeiras relevantes.

**Decisão:** Um lote vendido nunca retornará diretamente para `Disponível`. Será obrigatório concluir cancelamento ou distrato, acerto financeiro, anexação dos documentos, aprovação de diretor e registro de auditoria.

### C6 — Segregação de funções em contratos — Resolvido

O perfil Financeiro foi indicado para elaborar, revisar, aprovar e cancelar o mesmo contrato. É necessário confirmar se uma única pessoa poderá executar todas as ações ou se aprovação/cancelamento exigirá outro usuário, especialmente um diretor.

**Decisão:** Comercial prepara a negociação; Financeiro valida valores e registra pagamentos; Jurídico valida contrato e exceções jurídicas; Diretor aprova cancelamentos, distratos, estornos e ajustes sensíveis. O solicitante não poderá aprovar a própria operação sensível.

### C7 — Base local e operação sem internet — Resolvido

“Base de dados local” pode significar um servidor central na empresa ou um banco instalado em cada dispositivo. “Funcionar sem internet” pode significar continuar na rede local sem acesso externo ou operar completamente offline e sincronizar depois. Essas alternativas geram arquiteturas muito diferentes e precisam ser esclarecidas antes da escolha técnica.

**Decisão:** A aplicação e o PostgreSQL serão centralizados em servidor local e acessíveis pela rede interna. O MVP continuará funcionando durante indisponibilidade da internet desde que servidor e rede local estejam disponíveis. Não haverá banco por dispositivo nem sincronização offline no MVP.

### C8 — Mapa geográfico — Adiado para versão futura

Foi solicitado mapa com coordenadas reais, mas a fonte cartográfica ainda é desconhecida. DWG/DXF, PDF, imagem ou GeoJSON exigem processos e níveis de precisão diferentes. O material precisa ser inventariado antes de confirmar a solução.

**Decisão:** Como não há atualmente informação ou material cartográfico disponível, o mapa interativo e a representação geográfica real foram retirados do MVP. Em versão futura, deverão ser priorizados DWG/DXF, GeoJSON ou levantamento oficial; PDF ou imagem não terá validade geométrica sem validação técnica.

## Próximas confirmações mínimas

1. Fornecer uma amostra representativa dos dados atuais para executar a estratégia de migração aprovada.
2. Identificar os titulares e substitutos das áreas de Negócio, Comercial, Financeiro, Jurídico, TI, Dados e Cartografia.
3. Iniciar o questionário de regras financeiras.

## Aprovação da conciliação

- [x] A conciliação representa corretamente as respostas fornecidas.
- [x] As interpretações temporárias estão claramente separadas das decisões.
- [x] As pendências possuem responsáveis por área; datas-alvo serão definidas no planejamento.

**Aprovado por:**  Andrey Bom
**Data:**  20/09/2026 16:01

**Aprovação complementar:** Andrey Bom  
**Data:** 10/10/2026 23:02
