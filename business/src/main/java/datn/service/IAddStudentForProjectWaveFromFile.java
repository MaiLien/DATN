package datn.service;

import datn.interfaces.request.StudentRequest;
import datn.interfaces.response.ImportFromFileResponse;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.StudentResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IAddStudentForProjectWaveFromFile {

    RestApiResponse<ImportFromFileResponse<StudentResponse, StudentRequest>> addStudentForProjectWaveFromFile(MultipartFile excelFile, String projectWaveId);

}
