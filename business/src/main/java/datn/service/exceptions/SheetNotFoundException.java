package datn.service.exceptions;

public class SheetNotFoundException extends RuntimeException{

    public SheetNotFoundException(String message) {
        super(message);
    }

}
