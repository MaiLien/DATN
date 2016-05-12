package datn.service.exceptions;

public class TeacherNotFoundException extends RuntimeException{

    private String errMessage;

    public TeacherNotFoundException(){}

    public TeacherNotFoundException(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
