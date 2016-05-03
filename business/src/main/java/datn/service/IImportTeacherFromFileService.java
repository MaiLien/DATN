package datn.service;

import datn.interfaces.request.TeacherRequest;
import datn.interfaces.response.ImportFromFileResponse;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.TeacherResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IImportTeacherFromFileService {

    RestApiResponse<ImportFromFileResponse<TeacherResponse, TeacherRequest>> importData(MultipartFile excelFile);

}
