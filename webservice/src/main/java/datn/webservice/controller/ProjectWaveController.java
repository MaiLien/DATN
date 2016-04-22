package datn.webservice.controller;

import datn.interfaces.request.ProjectWaveRequest;
import datn.interfaces.response.ProjectWaveResponse;
import datn.interfaces.response.RestApiResponse;
import datn.service.IProjectWaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/API")
public class ProjectWaveController {

    @Autowired
    IProjectWaveService projectWaveService;

    @RequestMapping(value = "/addProjectWave", method = RequestMethod.POST)
    public RestApiResponse<ProjectWaveResponse> addProjectWave(@RequestBody ProjectWaveRequest projectWaveRequest){
        return projectWaveService.addProjectWave(projectWaveRequest);
    }

}
