package datn.webservice.controller;

import datn.interfaces.request.StudentReportRequest;
import datn.interfaces.request.StudentRequest;
import datn.interfaces.response.StudentProjectInfoOfWaveResponse;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.StudentResponse;
import datn.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/API")
public class StudentReportController {

    @Autowired
    IReportService reportService;

    @RequestMapping(value = "/getStudentProjectInfoOfWaveResponse", method = RequestMethod.GET)
    public RestApiResponse<StudentProjectInfoOfWaveResponse> getStudentProjectInfoOfWaveResponse(String studentId, String projectWaveId){
        return reportService.getStudentProjectInfoOfWaveResponse(studentId, projectWaveId);
    }

    @RequestMapping(value = "/saveStudentReport", method = RequestMethod.POST)
    public RestApiResponse<StudentResponse> saveStudentReport(@RequestBody StudentReportRequest request){
        return reportService.saveStudentReport(request);
    }

}
