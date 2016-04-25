package datn.service;

import datn.interfaces.request.ProjectWaveRequest;
import datn.interfaces.response.ProjectWaveResponse;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.StudentResponse;
import datn.interfaces.response.TeacherResponse;
import org.springframework.data.domain.Page;

import java.util.ArrayList;

public interface IProjectWaveService{

    RestApiResponse<ProjectWaveResponse> addProjectWave(ProjectWaveRequest request);

    RestApiResponse<ProjectWaveResponse> getProjectWave(String id);

    RestApiResponse<ArrayList<StudentResponse>> getStudentsOfProjectWave(String id);

    RestApiResponse<TeacherResponse> getTeachersOfProjectWave(String id);

    RestApiResponse<ArrayList<ProjectWaveResponse>> getAllProjectWave();

    RestApiResponse<Page<ProjectWaveResponse>> getPageProjectWaves(int pageIndex, int sizeOfPage, String searchInput);

    RestApiResponse<ProjectWaveResponse> deleteProjectWave(String projectWaveId);
}
