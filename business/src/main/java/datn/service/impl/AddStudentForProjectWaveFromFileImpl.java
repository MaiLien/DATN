package datn.service.impl;

import datn.dao.entity.ProjectWave;
import datn.dao.entity.Student;
import datn.dao.entity.StudentWave;
import datn.dao.repository.ProjectWaveRepository;
import datn.dao.repository.StudentRepository;
import datn.dao.repository.StudentWaveRepository;
import datn.interfaces.constant.MessageCodeConstant;
import datn.interfaces.request.StudentRequest;
import datn.interfaces.response.FailItemResponse;
import datn.interfaces.response.ImportFromFileResponse;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.StudentResponse;
import datn.interfaces.util.ConvertObject;
import datn.interfaces.util.DateUtil;
import datn.service.IAddStudentForProjectWaveFromFile;
import datn.service.IStudentService;
import datn.service.exceptions.ExcelFileNotFoundException;
import datn.service.exceptions.ExtensionExcelFileException;
import datn.service.exceptions.ProjectWaveException;
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
public class AddStudentForProjectWaveFromFileImpl extends ImportDataServiceImpl implements IAddStudentForProjectWaveFromFile{

    public static final String STUDENT_SHEET = "Student";

    private Map<String, Student> studentMap = null;
    private Map<String, Student> studentInWaveMap = null;
    private Map<String, String> studentFileMap = null;

    private List<StudentResponse> successItems = null;
    private List<FailItemResponse<StudentRequest>> failItems = null;

    private String projectWaveId;

    private String getProjectWaveId() {
        return projectWaveId;
    }

    private void setProjectWaveId(String projectWaveId) {
        this.projectWaveId = projectWaveId;
    }

    @Autowired
    IStudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ProjectWaveRepository projectWaveRepository;

    @Autowired
    StudentWaveRepository studentWaveRepository;

    @Override
    public synchronized RestApiResponse<ImportFromFileResponse<StudentResponse, StudentRequest>> addStudentForProjectWaveFromFile(MultipartFile excelFile, String projectWaveId) {
        setProjectWaveId(projectWaveId);
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
        studentInWaveMap = new HashMap<>();
        studentFileMap = new HashMap<>();
        setStudentMap();
        setStudentInWaveMap();
    }

    private void setStudentInWaveMap(){
        ProjectWave projectWave = projectWaveRepository.findOne(getProjectWaveId());
        ArrayList<StudentWave> studentWaves = studentWaveRepository.findByProjectWave(projectWave);
        Student student;
        for(int i = 0; i<studentWaves.size(); i++){
            student = studentWaves.get(i).getStudent();
            studentInWaveMap.put(student.getUsername(), student);
        }
    }

    private void setStudentMap() {
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

    private void saveStudents(Sheet studentSheet){

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

            else if(studentIsExistInProjectWave(student)){
                addFailItemResponse(index, student, "Sinh viên đã tham gia đợt ĐATN");
            }

            else if(studentIsInOtherProjectWave(student)){
                addFailItemResponse(index, student, "Sinh viên đang tham gia đợt ĐATN khác");
            }

            else if(isNotValidRow(student.getUsername(), student.getName(), student.getClass_(), student.getBirthday())){
                addFailItemResponse(index, student, "Không đủ dữ liệu");
            }

            else{
                Student studentEntity = null;
                StudentResponse studentResponse = null;
                if(!studentIsExistInDatabase(student))
                    studentEntity = saveNewStudent(student);
                else{
                    studentEntity = studentRepository.findByUsername(student.getUsername());
                }
                saveStudentInProjectWave(studentEntity);
                studentResponse = ConvertObject.convertStudentEntityToStudentResponse(studentEntity);
                successItems.add(studentResponse);
            }

            studentFileMap.put(student.getUsername(), student.getUsername());
            index++;
        }

    }

    private void saveStudentInProjectWave(Student student) {
        ProjectWave projectWave = projectWaveRepository.findOne(getProjectWaveId());
        StudentWave studentWave = new StudentWave();
        studentWave.setStudent(student);
        studentWave.setProjectWave(projectWave);
        studentWaveRepository.save(studentWave);
    }

    private boolean studentIsInOtherProjectWave(StudentRequest s) {
        ProjectWave projectWave = projectWaveRepository.findOne(getProjectWaveId());
        Student student = studentRepository.findByUsername(s.getUsername());

        return checkStudentJoinProjectWaveInSameTime(student, projectWave);
    }

    private boolean checkStudentJoinProjectWaveInSameTime(Student student, ProjectWave projectWave) {
        ArrayList<StudentWave> studentWaves = studentWaveRepository.findByStudent(student);
        if((studentWaves != null) && (studentWaves.size() != 0)){
            for(int i = 0; i < studentWaves.size(); i++){
                if(checkProjectWaveInSameTime(projectWave, studentWaves.get(i).getProjectWave())){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkProjectWaveInSameTime(ProjectWave wave, ProjectWave otherWave) {
        if(DateUtil.isDateInPeriodTime(wave.getStartDay(), otherWave.getStartDay(), otherWave.getEndDay()) || DateUtil.isDateInPeriodTime(wave.getEndDay(), otherWave.getStartDay(), otherWave.getEndDay()))
            return true;

        if(DateUtil.isDateInPeriodTime(otherWave.getStartDay(), wave.getStartDay(), wave.getEndDay()) || DateUtil.isDateInPeriodTime(otherWave.getEndDay(), wave.getStartDay(), wave.getEndDay()))
            return true;

        return false;
    }

    private boolean studentIsExistInProjectWave(StudentRequest student){
        String key = student.getUsername();
        Student studentInMap = studentInWaveMap.get(key);
        if (studentInMap != null) {
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

    private Student saveNewStudent(StudentRequest studentRequest){
        StudentResponse studentResponse = studentService.addStudent(studentRequest).getBody();
        return studentRepository.findOne(studentResponse.getId());
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
