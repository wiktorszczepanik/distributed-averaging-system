package Exceptions;

public class ModeException extends RuntimeException {
    public ModeException(String message) {
        super("\"mode\": ".concat(message));
    }
}
