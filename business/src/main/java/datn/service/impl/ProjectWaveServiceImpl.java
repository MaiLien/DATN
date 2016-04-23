package datn.service.impl;

import datn.dao.entity.ProjectWave;
import datn.dao.entity.Report;
import datn.dao.entity.Student;
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
import datn.interfaces.util.FormatSearchInput;
import datn.interfaces.util.JsonUtil;
import datn.service.IProjectWaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectWaveServiceImpl implements IProjectWaveService{

    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

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
        response.setDescription("Đợt đồ án dành cho lớp 11TCLC");
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

    @Override
    public RestApiResponse<ArrayList<ProjectWaveResponse>> getAllProjectWave(){
        ArrayList<ProjectWave> projectWaves = (ArrayList<ProjectWave>) projectWaveRepository.findAll();
        ArrayList<ProjectWaveResponse> projectWaveResponses = convertProjectWaveEntitiesToProjectWaveResponses(projectWaves);

        return new RestApiResponse<>(projectWaveResponses);
    }

    @Override
    public RestApiResponse<Page<ProjectWaveResponse>> getPageProjectWaves(int pageIndex, int sizeOfPage, String searchInput) {
        PageRequest pageable = new PageRequest(pageIndex, sizeOfPage);
        Page<ProjectWave> projectWavePage = null;
        if(searchInput == null || "".equals(searchInput)){
            projectWavePage = projectWaveRepository.findAll(pageable);
        }else{
            searchInput = FormatSearchInput.formatSearchInput(searchInput);
            projectWavePage = projectWaveRepository.findBySearchInput(pageable, searchInput);
        }
        Page<ProjectWaveResponse> projectWaveResponsePage = convertProjectWaveEntityPageToProjectWaveResponsePage(projectWavePage, pageable);

        RestApiResponse<Page<ProjectWaveResponse>> response = new RestApiResponse<>(projectWaveResponsePage);
        return  response;
    }

    private Page<ProjectWaveResponse> convertProjectWaveEntityPageToProjectWaveResponsePage(Page<ProjectWave> projectWavePage, PageRequest pageable){
        ArrayList<ProjectWaveResponse> projectWaveResponses = convertProjectWaveEntitiesToProjectWaveResponses(new ArrayList<>(projectWavePage.getContent()));
        Page<ProjectWaveResponse> projectWaveResponsePage = new PageImpl<>(projectWaveResponses, pageable, projectWavePage.getTotalElements());
        return projectWaveResponsePage;
    }

    private ArrayList<ProjectWaveResponse> convertProjectWaveEntitiesToProjectWaveResponses(ArrayList<ProjectWave> entities){
        ArrayList<ProjectWaveResponse> responses = new ArrayList<>();
        ProjectWaveResponse temp;
        for (int i = 0; i<entities.size(); i++){
            temp = convertProjectWaveEntityToProjectWaveResponse(entities.get(i));
            responses.add(temp);
        }

        return responses;
    }

    private ProjectWaveResponse convertProjectWaveEntityToProjectWaveResponse(ProjectWave entity){
        ProjectWaveResponse response = new ProjectWaveResponse();
        response.setId(entity.getId());
        response.setSchoolYear(entity.getSchoolYear());
        response.setSemester(entity.getSemester());
        response.setDescription(entity.getDescription());
        response.setStartTimeAndEndTime(getTimeForStudentDefend(entity));
        response.setTimeForTeacherProposesStudent(getTimeForTeacherProposesStudent(entity));
        response.setTimeForStudentRegisterTeacher(getTimeForStudentRegisterTeacher(entity));
        response.setTimeForStudentSubmitProject(getTimeForStudentSubmitProject(entity));
        response.setTimeForStudentDefend(getTimeForStudentStudentDefend(entity));
        response.setReportTimes(getReportTimes(entity));
        return response;
    }

    private ArrayList<String> getReportTimes(ProjectWave entity){
        ArrayList<String> out = new ArrayList<>();
        ArrayList<Report> reports = reportRepository.findByProjectWave(entity);
        String start;
        String end;
        for (int i =0; i<reports.size(); i++){
            start = dateTimeFormat.format(reports.get(i).getStartTime());
            end = dateTimeFormat.format(reports.get(i).getEndTime());
            out.add(start + " - " + end);
        }

        return out;
    }

    private String getTimeForStudentStudentDefend(ProjectWave entity){
        String start;
        String end;

        start = dateFormat.format(entity.getStartTimeForStudentSubmitProject());
        end = dateFormat.format(entity.getEndTimeForStudentSubmitProject());

        String out = start + " - " + end;
        return out;
    }

    private String getTimeForStudentSubmitProject (ProjectWave entity){
        String start;
        String end;

        start = dateTimeFormat.format(entity.getStartTimeForStudentSubmitProject());
        end = dateTimeFormat.format(entity.getEndTimeForStudentSubmitProject());

        String out = start + " - " + end;
        return out;
    }

    private String getTimeForStudentRegisterTeacher(ProjectWave entity){
        String start;
        String end;

        start = dateTimeFormat.format(entity.getStartTimeForStudentRegisterTeacher());
        end = dateTimeFormat.format(entity.getEndTimeForStudentRegisterTeacher());

        String out = start + " - " + end;
        return out;
    }

    private String getTimeForTeacherProposesStudent(ProjectWave entity){
        String start;
        String end;

        start = dateTimeFormat.format(entity.getStartTimeForTeacherProposeStudent());
        end = dateTimeFormat.format(entity.getEndTimeForTeacherProposeStudent());

        String out = start + " - " + end;
        return out;
    }

    private String getTimeForStudentDefend(ProjectWave entity){
        String start;
        String end;

        start = dateFormat.format(entity.getStartDay());
        end = dateFormat.format(entity.getEndDay());

        String out = start + " - " + end;
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

        Date startDate = null;
        Date endDate = null;
        try {
            startDate = dateFormat.parse(start);
            endDate = dateFormat.parse(end);
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

        Date startDate = null;
        Date endDate = null;
        try {
            startDate = dateTimeFormat.parse(start);
            endDate = dateTimeFormat.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<Date> out = new ArrayList<Date>();
        out.add(startDate);
        out.add(endDate);

        return out;
    }

}
