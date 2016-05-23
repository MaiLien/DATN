package datn.service;

import datn.interfaces.response.StudentProjectInfoOfWaveResponse;
import datn.interfaces.response.RestApiResponse;

public interface IReportService {

    RestApiResponse<StudentProjectInfoOfWaveResponse> getStudentProjectInfoOfWaveResponse(String studentId, String projectWaveId);

}
