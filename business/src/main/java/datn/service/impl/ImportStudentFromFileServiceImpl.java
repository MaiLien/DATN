package datn.service.impl;

import datn.dao.entity.Student;
import datn.dao.repository.StudentRepository;
import datn.interfaces.request.StudentRequest;
import datn.interfaces.request.TeacherRequest;
import datn.interfaces.response.*;
import datn.service.IImportStudentFromFileService;
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
public class ImportStudentFromFileServiceImpl extends ImportDataServiceImpl implements IImportStudentFromFileService{
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
            studentMap.put(student.getUsername(), student);
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
                addFailItemResponse(index, student, "Mã sinh viên đã có trong file");
            }

            else if(studentIsExistInDatabase(student)){
                addFailItemResponse(index, student, "Mã sinh viên đã tồn tại");
            }

            else if(isNotValidRow(student.getUsername(), student.getName(), student.getClass_(), student.getBirthday())){
                addFailItemResponse(index, student, "Không đủ dữ liệu");
            }

            else{
                StudentResponse studentResponse = saveNewStudent(student);
                try{
                    successItems.add(studentResponse);
                }catch (RuntimeException e){
                    addFailItemResponse(index, student, "Dữ liệu lỗi");
                }
            }

            studentFileMap.put(student.getUsername(), student.getUsername());
            index++;
        }

    }

    private void addFailItemResponse(int rowIndex, StudentRequest student, String reason){
        FailItemResponse<StudentRequest> failItem = new FailItemResponse<>();
        failItem.setRow(rowIndex);
        failItem.setReason(reason);
        failItem.setErrorItem(student);

        failItems.add(failItem);
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

}
