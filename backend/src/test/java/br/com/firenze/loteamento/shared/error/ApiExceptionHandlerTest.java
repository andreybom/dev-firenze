package br.com.firenze.loteamento.shared.error;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

class ApiExceptionHandlerTest {

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(new TestController())
            .setControllerAdvice(new ApiExceptionHandler())
            .addFilters(new CorrelationIdFilter())
            .build();
  }

  @Test
  void returnsFieldErrorsAndPreservesSafeCorrelationId() throws Exception {
    mockMvc
        .perform(
            post("/test/validation")
                .header(CorrelationIdFilter.HEADER_NAME, "request-12345678")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
        .andExpect(header().string(CorrelationIdFilter.HEADER_NAME, "request-12345678"))
        .andExpect(jsonPath("$.type").value("validation_error"))
        .andExpect(jsonPath("$.status").value(400))
        .andExpect(jsonPath("$.correlationId").value("request-12345678"))
        .andExpect(jsonPath("$.errors[0].field").value("name"))
        .andExpect(jsonPath("$.errors[0].code").value("required"))
        .andExpect(jsonPath("$.errors[0].message").value("Nome é obrigatório"));
  }

  @Test
  void returnsSafeNotFoundAndConflictMessages() throws Exception {
    mockMvc
        .perform(get("/test/not-found"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.type").value("resource_not_found"))
        .andExpect(jsonPath("$.detail").value("Lote não encontrado."));

    mockMvc
        .perform(get("/test/conflict"))
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.type").value("business_conflict"))
        .andExpect(jsonPath("$.detail").value("O lote não está disponível."));
  }

  @Test
  void returnsInvalidRequestForMalformedJson() throws Exception {
    mockMvc
        .perform(post("/test/validation").contentType(MediaType.APPLICATION_JSON).content("{"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.type").value("invalid_request"))
        .andExpect(jsonPath("$.errors").isEmpty());
  }

  @Test
  void returnsFieldErrorForInvalidQueryParameter() throws Exception {
    mockMvc
        .perform(get("/test/page").queryParam("size", "0"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.type").value("validation_error"))
        .andExpect(jsonPath("$.errors[0].field").value("size"))
        .andExpect(jsonPath("$.errors[0].code").value("out_of_range"))
        .andExpect(jsonPath("$.errors[0].message").value("Tamanho deve ser positivo"));
  }

  @Test
  void hidesUnexpectedExceptionDetailsAndGeneratesCorrelationId() throws Exception {
    mockMvc
        .perform(get("/test/failure"))
        .andExpect(status().isInternalServerError())
        .andExpect(
            header()
                .string(
                    CorrelationIdFilter.HEADER_NAME,
                    matchesPattern("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")))
        .andExpect(jsonPath("$.type").value("internal_error"))
        .andExpect(
            jsonPath("$.detail")
                .value(
                    org.hamcrest.Matchers.not(
                        org.hamcrest.Matchers.containsString("database-password"))))
        .andExpect(jsonPath("$.correlationId").isNotEmpty());
  }

  @RestController
  @RequestMapping("/test")
  static class TestController {

    @PostMapping("/validation")
    String validate(@Valid @RequestBody TestRequest request) {
      return request.name();
    }

    @GetMapping("/not-found")
    void notFound() {
      throw new ResourceNotFoundException("Lote não encontrado.");
    }

    @GetMapping("/conflict")
    void conflict() {
      throw new BusinessConflictException("O lote não está disponível.");
    }

    @GetMapping("/failure")
    void failure() {
      throw new IllegalStateException("database-password=secret");
    }

    @GetMapping("/page")
    int page(@RequestParam @Positive(message = "Tamanho deve ser positivo") int size) {
      return size;
    }
  }

  record TestRequest(@NotBlank(message = "Nome é obrigatório") String name) {}
}
