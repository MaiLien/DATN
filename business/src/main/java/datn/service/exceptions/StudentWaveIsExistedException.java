package datn.service.exceptions;

public class StudentWaveIsExistedException extends RuntimeException{
    private String[] errMessages;

    public StudentWaveIsExistedException(){}

    public StudentWaveIsExistedException(String... errMessages) {
        this.errMessages = errMessages;
    }

    public String[] getErrMessages() {
        return this.errMessages;
    }

    public void setErrMessage(String... errMessages) {
        this.errMessages = errMessages;
    }
}
