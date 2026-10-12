# Inventário inicial do projeto

**Data:** 12/10/2026  
**Tarefa:** ARC-001 — Inventariar código, dependências, builds e configurações atuais  
**Escopo da inspeção:** inventário inicial mais verificações de compilação, testes e configuração executadas em 12/10/2026.

## Estado encontrado

### Backend

- Projeto Maven em `backend/pom.xml`, com Spring Boot 4.1.0, Java 25 e artefato `br.com.firenze:loteamento:0.0.1-SNAPSHOT`.
- Dependências atuais: Spring Web, Spring Data JPA, validação, driver PostgreSQL, DevTools e starter de testes.
- Código-fonte inicial contém a aplicação Spring Boot, configuração CORS e endpoint de saúde; não há módulos de domínio implementados.
- `backend/src/main/resources/application.yml` aponta para PostgreSQL local (`loteamento`) e contém usuário/senha padrão no arquivo, `hibernate.ddl-auto: update` e log SQL habilitado.
- Não foi identificada ferramenta de migração de esquema versionada nas dependências atuais.

### Frontend

- Aplicação Angular standalone em `frontend`, com Angular 22, TypeScript e RxJS; scripts declarados para servir, compilar e testar via Angular CLI/Vitest.
- Código-fonte inicial contém o componente raiz, rotas e configuração da aplicação; não há funcionalidades de domínio implementadas.
- A configuração de desenvolvimento aponta para a API local em `http://localhost:8080/api`.
- O lockfile está presente e permite instalação reproduzível com `npm ci`.

### Ambiente e repositório

- Há diretórios `backend/target` e `frontend/public`; a inspeção não determina quando os artefatos em `target` foram gerados.
- O repositório Git foi regularizado, usa a branch `main`, acompanha `origin/main` e teve seu commit inicial publicado em `https://github.com/andreybom/dev-firenze.git`.
- Ambiente local encontrado: Eclipse Temurin Java 25.0.4.1, Node.js 24.15.0 e npm 12.1.0.
- Maven 3.10.0 foi confirmado em `D:\apache-maven-3.10.0` ao definir `MAVEN_HOME` e `JAVA_HOME` no processo de verificação. Sem `JAVA_HOME` explícito, Maven selecionou Java 21; com o JDK 25.0.4.1 indicado pelo projeto, informou corretamente Java 25.0.4.1. O ambiente desta sessão ainda não localiza `mvn` pelo `PATH`.
- O cliente `psql` foi confirmado em `C:\Program Files\pgAdmin 4\runtime\psql.exe`, versão 18.1.
- O cliente `psql` encontrado é 18.1. Em 12/10/2026, a consulta ao servidor local na porta 5432 confirmou PostgreSQL 18.6 em ambiente Docker/WSL. A DEC-040 define a major 18 e permite atualização corretiva `18.x`; portanto, o servidor validado atende à decisão.
- Não há Maven Wrapper no projeto.

## Validação de compatibilidade (ARC-002)

| Componente | Projeto/ambiente | Resultado inicial |
|---|---|---|
| Java/Spring Boot | Java 25.0.4.1 local; Spring Boot 4.1.0 declarado | Compatível em princípio: a documentação oficial do Spring Boot 4.1.1 requer Java 17 e aceita até Java 26. A versão do projeto é 4.1.0, cuja página exata de requisitos não ficou acessível nesta consulta; validar versão/minor no fechamento da decisão. |
| Angular/Node.js | Angular 22; Node.js 24.15.0 local | Compatível: a tabela oficial do Angular 22 aceita Node `^24.15.0`. |
| Angular/TypeScript | Angular 22; TypeScript `~6.0.0`; Node.js 24.15.0 | Compatibilidade confirmada pela compilação de produção Angular. Lockfile criado e dependências instaladas. |
| RxJS | `~7.8.0` declarado | Dentro da faixa aceita pelo Angular 22 (`^6.5.3` ou `^7.4.0`). |
| Maven/PostgreSQL | Maven 3.10.0 confirmado com `JAVA_HOME` explícito em 25.0.4.1; cliente `psql` 18.1; servidor local PostgreSQL 18.6; major 18 aprovada na DEC-040 | Compatibilidade confirmada: migração e aplicação validadas no PostgreSQL 18.6. Manter Maven/JDK 25 disponíveis no ambiente normal. Spring Boot 4.1.1 requer Maven 3.6.3 ou posterior. |

