package br.com.firenze.loteamento.shared.api;

public record PageMetadata(int number, int size, long totalElements, int totalPages) {

  public PageMetadata {
    if (number < 0) {
      throw new IllegalArgumentException("Page number must not be negative");
    }
    if (size < 1) {
      throw new IllegalArgumentException("Page size must be positive");
    }
    if (totalElements < 0 || totalPages < 0) {
      throw new IllegalArgumentException("Page totals must not be negative");
    }
  }
}
