export type ApiErrorType =
  | 'validation_error'
  | 'invalid_request'
  | 'resource_not_found'
  | 'business_conflict'
  | 'authentication_required'
  | 'access_denied'
  | 'method_not_allowed'
  | 'unsupported_media_type'
  | 'internal_error';

export interface ApiFieldError {
  readonly field: string;
  readonly code: string;
  readonly message: string;
}

export interface ApiError {
  readonly type: ApiErrorType | string;
  readonly title: string;
  readonly status: number;
  readonly detail: string;
  readonly instance: string;
  readonly correlationId: string;
  readonly errors: readonly ApiFieldError[];
}

export function isApiError(value: unknown): value is ApiError {
  if (!isRecord(value)) {
    return false;
  }
  return (
    typeof value['type'] === 'string' &&
    typeof value['title'] === 'string' &&
    typeof value['status'] === 'number' &&
    typeof value['detail'] === 'string' &&
    typeof value['instance'] === 'string' &&
    typeof value['correlationId'] === 'string' &&
    Array.isArray(value['errors']) &&
    value['errors'].every(isApiFieldError)
  );
}

export function userMessage(value: unknown): string {
  return isApiError(value)
    ? value.detail
    : 'Não foi possível concluir a operação. Tente novamente.';
}

export function fieldErrors(value: unknown): ReadonlyMap<string, readonly ApiFieldError[]> {
  if (!isApiError(value)) {
    return new Map();
  }

  const grouped = new Map<string, ApiFieldError[]>();
  for (const error of value.errors) {
    const current = grouped.get(error.field) ?? [];
    current.push(error);
    grouped.set(error.field, current);
  }
  return grouped;
}

function isApiFieldError(value: unknown): value is ApiFieldError {
  return (
    isRecord(value) &&
    typeof value['field'] === 'string' &&
    typeof value['code'] === 'string' &&
    typeof value['message'] === 'string'
  );
}

function isRecord(value: unknown): value is Record<string, unknown> {
  return typeof value === 'object' && value !== null;
}
