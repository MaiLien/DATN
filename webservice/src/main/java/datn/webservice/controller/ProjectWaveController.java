package datn.webservice.controller;

import datn.interfaces.request.*;
import datn.interfaces.response.*;
import datn.service.IProjectWaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/getStudentsOfProjectWaveToPropose", method = RequestMethod.GET)
    public RestApiResponse<ArrayList<StudentOfProjectWaveToProposeResponse>> getStudentsOfProjectWaveToPropose(String teacherId, String projectWaveId){
        return projectWaveService.getStudentsOfProjectWaveToPropose(teacherId, projectWaveId);
    }

    @RequestMapping(value = "/getTeachersOfProjectWave", method = RequestMethod.GET)
    public RestApiResponse<ArrayList<TeacherInProjectWaveResponse>> getTeachersOfProjectWave(String id){
        return projectWaveService.getTeachersOfProjectWave(id);
    }

    @RequestMapping(value = "/getWavesStudentJoined", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RestApiResponse<WavesStudentJoinedResponse> getWavesStudentJoined(String studentId){
        return projectWaveService.getWavesStudentJoined(studentId);
    }

    @RequestMapping(value = "/getWavesTeacherJoined", method = RequestMethod.GET)
    public RestApiResponse<ArrayList<ProjectWaveResponse>> getWavesTeacherJoined(String teacherId){
        return projectWaveService.getWavesTeacherJoined(teacherId);
    }

    @RequestMapping(value = "/getTeachersWhoDirectingStudentInProjectWave", method = RequestMethod.GET)
    public RestApiResponse<ArrayList<TeacherInProjectWaveResponse>> getTeachersWhoDirectingStudentInProjectWave(String studentId, String waveId){
        return projectWaveService.getTeachersWhoDirectingStudentInProjectWave(studentId, waveId);
    }

    @RequestMapping(value = "/cancelRegisterTeacher", method = RequestMethod.POST)
    public RestApiResponse<RegisterTeacherResponse> cancelRegisterTeacher(@RequestBody RegisterTeacherRequest request){
        return projectWaveService.cancelRegisterTeacher(request);
    }

    @RequestMapping(value = "/registerTeacher", method = RequestMethod.POST)
    public RestApiResponse<RegisterTeacherResponse> registerTeacher(@RequestBody RegisterTeacherRequest request){
        return projectWaveService.registerTeacher(request);
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

    @RequestMapping(value = "/addTeacherForProjectWave", method = RequestMethod.POST)
    public RestApiResponse<TeacherResponse> addTeacherForProjectWave(@RequestBody AddTeacherForProjectWaveRequest request){
        return projectWaveService.addTeacherForProjectWave(request);
    }

}
