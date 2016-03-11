package datn.webservice.controller;


import datn.interfaces.constant.MessageCodeConstant;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.RestApiResponseHeaders;
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

@ControllerAdvice
public class ErrorHandlingController {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RestApiResponse<?> handleMethodArgumentNotValidExceptionS(HttpServletRequest request, HttpServletResponse response,
                                                     Object handler, MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        if (isObjectValidateError(result)) {
            ObjectError error = result.getGlobalError();
            return createBeagleFairResponseError(request, error.getDefaultMessage(), null);
        } else {
            FieldError fieldError = result.getFieldErrors().get(0);
            return createBeagleFairResponseError(request, fieldError.getDefaultMessage(), fieldError.getArguments());
        }
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public RestApiResponse<?> handleBadCredentialsException(HttpServletRequest request, HttpServletResponse response,
                                                            Object handler, BadCredentialsException exception){
        return createBeagleFairResponseError(request, exception.getMessage(), null);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public RestApiResponse<?> handleHttpMessageNotReadableException(HttpServletRequest request, HttpServletResponse response,
                                                                    Object handler, HttpMessageNotReadableException exception){
        return createBeagleFairResponseError(request, MessageCodeConstant.ERROR_HTTP_MESSAGE_NOT_READABLE, null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestApiResponse<?> handleException(HttpServletRequest request, HttpServletResponse response,
                                                           Object handler, Exception ex) {
        ex.printStackTrace();
        return createBeagleFairResponseError(request, MessageCodeConstant.ERROR_INTERNAL_SERVER, null);
    }

    private RestApiResponse<?> createBeagleFairResponseError(HttpServletRequest request, String resultCode, Object[] messageArguments) {
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
