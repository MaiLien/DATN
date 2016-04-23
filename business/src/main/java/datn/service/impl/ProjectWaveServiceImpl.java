package datn.service.impl;

import datn.dao.entity.ProjectWave;
import datn.dao.entity.Report;
import datn.dao.entity.StudentWave;
import datn.dao.repository.ProjectWaveRepository;
import datn.dao.repository.ReportRepository;
import datn.dao.repository.StudentWaveRepository;
import datn.interfaces.request.ProjectWaveRequest;
import datn.interfaces.response.ProjectWaveResponse;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.StudentResponse;
import datn.interfaces.response.TeacherResponse;
import datn.interfaces.util.ConvertStudentObject;
import datn.interfaces.util.JsonUtil;
import datn.service.IProjectWaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectWaveServiceImpl implements IProjectWaveService{

    @Autowired
    ProjectWaveRepository projectWaveRepository;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    StudentWaveRepository studentWaveRepository;

    static ProjectWaveResponse virtualProjectWaveResponse;

    public RestApiResponse<ProjectWaveResponse> addProjectWave(ProjectWaveRequest request) {
        ProjectWave projectWave = convertProjectWaveRequestToProjectWaveEntity(request);
        ProjectWave entity = projectWaveRepository.save(projectWave);
        addProgressReportsToProjectWave(projectWave, request.getReportTimes());
        ProjectWaveResponse response = convertProjectWaveRequestToProjectWaveResponse(entity, request);

        virtualProjectWaveResponse = response;
        System.out.println("virtualProjectWaveResponse"+JsonUtil.convertObjectToJson(virtualProjectWaveResponse));

        return new RestApiResponse<>(response);
    }

    @Override
    public RestApiResponse<ProjectWaveResponse> getProjectWave(String id) {
        RestApiResponse<ProjectWaveResponse> out = new RestApiResponse<>();

        ProjectWaveResponse response = new ProjectWaveResponse();
        response.setId(id);
        out.setBody(response);

        if(virtualProjectWaveResponse != null)
            out.setBody(virtualProjectWaveResponse);

        return out;
    }

    @Override
    public RestApiResponse<ArrayList<StudentResponse>> getStudentsOfProjectWave(String id) {
        ArrayList<StudentWave> studentWaves = (ArrayList<StudentWave>) studentWaveRepository.findByProjectWave(new ProjectWave(id));
        ArrayList<StudentResponse> students = getStudentsFromStudentWaves(studentWaves);
        return new RestApiResponse<>(students);
    }

    @Override
    public RestApiResponse<TeacherResponse> getTeachersOfProjectWave(String id) {
        return null;
    }

    private ArrayList<StudentResponse> getStudentsFromStudentWaves(ArrayList<StudentWave> studentWaves){
        ArrayList<StudentResponse> out = new ArrayList<>();
        for (int i =0; i<studentWaves.size(); i++){
            out.add(ConvertStudentObject.convertStudentEntityToStudentResponse(studentWaves.get(i).getStudent()));
        }

        return out;
    }

    private ProjectWaveResponse convertProjectWaveRequestToProjectWaveResponse(ProjectWave entity, ProjectWaveRequest request){
        ProjectWaveResponse response = new ProjectWaveResponse();
        response.setId(entity.getId());
        response.setSchoolYear(request.getSchoolYear());
        response.setSemester(request.getSemester());
        response.setStartTimeAndEndTime(request.getStartTimeAndEndTime());
        response.setTimeForTeacherProposesStudent(request.getTimeForTeacherProposesStudent());
        response.setTimeForStudentRegisterTeacher(request.getTimeForStudentRegisterTeacher());
        response.setTimeForStudentSubmitProject(request.getTimeForStudentSubmitProject());
        response.setTimeForStudentDefend(request.getTimeForStudentDefend());
        response.setReportTimes(request.getReportTimes());
        response.setDescription(request.getDescription());

        return response;
    }

    private ProjectWave convertProjectWaveRequestToProjectWaveEntity(ProjectWaveRequest request){
        ProjectWave projectWave = new ProjectWave();
        projectWave.setSchoolYear(request.getSchoolYear());
        projectWave.setSemester(request.getSemester());
        setStartTimeAndEndTime(projectWave, request.getStartTimeAndEndTime());
        setTimeForTeacherProposesStudent(projectWave, request.getTimeForTeacherProposesStudent());
        setTimeForStudentRegisterTeacher(projectWave, request.getTimeForStudentRegisterTeacher());
        setTimeForStudentSubmitProject(projectWave, request.getTimeForStudentSubmitProject());
        setTimeForStudentDefend(projectWave, request.getTimeForStudentDefend());
        projectWave.setHowManyTimeProgressReport(request.getReportTimes().size());

        return projectWave;
    }

    private void addProgressReportsToProjectWave(ProjectWave projectWave, List<String> reportTimes) {
        List<Report> reports = new ArrayList<>();
        ArrayList<Date> dates;
        Report report;

        for(int i=0; i<reportTimes.size(); i++){
            report = new Report();
            dates = convertStringArrayDateTimeToDate(reportTimes.get(i));
            report.setStartTime(dates.get(0));
            report.setEndTime(dates.get(1));
            report.setProjectWave(projectWave);
            report = reportRepository.save(report);

            reports.add(report);
        }

        projectWave.setReports(reports);
    }

    private void setTimeForStudentDefend(ProjectWave projectWave, String timeForStudentDefend) {
        ArrayList<Date> dates = convertStringArrayDateToDate(timeForStudentDefend);

        projectWave.setStartTimeForDefendingProject(dates.get(0));
        projectWave.setEndTimeForDefendingProject(dates.get(1));
    }

    private void setTimeForStudentSubmitProject(ProjectWave projectWave, String timeForStudentSubmitProject) {
        ArrayList<Date> dateTimes = convertStringArrayDateTimeToDate(timeForStudentSubmitProject);

        projectWave.setStartTimeForStudentSubmitProject(dateTimes.get(0));
        projectWave.setEndTimeForStudentSubmitProject(dateTimes.get(1));
    }

    private void setTimeForStudentRegisterTeacher(ProjectWave projectWave, String timeForStudentRegisterTeacher) {
        ArrayList<Date> dateTimes = convertStringArrayDateTimeToDate(timeForStudentRegisterTeacher);

        projectWave.setStartTimeForStudentRegisterTeacher(dateTimes.get(0));
        projectWave.setEndTimeForStudentRegisterTeacher(dateTimes.get(1));
    }

    private void setTimeForTeacherProposesStudent(ProjectWave projectWave, String timeForTeacherProposesStudent) {
        ArrayList<Date> dateTimes = convertStringArrayDateTimeToDate(timeForTeacherProposesStudent);

        projectWave.setStartTimeForTeacherProposeStudent(dateTimes.get(0));
        projectWave.setEndTimeForTeacherProposeStudent(dateTimes.get(1));
    }

    private void setStartTimeAndEndTime(ProjectWave projectWave, String startTimeAndEndTime) {
        ArrayList<Date> dates = convertStringArrayDateToDate(startTimeAndEndTime);

        projectWave.setStartDay(dates.get(0));
        projectWave.setEndDay(dates.get(1));
    }

    private ArrayList<Date> convertStringArrayDateToDate(String s) {
        String[] arrTemp = s.split(" - ");
        String start = arrTemp[0];
        String end = arrTemp[1];

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = df.parse(start);
            endDate = df.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<Date> out = new ArrayList<Date>();
        out.add(startDate);
        out.add(endDate);

        return out;
    }

    private ArrayList<Date> convertStringArrayDateTimeToDate(String s){
        String[] arrTemp = s.split(" - ");
        String start = arrTemp[0];
        String end = arrTemp[1];

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = df.parse(start);
            endDate = df.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<Date> out = new ArrayList<Date>();
        out.add(startDate);
        out.add(endDate);

        return out;
    }

}
