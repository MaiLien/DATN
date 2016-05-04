package datn.service.impl;

import datn.dao.entity.Teacher;
import datn.dao.repository.TeacherRepository;
import datn.interfaces.request.TeacherRequest;
import datn.interfaces.response.FailItemResponse;
import datn.interfaces.response.ImportFromFileResponse;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.TeacherResponse;
import datn.service.IImportTeacherFromFileService;
import datn.service.ITeacherService;
import datn.service.exceptions.ExcelFileNotFoundException;
import datn.service.exceptions.ExtensionExcelFileException;
import datn.service.exceptions.SheetNotFoundException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class ImportTeacherFromFileServiceImpl extends ImportDataServiceImpl implements IImportTeacherFromFileService{

    public static final String TEACHER_SHEET = "Teacher";

    private Map<String, Teacher> teacherMap = null;
    private Map<String, String> teacherFileMap = null;

    private List<TeacherResponse> successItems = null;
    private List<FailItemResponse<TeacherRequest>> failItems = null;

    @Autowired
    ITeacherService teacherService;

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public synchronized RestApiResponse<ImportFromFileResponse<TeacherResponse, TeacherRequest>> importData(MultipartFile excelFile) {

        if (!excelFile.isEmpty()) {
            try {
                String fileName = excelFile.getOriginalFilename();
                InputStream inputStream = excelFile.getInputStream();

                String fileExtension = FilenameUtils.getExtension(fileName);

                if (StringUtils.equals(fileExtension, "xlsx")) {
                    readExcelWithXlsx(inputStream);
                }
                else if (StringUtils.equals(fileExtension, "xls")) {
                    readExcelWithXls(inputStream);
                }
                else {
                    throw new ExtensionExcelFileException("Only accept .xlsx or .xls file.");
                }
            }catch (IOException e){

            }
            finally {
                resetAllMap();
            }
        } else {
            throw new ExcelFileNotFoundException("excelFile not found");
        }
        return new RestApiResponse<>(new ImportFromFileResponse<>(successItems, failItems));
    }

    private void readExcelWithXls(InputStream is) throws IOException {
        initTeacherMap();
        HSSFWorkbook workbook = new HSSFWorkbook(is);
        validateAndSaveTeacherSheet(workbook);
    }

    private void readExcelWithXlsx(InputStream is) throws IOException {
        initTeacherMap();
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        validateAndSaveTeacherSheet(workbook);
    }

    private void initTeacherMap() {
        failItems = new ArrayList<>();
        successItems = new ArrayList<>();
        teacherMap = new HashMap<>();
        teacherFileMap = new HashMap<>();
        ArrayList<Teacher> teachers = (ArrayList<Teacher>) teacherRepository.findAll();
        for (Teacher teacher: teachers) {
            teacherMap.put(teacher.getUsername(), teacher);
        }
    }

    private void resetAllMap() {
        teacherMap = null;
    }

    private void validateAndSaveTeacherSheet(Workbook workbook) {
        Sheet teacherSheet = workbook.getSheet(TEACHER_SHEET);
        if (teacherSheet == null) {
            throw new SheetNotFoundException("Teacher sheet not found");
        }
        saveTeachers(teacherSheet);
    }

    private void saveTeachers(Sheet teacherSheet) {

        Iterator<Row> rowIterator = teacherSheet.iterator();
        ignoreFistRow(rowIterator);
        int index = 1;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            TeacherRequest teacher = getTeacher(row);

            if(teacher == null) break;

            if(teacherIsExistInFile(teacher)){
                addFailItemResponse(index, teacher, "Mã giảng viên đã có trong file");
            }

            else if(teacherIsExistInDatabase(teacher)){
                addFailItemResponse(index, teacher, "Mã giảng viên đã tồn tại");
            }

            else if(isNotValidRow(teacher.getUsername(), teacher.getName(), teacher.getBirthday())){
                addFailItemResponse(index, teacher, "Không đủ dữ liệu");
            }

            else{
                TeacherResponse teacherResponse = saveNewTeacher(teacher);
                try{
                    successItems.add(teacherResponse);
                }catch(RuntimeException e){
                    addFailItemResponse(index, teacher, "Dữ liệu lỗi");
                }
            }

            teacherFileMap.put(teacher.getUsername(), teacher.getUsername());
            index++;
        }

    }

    private void addFailItemResponse(int rowIndex, TeacherRequest teacher, String reason){
        FailItemResponse<TeacherRequest> failItem = new FailItemResponse<>();
        failItem.setRow(rowIndex);
        failItem.setReason(reason);
        failItem.setErrorItem(teacher);

        failItems.add(failItem);
    }

    private boolean teacherIsExistInFile(TeacherRequest teacher) {
        String key = teacher.getUsername();
        String result = teacherFileMap.get(key);
        if(result != null){
            return true;
        }

        return false;
    }

    private boolean teacherIsExistInDatabase(TeacherRequest teacher){
        String key = teacher.getUsername();
        Teacher teacherInMap = teacherMap.get(key);
        if (teacherInMap != null) {
            return true;
        }

        return false;
    }

    private TeacherResponse saveNewTeacher(TeacherRequest teacherRequest){
        return teacherService.addTeacher(teacherRequest).getBody();
    }

    private TeacherRequest getTeacher(Row row) {
        TeacherRequest teacher = new TeacherRequest();
        String index = getValueOfRowAt(row, 0).trim();
        if(index == null || "".equalsIgnoreCase(index))
            return null;
        String userName = getValueOfRowAt(row, 1).trim();
        String name = getValueOfRowAt(row, 2);
        String birthday = getValueOfRowAt(row, 3);

        teacher.setUsername(userName);
        teacher.setName(name);
        teacher.setBirthday(birthday);

        return teacher;
    }

}
