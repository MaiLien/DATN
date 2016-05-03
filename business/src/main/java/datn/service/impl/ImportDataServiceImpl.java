package datn.service.impl;

import datn.dao.entity.Student;
import datn.dao.repository.StudentRepository;
import datn.interfaces.request.StudentRequest;
import datn.interfaces.response.*;
import datn.service.IImportDataService;
import datn.service.IStudentService;
import datn.service.exceptions.ExcelFileNotFoundException;
import datn.service.exceptions.ExtensionExcelFileException;
import datn.service.exceptions.SheetNotFoundException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;


@Service
public class ImportDataServiceImpl implements IImportDataService{

    public static final String STUDENT_SHEET = "Student";

    private Map<String, Student> studentMap = null;
    private Map<String, String> studentFileMap = null;

    private List<StudentResponse> successItems = null;
    private List<FailItemResponse<StudentRequest>> failItems = null;

    @Autowired
    IStudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    public synchronized RestApiResponse<ImportFromFileResponse<StudentResponse, StudentRequest>> importData(MultipartFile excelFile){
        RestApiResponse<ArrayList<StudentResponse>> response = new RestApiResponse<>(null);
        response.setHeaders(new RestApiResponseHeaders());

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
        initStudentMap();
        HSSFWorkbook workbook = new HSSFWorkbook(is);
        validateAndSaveStudentSheet(workbook);
    }

    private void readExcelWithXlsx(InputStream is) throws IOException {
        initStudentMap();
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        validateAndSaveStudentSheet(workbook);
    }

    private void initStudentMap() {
        failItems = new ArrayList<>();
        successItems = new ArrayList<>();
        studentMap = new HashMap<>();
        studentFileMap = new HashMap<>();
        ArrayList<Student> students = (ArrayList<Student>) studentRepository.findAll();
        for (Student student: students) {
            studentMap.put(StringUtils.lowerCase(student.getUsername()), student);
        }
    }

    private void resetAllMap() {
        studentMap = null;
    }

    private void validateAndSaveStudentSheet(Workbook workbook) {
        Sheet studentSheet = workbook.getSheet(STUDENT_SHEET);
        if (studentSheet == null) {
            throw new SheetNotFoundException("Student sheet not found");
        }
        saveStudents(studentSheet);
    }

    private void saveStudents(Sheet studentSheet) {

        Iterator<Row> rowIterator = studentSheet.iterator();
        ignoreFistRow(rowIterator);
        int index = 1;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            StudentRequest student = getStudent(row);

            if(student == null) break;

            if(studentIsExistInFile(student)){
                FailItemResponse<StudentRequest> failItem = new FailItemResponse<>();
                failItem.setRow(index);
                failItem.setReason("Mã sinh viên đã có trong file");
                failItem.setErrorItem(student);

                failItems.add(failItem);
            }

            else if(studentIsExistInDatabase(student)){
                FailItemResponse<StudentRequest> failItem = new FailItemResponse<>();
                failItem.setRow(index);
                failItem.setReason("Mã sinh viên đã tồn tại");
                failItem.setErrorItem(student);

                failItems.add(failItem);
            }

            else if(isNotValidRow(student.getId(), student.getName(), student.getClass_(), student.getBirthday())){
                FailItemResponse<StudentRequest> failItem = new FailItemResponse<>();
                failItem.setRow(index);
                failItem.setReason("Không đủ dữ liệu");
                failItem.setErrorItem(student);

                failItems.add(failItem);
            }

            else{
                StudentResponse studentResponse = saveNewStudent(student);
                successItems.add(studentResponse);
            }

            studentFileMap.put(student.getUsername(), student.getUsername());
            index++;
        }

    }

    private boolean studentIsExistInFile(StudentRequest student) {
        String key = student.getUsername();
        String result = studentFileMap.get(key);
        if(result != null){
            return true;
        }

        return false;
    }

    private boolean studentIsExistInDatabase(StudentRequest student){
        String key = student.getUsername();
        Student studentInMap = studentMap.get(key);
        if (studentInMap != null) {
            return true;
        }

        return false;
    }

    private StudentResponse saveNewStudent(StudentRequest studentRequest){
        return studentService.addStudent(studentRequest).getBody();
    }

    private StudentRequest getStudent(Row row) {
        StudentRequest student = new StudentRequest();
        String index = getValueOfRowAt(row, 0).trim();
        if(index == null || "".equalsIgnoreCase(index))
            return null;
        String studentUserName = getValueOfRowAt(row, 1).trim();
        String studentName = getValueOfRowAt(row, 2);
        String studentClass = getValueOfRowAt(row, 3);
        String studentBirthday = getValueOfRowAt(row, 4);

        student.setUsername(studentUserName);
        student.setName(studentName);
        student.setClass_(studentClass);
        student.setBirthday(studentBirthday);

        return student;
    }

    private String getValueOfRowAt(Row row, int i) {
        String res = "";
        Cell cell = row.getCell(i);
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    res = new BigDecimal(cell.getNumericCellValue()).toString();
                    break;
                case Cell.CELL_TYPE_STRING:
                    res = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    res = getFormulaCellValue(cell);
                default:
                    break;
            }
        }
        return StringUtils.trim(res);
    }

    private String getFormulaCellValue(Cell cell) {
        String res = "";
        if (cell != null) {
            switch (cell.getCachedFormulaResultType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    res = new BigDecimal(cell.getNumericCellValue()).toString();
                    break;
                case Cell.CELL_TYPE_STRING:
                    res = cell.getStringCellValue();
                    break;
                default:
                    break;
            }
        }
        return res;
    }

    private boolean isNotValidRow(String ... inputStr) {
        boolean invalidRow = true;
        if (inputStr != null) {
            for (String s : inputStr) {
                invalidRow &= StringUtils.isBlank(s);
            }
        }
        return invalidRow;
    }

    private void ignoreFistRow(Iterator iterator) {
        if (iterator.hasNext()) {
            iterator.next();
        }
    }

}
