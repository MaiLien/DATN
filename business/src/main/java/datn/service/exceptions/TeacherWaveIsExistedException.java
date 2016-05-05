package datn.service.exceptions;

public class TeacherWaveIsExistedException extends RuntimeException{

    private String[] errMessages;

    public TeacherWaveIsExistedException(){}

    public TeacherWaveIsExistedException(String... errMessages) {
        this.errMessages = errMessages;
    }

    public String[] getErrMessages() {
        return this.errMessages;
    }

    public void setErrMessage(String... errMessages) {
        this.errMessages = errMessages;
    }
}
