package test.core.api.exception;
public class EmployeeServiceException extends RuntimeException {
    public EmployeeServiceException(String message) {
        super(message);
    }
}
