package ada.grupo5.msfinanceiro.controllers;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ada.grupo5.msfinanceiro.exceptions.CardNotFoundException;
import ada.grupo5.msfinanceiro.exceptions.CustomerAlreadyHaveActiveCardException;
import ada.grupo5.msfinanceiro.exceptions.CustomerHasNoActiveCardException;
import ada.grupo5.msfinanceiro.exceptions.CustomerNotFoundException;
import jakarta.validation.ValidationException;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler{
    @ExceptionHandler({
        NotFoundException.class,
        ValidationException.class,
        CardNotFoundException.class,
        CustomerNotFoundException.class,
        CustomerHasNoActiveCardException.class,
        CustomerAlreadyHaveActiveCardException.class,
    })
    public ResponseEntity<String> handleBadRequestException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}