package exceptions;

public class AccessTokenException extends  RuntimeException {

    public AccessTokenException(String message) {
        super(message);
    }
}
