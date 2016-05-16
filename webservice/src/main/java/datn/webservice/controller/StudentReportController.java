package datn.webservice.controller;

import datn.interfaces.response.ReportsOfWaveResponse;
import datn.interfaces.response.RestApiResponse;
import datn.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/API")
public class StudentReportController {

    @Autowired
    IReportService reportService;

    @RequestMapping(value = "/getStudentReportsOfWave", method = RequestMethod.GET)
    public RestApiResponse<ReportsOfWaveResponse> getStudentReportsOfWave(String studentId, String projectWaveId){
        return reportService.getStudentReportsOfWave(studentId, projectWaveId);
    }

}
