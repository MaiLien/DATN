package datn.service.impl;

import datn.dao.entity.*;
import datn.dao.repository.*;
import datn.interfaces.response.*;
import datn.interfaces.util.ConvertObject;
import datn.interfaces.util.DateUtil;
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
    StudentWaveRepository studentWaveRepository;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    StudentReportRepository studentReportRepository;

    @Autowired
    StudentReportDetailRepository studentReportDetailRepository;

    @Autowired
    ProjectWaveServiceImpl projectWaveService;

    @Override
    public RestApiResponse<StudentProjectInfoOfWaveResponse> getStudentProjectInfoOfWaveResponse(String studentId, String projectWaveId) {
        StudentProjectInfoOfWaveResponse response = new StudentProjectInfoOfWaveResponse();

        Student student = saveStudentForProjectInfo(studentId, response);

        ProjectWave projectWave = saveProjectWaveForProjectInfo(projectWaveId, response);

        StudentWaveResponse studentWaveResponse = getStudentWaveForProjectInfo(student, projectWave);
        response.setProjectInforResponse(studentWaveResponse);

        return new RestApiResponse<>(response);
    }

    private StudentWaveResponse getStudentWaveForProjectInfo(Student student, ProjectWave projectWave) {

        StudentWaveResponse studentWaveResponse = saveProjectInfo(student, projectWave);

        StudentReport studentReport;

        ArrayList<StudentReportDetail> studentReportDetails;
        StudentReportDetail studentReportDetail;

        ArrayList<ReportResponse> reportResponses = new ArrayList<>();
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
                reportResponse.setPercentFinish(studentReport.getPercentFinish());

                studentReportDetails = studentReportDetailRepository.findByStudentReport(studentReport);
                reportDetailResponses = new ArrayList<>();
                String startTime;
                String endTime;
                for(int j = 0; j < studentReportDetails.size(); j++){
                    studentReportDetail = studentReportDetails.get(j);
                    reportDetailResponse = new ReportDetailResponse();
                    startTime = DateUtil.convertDateToString(studentReportDetail.getStartTime());
                    endTime = DateUtil.convertDateToString(studentReportDetail.getEndTime());
                    reportDetailResponse.setStartTimeAndEndTime(startTime + " - " + endTime);
                    reportDetailResponse.setWorkContent(studentReportDetail.getWorkContent());
                    reportDetailResponse.setNote(studentReportDetail.getNote());
                    reportDetailResponses.add(reportDetailResponse);
                }
                reportResponse.setReportDetails(reportDetailResponses);
            }
            reportResponses.add(reportResponse);
        }
        studentWaveResponse.setReports(reportResponses);

        return studentWaveResponse;
    }

    private StudentWaveResponse saveProjectInfo(Student student, ProjectWave projectWave) {
        StudentWaveResponse studentWaveResponse = new StudentWaveResponse();

        ArrayList<StudentWave> studentWaves = studentWaveRepository.findByStudentAndProjectWave(student, projectWave);
        StudentWave studentWave;
        if (studentWaves.size() == 1){
            studentWave = studentWaves.get(0);
            studentWaveResponse.setId(studentWave.getId());
            studentWaveResponse.setStatus(studentWave.getStatus());
            studentWaveResponse.setTopic(studentWave.getTopic());
            studentWaveResponse.setDescription(studentWave.getDescription());
        }

        return studentWaveResponse;
    }

    private ProjectWave saveProjectWaveForProjectInfo(String projectWaveId, StudentProjectInfoOfWaveResponse response) {
        ProjectWave projectWave = projectWaveRepository.findOne(projectWaveId);
        if(projectWave == null)
            throw new ProjectWaveNotFoundException(projectWaveId);
        else response.setProjectWave(getProjectWaveResponse(projectWave));

        return projectWave;
    }

    private Student saveStudentForProjectInfo(String studentId, StudentProjectInfoOfWaveResponse response) {
        Student student = studentRepository.findOne(studentId);
        if(student == null)
            throw new StudentNotFoundException(studentId);
        else response.setStudent(ConvertObject.convertStudentEntityToStudentResponse(student));

        return student;
    }

    private ProjectWaveResponse getProjectWaveResponse(ProjectWave projectWave) {
        return projectWaveService.convertProjectWaveEntityToProjectWaveResponse(projectWave);
    }

}
