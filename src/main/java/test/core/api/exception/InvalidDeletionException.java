package test.core.api.exception;
public class InvalidDeletionException extends RuntimeException {
    public InvalidDeletionException(String message) {
        super(message);
    }
}
