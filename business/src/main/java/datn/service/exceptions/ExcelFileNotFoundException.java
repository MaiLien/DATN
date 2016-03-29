package datn.service.exceptions;

public class ExcelFileNotFoundException extends RuntimeException{


    public ExcelFileNotFoundException(String message) {
        super(message);
    }
}
