# Configuração de ambientes

**Tarefa:** ARC-010 — externalizar configurações e segredos por ambiente

**Status:** Implementado em 12/10/2026

## Perfis

| Ambiente | Perfil Spring | Banco | CORS | Observação |
|---|---|---|---|---|
| Desenvolvimento local | `local` | Instância/banco exclusivo do desenvolvedor | `http://localhost:4200` por padrão | URL e CORS possuem padrão; usuário e senha continuam obrigatórios |
| Testes automatizados rápidos | `test` | H2 efêmero | Origem fixa de teste | Configuração existe somente em `src/test` |
| Homologação | `homologation` | Banco exclusivo de homologação | Origem HTTPS de homologação | Todas as variáveis sensíveis são obrigatórias |
| Produção | `production` | Banco exclusivo de produção | Origem HTTPS de produção | Configuração externa, logs SQL desligados e encerramento gracioso |

Não é permitido compartilhar banco, usuário de banco, senha, arquivo de documentos ou origem CORS entre homologação e produção.

## Variáveis

| Variável | Obrigatória | Sensível | Finalidade |
|---|---|---|---|
| `SPRING_PROFILES_ACTIVE` | Sim fora de testes | Não | Seleciona `local`, `homologation` ou `production` |
| `DB_URL` | Sim fora do perfil local | Não | URL JDBC do banco exclusivo do ambiente |
| `DB_USERNAME` | Sim | Não | Usuário de aplicação com privilégios mínimos |
| `DB_PASSWORD` | Sim | Sim | Senha do usuário de banco |
| `APP_CORS_ALLOWED_ORIGINS` | Sim fora do perfil local | Não | Lista de origens autorizadas |
| `SERVER_PORT` | Não | Não | Porta HTTP interna; padrão `8080` |
| `DB_SHOW_SQL` | Não | Não | Diagnóstico local; manter `false` em ambientes compartilhados |
| `DB_FORMAT_SQL` | Não | Não | Formatação do SQL de diagnóstico |

O arquivo `.env.example` contém somente valores de exemplo. Arquivos `.env` reais, credenciais, tokens, chaves privadas e certificados permanecem fora do Git.

## Regras operacionais

1. injetar segredos pelo mecanismo do servidor ou da plataforma de implantação;
2. conceder ao usuário da aplicação apenas os privilégios necessários no seu banco;
3. restringir acesso aos valores de produção à equipe autorizada;
4. trocar credenciais sem recompilar a aplicação;
5. não registrar senhas, tokens, documentos pessoais ou strings completas de conexão em logs;
6. validar as variáveis obrigatórias durante a inicialização e falhar de forma explícita quando estiverem ausentes.

O provisionamento dos ambientes e dos usuários de banco pertence a OPS-002 e OPS-003. ARC-010 cobre a separação lógica e o contrato de configuração da aplicação.
