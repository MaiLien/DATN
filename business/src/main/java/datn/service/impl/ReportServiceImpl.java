package datn.service.impl;

import datn.dao.entity.*;
import datn.dao.repository.*;
import datn.interfaces.response.*;
import datn.interfaces.util.ConvertObject;
import datn.service.IProjectWaveService;
import datn.service.IReportService;
import datn.service.exceptions.ProjectWaveNotFoundException;
import datn.service.exceptions.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    StudentRepository studentRepository ;

    @Autowired
    ProjectWaveRepository projectWaveRepository;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    StudentReportRepository studentReportRepository;

    @Autowired
    StudentReportDetailRepository studentReportDetailRepository;

    @Autowired
    ProjectWaveServiceImpl projectWaveService;

    @Override
    public RestApiResponse<ReportsOfWaveResponse> getStudentReportsOfWave(String studentId, String projectWaveId) {
        ReportsOfWaveResponse response = new ReportsOfWaveResponse();
//TODO implement don't finish
        Student student = studentRepository.findOne(studentId);
        if(student == null)
            throw new StudentNotFoundException(studentId);
        else response.setStudent(ConvertObject.convertStudentEntityToStudentResponse(student));

        ProjectWave projectWave = projectWaveRepository.findOne(projectWaveId);
        if(projectWave == null)
            throw new ProjectWaveNotFoundException(projectWaveId);
        else response.setProjectWave(getProjectWaveResponse(projectWave));

        ArrayList<Report> reports = reportRepository.findByProjectWave(projectWave);
        Report report;
        StudentReport studentReport;
        StudentReportDetail studentReportDetail;
        ArrayList<ReportResponse> reportResponses = new ArrayList<>();
        ReportResponse reportResponse;
        ReportDetailResponse reportDetailResponse;
        for (int i =0; i<reports.size(); i++){
            report = reports.get(i);
            studentReport = studentReportRepository.findByStudentAndReport(student, report);
            if(studentReport != null){
                reportResponse = new ReportResponse();
//                reportResponse.setTimeSubmitReportString();
                studentReportDetail = studentReportDetailRepository.findByStudentReport(studentReport);

                reportResponses.add(reportResponse);
            }
        }
        response.setReports(reportResponses);

        return null;
    }

    private ProjectWaveResponse getProjectWaveResponse(ProjectWave projectWave) {
        return projectWaveService.convertProjectWaveEntityToProjectWaveResponse(projectWave);
    }

}
