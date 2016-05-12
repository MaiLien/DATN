package datn.service.exceptions;

public class StudentNotFoundException extends RuntimeException{

    private String errMessage;

    public StudentNotFoundException(){}

    public StudentNotFoundException(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
