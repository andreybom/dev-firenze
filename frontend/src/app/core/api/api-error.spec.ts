import { fieldErrors, isApiError, userMessage } from './api-error';

const validationError = {
  type: 'validation_error',
  title: 'Dados inválidos',
  status: 400,
  detail: 'Revise os campos informados.',
  instance: '/api/v1/lots',
  correlationId: 'request-12345678',
  errors: [
    { field: 'code', code: 'required', message: 'Código é obrigatório' },
    { field: 'code', code: 'invalid_size', message: 'Código muito longo' },
  ],
};

describe('API error contract', () => {
  it('recognizes the backend contract and exposes its safe message', () => {
    expect(isApiError(validationError)).toBe(true);
    expect(userMessage(validationError)).toBe('Revise os campos informados.');
  });

  it('groups field errors for reactive forms', () => {
    const grouped = fieldErrors(validationError);

    expect(grouped.get('code')).toHaveLength(2);
    expect(grouped.get('code')?.[0].code).toBe('required');
  });

  it('uses a safe fallback for network or non-contract failures', () => {
    expect(isApiError({ status: 500 })).toBe(false);
    expect(userMessage({ status: 500 })).toBe(
      'Não foi possível concluir a operação. Tente novamente.',
    );
    expect(fieldErrors(null).size).toBe(0);
  });
});
