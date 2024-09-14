package ru.egorov.paychecks.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class AppException extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(e -> {
                    if (e.contains(DateTimeParseException.class)) {
                        return "Введите корректную дату в формате dd-MM-yyyy";
                    }
                    return e.getDefaultMessage();
                })
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(new MessageErrorResponse(HttpStatus.BAD_REQUEST, errors));
    }
}
