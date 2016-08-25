package hu.oktatas.java.ee.exceptions;

public class ParkIsFullException extends Exception {

    public ParkIsFullException() {
        //Default constructor
    }

    public ParkIsFullException(String message) {
        super(message);
    }
}
