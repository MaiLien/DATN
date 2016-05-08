package datn.webservice.controller;

import datn.dao.entity.Officer;
import datn.interfaces.response.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public RestApiResponse<Object> getSession(){
        RestApiResponse<Object> userResponseRestApiResponse = new RestApiResponse<Object>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getDetails() instanceof UserResponse){
            userResponseRestApiResponse.setBody(auth.getDetails());
        }else{
            userResponseRestApiResponse.setBody(null);
        }

        return userResponseRestApiResponse;
    }

    @RequestMapping(value = "/logoutSuccess", method = RequestMethod.GET)
    public RestApiResponse<Object> logoutSuccess(){
        RestApiResponse<Object> restApiResponse = new RestApiResponse<Object>(null);
        RestApiResponseHeaders restApiResponseHeaders = new RestApiResponseHeaders();
        restApiResponse.setHeaders(restApiResponseHeaders);

        return restApiResponse;
    }

    @RequestMapping(value = "/getOfficerInfo", method = RequestMethod.GET)
    public RestApiResponse<OfficerResponse> getOfficerInfo(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        OfficerResponse user = null;
        if((auth != null) && (auth.getDetails() instanceof OfficerResponse)){
            user = (OfficerResponse) auth.getDetails();
        }

        RestApiResponse<OfficerResponse> response= new RestApiResponse<>(user);
        return response;
    }

    @RequestMapping(value = "/getStudentInfo", method = RequestMethod.GET)
    public RestApiResponse<StudentResponse> getStudentInfo(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        StudentResponse user = null;
        if((auth != null) && (auth.getDetails() instanceof StudentResponse)){
            user = (StudentResponse) auth.getDetails();
        }

        RestApiResponse<StudentResponse> response= new RestApiResponse<>(user);
        return response;
    }

    @RequestMapping(value = "/getTeacherInfo", method = RequestMethod.GET)
    public RestApiResponse<TeacherResponse> getTeacherInfo(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        TeacherResponse user = null;
        if((auth != null) && (auth.getDetails() instanceof TeacherResponse)){
            user = (TeacherResponse) auth.getDetails();
        }

        RestApiResponse<TeacherResponse> response= new RestApiResponse<>(user);
        return response;
    }

}
