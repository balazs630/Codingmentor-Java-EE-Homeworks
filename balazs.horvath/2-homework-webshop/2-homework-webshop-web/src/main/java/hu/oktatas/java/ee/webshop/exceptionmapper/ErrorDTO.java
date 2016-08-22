package hu.oktatas.java.ee.webshop.exceptionmapper;

public class ErrorDTO extends Throwable {

    public ErrorDTO(String message) {
        super(message);
    }

    public ErrorDTO(Throwable cause) {
        super(cause);
    }

    public ErrorDTO(String message, Throwable cause) {
        super(message, cause);
    }

}
