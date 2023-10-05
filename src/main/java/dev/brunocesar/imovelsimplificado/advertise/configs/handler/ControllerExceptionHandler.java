package dev.brunocesar.imovelsimplificado.advertise.configs.handler;

import dev.brunocesar.imovelsimplificado.advertise.exceptions.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApplicationErrorResponse> handleApplicationException(ApplicationException ex) {
        var response = new ApplicationErrorResponse();
        response.setHttpStatus(ex.getHttpStatus());
        response.setErrorMessage(List.of(ex.getMessage()));
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApplicationErrorResponse> handleValidationErrors(BindException ex) {
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