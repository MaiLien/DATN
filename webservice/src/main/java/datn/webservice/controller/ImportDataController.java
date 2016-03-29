package datn.webservice.controller;

import datn.interfaces.response.RestApiResponse;
import datn.service.IImportDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ImportDataController {

    @Autowired
    private IImportDataService importDataService;

    @RequestMapping(value = "/importStudentFromFile", method = RequestMethod.POST)
    @ResponseBody
    public RestApiResponse<?> importData(@RequestParam("excelFile") MultipartFile excelFile){
        return importDataService.importData(excelFile);
    }

}
