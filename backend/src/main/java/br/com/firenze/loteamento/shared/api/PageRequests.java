package br.com.firenze.loteamento.shared.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public final class PageRequests {

  public static final int DEFAULT_PAGE = 0;
  public static final int DEFAULT_SIZE = 20;
  public static final int MAX_SIZE = 100;

  private PageRequests() {}

  public static PageRequest create(
      Integer requestedPage,
      Integer requestedSize,
      List<String> requestedSorts,
      Set<String> allowedSortFields) {
    int page = requestedPage == null ? DEFAULT_PAGE : requestedPage;
    int size = requestedSize == null ? DEFAULT_SIZE : requestedSize;

    if (page < 0) {
      throw new IllegalArgumentException("Page number must not be negative");
    }
    if (size < 1 || size > MAX_SIZE) {
      throw new IllegalArgumentException("Page size must be between 1 and " + MAX_SIZE);
    }

    Objects.requireNonNull(allowedSortFields, "Allowed sort fields must not be null");
    List<Sort.Order> orders = new ArrayList<>();
    for (String requestedSort : requestedSorts == null ? List.<String>of() : requestedSorts) {
      orders.add(parseOrder(requestedSort, allowedSortFields));
    }

    return orders.isEmpty()
        ? PageRequest.of(page, size)
        : PageRequest.of(page, size, Sort.by(orders));
  }

  private static Sort.Order parseOrder(String requestedSort, Set<String> allowedSortFields) {
    if (requestedSort == null || requestedSort.isBlank()) {
      throw new IllegalArgumentException("Sort must not be blank");
    }

    String[] parts = requestedSort.split(",", -1);
    if (parts.length > 2 || parts[0].isBlank()) {
      throw new IllegalArgumentException("Sort must use the format field,direction");
    }

    String field = parts[0];
    if (!allowedSortFields.contains(field)) {
      throw new IllegalArgumentException("Sort field is not allowed: " + field);
    }

    String direction = parts.length == 1 ? "asc" : parts[1].toLowerCase(Locale.ROOT);
    return switch (direction) {
      case "asc" -> Sort.Order.asc(field);
      case "desc" -> Sort.Order.desc(field);
      default -> throw new IllegalArgumentException("Sort direction must be asc or desc");
    };
  }
}
