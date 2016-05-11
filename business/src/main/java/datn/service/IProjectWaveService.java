package datn.service;

import datn.interfaces.request.AddStudentForProjectWaveRequest;
import datn.interfaces.request.AddTeacherForProjectWaveRequest;
import datn.interfaces.request.ProjectWaveRequest;
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
}
