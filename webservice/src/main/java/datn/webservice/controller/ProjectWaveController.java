package datn.webservice.controller;

import datn.interfaces.request.AddStudentForProjectWaveRequest;
import datn.interfaces.request.ProjectWaveRequest;
import datn.interfaces.request.StudentRequest;
import datn.interfaces.response.ProjectWaveResponse;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.StudentResponse;
import datn.interfaces.util.JsonUtil;
import datn.service.IProjectWaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/API")
public class ProjectWaveController {

    @Autowired
    IProjectWaveService projectWaveService;

    @RequestMapping(value = "/addProjectWave", method = RequestMethod.POST)
    public RestApiResponse<ProjectWaveResponse> addProjectWave(@RequestBody ProjectWaveRequest projectWaveRequest){
    return projectWaveService.addProjectWave(projectWaveRequest);
    }

    @RequestMapping(value = "/getProjectWave", method = RequestMethod.GET)
    public RestApiResponse<ProjectWaveResponse> getProjectWave(String id){
        return projectWaveService.getProjectWave(id);
    }

    @RequestMapping(value = "/getAllProjectWave", method = RequestMethod.GET)
    public RestApiResponse<ArrayList<ProjectWaveResponse>> getAllProjectWave(){
        return projectWaveService.getAllProjectWave();
    }

    @RequestMapping(value = "/getProjectWavesByPage", method = RequestMethod.GET)
    public RestApiResponse<Page<ProjectWaveResponse>> getStudentsByPage(int pageIndex, int sizeOfPage, String searchInput) {
        return projectWaveService.getPageProjectWaves(pageIndex, sizeOfPage, searchInput);
    }

    @RequestMapping(value = "/getStudentsOfProjectWave", method = RequestMethod.GET)
    public RestApiResponse<ArrayList<StudentResponse>> getStudentsOfProjectWave(String id){
        return projectWaveService.getStudentsOfProjectWave(id);
    }

    /*Can not pass parameter with Postman if it has @RequestBody, otherwise website*/
    @RequestMapping(value = "/deleteProjectWave", method = RequestMethod.DELETE)
    public RestApiResponse<ProjectWaveResponse> deleteProjectWave(String projectWaveId){
        return projectWaveService.deleteProjectWave(projectWaveId);
    }

    @RequestMapping(value = "/addStudentForProjectWave", method = RequestMethod.POST)
    public RestApiResponse<StudentResponse> addStudentForProjectWave(@RequestBody AddStudentForProjectWaveRequest request){
        return projectWaveService.addStudentForProjectWave(request);
    }

}
