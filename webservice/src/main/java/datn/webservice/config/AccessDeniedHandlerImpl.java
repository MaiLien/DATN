package datn.webservice.config;

import com.google.gson.Gson;
import datn.interfaces.constant.MessageCodeConstant;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.RestApiResponseHeaders;
import datn.interfaces.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessDeniedHandlerImpl implements AccessDeniedHandler{

    //Dang nhap roi nhung khong co quyen access link

    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        RestApiResponse<UserResponse> apiResponse = new RestApiResponse<UserResponse>();
        RestApiResponseHeaders headers = new RestApiResponseHeaders();
        headers.setResultCode(MessageCodeConstant.ERROR_ACCESS_DENIED);
        headers.setResultDescription("Don't have permission");
        apiResponse.setHeaders(headers);
        apiResponse.setBody(null);
        String json = new Gson().toJson(apiResponse);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(json);
    }
}
