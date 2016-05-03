package datn.webservice.controller;


import datn.interfaces.constant.MessageCodeConstant;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.RestApiResponseHeaders;
import datn.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

@ControllerAdvice
public class ErrorHandlingController {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RestApiResponse<?> handleMethodArgumentNotValidExceptionS(HttpServletRequest request, MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        if (isObjectValidateError(result)) {
            ObjectError error = result.getGlobalError();
            return createResponseError(request, error.getDefaultMessage(), null);
        } else {
            FieldError fieldError = result.getFieldErrors().get(0);
            return createResponseError(request, fieldError.getDefaultMessage(), fieldError.getArguments());
        }
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public RestApiResponse<?> handleBadCredentialsException(HttpServletRequest request, BadCredentialsException exception){
        return createResponseError(request, exception.getMessage(), null);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public RestApiResponse<?> handleHttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException exception){
        return createResponseError(request, MessageCodeConstant.ERROR_HTTP_MESSAGE_NOT_READABLE, null);
    }

    @ExceptionHandler(ExcelFileNotFoundException.class)
    @ResponseBody
    public RestApiResponse<?> handleExcelFileNotFoundException(HttpServletRequest request, ExcelFileNotFoundException exception){
        return createResponseError(request, MessageCodeConstant.ERROR_EXCEL_FILE_NOT_FOUND, null);
    }

    @ExceptionHandler(ExtensionExcelFileException.class)
    @ResponseBody
    public RestApiResponse<?> handleExtensionExcelFileException(HttpServletRequest request, ExtensionExcelFileException exception){
        return createResponseError(request, MessageCodeConstant.ERROR_EXTENSION_EXCEL_FILE, null);
    }

    @ExceptionHandler(NotFullInputAtRowException.class)
    @ResponseBody
    public RestApiResponse<?> handleNotFullInputAtRowException(HttpServletRequest request, NotFullInputAtRowException exception){
        return createResponseError(request, MessageCodeConstant.ERROR_NOT_FULL_INPUT_AT_ROW, null);
    }

    @ExceptionHandler(SheetNotFoundException.class)
    @ResponseBody
    public RestApiResponse<?> handleSheetNotFoundException(HttpServletRequest request, SheetNotFoundException exception){
        return createResponseError(request, MessageCodeConstant.ERROR_SHEET_NOT_FOUND, null);
    }

    @ExceptionHandler(UserExistedException.class)
    @ResponseBody
    public RestApiResponse<?> handleUserExistedException(HttpServletRequest request, UserExistedException exception){
        return createResponseError(request, MessageCodeConstant.ERROR_USER_EXISTED, exception.getErrMessage());
    }

    @ExceptionHandler(IOException.class)
    @ResponseBody
    public RestApiResponse<?> handleIOException(HttpServletRequest request, HttpServletResponse response,
                                                         Object handler, HttpMessageNotReadableException exception){
        return createResponseError(request, MessageCodeConstant.ERROR_INTERNAL_SERVER, null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestApiResponse<?> handleException(HttpServletRequest request, HttpServletResponse response,
                                                           Object handler, Exception ex) {
        ex.printStackTrace();
        return createResponseError(request, MessageCodeConstant.ERROR_INTERNAL_SERVER, null);
    }

    private RestApiResponse<?> createResponseError(HttpServletRequest request, String resultCode, Object... messageArguments) {
        RestApiResponse<String> RestApiResponse = new RestApiResponse<String>();
        RestApiResponseHeaders responseHeaders = RestApiResponse.getHeaders();
        responseHeaders.setResultCode(resultCode);
        responseHeaders.setResultDescription(getMessage(resultCode, messageArguments));
        return RestApiResponse;
    }

    private boolean isObjectValidateError(BindingResult result) {
        return result.getGlobalErrorCount() > 0;
    }

    private String getMessage(String resultCode, Object[] messageAgruments) {
        try {
            return messageSource.getMessage(resultCode, messageAgruments, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultCode;
    }
}
