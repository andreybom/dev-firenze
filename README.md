# Loteamento

Estrutura inicial do projeto, com backend em Java 25 + Spring Boot 4.1 e frontend em Angular 22.

## Backend (`/backend`)

Requisitos: JDK 25 e Maven.

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

A API sobe em `http://localhost:8080`. Endpoint de teste: `GET /api/health`.

O backend exige `DB_USERNAME` e `DB_PASSWORD`; em produção, informe também `DB_URL` e `APP_CORS_ALLOWED_ORIGINS`. O perfil `local` fornece apenas os padrões de URL e CORS para desenvolvimento. O esquema está configurado para validação pelo Hibernate; as alterações serão aplicadas por migrações Flyway em `src/main/resources/db/migration`. `flyway clean` fica desabilitado.

## Frontend (`/frontend`)

Requisitos: Node.js LTS mais recente e Angular CLI 22.

```bash
cd frontend
npm ci
npm start
```

A aplicação sobe em `http://localhost:4200` e já está configurada para chamar a API em `http://localhost:8080/api` (ver `src/environments/environment.ts`).

## Próximos passos sugeridos

- Ajustar `groupId`/pacote Java conforme a organização real do projeto.
- Preparar o ambiente com PostgreSQL Server 18.1, versão aprovada para o MVP (o `pom.xml` já traz o driver PostgreSQL).
- Criar as primeiras entidades/telas do domínio de loteamento.

## Planejamento

- [Fase 0 — descoberta e decisões](docs/fase-0/README.md)
- [Plano detalhado de implementação e implantação](docs/plano-implantacao/README.md)

O plano foi aprovado e a implementação foi autorizada em 12/10/2026. Acompanhe o andamento no [controle de execução](docs/plano-implantacao/06-controle-de-execucao.md) e consulte o [inventário inicial](docs/plano-implantacao/07-inventario-inicial.md).
