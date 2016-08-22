package hu.oktatas.java.ee.webshop.exceptionmapper;

public class ErrorDTO {

    private String errorMessage;

    public ErrorDTO() {
    }

    public ErrorDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
