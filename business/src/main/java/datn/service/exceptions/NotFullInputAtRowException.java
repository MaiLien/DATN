package datn.service.exceptions;

public class NotFullInputAtRowException extends RuntimeException {

    public NotFullInputAtRowException(String errMessage) {
        super(errMessage);
    }

}
