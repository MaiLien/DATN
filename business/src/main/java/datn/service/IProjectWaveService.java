package datn.service;

import datn.interfaces.request.ProjectWaveRequest;
import datn.interfaces.response.ProjectWaveResponse;
import datn.interfaces.response.RestApiResponse;

public interface IProjectWaveService{

    RestApiResponse<ProjectWaveResponse> addProjectWave(ProjectWaveRequest request);

    RestApiResponse<ProjectWaveResponse> getProjectWave(String id);

}
