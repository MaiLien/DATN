package datn.service;

import datn.interfaces.response.ReportsOfWaveResponse;
import datn.interfaces.response.RestApiResponse;

import java.util.ArrayList;

public interface IReportService {
    RestApiResponse<ReportsOfWaveResponse> getStudentReportsOfWave(String studentId, String projectWaveId);
}
