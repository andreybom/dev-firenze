export const APP_LOCALE = 'pt-BR';
export const APP_TIME_ZONE = 'America/Sao_Paulo';
export const APP_CURRENCY = 'BRL';

const CIVIL_DATE_PATTERN = /^(\d{4})-(\d{2})-(\d{2})$/;
const DECIMAL_MONEY_PATTERN = /^(-?)(\d+)(?:\.(\d{1,2}))?$/;
const OFFSET_PATTERN = /(Z|[+-]\d{2}:\d{2})$/i;

export function formatBusinessDate(value: string): string {
  const match = CIVIL_DATE_PATTERN.exec(value);
  if (match === null || !isValidCivilDate(Number(match[1]), Number(match[2]), Number(match[3]))) {
    throw new Error(`Invalid civil date: ${value}`);
  }
  return `${match[3]}/${match[2]}/${match[1]}`;
}

export function formatBusinessInstant(value: string): string {
  if (!OFFSET_PATTERN.test(value)) {
    throw new Error(`Instant must include an offset: ${value}`);
  }

  const instant = new Date(value);
  if (Number.isNaN(instant.getTime())) {
    throw new Error(`Invalid instant: ${value}`);
  }

  const parts = new Intl.DateTimeFormat(APP_LOCALE, {
    timeZone: APP_TIME_ZONE,
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit',
    hourCycle: 'h23',
  }).formatToParts(instant);
  const part = (type: Intl.DateTimeFormatPartTypes): string =>
    parts.find((candidate) => candidate.type === type)?.value ?? '';

  return `${part('day')}/${part('month')}/${part('year')} ${part('hour')}:${part('minute')}`;
}

export function formatBrl(value: string): string {
  const match = DECIMAL_MONEY_PATTERN.exec(value);
  if (match === null) {
    throw new Error(`Invalid monetary value: ${value}`);
  }

  const integer = (match[2].replace(/^0+(?=\d)/, '') || '0').replace(/\B(?=(\d{3})+(?!\d))/g, '.');
  const fraction = (match[3] ?? '').padEnd(2, '0');
  const sign = match[1] === '-' ? '-' : '';
  return `${sign}R$\u00a0${integer},${fraction}`;
}

function isValidCivilDate(year: number, month: number, day: number): boolean {
  const parsed = new Date(Date.UTC(year, month - 1, day));
  return (
    parsed.getUTCFullYear() === year &&
    parsed.getUTCMonth() === month - 1 &&
    parsed.getUTCDate() === day
  );
}
