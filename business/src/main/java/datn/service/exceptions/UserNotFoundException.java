package datn.service.exceptions;

public class UserNotFoundException extends RuntimeException{

    private String errMessage;

    public UserNotFoundException(){}

    public UserNotFoundException(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
