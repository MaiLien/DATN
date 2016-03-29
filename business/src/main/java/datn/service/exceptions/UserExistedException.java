package datn.service.exceptions;

public class UserExistedException extends RuntimeException{

    public UserExistedException(String errMessage) {
        super(errMessage);
    }

}
