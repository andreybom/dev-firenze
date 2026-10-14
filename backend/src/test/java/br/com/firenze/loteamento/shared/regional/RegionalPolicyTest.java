package br.com.firenze.loteamento.shared.regional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Currency;
import java.util.Locale;
import org.junit.jupiter.api.Test;

class RegionalPolicyTest {

  @Test
  void convertsUtcInstantToSaoPauloBusinessTime() {
    var settings = new RegionalSettingsProperties("pt-BR", "America/Sao_Paulo", "BRL");
    var policy = new RegionalPolicy(settings);

    var businessTime = policy.atBusinessTime(Instant.parse("2026-10-13T02:30:00Z"));

    assertEquals(LocalDate.of(2026, 10, 12), businessTime.toLocalDate());
    assertEquals(LocalTime.of(23, 30), businessTime.toLocalTime());
    assertEquals(ZoneId.of("America/Sao_Paulo"), policy.zoneId());
    assertEquals(Locale.forLanguageTag("pt-BR"), policy.locale());
    assertEquals(Currency.getInstance("BRL"), policy.currency());
  }
}
