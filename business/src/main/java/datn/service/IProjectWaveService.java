package datn.service;

import datn.interfaces.request.ProjectWaveRequest;
import datn.interfaces.response.ProjectWaveResponse;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.StudentResponse;
import datn.interfaces.response.TeacherResponse;

import java.util.ArrayList;

public interface IProjectWaveService{

    RestApiResponse<ProjectWaveResponse> addProjectWave(ProjectWaveRequest request);

    RestApiResponse<ProjectWaveResponse> getProjectWave(String id);

    RestApiResponse<ArrayList<StudentResponse>> getStudentsOfProjectWave(String id);

    RestApiResponse<TeacherResponse> getTeachersOfProjectWave(String id);

}
