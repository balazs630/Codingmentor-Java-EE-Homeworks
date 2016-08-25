package hu.oktatas.java.ee.exceptionhandling;

class ErrorDTO {

    private String errorMessage;

    public ErrorDTO() {
        //Default contructor
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
