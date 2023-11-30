package br.com.digitalhouse.projetointegradorpi.api.handler;

import br.com.digitalhouse.projetointegradorpi.domain.exceptions.CarNotFoundException;
import br.com.digitalhouse.projetointegradorpi.domain.exceptions.ExpiredException;
import br.com.digitalhouse.projetointegradorpi.domain.exceptions.UserAlreadyExistsException;
import br.com.digitalhouse.projetointegradorpi.domain.exceptions.UserNotFoundException;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import jakarta.validation.ConstraintViolationException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    public static final String GENERIC_ERROR_MESSAGE = "Ocorreu um erro interno inesperado no sistema tente novamente e " +
            "se o problema persistir, entre em contato com o administrador do sistema.";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatusCode status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.SYSTEM_ERROR;

        log.error(ex.getMessage(), ex);

        Problem problem = createProblemBuilder(status, problemType, GENERIC_ERROR_MESSAGE)
                .userMessage(GENERIC_ERROR_MESSAGE)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundHandler(UserNotFoundException ex, WebRequest request) {
        HttpStatusCode status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;

        log.error(ex.getMessage(), ex);

        Problem problem = createProblemBuilder(status, problemType, "Usuário não encontrado")
                .userMessage(ex.getMessage())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }@ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> userAlreadyExistHandler(UserAlreadyExistsException ex, WebRequest request) {
        HttpStatusCode status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.INVALID_DATA;

        log.error(ex.getMessage(), ex);

        Problem problem = createProblemBuilder(status, problemType, "E-mail informado já cadastrado, por favor, informe um e-mail válido")
                .userMessage(ex.getMessage())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex,
                                                         HttpHeaders headers,
                                                         HttpStatusCode status,
                                                         WebRequest request) {
        return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
        String detail = "O recurso %s, que você tentou acessar, é inexistente.".formatted(ex.getRequestURL());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_ERROR_MESSAGE)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatusCode status, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException mismatchException) {
            if (isRequiredPathParameterLong(mismatchException))
                return handleGenericNotFound(mismatchException, headers, HttpStatus.NOT_FOUND, request);

            return handleMethodArgumentTypeMismatch(mismatchException, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
        }

        ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
        String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_ERROR_MESSAGE)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDenied(AccessDeniedException ex, WebRequest request) {

        HttpStatusCode status = HttpStatus.FORBIDDEN;
        ProblemType problemType = ProblemType.FORBIDDEN;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage("Você não possui permissão para executar essa operação.")
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<?> jwtExceptionHandler(CarNotFoundException ex, WebRequest request) {

        HttpStatusCode status = HttpStatus.FORBIDDEN;
        ProblemType problemType = ProblemType.FORBIDDEN;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage("Carro não encontrado!.")
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatusCode status, WebRequest request) {

        if (ex instanceof MissingServletRequestParameterException) {
            return handleMissing((MissingServletRequestParameterException) ex, headers, request);
        }

        if (ex instanceof ConstraintViolationException) {
            return handleConstraint((ConstraintViolationException) ex, headers, request);
        }

        if (body == null) {
            body = Problem.builder()
                    .timestamp(OffsetDateTime.now())
                    .status(status.value())
                    .userMessage(GENERIC_ERROR_MESSAGE)
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .timestamp(OffsetDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .userMessage(GENERIC_ERROR_MESSAGE)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, HttpHeaders headers,
                                                            HttpStatusCode status, WebRequest request, BindingResult bindingResult) {
        ProblemType problemType = ProblemType.INVALID_DATA;
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }

                    return Problem.Object.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .objects(problemObjects)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private boolean isRequiredPathParameterLong(MethodArgumentTypeMismatchException mismatchException) {
        return mismatchException.getRequiredType() != null
                && mismatchException.getParameter().hasParameterAnnotation(PathVariable.class)
                && mismatchException.getRequiredType().equals(String.class);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

        ProblemType problemType = ProblemType.INVALID_PARAMETER;

        assert ex.getRequiredType() != null;

        String detail = ("O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido." +
                "Corrija e informe um valor compatível com o tipo %s.")
                .formatted(ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_ERROR_MESSAGE)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleGenericNotFound(
            MethodArgumentTypeMismatchException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;

        String detail = problemType.getTitle();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(problemType.getTitle())
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex,
                                                         HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String path = joinPath(ex.getPath());

        ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
        String detail = String.format("A propriedade '%s' não existe. "
                + "Corrija ou remova essa propriedade e tente novamente.", path);

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_ERROR_MESSAGE)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex,
                                                       HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String path = joinPath(ex.getPath());

        ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
                        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                path, ex.getValue(), ex.getTargetType().getSimpleName());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_ERROR_MESSAGE)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<?> handleNotFound(Exception ex, WebRequest request) {
        HttpStatusCode status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handleMissing(MissingServletRequestParameterException ex,
                                                 HttpHeaders headers, WebRequest request) {
        HttpStatusCode status = HttpStatus.BAD_REQUEST;

        ProblemType problemType = ProblemType.INVALID_DATA;
        String detail = "Um ou mais parâmetros obrigatórios não estão estão presentes.";

        Problem.Object object = Problem.Object.builder()
                .name(ex.getParameterName())
                .userMessage("Parâmetro \"%s\" é obrigatório.".formatted(ex.getParameterName()))
                .build();

        List<Problem.Object> problemObjects = new ArrayList<>();
        problemObjects.add(object);

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .objects(problemObjects)
                .build();

        return super.handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleConstraint(ConstraintViolationException ex,
                                                    HttpHeaders headers, WebRequest request) {
        HttpStatusCode status = HttpStatus.BAD_REQUEST;

        ProblemType problemType = ProblemType.INVALID_DATA;
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        List<Problem.Object> problemObjects = ex.getConstraintViolations().stream()
                .map(objectError -> {

                    String fileName = objectError.getPropertyPath().toString();

                    String userErrorMessage = objectError.getMessage();

                    if (StringUtils.contains(fileName, ".")) {
                        String[] pathNames = StringUtils.split(fileName, ".");
                        fileName = pathNames[pathNames.length - 1];
                    }

                    if (objectError instanceof FieldError) {
                        fileName = ((FieldError) objectError).getField();
                    }

                    return Problem.Object.builder()
                            .name(fileName)
                            .userMessage(userErrorMessage)
                            .build();
                })
                .collect(Collectors.toList());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .objects(problemObjects)
                .build();

        return super.handleExceptionInternal(ex, problem, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatusCode status, ProblemType problemType, String detail) {
        return Problem.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));
    }


}
