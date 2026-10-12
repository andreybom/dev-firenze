# Loteamento

Estrutura inicial do projeto, com backend em Java 25 + Spring Boot 4.1 e frontend em Angular 22.

## Backend (`/backend`)

Requisitos: JDK 25 e Maven.

Em Windows, caso o Maven não reconheça a cadeia de certificados do sistema, mantenha a validação TLS ativa usando o trust store nativo antes de executar os comandos:

```powershell
$env:MAVEN_OPTS = '-Djavax.net.ssl.trustStoreType=Windows-ROOT'
```

Defina as variáveis de conexão antes de iniciar. Mantenha a senha apenas no ambiente local e crie previamente um usuário de aplicação com privilégios mínimos no PostgreSQL:

```powershell
$env:DB_URL = 'jdbc:postgresql://localhost:5432/loteamento'
$env:DB_USERNAME = 'loteamento_app'
$env:DB_PASSWORD = '<defina localmente>'
$env:APP_CORS_ALLOWED_ORIGINS = 'http://localhost:4200'
$env:SPRING_PROFILES_ACTIVE = 'local'
```

```powershell
cd backend
mvn spring-boot:run
```

A API sobe em `http://localhost:8080`. Endpoint de teste: `GET /api/v1/health`.

Para validar a linha de base do backend sem depender de um PostgreSQL local:

```powershell
cd backend
mvn clean test
mvn package
```

O backend exige `DB_USERNAME` e `DB_PASSWORD`; em produção, informe também `DB_URL` e `APP_CORS_ALLOWED_ORIGINS`. O perfil `local` fornece apenas os padrões de URL e CORS para desenvolvimento. O esquema está configurado para validação pelo Hibernate; as alterações serão aplicadas por migrações Flyway em `src/main/resources/db/migration`. `flyway clean` fica desabilitado.

A migração `V1__create_structural_core.sql` cria o núcleo inicial de empresa, loteamento, quadra e lote. Os códigos internos dos estados de lote são `AVAILABLE`, `RESERVED`, `UNDER_CONTRACT`, `SOLD`, `BLOCKED`, `EXCHANGED`, `INTERNAL_USE` e `UNAVAILABLE`; a interface apresentará seus nomes em português.

## Frontend (`/frontend`)

Requisitos: Node.js LTS mais recente e Angular CLI 22.

```bash
cd frontend
npm ci
npm start
```

A aplicação sobe em `http://localhost:4200` e já está configurada para chamar a API em `http://localhost:8080/api` (ver `src/environments/environment.ts`).

```bash
npm run test -- --watch=false
npm run build
```

## Próximos passos sugeridos

- Preparar os ambientes com PostgreSQL major 18 e uma atualização corretiva `18.x` aprovada; a versão 18.6 já foi validada localmente.
- Criar as primeiras entidades/telas do domínio de loteamento.

## Planejamento

- [Fase 0 — descoberta e decisões](docs/fase-0/README.md)
- [Plano detalhado de implementação e implantação](docs/plano-implantacao/README.md)
- [Arquitetura aprovada no M1](docs/arquitetura/README.md)

O plano foi aprovado e a implementação foi autorizada em 12/10/2026. Acompanhe o andamento no [controle de execução](docs/plano-implantacao/06-controle-de-execucao.md) e consulte o [inventário inicial](docs/plano-implantacao/07-inventario-inicial.md).

As configurações por ambiente estão em [configuração de ambientes](docs/arquitetura/03-configuracao-de-ambientes.md), e os comandos obrigatórios de lint, formatação e revisão estão em [convenções de desenvolvimento](docs/arquitetura/04-convencoes-de-desenvolvimento.md).
