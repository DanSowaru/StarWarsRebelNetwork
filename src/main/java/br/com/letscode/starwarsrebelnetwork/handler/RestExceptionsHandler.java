package br.com.letscode.starwarsrebelnetwork.handler;

import br.com.letscode.starwarsrebelnetwork.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionsHandler {

    @ExceptionHandler(value = IdNotFoundException.class)
    public ResponseEntity<?> handleIdNotFoundException(IdNotFoundException exception) {
        exception.getReturnMessage();
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}
