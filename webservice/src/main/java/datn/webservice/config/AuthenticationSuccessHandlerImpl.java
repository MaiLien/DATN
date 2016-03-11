package datn.webservice.config;

import com.google.gson.Gson;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.RestApiResponseHeaders;
import datn.interfaces.response.UserResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Service
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler{


    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        RestApiResponse<UserResponse> apiResponse = new RestApiResponse<UserResponse>();
        RestApiResponseHeaders headers = new RestApiResponseHeaders();

        apiResponse.setHeaders(headers);
        apiResponse.setBody((UserResponse) authentication.getDetails());

        String json = new Gson().toJson(apiResponse);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(json);
    }
}
