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

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public RestApiResponse<?> handleUserNotFoundException(HttpServletRequest request, UserNotFoundException exception){
        return createResponseError(request, MessageCodeConstant.ERROR_USER_NOT_FOUND, exception.getErrMessage());
    }

    @ExceptionHandler(ProjectWaveNotFoundException.class)
    @ResponseBody
    public RestApiResponse<?> handleProjectWaveNotFoundException(HttpServletRequest request, ProjectWaveNotFoundException exception){
        return createResponseError(request, MessageCodeConstant.PROJECT_WAVE_NOT_FOUND, exception.getErrMessage());
    }

    @ExceptionHandler(StudentWaveIsExistedException.class)
    @ResponseBody
    public RestApiResponse<?> handleStudentWaveIsExistedException(HttpServletRequest request, StudentWaveIsExistedException exception){
        return createResponseError(request, MessageCodeConstant.STUDENT_WAVE_IS_EXISTED, exception.getErrMessages());
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
        RestApiResponse<String> restApiResponse = new RestApiResponse<>();
        RestApiResponseHeaders responseHeaders = restApiResponse.getHeaders();
        responseHeaders.setResultCode(resultCode);
        responseHeaders.setResultDescription(getMessage(resultCode, messageArguments));
        return restApiResponse;
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
