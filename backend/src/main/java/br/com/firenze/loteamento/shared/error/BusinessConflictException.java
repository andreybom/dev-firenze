package br.com.firenze.loteamento.shared.error;

public class BusinessConflictException extends RuntimeException {

  public BusinessConflictException(String userMessage) {
    super(userMessage);
  }
}
