package datn.webservice.controller;

import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.RestApiResponseHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public RestApiResponse<Object> getSession(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getDetails();

        RestApiResponse<Object> userResponseRestApiResponse = new RestApiResponse<Object>(auth.getDetails());
        return userResponseRestApiResponse;
    }

    @RequestMapping(value = "/logoutSuccess", method = RequestMethod.GET)
    public RestApiResponse<Object> logoutSuccess(){
        RestApiResponse<Object> restApiResponse = new RestApiResponse<Object>(null);
        RestApiResponseHeaders restApiResponseHeaders = new RestApiResponseHeaders();
        restApiResponse.setHeaders(restApiResponseHeaders);

        return restApiResponse;
    }

}
