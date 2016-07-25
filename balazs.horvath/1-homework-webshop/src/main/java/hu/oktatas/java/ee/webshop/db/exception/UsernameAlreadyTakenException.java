package hu.oktatas.java.ee.webshop.db.exception;

public class UsernameAlreadyTakenException extends Exception {

    public UsernameAlreadyTakenException(String message) {
        super(message);
    }
}
