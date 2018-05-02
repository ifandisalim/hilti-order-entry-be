package exceptions;

public class ExceedCreditLimitException extends RuntimeException {

    public ExceedCreditLimitException(String message) {
        super(message);
    }

}
