package br.com.firenze.loteamento.shared.regional;

import jakarta.validation.constraints.NotBlank;
import java.time.ZoneId;
import java.util.Currency;
import java.util.Locale;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("app.regional")
public record RegionalSettingsProperties(
    @NotBlank String locale, @NotBlank String timeZone, @NotBlank String currency) {

  public Locale localeValue() {
    Locale value = Locale.forLanguageTag(locale);
    if (value.getLanguage().isBlank()) {
      throw new IllegalArgumentException("Invalid locale: " + locale);
    }
    return value;
  }

  public ZoneId zoneId() {
    return ZoneId.of(timeZone);
  }

  public Currency currencyValue() {
    return Currency.getInstance(currency);
  }
}
