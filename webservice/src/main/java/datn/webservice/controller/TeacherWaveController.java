package datn.webservice.controller;

import datn.interfaces.request.ProposeStudentRequest;
import datn.interfaces.request.StudentResearchTopicRequest;
import datn.interfaces.response.*;
import datn.service.IProjectWaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/API")
public class TeacherWaveController {

    @Autowired
    IProjectWaveService projectWaveService;

    @RequestMapping(value = "/proposeStudent", method = RequestMethod.POST)
    public RestApiResponse<ProposeStudentResponse> proposeStudent(@RequestBody ProposeStudentRequest request){
        return projectWaveService.proposeStudent(request);
    }

    @RequestMapping(value = "/getReportToApprove", method = RequestMethod.GET)
    public RestApiResponse<ArrayList<ReportToApproveResponse>> getReportToApprove(String teacherId){
        return projectWaveService.getReportToApprove(teacherId);
    }

}
