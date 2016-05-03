package datn.service.impl;

import datn.interfaces.request.TeacherRequest;
import datn.interfaces.response.ImportFromFileResponse;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.TeacherResponse;
import datn.service.IImportTeacherFromFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImportTeacherFromFileServiceImpl extends ImportDataServiceImpl implements IImportTeacherFromFileService{

    @Override
    public RestApiResponse<ImportFromFileResponse<TeacherResponse, TeacherRequest>> importData(MultipartFile excelFile) {
        return null;
    }

}
