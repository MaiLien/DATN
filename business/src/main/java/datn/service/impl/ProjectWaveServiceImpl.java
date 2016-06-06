package datn.service.impl;

import datn.dao.entity.*;
import datn.dao.repository.*;
import datn.interfaces.constant.MessageCodeConstant;
import datn.interfaces.request.*;
import datn.interfaces.response.*;
import datn.interfaces.util.ConvertObject;
import datn.interfaces.util.DateUtil;
import datn.interfaces.util.FormatSearchInput;
import datn.service.IProjectWaveService;
import datn.service.exceptions.*;
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

    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    StudentReportRepository studentReportRepository;

    @Autowired
    StudentReportDetailRepository studentReportDetailRepository;

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
        ArrayList<StudentResponse> students;
        ProjectWave projectWave = projectWaveRepository.findOne(id);
        if(projectWave == null)
            throw new ProjectWaveException(MessageCodeConstant.PROJECT_WAVE_NOT_FOUND, id);
        else{
            ArrayList<StudentWave> studentWaves = (ArrayList<StudentWave>) studentWaveRepository.findByProjectWave(projectWave);
            students = getStudentsFromStudentWaves(studentWaves);
        }
        return new RestApiResponse<>(students);
    }

    @Override//TODO implement is not done
    public RestApiResponse<ArrayList<StudentOfProjectWaveToProposeResponse>> getStudentsOfProjectWaveToPropose(String teacherId, String projectWaveId) {
        ArrayList<StudentOfProjectWaveToProposeResponse> responses = new ArrayList<>();
        Teacher teacher = teacherRepository.findOne(teacherId);
        ProjectWave projectWave = projectWaveRepository.findOne(projectWaveId);

        ArrayList<StudentWave> studentWaves = (ArrayList<StudentWave>) studentWaveRepository.findByProjectWave(projectWave);
        StudentOfProjectWaveToProposeResponse studentOfProjectWaveToProposeResponse;
        Student student;
        Assignment assignment;
        ArrayList<Assignment> assignments;
        for (int i =0; i < studentWaves.size(); i++){
            studentOfProjectWaveToProposeResponse = new StudentOfProjectWaveToProposeResponse();
            student = studentWaves.get(i).getStudent();
            studentOfProjectWaveToProposeResponse.setStudentResponse(ConvertObject.convertStudentEntityToStudentResponse(student));

            assignment = assignmentRepository.findByStudentAndTeacherAndWave(student, teacher, projectWave);
            if(assignment != null){
                studentOfProjectWaveToProposeResponse.setProposed(true);
            }else{
                assignments = assignmentRepository.findByStudentAndWave(student, projectWave);
                if(assignments.size() > 0){
                    studentOfProjectWaveToProposeResponse.setProposedByAnotherTeacher(true);
                    studentOfProjectWaveToProposeResponse.setNote("Đã được hướng dẫn bởi giảng viên " + assignments.get(0).getTeacher().getName());
                }
            }
            responses.add(studentOfProjectWaveToProposeResponse);
        }

        return new RestApiResponse<>(responses);
    }

    @Override
    public RestApiResponse<ArrayList<TeacherResponse>> getTeachersToAddForProjectWave(String projectWaveId) {
        ProjectWave projectWave = projectWaveRepository.findOne(projectWaveId);

        ArrayList<Teacher> teachers = teacherRepository.findTeacherNotJoinProjectWave(projectWave);
        ArrayList<TeacherResponse> responses = new ArrayList<>();
        TeacherResponse teacherResponse;
        for (int i = 0; i<teachers.size(); i++){
            teacherResponse = ConvertObject.convertTeacherEntityToTeacherResponse(teachers.get(i));
            responses.add(teacherResponse);
        }

        return new RestApiResponse<>(responses);
    }

    @Override
    public RestApiResponse<?> addTeachersForWave(AddTeachersForWaveRequest request) {
        ProjectWave projectWave = projectWaveRepository.findOne(request.getProjectWaveId());
        Teacher teacher;
        TeacherWave teacherWave;
        for (int i = 0; i<request.getTeachers().size(); i++){
            teacher = teacherRepository.findOne(request.getTeachers().get(i).getTeacherId());

            teacherWave = new TeacherWave();
            teacherWave.setTeacher(teacher);
            teacherWave.setProjectWave(projectWave);
            teacherWave.setMinNumberOfStudent(request.getTeachers().get(i).getMaxGuide());
            teacherWave.setMaxNumberOfStudent(request.getTeachers().get(i).getMaxGuide());
            teacherWaveRepository.save(teacherWave);
        }
        return new RestApiResponse<>();
    }

    @Override
    public RestApiResponse<?> deleteTeacherFromWave(String teacherId, String projectWaveId) {
        Teacher teacher = teacherRepository.findOne(teacherId);
        ProjectWave projectWave = projectWaveRepository.findOne(projectWaveId);

        ArrayList<TeacherWave> teacherWave = teacherWaveRepository.findByTeacherAndProjectWave(teacher, projectWave);
        if(teacherWave.size() > 0){
            teacherWaveRepository.delete(teacherWave.get(0));
        }
        return new RestApiResponse<>();
    }

    @Override
    public RestApiResponse<ArrayList<StudentResponse>> getListStudentWhoTeacherGuideInWave(String teacherId, String waveId) {
        Teacher teacher = teacherRepository.findOne(teacherId);
        ProjectWave projectWave = projectWaveRepository.findOne(waveId);
        ArrayList<StudentWave> students = studentWaveRepository.findByTeacherAndProjectWave(teacher, projectWave);
        ArrayList<StudentResponse> out = getStudentsFromStudentWaves(students);

        return new RestApiResponse<>(out);
    }

    @Override
    public RestApiResponse<?> approveReport(ApproveReportRequest request) {
        StudentReport studentReport = studentReportRepository.findOne(request.getReportId());
        studentReport.setStatus(request.getStatus());
        studentReport.setTeacherOpinion(request.getTeacherOpinions());
        studentReportRepository.save(studentReport);

        return new RestApiResponse<>();
    }

    @Override
    public RestApiResponse<ArrayList<ReportToApproveResponse>> getReportToApprove(String teacherId){
        ArrayList<ReportToApproveResponse> out = new ArrayList<>();

        Teacher teacher = teacherRepository.findOne(teacherId);
        ArrayList<Assignment> assignments = assignmentRepository.findByTeacher(teacher);
        ProjectWave projectWave;
        Student student;
        StudentWave studentWave;
        StudentReport studentReport;
        ArrayList<Report> reports;
        Report report;
        ReportToApproveResponse response = null;
        ArrayList<StudentReportDetail> studentReportDetails;
        ArrayList<ReportDetailResponse> reportDetailResponses;
        ReportDetailResponse reportDetailResponse;
        StudentReportDetail studentReportDetail;
        for(int i = 0; i < assignments.size(); i++){
            projectWave = assignments.get(i).getProjectWave();
            student = assignments.get(i).getStudent();
            studentWave = getStudentWave(student, projectWave);
            reports = reportRepository.findByProjectWave(projectWave);
            for (int j = 0; j<reports.size(); j++){
                report = reports.get(j);
                studentReport = studentReportRepository.findByStudentAndReport(student, report);
                if(studentReport != null && studentReport.getStatus() == 1){
                    response = new ReportToApproveResponse();
                    response.setId(studentReport.getId());
                    response.setStudentId(student.getId());
                    response.setStudentUserName(student.getUsername());
                    response.setStudentName(student.getName());
                    response.setStudentClass(student.getClass_());

                    response.setTopic(studentWave.getTopic());
                    response.setDescription(studentWave.getDescription());
                    response.setTimeSubmitReport(DateUtil.isDateInPeriodTime(new Date(), report.getStartTime(), report.getEndTime()));
                    response.setTimeSubmitReportString(getPeriodDateString(report.getStartTime(), report.getEndTime()));
                    response.setOrdinal(report.getOrdinal());

                    response.setStatus(studentReport.getStatus());
                    response.setCreatedDate(DateUtil.convertDateTimeToString(studentReport.getCreatedDate()));
                    response.setStudentOpinion(studentReport.getStudentOpinion());
                    response.setTeacherOpinion(studentReport.getTeacherOpinion());
                    response.setPercentFinish(studentReport.getPercentFinish());

                    studentReportDetails = studentReportDetailRepository.findByStudentReport(studentReport);
                    reportDetailResponses = new ArrayList<>();
                    String startTime;
                    String endTime;
                    for(int k = 0; k < studentReportDetails.size(); k++){
                        studentReportDetail = studentReportDetails.get(k);
                        reportDetailResponse = new ReportDetailResponse();
                        startTime = DateUtil.convertDateToString(studentReportDetail.getStartTime());
                        endTime = DateUtil.convertDateToString(studentReportDetail.getEndTime());
                        reportDetailResponse.setStartTimeAndEndTime(startTime + " - " + endTime);
                        reportDetailResponse.setWorkContent(studentReportDetail.getWorkContent());
                        reportDetailResponse.setNote(studentReportDetail.getNote());
                        reportDetailResponses.add(reportDetailResponse);
                    }
                    response.setReportDetails(reportDetailResponses);

                    out.add(response);
                }
            }

        }

        return new RestApiResponse<>(out);
    }

    @Override
    public RestApiResponse<ArrayList<AssignmentDispalyedInStudentResponse>> getAssignmentsDispalyedInStudents(String projectWaveId) {
        ArrayList<AssignmentDispalyedInStudentResponse> out = new ArrayList<>();
        AssignmentDispalyedInStudentResponse responseItem;
        ProjectWave projectWave = projectWaveRepository.findOne(projectWaveId);
        Student student;
        ArrayList<StudentWave> studentWaves = studentWaveRepository.findByProjectWave(projectWave);
        ArrayList<Assignment> assignments;
        for (int i = 0; i<studentWaves.size(); i++){
            responseItem = new AssignmentDispalyedInStudentResponse();
            student = studentWaves.get(i).getStudent();
            responseItem.setStudent(ConvertObject.convertStudentEntityToStudentResponse(student));
            responseItem.setTeacher(getTeacherGuideStudent(student, projectWave));

            out.add(responseItem);
        }

        return new RestApiResponse<>(out);
    }

    private ArrayList<TeacherResponse> getTeacherGuideStudent(Student student, ProjectWave projectWave) {
        ArrayList<Assignment> assignments = assignmentRepository.findByStudentAndWave(student, projectWave);
        ArrayList<TeacherResponse> responses = new ArrayList<>();
        for(int i = 0; i<assignments.size(); i++){
            responses.add(ConvertObject.convertTeacherEntityToTeacherResponse(assignments.get(i).getTeacher()));
        }

        return responses;
    }

    private String getPeriodDateString(Date dateStart, Date dateEnd){
        String start = DateUtil.convertDateTimeToString(dateStart);
        String end = DateUtil.convertDateTimeToString(dateEnd);

        return start + " - "+ end;
    }

    private StudentWave getStudentWave(Student student, ProjectWave projectWave){
        ArrayList<StudentWave> studentWaves = studentWaveRepository.findByStudentAndProjectWave(student, projectWave);
        if(studentWaves.size() > 0)
            return studentWaves.get(0);
        else
            return null;
    }

    @Override
    public RestApiResponse<ChangeAssignmentResponse> getTeachersToChangeAssignment(String studentId, String projectWaveId) {
        Student student = studentRepository.findOne(studentId);
        ProjectWave projectWave = projectWaveRepository.findOne(projectWaveId);

        ChangeAssignmentResponse response = new ChangeAssignmentResponse();
        response.setStudent(ConvertObject.convertStudentEntityToStudentResponse(student));
        response.setGuideTeachers(getTeacherGuideStudent(student, projectWave));
        response.setTeacherOptions(getTeacherOptionsToChangeAssignment(student, projectWave));

        return new RestApiResponse<>(response);
    }

    @Override
    public RestApiResponse<?> changeAssignment(ChangeAssignmentRequest request) {
        Student student = studentRepository.findOne(request.getStudentId());
        ProjectWave projectWave = projectWaveRepository.findOne(request.getProjectWaveId());

        ArrayList<Assignment> assignments = assignmentRepository.findByStudentAndWave(student, projectWave);
        for (int i = 0; i < assignments.size(); i++){
            assignmentRepository.delete(assignments.get(i));
        }

        Assignment assignment;
        Teacher teacher;
        for (int i = 0; i < request.getTeachers().size(); i++){
            teacher = teacherRepository.findOne(request.getTeachers().get(i));
            assignment = new Assignment();
            assignment.setTeacher(teacher);
            assignment.setStudent(student);
            assignment.setProjectWave(projectWave);

            assignmentRepository.save(assignment);
        }

        return new RestApiResponse<>();
    }

    private ArrayList<TeacherToChangeAssignmentResponse> getTeacherOptionsToChangeAssignment(Student student, ProjectWave projectWave) {
        ArrayList<TeacherToChangeAssignmentResponse> responses = new ArrayList<>();

        ArrayList<TeacherWave> teacherWaves = teacherWaveRepository.findByProjectWave(projectWave);
        ArrayList<Teacher> teachers = getTeacherEntitiesFromTeacherWaves(teacherWaves);
        TeacherToChangeAssignmentResponse teacherResponse;
        Teacher teacherEntity;
        for(int i = 0; i < teachers.size(); i++){
            teacherEntity = teachers.get(i);
            teacherResponse = convertTeacherEntityToTeacherToChangeAssignmentResponse(teacherEntity);
            teacherResponse.setMaxGuide(getMaxGuideForTeacher(teacherEntity, projectWave));
            teacherResponse.setActualGuide(getActualGuideForTeacher(teacherEntity, projectWave));
            teacherResponse.setGuideStudent(isGuideStudent(student, teacherEntity, projectWave));

            responses.add(teacherResponse);
        }

        return responses;
    }

    private boolean isGuideStudent(Student student, Teacher teacher, ProjectWave projectWave) {
        Assignment assignment = assignmentRepository.findByStudentAndTeacherAndWave(student, teacher, projectWave);
        if(assignment == null)
            return false;
        else
            return true;
    }

    private TeacherToChangeAssignmentResponse convertTeacherEntityToTeacherToChangeAssignmentResponse(Teacher teacher){
        TeacherToChangeAssignmentResponse response = new TeacherToChangeAssignmentResponse();

        response.setId(teacher.getId());
        response.setUsername(teacher.getUsername());
        response.setBirthday(DateUtil.convertDateToString(teacher.getBirthday()));
        response.setDeleted(teacher.getDeleted());
        response.setDescription(teacher.getDescription());
        response.setEmail(teacher.getEmail());
        if(teacher.getGender() != null)
            response.setGender(teacher.getGender().getValue());
        response.setName(teacher.getName());
        response.setPhoneNumber(teacher.getPhoneNumber());
        response.setStatus(teacher.getStatus());
        if(teacher.getTypeOfUser() != null)
            response.setTypeOfUser(teacher.getTypeOfUser().getValue());
        response.setMajor(teacher.getMajor());
        response.setDegree(teacher.getDegree());
        response.setResearchDirection(teacher.getResearchDirection());

        return response;
    }

    @Override
    public RestApiResponse<ArrayList<TeacherInProjectWaveResponse>> getTeachersOfProjectWave(String id) {
        ArrayList<TeacherInProjectWaveResponse> responses = new ArrayList<>();
        ProjectWave projectWave = projectWaveRepository.findOne(id);
        if(projectWave == null)
            throw new ProjectWaveException(MessageCodeConstant.PROJECT_WAVE_NOT_FOUND, id);
        else{
            ArrayList<TeacherWave> teacherWaves = teacherWaveRepository.findByProjectWave(projectWave);
            ArrayList<Teacher> teachers = getTeacherEntitiesFromTeacherWaves(teacherWaves);
            TeacherInProjectWaveResponse teacherResponse;
            Teacher teacherEntity;
            for(int i = 0; i < teachers.size(); i++){
                teacherEntity = teachers.get(i);
                teacherResponse = convertTeacherEntityToTeacherInProjectWaveResponse(teacherEntity);
                teacherResponse.setMaxGuide(getMaxGuideForTeacher(teacherEntity, projectWave));
                teacherResponse.setActualGuide(getActualGuideForTeacher(teacherEntity, projectWave));
                responses.add(teacherResponse);
            }
        }

        return new RestApiResponse<>(responses);
    }

    @Override
    public RestApiResponse<RegisterTeacherResponse> registerTeacher(RegisterTeacherRequest request) {
        Student student = studentRepository.findOne(request.getStudentId());
        if(student == null)
            throw new StudentNotFoundException(request.getStudentId());

        Teacher teacher = teacherRepository.findOne(request.getTeacherId());
        if(teacher == null)
            throw new TeacherNotFoundException(request.getTeacherId());

        ProjectWave projectWave = projectWaveRepository.findOne(request.getProjectWaveId());
        if(projectWave == null)
            throw new ProjectWaveNotFoundException(request.getProjectWaveId());

        if(!DateUtil.isDateInPeriodTime(new Date(), projectWave.getStartTimeForStudentRegisterTeacher(), projectWave.getEndTimeForStudentRegisterTeacher()))
            throw new ProjectWaveException(MessageCodeConstant.ERROR_NOT_TIME_TO_REGISTER_TEACHER, request.getProjectWaveId());

        ArrayList<TeacherWave> teacherWave = teacherWaveRepository.findByTeacherAndProjectWave(teacher, projectWave);
        if(teacherWave.size() == 0)
            throw new ProjectWaveException(MessageCodeConstant.ERROR_TEACHER_NOT_IN_WAVE, request.getTeacherId(), request.getProjectWaveId());

        ArrayList<Assignment> assignments = assignmentRepository.findByStudentAndWave(student, projectWave);
        if(assignments.size() > 0)
            throw  new ProjectWaveException(MessageCodeConstant.ERROR_STUDENT_CAN_NOT_REGISTER_MORE_ONE_TEACHER_IN_SAME_WAVE, student.getId(), projectWave.getId());

        Assignment assignment = assignmentRepository.findByStudentAndTeacherAndWave(student, teacher, projectWave);
        if(assignment != null)
            throw new AssignmentException(MessageCodeConstant.ERROR_ASSIGNMENT_EXISTED, student.getId(), teacher.getId(), projectWave.getId());

        Assignment newAssignment = new Assignment();
        newAssignment.setStudent(student);
        newAssignment.setTeacher(teacher);
        newAssignment.setProjectWave(projectWave);
        assignmentRepository.save(newAssignment);

        return new RestApiResponse<>();
    }

    @Override
    public RestApiResponse<RegisterTeacherResponse> cancelRegisterTeacher(RegisterTeacherRequest request) {
        Student student = studentRepository.findOne(request.getStudentId());
        if(student == null)
            throw new StudentNotFoundException(request.getStudentId());

        Teacher teacher = teacherRepository.findOne(request.getTeacherId());
        if(teacher == null)
            throw new TeacherNotFoundException(request.getTeacherId());

        ProjectWave projectWave = projectWaveRepository.findOne(request.getProjectWaveId());
        if(projectWave == null)
            throw new ProjectWaveNotFoundException(request.getProjectWaveId());

        if(!DateUtil.isDateInPeriodTime(new Date(), projectWave.getStartTimeForStudentRegisterTeacher(), projectWave.getEndTimeForStudentRegisterTeacher()))
            throw new ProjectWaveException(MessageCodeConstant.ERROR_NOT_TIME_TO_REGISTER_TEACHER, request.getProjectWaveId());

        Assignment assignment = assignmentRepository.findByStudentAndTeacherAndWave(student, teacher, projectWave);
        if(assignment == null)
            throw new AssignmentException(MessageCodeConstant.ERROR_ASSIGNMENT_NOT_EXISTED, student.getId(), teacher.getId(), projectWave.getId());

        assignmentRepository.delete(assignment);

        return new RestApiResponse<>();
    }

    @Override
    public RestApiResponse<WavesTeacherJoinedResponse> getProjectWaveTeacherJoin(String teacherId) {
        Teacher teacher = teacherRepository.findOne(teacherId);
        ArrayList<ProjectWaveResponse> projectWavesJoined = new ArrayList<>();
        ArrayList<ProjectWaveResponse> projectWavesJoining = new ArrayList<>();
        if(teacher == null)
            throw new UserNotFoundException();
        else{
            ArrayList<TeacherWave> teacherWaves = teacherWaveRepository.findByTeacher(teacher);
            ProjectWave tempProjectWave;
            ProjectWaveResponse projectWaveResponse;
            for(int i = 0; i<teacherWaves.size(); i++){
                tempProjectWave = teacherWaves.get(i).getProjectWave();
                projectWaveResponse = convertProjectWaveEntityToProjectWaveResponse(tempProjectWave);
                if(projectWaveResponse.isDone())
                    projectWavesJoined.add(projectWaveResponse);
                else projectWavesJoining.add(projectWaveResponse);
            }
        }
        return new RestApiResponse<>(new WavesTeacherJoinedResponse(projectWavesJoining, projectWavesJoined));
    }

    @Override
    public RestApiResponse<ProposeStudentResponse> proposeStudent(ProposeStudentRequest request) {
        Teacher teacher = teacherRepository.findOne(request.getTeacherId());
        ProjectWave projectWave = projectWaveRepository.findOne(request.getProjectWaveId());
        deleteAssignmentByTeacherAndProjectWave(teacher, projectWave);
        Assignment assignment;
        Student student;
        for (int i = 0; i<request.getStudentsProposed().size(); i++){
            student = studentRepository.findOne(request.getStudentsProposed().get(i));
            assignment = new Assignment();
            assignment.setStudent(student);
            assignment.setTeacher(teacher);
            assignment.setProjectWave(projectWave);
            assignmentRepository.save(assignment);
        }
        return new RestApiResponse<>();
    }

    private void deleteAssignmentByTeacherAndProjectWave(Teacher teacher, ProjectWave projectWave){
        ArrayList<Assignment> assignments = assignmentRepository.findByTeacherAndWave(teacher, projectWave);
        for(int i = 0; i<assignments.size(); i++){
            assignmentRepository.delete(assignments.get(i));
        }
    }

    private ArrayList<Teacher> getTeacherEntitiesFromTeacherWaves(ArrayList<TeacherWave> teacherWaves){
        ArrayList<Teacher> out = new ArrayList<>();
        for (int i =0; i<teacherWaves.size(); i++){
            out.add(teacherWaves.get(i).getTeacher());
        }

        return out;
    }

    private ArrayList<StudentResponse> getStudentsFromStudentWaves(ArrayList<StudentWave> studentWaves){
        ArrayList<StudentResponse> out = new ArrayList<>();
        for (int i =0; i<studentWaves.size(); i++){
            out.add(ConvertObject.convertStudentEntityToStudentResponse(studentWaves.get(i).getStudent()));
        }

        return out;
    }

    private ArrayList<TeacherResponse> getTeacherResponsesFromTeacherWaves(ArrayList<TeacherWave> teacherWaves){
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

        checkStudentJoinProjectWaveInSameTime(student, projectWave);

        StudentWave studentWave = new StudentWave();
        studentWave.setStudent(student);
        studentWave.setProjectWave(projectWave);
        studentWaveRepository.save(studentWave);

        StudentResponse studentResponse = ConvertObject.convertStudentEntityToStudentResponse(student);
        RestApiResponse<StudentResponse> responseRestApiResponse =  new RestApiResponse<>(studentResponse);
        return responseRestApiResponse;
    }

    private void checkStudentJoinProjectWaveInSameTime(Student student, ProjectWave projectWave) {
        ArrayList<StudentWave> studentWaves = studentWaveRepository.findByStudent(student);
        if((studentWaves != null) && (studentWaves.size() != 0)){
            for(int i = 0; i < studentWaves.size(); i++){
                if(checkProjectWaveInSameTime(projectWave, studentWaves.get(i).getProjectWave())){
                    throw new ProjectWaveException(MessageCodeConstant.ERROR_STUDENT_CAN_NOT_JOIN_OTHER_WAVE_IN_SAME_TIME);
                }
            }
        }
    }

    private boolean checkProjectWaveInSameTime(ProjectWave wave, ProjectWave otherWave) {
        if(DateUtil.isDateInPeriodTime(wave.getStartDay(), otherWave.getStartDay(), otherWave.getEndDay()) || DateUtil.isDateInPeriodTime(wave.getEndDay(), otherWave.getStartDay(), otherWave.getEndDay()))
            return true;

        if(DateUtil.isDateInPeriodTime(otherWave.getStartDay(), wave.getStartDay(), wave.getEndDay()) || DateUtil.isDateInPeriodTime(otherWave.getEndDay(), wave.getStartDay(), wave.getEndDay()))
            return true;

        return false;
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
        teacherWave.setMaxNumberOfStudent(request.getNumberOfStudent());
        teacherWaveRepository.save(teacherWave);

        TeacherResponse teacherResponse = ConvertObject.convertTeacherEntityToTeacherResponse(teacher);
        RestApiResponse<TeacherResponse> responseRestApiResponse =  new RestApiResponse<>(teacherResponse);
        return responseRestApiResponse;
    }

    @Override
    public RestApiResponse<WavesStudentJoinedResponse> getWavesStudentJoined(String studentId) {
        Student student = studentRepository.findOne(studentId);
        ArrayList<ProjectWaveResponse> projectWavesJoined = new ArrayList<>();
        ArrayList<ProjectWaveResponse> projectWavesJoining = new ArrayList<>();
        if(student == null)
            throw new UserNotFoundException();
        else{
            ArrayList<StudentWave> studentWaves = studentWaveRepository.findByStudent(student);
            ProjectWave tempProjectWave;
            ProjectWaveResponse projectWaveResponse;
            for(int i = 0; i<studentWaves.size(); i++){
                tempProjectWave = studentWaves.get(i).getProjectWave();
                projectWaveResponse = convertProjectWaveEntityToProjectWaveResponse(tempProjectWave);
                if(projectWaveResponse.isDone())
                    projectWavesJoined.add(projectWaveResponse);
                else projectWavesJoining.add(projectWaveResponse);
            }
        }
        return new RestApiResponse<>(new WavesStudentJoinedResponse(projectWavesJoining, projectWavesJoined));
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

    @Override
    public RestApiResponse<ArrayList<TeacherInProjectWaveResponse>> getTeachersWhoDirectingStudentInProjectWave(String studentId, String waveId) {
        Student student = studentRepository.findOne(studentId);
        if(student == null)
            throw new UserNotFoundException(studentId);

        ProjectWave projectWave = projectWaveRepository.findOne(waveId);
        if(projectWave == null)
            throw  new ProjectWaveNotFoundException(waveId);

        ArrayList<Assignment>  assignments = assignmentRepository.findByStudentAndWave(student, projectWave);

        ArrayList<TeacherInProjectWaveResponse> teacherInProjectWaveResponses = getTeachersInProjectWaveResponses(assignments);

        return new RestApiResponse<>(teacherInProjectWaveResponses);
    }

    private ArrayList<TeacherInProjectWaveResponse> getTeachersInProjectWaveResponses(ArrayList<Assignment> assignments) {
        ArrayList<TeacherInProjectWaveResponse> responses = new ArrayList<>();

        TeacherInProjectWaveResponse teacherResponse;
        ProjectWave projectWave;
        Teacher teacher;
        Assignment assignment;
        for(int i = 0; i < assignments.size(); i++){
            assignment = assignments.get(i);
            projectWave = assignment.getProjectWave();
            teacher = assignment.getTeacher();
            teacherResponse = convertTeacherEntityToTeacherInProjectWaveResponse(teacher);
            teacherResponse.setMaxGuide(getMaxGuideForTeacher(teacher, projectWave));
            teacherResponse.setActualGuide(getActualGuideForTeacher(teacher, projectWave));

            responses.add(teacherResponse);
        }

        return responses;
    }

    private int getActualGuideForTeacher(Teacher teacher, ProjectWave projectWave) {
        int actualGuide = 0;

        ArrayList<Assignment> assignments = assignmentRepository.findByTeacherAndWave(teacher, projectWave);
        actualGuide = assignments.size();

        return actualGuide;
    }

    private int getMaxGuideForTeacher(Teacher teacher, ProjectWave wave) {
        ArrayList<TeacherWave> teacherWaves = teacherWaveRepository.findByTeacherAndProjectWave(teacher, wave);
        int maxGuide = 0;
        if(teacherWaves.size() > 1){
            throw new ProjectWaveException(MessageCodeConstant.ERROR_TEACHER_IN_WAVE_EXIST_MORE_ONE, teacher.getId(), wave.getId());
        }
        else if(teacherWaves.size() == 1){
            maxGuide = teacherWaves.get(0).getMaxNumberOfStudent();
        }

        return maxGuide;
    }

    public TeacherInProjectWaveResponse convertTeacherEntityToTeacherInProjectWaveResponse(Teacher teacher) {//TODO note
        TeacherInProjectWaveResponse response = new TeacherInProjectWaveResponse();

        response.setId(teacher.getId());
        response.setUsername(teacher.getUsername());
        response.setBirthday(DateUtil.convertDateToString(teacher.getBirthday()));
        response.setDeleted(teacher.getDeleted());
        response.setDescription(teacher.getDescription());
        response.setEmail(teacher.getEmail());
        if(teacher.getGender() != null)
            response.setGender(teacher.getGender().getValue());
        response.setName(teacher.getName());
        response.setPhoneNumber(teacher.getPhoneNumber());
        response.setStatus(teacher.getStatus());
        if(teacher.getTypeOfUser() != null)
            response.setTypeOfUser(teacher.getTypeOfUser().getValue());
        response.setMajor(teacher.getMajor());
        response.setDegree(teacher.getDegree());
        response.setResearchDirection(teacher.getResearchDirection());

        return response;
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

    public ProjectWaveResponse convertProjectWaveEntityToProjectWaveResponse(ProjectWave entity){
        ProjectWaveResponse response = new ProjectWaveResponse();
        response.setId(entity.getId());
        response.setSchoolYear(entity.getSchoolYear());
        response.setSemester(entity.getSemester());
        response.setDescription(entity.getDescription());

        Date currentDate = new Date();
        if(entity.getEndDay().compareTo(currentDate) >= 0)
            response.setStatus(1);

        response.setStartTimeAndEndTime(getTimeForStudentDefend(entity));
        response.setTimeForTeacherProposesStudent(getTimeForTeacherProposesStudent(entity));
        response.setTimeForStudentRegisterTeacher(getTimeForStudentRegisterTeacher(entity));
        response.setTimeForStudentSubmitProject(getTimeForStudentSubmitProject(entity));
        response.setTimeForStudentDefend(getTimeForStudentStudentDefend(entity));
        response.setReportTimes(getReportTimes(entity));

        if(DateUtil.isDateInPeriodTime(currentDate, entity.getStartDay(), entity.getEndDay()))
            response.setDone(false);
        else response.setDone(true);

        if(DateUtil.isDateInPeriodTime(currentDate, entity.getStartTimeForDefendingProject(), entity.getEndTimeForDefendingProject()))
            response.setTimeStudentDefend(true);
        else response.setTimeStudentDefend(false);

        if(DateUtil.isDateInPeriodTime(currentDate, entity.getStartTimeForStudentRegisterTeacher(), entity.getEndTimeForStudentRegisterTeacher()))
            response.setTimeStudentRegistersTeacher(true);
        else response.setTimeStudentRegistersTeacher(false);

        if(DateUtil.isDateInPeriodTime(currentDate, entity.getStartTimeForStudentSubmitProject(), entity.getEndTimeForStudentSubmitProject()))
            response.setTimeStudentSubmit(true);
        else response.setTimeStudentSubmit(false);

        if(DateUtil.isDateInPeriodTime(currentDate, entity.getStartTimeForTeacherProposeStudent(), entity.getEndTimeForTeacherProposeStudent()))
            response.setTimeTeacherProposesStudent(true);
        else response.setTimeTeacherProposesStudent(false);

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

        for(int i = 0; i<reports.size()-1; i++){
            for(int j = i+1; j<reports.size(); j++){
                if(reports.get(i).getStartTime().compareTo(reports.get(j).getStartTime()) > 0){
                    report = reports.get(i);
                    reports.set(i, reports.get(j));
                    reports.set(j, report);
                }
            }
        }

        for(int i = 0; i<reports.size(); i++) {
            reports.get(i).setOrdinal(i+1);
            reportRepository.save(reports.get(i));
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
