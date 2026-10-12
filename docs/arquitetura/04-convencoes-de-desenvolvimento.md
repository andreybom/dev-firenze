# Convenções de desenvolvimento e revisão

**Tarefa:** ARC-011 — lint, formatação e convenções de commit/revisão

**Status:** Implementado em 12/10/2026

## Verificações locais

### Backend

```powershell
cd backend
mvn spotless:check
mvn test
```

Para corrigir formatação Java automaticamente:

```powershell
mvn spotless:apply
```

### Frontend

```powershell
cd frontend
npm run lint
npm run format:check
npm run test -- --watch=false
npm run build
```

Para corrigir formatação automaticamente:

```powershell
npm run format
```

## Commits

- usar mensagem curta no imperativo, descrevendo uma alteração lógica;
- não misturar refatoração ampla com mudança funcional;
- não incluir artefatos de build, dependências instaladas ou segredos;
- referenciar o ID do backlog quando a relação não estiver evidente;
- manter cada commit compilável e com verificações aplicáveis aprovadas.

Exemplos: `Implementa migração inicial do núcleo` e `Configura lint do frontend`.

## Revisão

Toda revisão deve verificar:

1. aderência ao requisito e às decisões registradas;
2. autorização no backend e isolamento por loteamento quando aplicável;
3. migração versionada para qualquer mudança de esquema;
4. ausência de segredos e dados pessoais em código, testes e logs;
5. testes positivos, negativos e de concorrência proporcionais ao risco;
6. compatibilidade com computador e tablet nas mudanças de interface;
7. atualização da documentação e do controle de execução.

Mudanças financeiras, de segurança, migração ou produção exigem revisão por segunda pessoa conforme o backlog aprovado.
