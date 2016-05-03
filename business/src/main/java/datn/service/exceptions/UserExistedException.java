package datn.service.exceptions;

public class UserExistedException extends RuntimeException{

    private String errMessage;

    public UserExistedException(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
