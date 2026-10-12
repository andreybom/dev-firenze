# Inventário inicial do projeto

**Data:** 12/10/2026  
**Tarefa:** ARC-001 — Inventariar código, dependências, builds e configurações atuais  
**Escopo da inspeção:** inventário inicial mais verificações de compilação e configuração executadas em 12/10/2026; testes automatizados não foram executados.

## Estado encontrado

### Backend

- Projeto Maven em `backend/pom.xml`, com Spring Boot 4.1.0, Java 25 e artefato `com.suaempresa:loteamento:0.0.1-SNAPSHOT`.
- Dependências atuais: Spring Web, Spring Data JPA, validação, driver PostgreSQL, DevTools e starter de testes.
- Código-fonte inicial contém a aplicação Spring Boot, configuração CORS e endpoint de saúde; não há módulos de domínio implementados.
- `backend/src/main/resources/application.yml` aponta para PostgreSQL local (`loteamento`) e contém usuário/senha padrão no arquivo, `hibernate.ddl-auto: update` e log SQL habilitado.
- Não foi identificada ferramenta de migração de esquema versionada nas dependências atuais.

### Frontend

- Aplicação Angular standalone em `frontend`, com Angular 22, TypeScript e RxJS; scripts declarados para servir, compilar e testar via Angular CLI/Karma.
- Código-fonte inicial contém o componente raiz, rotas e configuração da aplicação; não há funcionalidades de domínio implementadas.
- A configuração de desenvolvimento aponta para a API local em `http://localhost:8080/api`.
- Não foi encontrado arquivo de lock de dependências na listagem da pasta `frontend`.

### Ambiente e repositório

- Há diretórios `backend/target` e `frontend/public`; a inspeção não determina quando os artefatos em `target` foram gerados.
- O comando `git status` não encontrou metadados Git em `D:\dev-firenze`; origem, branch e estado versionado do checkout precisam ser confirmados no kickoff.
- Ambiente local encontrado: Eclipse Temurin Java 25.0.4.1, Node.js 24.15.0 e npm 12.1.0.
- Maven 3.10.0 foi confirmado em `D:\apache-maven-3.10.0` ao definir `MAVEN_HOME` e `JAVA_HOME` no processo de verificação. Sem `JAVA_HOME` explícito, Maven selecionou Java 21; com o JDK 25.0.4.1 indicado pelo projeto, informou corretamente Java 25.0.4.1. O ambiente desta sessão ainda não localiza `mvn` pelo `PATH`.
- O cliente `psql` foi confirmado em `C:\Program Files\pgAdmin 4\runtime\psql.exe`, versão 18.1.
- O cliente `psql` encontrado é 18.1, mas isso, por si só, não identifica a versão do servidor. As observações anteriores de um diretório de dados 17 e de uma configuração Compose 15 são históricas e foram superadas pela DEC-036: a versão-alvo aprovada é PostgreSQL Server 18.1. A sessão não conseguiu consultar `server_version` por bloqueio de rede local.
- Não há Maven Wrapper no projeto.

## Validação parcial de compatibilidade (ARC-002)

| Componente | Projeto/ambiente | Resultado inicial |
|---|---|---|
| Java/Spring Boot | Java 25.0.4.1 local; Spring Boot 4.1.0 declarado | Compatível em princípio: a documentação oficial do Spring Boot 4.1.1 requer Java 17 e aceita até Java 26. A versão do projeto é 4.1.0, cuja página exata de requisitos não ficou acessível nesta consulta; validar versão/minor no fechamento da decisão. |
| Angular/Node.js | Angular 22; Node.js 24.15.0 local | Compatível: a tabela oficial do Angular 22 aceita Node `^24.15.0`. |
| Angular/TypeScript | Angular 22; TypeScript `~6.0.0`; Node.js 24.15.0 | Compatibilidade confirmada pela compilação de produção Angular. Lockfile criado e dependências instaladas. |
| RxJS | `~7.8.0` declarado | Dentro da faixa aceita pelo Angular 22 (`^6.5.3` ou `^7.4.0`). |
| Maven/PostgreSQL | Maven 3.10.0 confirmado com `JAVA_HOME` explícito em 25.0.4.1; cliente `psql` 18.1 localizado; alvo PostgreSQL Server 18.1 aprovado pela DEC-036 | Preparar o servidor 18.1 e validar conexão/versão em ambiente com acesso local; manter Maven/JDK 25 disponíveis no ambiente normal. Spring Boot 4.1.1 requer Maven 3.6.3 ou posterior. |

Referências oficiais consultadas em 12/10/2026: [requisitos do Spring Boot](https://docs.spring.io/spring-boot/system-requirements.html), [compatibilidade de versões do Angular](https://angular.dev/reference/versions), [releases do Node.js](https://nodejs.org/en/about/previous-releases) e [política de versões do PostgreSQL](https://www.postgresql.org/support/versioning/).

**Manutenção do banco:** a DEC-036 fixa PostgreSQL 18.1 conforme solicitado. Na consulta de 12/10/2026, a minor corrente era 18.6; o projeto recomenda usar a minor corrente, e a 18.6 contém correções de segurança e bugs. Reavaliar o patch antes de disponibilizar ambientes compartilhados ou produção, preservando a major 18 escolhida.

## Estado após o inventário (12/10/2026)

- Backend: credenciais saíram do YAML; URL e CORS são obrigatórios fora do perfil `local`; SQL de diagnóstico fica desligado por padrão; Hibernate usa `ddl-auto: validate` e `open-in-view: false`.
- Migrações: Flyway e o módulo PostgreSQL foram adicionados, com `clean` desabilitado. O histórico de migrações será criado em `backend/src/main/resources/db/migration`; a primeira migração de domínio aguarda o modelo aprovado no M1.
- Backend: `mvn compile` concluiu com sucesso usando Maven 3.10.0 e JDK 25. O empacotamento não foi confirmado: o Maven não conseguiu resolver componentes do Surefire por erro de certificado TLS. Nenhum teste foi executado.
- Frontend: lockfile criado; Angular usa os builders oficiais `@angular/build` e Vitest, com TypeScript 6.0.3; `npm ci --ignore-scripts --no-audit` e `npm run build` concluíram. A compilação gerou o bundle de produção.
- Segurança de dependências: a primeira versão do lockfile reportou 11 vulnerabilidades altas na cadeia Webpack/Karma. Após a migração, `npm audit --audit-level=high` reportou zero vulnerabilidades. Repetir a auditoria no CI (ARC-012).
- Testes: não há arquivos `*.spec.ts` no frontend atualmente; nenhum teste foi executado.
- Ambiente: o cliente psql 18.1 está disponível, mas a sessão ainda não conseguiu confirmar a versão do servidor PostgreSQL em execução.

## Ações seguintes

1. Concluir ARC-002: deixar Maven/JDK 25 disponíveis no ambiente normal e validar conexão ao PostgreSQL Server 18.1.
2. No kickoff técnico, confirmar acesso ao repositório remoto, branch de trabalho, equipe e ambientes.
3. Aprovar arquitetura e modelo inicial no M1; em seguida criar a primeira migração de domínio e validar Flyway no PostgreSQL 18.1.
4. Configurar lint/formatação e revisão automática em ARC-011/012; repetir auditoria npm no CI.

## Evidência e limites

Este inventário combina leitura de estrutura, manifestos e configurações, consultas à documentação oficial e compilações explicitamente listadas acima. A compilação não valida integração com PostgreSQL; os testes automatizados não foram executados.
