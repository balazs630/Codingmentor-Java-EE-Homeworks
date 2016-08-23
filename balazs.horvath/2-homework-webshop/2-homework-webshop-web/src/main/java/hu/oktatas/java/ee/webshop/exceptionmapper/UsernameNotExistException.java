package hu.oktatas.java.ee.webshop.exceptionmapper;

public class UsernameNotExistException extends RuntimeException {

    public UsernameNotExistException() {
        super();
    }

    public UsernameNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
