package az.atlacademy.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
@Slf4j
public class ControllerAdvice {
    @ExceptionHandler(OrderNotFound.class)
    public ResponseEntity<String> userNotFoundException(OrderNotFound e) {
        log.info(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(BalanceException.class)
    public ResponseEntity<String> balanceNotEnoughException(BalanceException e) {
        log.info(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StockException.class)
    public ResponseEntity<String> stockNotEnoughException(StockException e) {
        log.info(e.getMessage());
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

}
