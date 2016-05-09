package datn.service.impl;

import datn.dao.entity.*;
import datn.dao.repository.*;
import datn.interfaces.request.AddStudentForProjectWaveRequest;
import datn.interfaces.request.AddTeacherForProjectWaveRequest;
import datn.interfaces.request.ProjectWaveRequest;
import datn.interfaces.response.ProjectWaveResponse;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.StudentResponse;
import datn.interfaces.response.TeacherResponse;
import datn.interfaces.util.ConvertObject;
import datn.interfaces.util.FormatSearchInput;
import datn.service.IProjectWaveService;
import datn.service.exceptions.ProjectWaveNotFoundException;
import datn.service.exceptions.StudentWaveIsExistedException;
import datn.service.exceptions.TeacherWaveIsExistedException;
import datn.service.exceptions.UserNotFoundException;
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

    @Autowired
    TeacherWaveRepository teacherWaveRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    public RestApiResponse<ProjectWaveResponse> addProjectWave(ProjectWaveRequest request) {
        ProjectWave projectWave = convertProjectWaveRequestToProjectWaveEntity(request);
        ProjectWave entity = projectWaveRepository.save(projectWave);
        addProgressReportsToProjectWave(entity, request.getReportTimes());
        ProjectWaveResponse response = convertProjectWaveEntityToProjectWaveResponse(entity);

        return new RestApiResponse<>(response);
    }

    @Override
    public RestApiResponse<ProjectWaveResponse> getProjectWave(String id) {
        ProjectWave projectWave = projectWaveRepository.findOne(id);
        ProjectWaveResponse response = convertProjectWaveEntityToProjectWaveResponse(projectWave);

        return new RestApiResponse<>(response);
    }

    @Override
    public RestApiResponse<ArrayList<StudentResponse>> getStudentsOfProjectWave(String id) {
        ArrayList<StudentWave> studentWaves = (ArrayList<StudentWave>) studentWaveRepository.findByProjectWave(new ProjectWave(id));
        ArrayList<StudentResponse> students = getStudentsFromStudentWaves(studentWaves);
        return new RestApiResponse<>(students);
    }

    @Override
    public RestApiResponse<ArrayList<TeacherResponse>> getTeachersOfProjectWave(String id) {
        ArrayList<TeacherWave> teacherWaves = (ArrayList<TeacherWave>) teacherWaveRepository.findByProjectWave(new ProjectWave(id));
        ArrayList<TeacherResponse> teacherResponses = getTeachersFromTeacherWaves(teacherWaves);
        return new RestApiResponse<>(teacherResponses);
    }

    private ArrayList<StudentResponse> getStudentsFromStudentWaves(ArrayList<StudentWave> studentWaves){
        ArrayList<StudentResponse> out = new ArrayList<>();
        for (int i =0; i<studentWaves.size(); i++){
            out.add(ConvertObject.convertStudentEntityToStudentResponse(studentWaves.get(i).getStudent()));
        }

        return out;
    }

    private ArrayList<TeacherResponse> getTeachersFromTeacherWaves(ArrayList<TeacherWave> teacherWaves){
        ArrayList<TeacherResponse> out = new ArrayList<>();
        for (int i =0; i<teacherWaves.size(); i++){
            out.add(ConvertObject.convertTeacherEntityToTeacherResponse(teacherWaves.get(i).getTeacher()));
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

    @Override
    public RestApiResponse<ProjectWaveResponse> deleteProjectWave(String projectWaveId) {
        if(projectWaveIsExist(projectWaveId))
            projectWaveRepository.delete(projectWaveId);

        return new RestApiResponse<>();
    }

    @Override
    public RestApiResponse<StudentResponse> addStudentForProjectWave(AddStudentForProjectWaveRequest request) {
        Student student = studentRepository.findByUsername(request.getStudentUsername());
        if(student == null)
            throw new UserNotFoundException(request.getStudentUsername());

        ProjectWave projectWave = projectWaveRepository.findOne(request.getProjectWaveId());
        if(projectWave == null)
            throw  new ProjectWaveNotFoundException(request.getProjectWaveId());

        List<StudentWave> sw = studentWaveRepository.findByStudentAndProjectWave(student, projectWave);
        if(sw.size() > 0)
            throw new StudentWaveIsExistedException(student.getId(), projectWave.getId());

        StudentWave studentWave = new StudentWave();
        studentWave.setStudent(student);
        studentWave.setProjectWave(projectWave);
        studentWaveRepository.save(studentWave);

        StudentResponse studentResponse = ConvertObject.convertStudentEntityToStudentResponse(student);
        RestApiResponse<StudentResponse> responseRestApiResponse =  new RestApiResponse<>(studentResponse);
        return responseRestApiResponse;
    }

    @Override
    public RestApiResponse<TeacherResponse> addTeacherForProjectWave(AddTeacherForProjectWaveRequest request) {
        Teacher teacher = teacherRepository.findByUsername(request.getTeacherUsername());

        if(teacher == null)
            throw new UserNotFoundException(request.getTeacherUsername());

        ProjectWave projectWave = projectWaveRepository.findOne(request.getProjectWaveId());
        if(projectWave == null)
            throw  new ProjectWaveNotFoundException(request.getProjectWaveId());

        List<TeacherWave> sw = teacherWaveRepository.findByTeacherAndProjectWave(teacher, projectWave);
        if(sw.size() > 0)
            throw new TeacherWaveIsExistedException(teacher.getId(), projectWave.getId());

        TeacherWave teacherWave = new TeacherWave();
        teacherWave.setTeacher(teacher);
        teacherWave.setProjectWave(projectWave);
        teacherWave.setMinNumberOfStudent(request.getNumberOfStudent());
        teacherWaveRepository.save(teacherWave);

        TeacherResponse teacherResponse = ConvertObject.convertTeacherEntityToTeacherResponse(teacher);
        RestApiResponse<TeacherResponse> responseRestApiResponse =  new RestApiResponse<>(teacherResponse);
        return responseRestApiResponse;
    }

    @Override
    public RestApiResponse<ArrayList<ProjectWaveResponse>> getWavesStudentJoined(String studentId) {
        Student student = studentRepository.findOne(studentId);
        ArrayList<ProjectWaveResponse> responses;
        if(student == null)
            throw new UserNotFoundException();
        else{
            ArrayList<StudentWave> studentWaves = studentWaveRepository.findByStudent(student);
            responses = new ArrayList<>();
            ProjectWave temp;
            for(int i = 0; i<studentWaves.size(); i++){
                temp = studentWaves.get(i).getProjectWave();
                responses.add(convertProjectWaveEntityToProjectWaveResponse(temp));
            }
        }
        return new RestApiResponse<>(responses);
    }

    @Override
    public RestApiResponse<ArrayList<ProjectWaveResponse>> getWavesTeacherJoined(String teacherId) {
        Teacher teacher = teacherRepository.findOne(teacherId);
        ArrayList<ProjectWaveResponse> responses;
        if(teacher == null)
            throw new UserNotFoundException();
        else{
            ArrayList<TeacherWave> teacherWaves = teacherWaveRepository.findByTeacher(teacher);
            responses = new ArrayList<>();
            ProjectWave temp;
            for(int i = 0; i<teacherWaves.size(); i++){
                temp = teacherWaves.get(i).getProjectWave();
                responses.add(convertProjectWaveEntityToProjectWaveResponse(temp));
            }
        }
        return new RestApiResponse<>(responses);
    }

    private boolean projectWaveIsExist(String id){
        ProjectWave projectWave = projectWaveRepository.findOne(id);
        if(projectWave != null)
            return true;

        return false;
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

        if(entity.getEndDay().compareTo(new Date()) >= 0)
            response.setStatus(1);

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
        projectWave.setDescription(request.getDescription());

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
