package datn.service.exceptions;

public class ProjectWaveException extends RuntimeException{

    private String errCode;
    private String[] errMessages;

    public ProjectWaveException(String errCode, String... errMessage) {
        this.errCode = errCode;
        this.errMessages = errMessages;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String[] getErrMessages() {
        return errMessages;
    }

    public void setErrMessages(String[] errMessages) {
        this.errMessages = errMessages;
    }
}
