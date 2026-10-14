import {
  APP_CURRENCY,
  APP_LOCALE,
  APP_TIME_ZONE,
  formatBrl,
  formatBusinessDate,
  formatBusinessInstant,
} from './regional-format';

describe('regional format', () => {
  it('uses the approved locale, time zone and currency', () => {
    expect(APP_LOCALE).toBe('pt-BR');
    expect(APP_TIME_ZONE).toBe('America/Sao_Paulo');
    expect(APP_CURRENCY).toBe('BRL');
  });

  it('formats civil dates without shifting the day', () => {
    expect(formatBusinessDate('2026-10-12')).toBe('12/10/2026');
    expect(() => formatBusinessDate('2026-02-30')).toThrowError();
  });

  it('converts offset instants to America/Sao_Paulo', () => {
    expect(formatBusinessInstant('2026-10-13T02:30:00Z')).toBe('12/10/2026 23:30');
    expect(() => formatBusinessInstant('2026-10-13T02:30:00')).toThrowError();
  });

  it('formats canonical decimal strings without floating point conversion', () => {
    expect(formatBrl('1234567.5')).toBe('R$\u00a01.234.567,50');
    expect(formatBrl('-10')).toBe('-R$\u00a010,00');
    expect(() => formatBrl('10.999')).toThrowError();
  });
});
