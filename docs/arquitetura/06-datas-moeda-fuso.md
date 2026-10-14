# Datas, horários, moeda e fuso

**Tarefa:** ARC-007
**Status:** Implementado em 12/10/2026

## 1. Decisão regional

| Aspecto | Padrão |
|---|---|
| Localidade de apresentação | `pt-BR` |
| Fuso de negócio | `America/Sao_Paulo` |
| Moeda do MVP | `BRL` |
| Armazenamento de instantes | UTC |
| Data civil na API | ISO 8601 `YYYY-MM-DD` |
| Instante na API | ISO 8601 com `Z` ou offset explícito |
| Valor monetário na API | string decimal canônica, por exemplo `"125000.00"` |

O identificador IANA `America/Sao_Paulo` é usado em vez de um deslocamento fixo para preservar corretamente regras históricas de fuso.

## 2. Tipos por significado

- Data sem horário, como nascimento ou vencimento: Java `LocalDate`, PostgreSQL `DATE` e string `YYYY-MM-DD` na API.
- Momento ocorrido, como criação, pagamento ou auditoria: Java `Instant`, PostgreSQL `TIMESTAMP WITH TIME ZONE` e ISO 8601 com offset na API.
- Horário civil futuro, se surgir no domínio: data, hora e zona devem ser preservadas separadamente; não converter prematuramente em deslocamento fixo.
- Duração: tipo de duração ou quantidade com unidade explícita; nunca uma data fictícia.

O backend persiste e serializa instantes em UTC. A conversão para o fuso de negócio acontece somente para regras baseadas no dia local e para apresentação. O navegador não decide sozinho o fuso: a interface sempre usa `America/Sao_Paulo`, ainda que o dispositivo esteja configurado em outra região.

## 3. Valores monetários

- Backend usa `BigDecimal`; `float` e `double` são proibidos para cálculo ou persistência financeira.
- PostgreSQL usa `NUMERIC(19,2)` para BRL, salvo precisão maior explicitamente exigida por uma regra.
- A API transmite dinheiro como string decimal com ponto e duas casas para evitar perda de precisão no JavaScript.
- O frontend apenas formata o valor canônico; cálculos financeiros definitivos pertencem ao backend.
- Resultados persistidos em BRL são arredondados para duas casas com `HALF_UP` no limite da regra de negócio. Valores intermediários mantêm a precisão necessária.
- Código da moeda acompanha contratos reutilizáveis quando houver possibilidade de outra moeda; no MVP o código aceito é `BRL`.

Exemplo:

```json
{
  "listPrice": "125000.00",
  "currency": "BRL"
}
```

Apresentação: `R$ 125.000,00`. Valores de entrada são convertidos da máscara brasileira para a representação canônica antes de chamar a API.

## 4. Limites e validação

- Um instante sem `Z` ou offset é inválido na API.
- Uma data civil inválida, como `2026-02-30`, é rejeitada sem normalização silenciosa.
- Intervalos de data documentam se os limites são inclusivos; por padrão, `From` e `To` são inclusivos.
- Regras como “vence hoje” calculam o dia corrente em `America/Sao_Paulo`, não em UTC nem no fuso do navegador.
- Logs técnicos mantêm timestamps UTC; telas e relatórios aplicam o fuso de negócio.
- Importações devem identificar o formato de origem e convertê-lo explicitamente; datas ambíguas não são inferidas.

## 5. Configuração e implementação

O backend define:

```yaml
spring:
  jackson:
    time-zone: UTC
  jpa:
    properties:
      hibernate:
        jdbc:
          time_zone: UTC

app:
  regional:
    locale: pt-BR
    time-zone: America/Sao_Paulo
    currency: BRL
```

`RegionalSettingsProperties` valida e converte a configuração. `RegionalPolicy` centraliza a obtenção da data e do horário de negócio a partir de um `Instant`.

No frontend, `regional-format.ts` centraliza constantes e formatação. Não devem existir chamadas dispersas a `toLocaleString()` sem localidade e fuso explícitos.

## 6. Evidências automatizadas

- `RegionalPolicyTest` comprova que `2026-10-13T02:30:00Z` corresponde a `12/10/2026 23:30` em São Paulo.
- `regional-format.spec.ts` cobre data civil, instante com offset, rejeição de valores ambíguos e BRL sem conversão por ponto flutuante.
- O teste de contexto valida que a configuração regional obrigatória é carregada pela aplicação.
