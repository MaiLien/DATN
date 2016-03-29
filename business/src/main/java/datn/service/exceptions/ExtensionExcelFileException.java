package datn.service.exceptions;

import java.io.Serializable;

public class ExtensionExcelFileException extends RuntimeException{

    public ExtensionExcelFileException(String errMessage){
        super(errMessage);
    }
}
