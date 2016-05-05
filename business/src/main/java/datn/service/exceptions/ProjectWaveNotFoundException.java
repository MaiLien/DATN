package datn.service.exceptions;

public class ProjectWaveNotFoundException extends RuntimeException {

    private String errMessage;

    public ProjectWaveNotFoundException() {
    }

    public ProjectWaveNotFoundException(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
