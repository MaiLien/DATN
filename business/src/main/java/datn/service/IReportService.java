package datn.service;

import datn.interfaces.request.StudentReportRequest;
import datn.interfaces.response.StudentProjectInfoOfWaveResponse;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.StudentResponse;

public interface IReportService {

    RestApiResponse<StudentProjectInfoOfWaveResponse> getStudentProjectInfoOfWaveResponse(String studentId, String projectWaveId);

    RestApiResponse<?> saveStudentReport(StudentReportRequest request);
}
