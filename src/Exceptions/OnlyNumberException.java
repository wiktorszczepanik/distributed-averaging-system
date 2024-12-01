package Exceptions;

public class OnlyNumberException extends RuntimeException {
    public OnlyNumberException(String message) {
        super("(format) ".concat(message));
    }
}
