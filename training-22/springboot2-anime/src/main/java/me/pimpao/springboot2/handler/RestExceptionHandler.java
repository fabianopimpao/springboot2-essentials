package me.pimpao.springboot2.handler;

import me.pimpao.springboot2.exception.BadRequestException;
import me.pimpao.springboot2.exception.BadRequestExceptionDetails;
import me.pimpao.springboot2.exception.ExceptionDetails;
import me.pimpao.springboot2.exception.ValidationExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException exception) {
        BadRequestExceptionDetails badRequestExceptionDetails = BadRequestExceptionDetails.builder()
                .title("Bad Request exception, check the documentation")
                .status(HttpStatus.BAD_REQUEST.value())
                .details(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(badRequestExceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        ValidationExceptionDetails validationExceptionDetails = ValidationExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .title("Bad Request Exception, Invalid Fields")
                .details(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .fields(fields)
                .fieldsMessage(fieldsMessage)
                .build();

        return new ResponseEntity<>(validationExceptionDetails, HttpStatus.BAD_REQUEST);

    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .title(ex.getCause().getMessage())
                .details(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(exceptionDetails, headers, status);
    }
}
