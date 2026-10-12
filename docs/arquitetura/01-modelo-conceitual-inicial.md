# Modelo conceitual inicial

**Status:** Aprovado no M1 em 12/10/2026

**Objetivo:** orientar a primeira migração Flyway sem antecipar todos os detalhes das fases seguintes.

## 1. Núcleo organizacional e acesso

| Entidade | Finalidade | Relações principais |
|---|---|---|
| `company` | Empresa proprietária dos loteamentos | possui loteamentos |
| `user_account` | Conta local de acesso | possui perfis e atribuições |
| `role` | Perfil estrutural do sistema | associado a usuários |
| `user_role` | Múltiplos perfis por usuário | usuário + perfil |
| `user_development_access` | Escopo do usuário por loteamento | usuário + loteamento |

Administrador possui escopo global. Diretor recebe loteamentos explicitamente, salvo concessão global registrada.

## 2. Estrutura do loteamento

| Entidade | Finalidade | Relações principais |
|---|---|---|
| `development` | Loteamento/empreendimento | empresa, quadras e configurações |
| `block` | Quadra | loteamento e lotes |
| `lot` | Unidade comercial | quadra, estado, preço e características |
| `lot_status_history` | Histórico de estado | lote, estado anterior/novo e evento de origem |
| `tag` / `lot_tag` | Etiquetas auxiliares | lote e etiqueta configurável |

Os estados estruturais são fixos: Disponível, Reservado, Em contratação, Vendido, Bloqueado, Permutado, Uso interno e Indisponível.

## 3. Pessoas e corretores

| Entidade | Finalidade | Relações principais |
|---|---|---|
| `party` | Identidade comum PF/PJ | especialização PF ou PJ |
| `natural_person` | Dados específicos de pessoa física | party |
| `legal_entity` | Dados específicos de pessoa jurídica | party |
| `party_contact` | E-mail e telefone | party |
| `party_address` | Endereço | party |
| `party_relationship` | Cônjuge, representante e relações futuras | duas parties + tipo |
| `broker` | Corretor vinculado a uma conta | party, user e carteira |

CPF/CNPJ serão normalizados e protegidos por unicidade quando informados.

## 4. Reserva e bloqueio

| Entidade | Finalidade | Relações principais |
|---|---|---|
| `reservation` | Reserva comercial com validade | lote, cliente e corretor |
| `reservation_event` | Criação, prorrogação, conversão, expiração e cancelamento | reserva, autor e motivo |
| `administrative_block` | Bloqueio sem prazo aplicado pelo Diretor | lote, categoria, justificativa e autor |
| `administrative_block_event` | Aplicação e retirada | bloqueio, autor, justificativa e documento |

O banco deverá impedir mais de uma reserva ativa por lote. Bloqueio administrativo é distinto de reserva e contratação.

## 5. Contrato e documentos

| Entidade | Finalidade | Relações principais |
|---|---|---|
| `contract` | Negociação/contrato do lote | lote, modalidade, estado e versão |
| `contract_party` | Compradores, cônjuge e representantes | contrato, party e papel |
| `contract_template` | Identidade do modelo jurídico | versões publicadas |
| `contract_template_version` | Conteúdo imutável do modelo | template, vigência e publicador |
| `stored_document` | Metadados do PDF/comprovante | chave, hash, tipo e proprietário |
| `contract_document` | Documento gerado ou assinado | contrato, template/version e arquivo |
| `contract_event` | Transições e decisões | contrato, autor e motivo |

O contrato preserva a versão usada. A venda exige assinatura e confirmação da entrada.

## 6. Financeiro

| Entidade | Finalidade | Relações principais |
|---|---|---|
| `installment` | Obrigação financeira | contrato, vencimento, valor e situação derivada |
| `financial_entry` | Evento financeiro imutável | contrato, tipo, valor, data e autor |
| `payment_allocation` | Distribuição de pagamento | lançamento + parcela + valor |
| `financial_entry_reversal` | Relação entre original e estorno | dois lançamentos e aprovação |

Saldo e situação serão calculados a partir dos eventos e alocações; lançamentos confirmados não serão sobrescritos.

## 7. Auditoria e notificações

| Entidade | Finalidade | Relações principais |
|---|---|---|
| `audit_event` | Trilha imutável de operação sensível | autor, entidade, ação, antes/depois e correlação |
| `email_outbox` | E-mail persistido antes do envio | destinatários, template, tentativas e situação |

## 8. Regras transversais

- UUID como chave interna.
- Datas de criação e alteração em UTC.
- Versão otimista em agregados mutáveis.
- Motivos obrigatórios persistidos com o evento correspondente.
- Exclusão lógica somente quando necessária; histórico financeiro e auditoria não são excluídos.
- Integridade crítica reforçada por restrições e índices do PostgreSQL.
- Documentos armazenam hash e metadados; o banco não armazena caminhos fornecidos pelo usuário.

## 9. Ordem proposta das migrações

1. extensões e tipos auxiliares estritamente necessários;
2. empresa, loteamento, quadra e lote;
3. contas, perfis e escopos;
4. pessoas, contatos e corretores;
5. reservas e bloqueios;
6. contratos, modelos e documentos;
7. parcelas e eventos financeiros;
8. auditoria e outbox.

A primeira migração está autorizada e conterá apenas `company`, `development`, `development_block` e `lot`. Os demais componentes serão introduzidos incrementalmente pelas tarefas do backlog.
