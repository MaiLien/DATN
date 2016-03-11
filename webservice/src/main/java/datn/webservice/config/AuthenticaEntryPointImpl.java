package datn.webservice.config;


import com.google.gson.Gson;
import datn.interfaces.constant.MessageCodeConstant;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.RestApiResponseHeaders;
import datn.interfaces.response.UserResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthenticaEntryPointImpl implements AuthenticationEntryPoint{

    //Chua dang nhap vao link bat buoc dang nhap
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        RestApiResponse<UserResponse> apiResponse = new RestApiResponse<UserResponse>();
        RestApiResponseHeaders headers = new RestApiResponseHeaders();
        headers.setResultCode(MessageCodeConstant.ERROR_ENTRY_POINT);
        headers.setResultDescription("Must login before access");
        apiResponse.setHeaders(headers);
        String json = new Gson().toJson(apiResponse);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(json);
    }
}
