package br.com.firenze.loteamento.shared.error;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String userMessage) {
    super(userMessage);
  }
}
