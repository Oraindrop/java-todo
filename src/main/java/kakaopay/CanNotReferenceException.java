package kakaopay;

public class CanNotReferenceException extends RuntimeException {
    public CanNotReferenceException(String message) {
        super(message);
    }
}
