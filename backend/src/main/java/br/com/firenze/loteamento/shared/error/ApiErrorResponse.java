package br.com.firenze.loteamento.shared.error;

import java.util.List;

public record ApiErrorResponse(
    String type,
    String title,
    int status,
    String detail,
    String instance,
    String correlationId,
    List<ApiFieldError> errors) {

  public ApiErrorResponse {
    errors = List.copyOf(errors);
  }
}
