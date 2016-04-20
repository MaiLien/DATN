package datn.service;

import datn.interfaces.request.ProjectWaveRequest;
import datn.interfaces.response.ProjectWaveResponse;
import datn.interfaces.response.RestApiResponse;

public interface IProjectWaveService{

    public RestApiResponse<ProjectWaveResponse> addProjectWave(ProjectWaveRequest request);

}
