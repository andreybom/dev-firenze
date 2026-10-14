package br.com.firenze.loteamento.shared.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

class PageResponseTest {

  @Test
  void mapsSpringPageToStableApiContract() {
    var source = new PageImpl<>(List.of("L-03", "L-04"), PageRequest.of(1, 2), 5);

    var response = PageResponse.from(source);

    assertEquals(List.of("L-03", "L-04"), response.items());
    assertEquals(new PageMetadata(1, 2, 5, 3), response.page());
    assertThrows(UnsupportedOperationException.class, () -> response.items().add("L-05"));
  }
}
