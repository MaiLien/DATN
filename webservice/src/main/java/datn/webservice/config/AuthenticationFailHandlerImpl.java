package datn.webservice.config;

import com.google.gson.Gson;
import datn.interfaces.constant.MessageCodeConstant;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.RestApiResponseHeaders;
import datn.interfaces.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AuthenticationFailHandlerImpl implements AuthenticationFailureHandler{

    @Autowired
    private MessageSource messageSource;

    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        RestApiResponse<UserResponse> apiResponse = new RestApiResponse<UserResponse>();
        RestApiResponseHeaders headers = new RestApiResponseHeaders();
        headers.setResultCode(MessageCodeConstant.ERROR_LOGIN);
        headers.setResultDescription("Username or password is incorrect");
        apiResponse.setHeaders(headers);
        String json = new Gson().toJson(apiResponse);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(json);
    }
}
