package hu.oktatas.java.ee.webshop.db.exceptions;

public class UsernameAlreadyTakenException extends Exception {

    public UsernameAlreadyTakenException(String message) {
        super(message);
    }
}
