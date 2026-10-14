package br.com.firenze.loteamento.shared.error;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<ApiErrorResponse> handleValidation(
      MethodArgumentNotValidException exception, HttpServletRequest request) {
    List<ApiFieldError> errors =
        exception.getBindingResult().getFieldErrors().stream()
            .map(
                error ->
                    new ApiFieldError(
                        error.getField(),
                        validationCode(error.getCode()),
                        defaultMessage(error.getDefaultMessage())))
            .sorted(Comparator.comparing(ApiFieldError::field).thenComparing(ApiFieldError::code))
            .toList();
    return response(
        HttpStatus.BAD_REQUEST,
        "validation_error",
        "Dados inválidos",
        "Revise os campos informados.",
        errors,
        request);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  ResponseEntity<ApiErrorResponse> handleConstraintViolation(
      ConstraintViolationException exception, HttpServletRequest request) {
    List<ApiFieldError> errors =
        exception.getConstraintViolations().stream()
            .map(
                violation ->
                    new ApiFieldError(
                        violation.getPropertyPath().toString(),
                        validationCode(
                            violation
                                .getConstraintDescriptor()
                                .getAnnotation()
                                .annotationType()
                                .getSimpleName()),
                        violation.getMessage()))
            .sorted(Comparator.comparing(ApiFieldError::field).thenComparing(ApiFieldError::code))
            .toList();
    return response(
        HttpStatus.BAD_REQUEST,
        "validation_error",
        "Dados inválidos",
        "Revise os campos informados.",
        errors,
        request);
  }

  @ExceptionHandler(HandlerMethodValidationException.class)
  ResponseEntity<ApiErrorResponse> handleMethodValidation(
      HandlerMethodValidationException exception, HttpServletRequest request) {
    List<ApiFieldError> errors =
        exception.getParameterValidationResults().stream()
            .flatMap(
                result -> {
                  String parameterName = result.getMethodParameter().getParameterName();
                  String field = parameterName == null ? "parameter" : parameterName;
                  return result.getResolvableErrors().stream()
                      .map(
                          error ->
                              new ApiFieldError(
                                  field,
                                  validationCode(firstCode(error.getCodes())),
                                  defaultMessage(error.getDefaultMessage())));
                })
            .sorted(Comparator.comparing(ApiFieldError::field).thenComparing(ApiFieldError::code))
            .toList();
    return response(
        HttpStatus.BAD_REQUEST,
        "validation_error",
        "Dados inválidos",
        "Revise os campos informados.",
        errors,
        request);
  }

  @ExceptionHandler({
    HttpMessageNotReadableException.class,
    MissingServletRequestParameterException.class,
    MethodArgumentTypeMismatchException.class,
    IllegalArgumentException.class
  })
  ResponseEntity<ApiErrorResponse> handleInvalidRequest(
      Exception exception, HttpServletRequest request) {
    return response(
        HttpStatus.BAD_REQUEST,
        "invalid_request",
        "Requisição inválida",
        "Verifique os parâmetros e o formato dos dados enviados.",
        List.of(),
        request);
  }

  @ExceptionHandler({ResourceNotFoundException.class, NoResourceFoundException.class})
  ResponseEntity<ApiErrorResponse> handleNotFound(Exception exception, HttpServletRequest request) {
    String detail =
        exception instanceof ResourceNotFoundException
            ? exception.getMessage()
            : "O recurso solicitado não foi encontrado.";
    return response(
        HttpStatus.NOT_FOUND,
        "resource_not_found",
        "Recurso não encontrado",
        detail,
        List.of(),
        request);
  }

  @ExceptionHandler(BusinessConflictException.class)
  ResponseEntity<ApiErrorResponse> handleConflict(
      BusinessConflictException exception, HttpServletRequest request) {
    return response(
        HttpStatus.CONFLICT,
        "business_conflict",
        "Operação não permitida",
        exception.getMessage(),
        List.of(),
        request);
  }

  @ExceptionHandler({
    DataIntegrityViolationException.class,
    OptimisticLockingFailureException.class
  })
  ResponseEntity<ApiErrorResponse> handlePersistenceConflict(
      RuntimeException exception, HttpServletRequest request) {
    return response(
        HttpStatus.CONFLICT,
        "business_conflict",
        "Operação não permitida",
        "O recurso foi alterado ou a operação viola uma regra de integridade.",
        List.of(),
        request);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  ResponseEntity<ApiErrorResponse> handleMethodNotAllowed(
      HttpRequestMethodNotSupportedException exception, HttpServletRequest request) {
    return response(
        HttpStatus.METHOD_NOT_ALLOWED,
        "method_not_allowed",
        "Método não permitido",
        "O método HTTP não é aceito para este recurso.",
        List.of(),
        request);
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  ResponseEntity<ApiErrorResponse> handleUnsupportedMediaType(
      HttpMediaTypeNotSupportedException exception, HttpServletRequest request) {
    return response(
        HttpStatus.UNSUPPORTED_MEDIA_TYPE,
        "unsupported_media_type",
        "Formato não suportado",
        "Envie o conteúdo em um formato aceito pelo recurso.",
        List.of(),
        request);
  }

  @ExceptionHandler(Exception.class)
  ResponseEntity<ApiErrorResponse> handleUnexpected(
      Exception exception, HttpServletRequest request) {
    String correlationId = correlationId(request);
    LOGGER.error(
        "Unhandled API failure: correlationId={}, method={}, path={}, exceptionType={}",
        correlationId,
        request.getMethod(),
        request.getRequestURI(),
        exception.getClass().getName());
    return response(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "internal_error",
        "Erro interno",
        "Não foi possível concluir a operação. Informe o código de correlação ao suporte.",
        List.of(),
        request);
  }

  private ResponseEntity<ApiErrorResponse> response(
      HttpStatus status,
      String type,
      String title,
      String detail,
      List<ApiFieldError> errors,
      HttpServletRequest request) {
    var body =
        new ApiErrorResponse(
            type,
            title,
            status.value(),
            detail,
            request.getRequestURI(),
            correlationId(request),
            errors);
    return ResponseEntity.status(status).contentType(MediaType.APPLICATION_PROBLEM_JSON).body(body);
  }

  private String correlationId(HttpServletRequest request) {
    Object value = request.getAttribute(CorrelationIdFilter.REQUEST_ATTRIBUTE);
    return value instanceof String id ? id : "unavailable";
  }

  private String validationCode(String sourceCode) {
    if (sourceCode == null) {
      return "invalid_value";
    }
    int separator = sourceCode.indexOf('.');
    String constraint = separator < 0 ? sourceCode : sourceCode.substring(0, separator);
    return switch (constraint) {
      case "NotBlank", "NotEmpty", "NotNull" -> "required";
      case "Email", "Pattern" -> "invalid_format";
      case "Size" -> "invalid_size";
      case "DecimalMax",
          "DecimalMin",
          "Max",
          "Min",
          "Negative",
          "NegativeOrZero",
          "Positive",
          "PositiveOrZero" ->
          "out_of_range";
      default -> "invalid_value";
    };
  }

  private String firstCode(String[] codes) {
    return codes == null || codes.length == 0 ? null : codes[0];
  }

  private String defaultMessage(String message) {
    return message == null || message.isBlank() ? "Valor inválido" : message;
  }
}
