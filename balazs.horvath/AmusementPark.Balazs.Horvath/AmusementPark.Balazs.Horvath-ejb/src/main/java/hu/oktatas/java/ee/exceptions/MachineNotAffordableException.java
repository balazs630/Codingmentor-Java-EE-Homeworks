package hu.oktatas.java.ee.exceptions;

public class MachineNotAffordableException extends Exception {

    public MachineNotAffordableException() {
        //Default constructor
    }

    public MachineNotAffordableException(String message) {
        super(message);
    }
}
