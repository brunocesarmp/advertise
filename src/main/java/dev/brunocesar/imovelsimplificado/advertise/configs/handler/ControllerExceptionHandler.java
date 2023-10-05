package dev.brunocesar.imovelsimplificado.advertise.configs.handler;

import dev.brunocesar.imovelsimplificado.advertise.exceptions.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApplicationErrorResponse> handleApplicationException(ApplicationException ex) {
        log.error("Erro. Status: [{}]. Message: [{}]", ex.getHttpStatus(), ex.getMessage(), ex);

        var response = new ApplicationErrorResponse();
        response.setErrorMessage(List.of(ex.getMessage()));
        response.setHttpStatus(ex.getHttpStatus());
        
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApplicationErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        var response = new ApplicationErrorResponse();
        response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        response.setErrorMessage(errors);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }
}