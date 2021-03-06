package datn.webservice.controller;

import datn.interfaces.request.*;
import datn.interfaces.response.*;
import datn.service.IAddStudentForProjectWaveFromFile;
import datn.service.IProjectWaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/API")
public class ProjectWaveController {

    @Autowired
    IProjectWaveService projectWaveService;

    @Autowired
    IAddStudentForProjectWaveFromFile addStudentForProjectWaveFromFile;

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

    @RequestMapping(value = "/getTeachersToAddForProjectWave", method = RequestMethod.GET)
    public RestApiResponse<ArrayList<TeacherResponse>> getTeachersToAddForProjectWave(String id){
        return projectWaveService.getTeachersToAddForProjectWave(id);
    }

    @RequestMapping(value = "/addTeachersForWave", method = RequestMethod.POST)
    public RestApiResponse<?> addTeachersForWave(@RequestBody AddTeachersForWaveRequest request){
        return projectWaveService.addTeachersForWave(request);
    }

    @RequestMapping(value = "/deleteTeacherFromWave", method = RequestMethod.DELETE)
    public RestApiResponse<?> deleteTeacherFromWave(String teacherId, String projectWaveId){
        return projectWaveService.deleteTeacherFromWave(teacherId, projectWaveId);
    }

    @RequestMapping(value = "/deleteStudentFromWave", method = RequestMethod.DELETE)
    public RestApiResponse<?> deleteStudentFromWave(String studentId, String projectWaveId){
        return projectWaveService.deleteStudentFromWave(studentId, projectWaveId);
    }

    @RequestMapping(value = "/getWavesStudentJoined", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RestApiResponse<WavesStudentJoinedResponse> getWavesStudentJoined(String studentId){
        return projectWaveService.getWavesStudentJoined(studentId);
    }

    @RequestMapping(value = "/getWavesTeacherJoined", method = RequestMethod.GET)
    public RestApiResponse<WavesTeacherJoinedResponse> getProjectWaveTeacherJoin(String teacherId){
        return projectWaveService.getProjectWaveTeacherJoin(teacherId);
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

    @RequestMapping(value = "/getListStudentWhoTeacherGuideInWave", method = RequestMethod.GET)
    public RestApiResponse<ArrayList<StudentResponse>> getListStudentWhoTeacherGuideInWave(String teacherId, String waveId){
        return projectWaveService.getListStudentWhoTeacherGuideInWave(teacherId, waveId);
    }

    @RequestMapping(value = "/approveReport", method = RequestMethod.POST)
    public RestApiResponse<?> approveReport(@RequestBody ApproveReportRequest request){
        return projectWaveService.approveReport(request);
    }

    @RequestMapping(value = "/getAssignmentsDispalyedInStudents", method = RequestMethod.GET)
    public RestApiResponse<ArrayList<AssignmentDispalyedInStudentResponse>> getAssignmentsDispalyedInStudents(String projectWaveId){
        return projectWaveService.getAssignmentsDispalyedInStudents(projectWaveId);
    }

    @RequestMapping(value = "/getTeachersToChangeAssignment", method = RequestMethod.GET)
    public RestApiResponse<ChangeAssignmentResponse> getTeachersToChangeAssignment(String studentId, String projectWaveId){
        return projectWaveService.getTeachersToChangeAssignment(studentId, projectWaveId);
    }

    @RequestMapping(value = "/changeAssignment", method = RequestMethod.POST)
    public RestApiResponse<?> changeAssignment(@RequestBody ChangeAssignmentRequest request){
        return projectWaveService.changeAssignment(request);
    }

    @RequestMapping(value = "/getReportsOfWave", method = RequestMethod.GET)
    public RestApiResponse<ArrayList<ReportOfWaveResponse>> getReportsOfWave(String projectWaveId){
        return projectWaveService.getReportsOfWave(projectWaveId);
    }

    @RequestMapping(value = "/getReportStatistic", method = RequestMethod.GET)
    public RestApiResponse<ReportStatisticResponse> getReportStatistic(String reportId){
        return projectWaveService.getReportStatistic(reportId);
    }

    @RequestMapping(value = "/addStudentForProjectWaveFromFile", method = RequestMethod.POST)
    @ResponseBody
    public RestApiResponse<ImportFromFileResponse<StudentResponse, StudentRequest>> addStudentForProjectWaveFromFile(@RequestParam("excelFile") MultipartFile excelFile, String projectWaveId){
        return addStudentForProjectWaveFromFile.addStudentForProjectWaveFromFile(excelFile, projectWaveId);
    }

}
