package atl.academy.project.library.exception;

public class BookAlreadyFoundException extends RuntimeException {
    public BookAlreadyFoundException(String message) {
        super(message);
    }
}
