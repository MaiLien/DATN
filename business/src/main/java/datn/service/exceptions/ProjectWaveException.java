package datn.service.exceptions;

public class ProjectWaveException extends RuntimeException{

    private String errCode;
    private String errMessage;

    public ProjectWaveException(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
