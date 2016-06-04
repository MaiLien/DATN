package datn.service;

import datn.interfaces.request.*;
import datn.interfaces.response.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;

public interface IProjectWaveService{

    RestApiResponse<ProjectWaveResponse> addProjectWave(ProjectWaveRequest request);

    RestApiResponse<ProjectWaveResponse> getProjectWave(String id);

    RestApiResponse<ArrayList<StudentResponse>> getStudentsOfProjectWave(String id);

    RestApiResponse<ArrayList<TeacherInProjectWaveResponse>> getTeachersOfProjectWave(String id);

    RestApiResponse<ArrayList<ProjectWaveResponse>> getAllProjectWave();

    RestApiResponse<Page<ProjectWaveResponse>> getPageProjectWaves(int pageIndex, int sizeOfPage, String searchInput);

    RestApiResponse<ProjectWaveResponse> deleteProjectWave(String projectWaveId);

    RestApiResponse<StudentResponse> addStudentForProjectWave(AddStudentForProjectWaveRequest request);

    RestApiResponse<TeacherResponse> addTeacherForProjectWave(AddTeacherForProjectWaveRequest request);

    RestApiResponse<WavesStudentJoinedResponse> getWavesStudentJoined(String studentId);

    RestApiResponse<ArrayList<ProjectWaveResponse>> getWavesTeacherJoined(String teacherId);

    RestApiResponse<ArrayList<TeacherInProjectWaveResponse>> getTeachersWhoDirectingStudentInProjectWave(String studentId, String waveId);

    RestApiResponse<RegisterTeacherResponse> registerTeacher(RegisterTeacherRequest request);

    RestApiResponse<RegisterTeacherResponse> cancelRegisterTeacher(RegisterTeacherRequest request);

    RestApiResponse<WavesTeacherJoinedResponse> getProjectWaveTeacherJoin(String teacherId);

    RestApiResponse<ProposeStudentResponse> proposeStudent(ProposeStudentRequest request);

    RestApiResponse<ArrayList<StudentOfProjectWaveToProposeResponse>> getStudentsOfProjectWaveToPropose(String teacherId, String projectWaveId);

    RestApiResponse<ArrayList<TeacherResponse>> getTeachersToAddForProjectWave(String id);

    RestApiResponse<?> addTeachersForWave(AddTeachersForWaveRequest request);

    RestApiResponse<?> deleteTeacherFromWave(String teacherId, String projectWaveId);

    RestApiResponse<ArrayList<StudentResponse>> getListStudentWhoTeacherGuideInWave(String teacherId, String waveId);

    RestApiResponse<?> approveReport(ApproveReportRequest request);
}
