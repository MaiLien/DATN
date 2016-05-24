package datn.webservice.controller;

import datn.interfaces.request.StudentResearchTopicRequest;
import datn.interfaces.request.StudentReportRequest;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.StudentProjectInfoOfWaveResponse;
import datn.interfaces.response.StudentResearchTopicResponse;
import datn.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/API")
public class StudentWaveController {


@Autowired
IReportService reportService;

    @RequestMapping(value = "/getStudentProjectInfoOfWaveResponse", method = RequestMethod.GET)
    public RestApiResponse<StudentProjectInfoOfWaveResponse> getStudentProjectInfoOfWaveResponse(String studentId, String projectWaveId){
        return reportService.getStudentProjectInfoOfWaveResponse(studentId, projectWaveId);
    }

    @RequestMapping(value = "/saveStudentReport", method = RequestMethod.POST)
    public RestApiResponse<?> saveStudentReport(@RequestBody StudentReportRequest request){
        return reportService.saveStudentReport(request);
    }

    @RequestMapping(value = "/getStudentWaveResearchTopic", method = RequestMethod.GET)
    public RestApiResponse<StudentResearchTopicResponse> getStudentWaveResearchTopic(String studentId, String projectWaveId){
        return reportService.getStudentWaveResearchTopic(studentId, projectWaveId);
    }


    @RequestMapping(value = "/saveResearchTopic", method = RequestMethod.POST)
    public RestApiResponse<StudentResearchTopicResponse> saveResearchTopic(@RequestBody StudentResearchTopicRequest request){
        return reportService.saveResearchTopic(request);
    }

}
