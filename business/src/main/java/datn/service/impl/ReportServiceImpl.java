package datn.service.impl;

import datn.dao.entity.*;
import datn.dao.repository.*;
import datn.interfaces.response.*;
import datn.interfaces.util.ConvertObject;
import datn.interfaces.util.DateUtil;
import datn.service.IProjectWaveService;
import datn.service.IReportService;
import datn.service.exceptions.ProjectWaveNotFoundException;
import datn.service.exceptions.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

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

        ArrayList<ReportResponse> reportResponses = new ArrayList<>();

        StudentReport studentReport;

        ArrayList<StudentReportDetail> studentReportDetails;
        StudentReportDetail studentReportDetail;

        ReportResponse reportResponse;
        ArrayList<ReportDetailResponse> reportDetailResponses;
        ReportDetailResponse reportDetailResponse;

        ArrayList<Report> reports = reportRepository.findByProjectWave(projectWave);
        Report report;
        for (int i =0; i<reports.size(); i++){
            report = reports.get(i);

            reportResponse = new ReportResponse();
            String start = DateUtil.convertDateTimeToString(report.getStartTime());
            String end = DateUtil.convertDateTimeToString(report.getEndTime());
            reportResponse.setTimeSubmitReportString(start + " - "+ end);
            reportResponse.setTimeSubmitReport(DateUtil.isDateInPeriodTime(new Date(), report.getStartTime(), report.getEndTime()));
            reportResponse.setOrdinal(report.getOrdinal());

            studentReport = studentReportRepository.findByStudentAndReport(student, report);
            if(studentReport != null){
                reportResponse.setId(studentReport.getId());
                reportResponse.setStatus(studentReport.getStatus());
                reportResponse.setCreatedDate(DateUtil.convertDateTimeToString(studentReport.getCreatedDate()));
                reportResponse.setStudentOpinion(studentReport.getStudentOpinion());
                reportResponse.setTeacherOpinion(studentReport.getTeacherOpinion());
                studentReportDetails = studentReportDetailRepository.findByStudentReport(studentReport);

                reportDetailResponses = new ArrayList<>();
                for(int j = 0; j < studentReportDetails.size(); j++){
                    studentReportDetail = studentReportDetails.get(j);
                    reportDetailResponse = new ReportDetailResponse();
                    reportDetailResponse.setStartTime(DateUtil.convertDateTimeToString(studentReportDetail.getStartTime()));
                    reportDetailResponse.setEndTime(DateUtil.convertDateTimeToString(studentReportDetail.getEndTime()));
                    reportDetailResponse.setWorkContent(studentReportDetail.getWorkContent());
                    reportDetailResponse.setNote(studentReportDetail.getNote());
                    reportDetailResponses.add(reportDetailResponse);
                }
                reportResponse.setReportDetails(reportDetailResponses);
            }
            reportResponses.add(reportResponse);
        }
        response.setReports(reportResponses);

        return new RestApiResponse<>(response);
    }

    private ProjectWaveResponse getProjectWaveResponse(ProjectWave projectWave) {
        return projectWaveService.convertProjectWaveEntityToProjectWaveResponse(projectWave);
    }

}
