package datn.service.impl;

import datn.interfaces.request.StudentRequest;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.RestApiResponseHeaders;
import datn.interfaces.response.StudentResponse;
import datn.service.IImportDataService;
import datn.service.IStudentService;
import datn.service.exceptions.*;
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

    private Map<String, StudentResponse> studentMap = new HashMap<String, StudentResponse>();

    @Autowired
    IStudentService studentService;

    public synchronized RestApiResponse<?> importData(MultipartFile excelFile){
        RestApiResponse<ArrayList<StudentResponse>> response = new RestApiResponse<ArrayList<StudentResponse>>(null);
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
            response.setHeaders(new RestApiResponseHeaders());
        } else {
            throw new ExcelFileNotFoundException("excelFile not found");
        }
        return response;
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
        ArrayList<StudentResponse> students = studentService.getStudents().getBody();
        for (StudentResponse student: students) {
            studentMap.put(StringUtils.lowerCase(student.getUsername()), student);
        }
    }

    private void resetAllMap() {
        studentMap = new HashMap<String, StudentResponse>();
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
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            StudentRequest student = getStudent(row);
            if (student != null) {
//                MultiKey key = new MultiKey(StringUtils.lowerCase(student.getUsername()), StringUtils.lowerCase(student.getName()));
                String key = student.getUsername();
                StudentResponse studentInMap = studentMap.get(key);
                if (studentInMap == null) {
                    saveNewStudent(student);
                }
                else {
                    throw new UserExistedException("User existed");
                }
            }
        }
    }

    private StudentResponse saveNewStudent(StudentRequest studentRequest){
        return studentService.addStudent(studentRequest).getBody();
    }

    private StudentRequest getStudent(Row row) {
        StudentRequest student = null;
        String studentId = getValueOfRowAt(row, 1);
        String studentName = getValueOfRowAt(row, 2);
        String studentClass = getValueOfRowAt(row, 3);
        String studentBirthday = getValueOfRowAt(row, 4);
        if (!isNotValidRow(studentId, studentName, studentClass, studentBirthday)) {
            if (!fullInput(studentId, studentName, studentClass, studentBirthday)) {
                throw new NotFullInputAtRowException("Not full input at row : " + row.getRowNum());
            }
            student = new StudentRequest();
            student.setUsername(studentId);
            student.setName(studentName);
            student.setClass_(studentClass);
            student.setBirthday(studentBirthday);
        }
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

    private boolean fullInput(String... inputStr) {
        boolean full = true;
        if (inputStr != null) {
            for (String s : inputStr) {
                full &= StringUtils.isNotBlank(s);
            }

        }
        return full;
    }

    private void ignoreFistRow(Iterator iterator) {
        if (iterator.hasNext()) {
            iterator.next();
        }
    }

}
