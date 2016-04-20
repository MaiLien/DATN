package datn.webservice.controller;

import datn.interfaces.request.ProjectWaveRequest;
import datn.interfaces.response.RestApiResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/API")
public class ProjectWaveController {

    @RequestMapping(value = "/project-wave", method = RequestMethod.POST)
    public RestApiResponse<ArrayList<?>> addProjectWave(@RequestBody ProjectWaveRequest projectWaveRequest){
        return new RestApiResponse<>();
    }

}