Referências oficiais consultadas em 12/10/2026: [requisitos do Spring Boot](https://docs.spring.io/spring-boot/system-requirements.html), [compatibilidade de versões do Angular](https://angular.dev/reference/versions), [releases do Node.js](https://nodejs.org/en/about/previous-releases) e [política de versões do PostgreSQL](https://www.postgresql.org/support/versioning/).

**Manutenção do banco:** a DEC-040 fixa a major PostgreSQL 18 e permite variar a atualização corretiva `18.x`. O patch deve ser aprovado na preparação de cada ambiente, priorizando correções de segurança e bugs sem trocar a major sem nova decisão.

## Estado após o inventário (12/10/2026)

- Backend: credenciais saíram do YAML; URL e CORS são obrigatórios fora do perfil `local`; SQL de diagnóstico fica desligado por padrão; Hibernate usa `ddl-auto: validate` e `open-in-view: false`.
- Migrações: Flyway e o módulo PostgreSQL foram adicionados, com `clean` desabilitado. Após a aprovação do M1, foi criada `V1__create_structural_core.sql` com empresa, loteamento, quadra e lote. O Spring Boot 4 exige o starter `spring-boot-starter-flyway` para ativar a auto-configuração; a dependência direta do `flyway-core` foi substituída.
- Backend: identidade migrada de `com.suaempresa` para `br.com.firenze`; o endpoint de saúde está versionado em `/api/v1/health`.
- Backend: `mvn clean test` e `mvn package` concluíram com Maven 3.10.0 e JDK 25. No Windows, foi necessário usar o repositório de certificados do sistema por meio de `MAVEN_OPTS=-Djavax.net.ssl.trustStoreType=Windows-ROOT`; a verificação TLS permaneceu ativa.
- Testes de backend: três testes foram aprovados com perfil `test` e H2 em modo de compatibilidade PostgreSQL: inicialização do contexto, aplicação da migração V1 em banco vazio e validação de hierarquia/estado fixo do lote.
- PostgreSQL: os mesmos três testes passaram em banco temporário isolado no servidor 18.6; a base foi removida ao final. Esta é a evidência de compatibilidade real da V1, enquanto H2 permanece apenas como teste rápido.
- Frontend: lockfile criado; Angular usa os builders oficiais `@angular/build` e Vitest, com TypeScript 6.0.3; `npm ci --ignore-scripts --no-audit`, o teste automatizado e `npm run build` concluíram. A compilação gerou o bundle de produção em `frontend/dist/loteamento-frontend`.
- Segurança de dependências: a primeira versão do lockfile reportou 11 vulnerabilidades altas na cadeia Webpack/Karma. Após a migração, `npm audit --audit-level=high` reportou zero vulnerabilidades. Repetir a auditoria no CI (ARC-012).
- Testes de frontend: um teste mínimo do componente raiz foi aprovado no Vitest.
- Ambiente: cliente psql 18.1 e servidor PostgreSQL 18.6 estão acessíveis localmente; nenhuma base permanente do projeto foi criada nesta validação.

## Ações seguintes

1. Manter Maven/JDK 25 disponíveis no ambiente normal e registrar o patch PostgreSQL 18.x efetivamente instalado em cada ambiente.
2. Confirmar equipe e ambientes; o acesso ao repositório remoto e a branch `main` já foram validados.
3. Iniciar a próxima etapa da fundação e dos cadastros a partir da V1 já validada em H2 e PostgreSQL 18.6.
4. Configurar lint/formatação e revisão automática em ARC-011/012; repetir builds, testes e auditoria npm no CI.

## Evidência e limites

Este inventário combina leitura de estrutura, manifestos e configurações, consultas à documentação oficial, compilações e testes explicitamente listados acima. A integração foi validada no PostgreSQL 18.6 local, compatível com a major 18 aprovada na DEC-040.
