package atl.academy.project.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(BookAlreadyFoundException.class)
    public ResponseEntity<String> bookAlreadyFoundException(BookAlreadyFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> bookNotFoundException(BookNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }


}
