package datn.service;

import datn.interfaces.response.RestApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImportDataService {

    RestApiResponse<?> importData(MultipartFile excelFile);

}
