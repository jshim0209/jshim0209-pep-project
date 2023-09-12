package Exception;

public class AccountAlreadyExistException extends Exception {
    public AccountAlreadyExistException() {
        super();
    }

    public AccountAlreadyExistException(String message) {
        super(message);
    }
    
}
