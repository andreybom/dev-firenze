package br.com.firenze.loteamento.shared.regional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.Locale;
import org.springframework.stereotype.Component;

@Component
public class RegionalPolicy {

  private final Locale locale;
  private final ZoneId zoneId;
  private final Currency currency;

  public RegionalPolicy(RegionalSettingsProperties settings) {
    this.locale = settings.localeValue();
    this.zoneId = settings.zoneId();
    this.currency = settings.currencyValue();
  }

  public ZonedDateTime atBusinessTime(Instant instant) {
    return instant.atZone(zoneId);
  }

  public LocalDate businessDate(Instant instant) {
    return atBusinessTime(instant).toLocalDate();
  }

  public Locale locale() {
    return locale;
  }

  public ZoneId zoneId() {
    return zoneId;
  }

  public Currency currency() {
    return currency;
  }
}
