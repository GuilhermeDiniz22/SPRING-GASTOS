package br.maxiprod.api_selecao.exception;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;

import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEntityNotFoundExcpetion(EntityNotFoundException exception,
                                                                      WebRequest request) {
        ErrorDetails error = new ErrorDetails();
        error.setTimestamp(LocalDate.now());
        error.setMessage(exception.getMessage());
        error.setDetails(request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> handleAcessDeniedException(AccessDeniedException exception,
                                                                   WebRequest request) {
        ErrorDetails error = new ErrorDetails();
        error.setTimestamp(LocalDate.now());
        error.setMessage(exception.getMessage());
        error.setDetails(request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ErrorDetails> handleEntityExistsException(EntityExistsException exception,
                                                                    WebRequest request) {
        ErrorDetails error = new ErrorDetails();
        error.setTimestamp(LocalDate.now());
        error.setMessage(exception.getMessage());
        error.setDetails(request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(error, HttpStatus.UNAUTHORIZED);
    }
}
