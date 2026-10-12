package br.com.firenze.loteamento;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.UUID;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class DatabaseMigrationTests {

  @Autowired private Flyway flyway;

  @Autowired private JdbcTemplate jdbcTemplate;

  @Test
  void appliesVersionOneAndCreatesTheStructuralCore() {
    assertThat(flyway.info().current().getVersion().toString()).isEqualTo("1");

    Integer tableCount =
        jdbcTemplate.queryForObject(
            """
                SELECT COUNT(*)
                  FROM information_schema.tables
                 WHERE LOWER(table_schema) = 'public'
                   AND LOWER(table_name) IN ('company', 'development', 'development_block', 'lot')
                """,
            Integer.class);

    assertThat(tableCount).isEqualTo(4);
  }

  @Test
  void enforcesTheStructuralHierarchyAndFixedLotStatuses() {
    UUID companyId = UUID.randomUUID();
    UUID developmentId = UUID.randomUUID();
    UUID blockId = UUID.randomUUID();

    jdbcTemplate.update(
        "INSERT INTO company (id, legal_name, tax_id) VALUES (?, ?, ?)",
        companyId,
        "Firenze Empreendimentos",
        "12345678000199");
    jdbcTemplate.update(
        "INSERT INTO development (id, company_id, code, name) VALUES (?, ?, ?, ?)",
        developmentId,
        companyId,
        "FIR-01",
        "Residencial Firenze");
    jdbcTemplate.update(
        "INSERT INTO development_block (id, development_id, code) VALUES (?, ?, ?)",
        blockId,
        developmentId,
        "Q01");
    jdbcTemplate.update(
        "INSERT INTO lot (id, development_block_id, code, status, area_square_meters, list_price) "
            + "VALUES (?, ?, ?, ?, ?, ?)",
        UUID.randomUUID(),
        blockId,
        "L01",
        "AVAILABLE",
        250.0000,
        125000.00);

    assertThatThrownBy(
            () ->
                jdbcTemplate.update(
                    "INSERT INTO lot (id, development_block_id, code, status) VALUES (?, ?, ?, ?)",
                    UUID.randomUUID(),
                    blockId,
                    "L02",
                    "INVALID_STATUS"))
        .satisfies(error -> assertThat(error.getMessage()).containsIgnoringCase("ck_lot_status"));
  }
}
