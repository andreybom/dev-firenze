package br.com.firenze.loteamento.shared.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

class PageRequestsTest {

  private static final Set<String> ALLOWED_SORTS = Set.of("code", "status", "createdAt");

  @Test
  void usesDefaultsAndNoSortWhenParametersAreAbsent() {
    var pageRequest = PageRequests.create(null, null, null, ALLOWED_SORTS);

    assertEquals(0, pageRequest.getPageNumber());
    assertEquals(20, pageRequest.getPageSize());
    assertEquals(Sort.unsorted(), pageRequest.getSort());
  }

  @Test
  void acceptsRepeatedSortsInDeclaredOrder() {
    var pageRequest = PageRequests.create(2, 50, List.of("status,desc", "code,asc"), ALLOWED_SORTS);

    assertEquals(2, pageRequest.getPageNumber());
    assertEquals(50, pageRequest.getPageSize());
    assertEquals(Sort.Direction.DESC, pageRequest.getSort().getOrderFor("status").getDirection());
    assertEquals(Sort.Direction.ASC, pageRequest.getSort().getOrderFor("code").getDirection());
  }

  @Test
  void rejectsOversizedPagesAndUnknownSortFields() {
    assertThrows(
        IllegalArgumentException.class,
        () -> PageRequests.create(0, 101, List.of(), ALLOWED_SORTS));
    assertThrows(
        IllegalArgumentException.class,
        () -> PageRequests.create(0, 20, List.of("price,asc"), ALLOWED_SORTS));
  }
}
