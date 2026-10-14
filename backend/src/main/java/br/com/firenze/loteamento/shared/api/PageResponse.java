package br.com.firenze.loteamento.shared.api;

import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.Page;

public record PageResponse<T>(List<T> items, PageMetadata page) {

  public PageResponse {
    items = List.copyOf(Objects.requireNonNull(items, "Items must not be null"));
    Objects.requireNonNull(page, "Page metadata must not be null");
  }

  public static <T> PageResponse<T> from(Page<T> source) {
    Objects.requireNonNull(source, "Source page must not be null");
    var metadata =
        new PageMetadata(
            source.getNumber(),
            source.getSize(),
            source.getTotalElements(),
            source.getTotalPages());
    return new PageResponse<>(source.getContent(), metadata);
  }
}
