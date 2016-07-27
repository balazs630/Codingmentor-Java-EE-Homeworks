package hu.oktatas.java.ee.webshop.shoppingcart;

public class MobileNotExistInTheCartException extends Exception {

    public MobileNotExistInTheCartException(String message) {
        super(message);
    }

}
