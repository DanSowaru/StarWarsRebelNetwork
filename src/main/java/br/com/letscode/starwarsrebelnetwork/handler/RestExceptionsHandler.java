package br.com.letscode.starwarsrebelnetwork.handler;

import br.com.letscode.starwarsrebelnetwork.exceptions.IdNotFoundException;
import br.com.letscode.starwarsrebelnetwork.exceptions.NotEqualTradeSumException;
import br.com.letscode.starwarsrebelnetwork.exceptions.SameIdTradeException;
import br.com.letscode.starwarsrebelnetwork.exceptions.TraitorTradeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionsHandler {

    @ExceptionHandler(value = IdNotFoundException.class)
    public ResponseEntity<?> handleIdNotFoundException(IdNotFoundException exception) {
        return new ResponseEntity<>(exception.getReturnMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotEqualTradeSumException.class)
    public ResponseEntity<?> handleNotEqualTradePointsException(NotEqualTradeSumException exception) {
        return new ResponseEntity<>(exception.buildMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TraitorTradeException.class)
    public ResponseEntity<?> handleTraitorTradeException(TraitorTradeException exception) {
        return new ResponseEntity<>(exception.buildMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SameIdTradeException.class)
    public ResponseEntity<?> handleSameIdTradeException(SameIdTradeException exception) {
        return new ResponseEntity<>(exception.getReturnMessage(), HttpStatus.BAD_REQUEST);
    }
}
