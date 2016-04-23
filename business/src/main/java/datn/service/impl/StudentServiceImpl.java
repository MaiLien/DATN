package datn.service.impl;

import datn.dao.constant.Gender;
import datn.dao.constant.UserStatusConstant;
import datn.dao.entity.Student;
import datn.dao.repository.StudentRepository;
import datn.interfaces.request.StudentRequest;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.StudentResponse;
import datn.interfaces.util.DateFormatUtil;
import datn.interfaces.util.FormatSearchInput;
import datn.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService{

    @Autowired
    StudentRepository studentRepository;

    public RestApiResponse<ArrayList<StudentResponse>> getStudents() {
        ArrayList<Student> studentEntities = (ArrayList<Student>) studentRepository.findAll();
        ArrayList<StudentResponse> studentResponses = convertStudentEntitiesToStudentResponses(studentEntities);
        RestApiResponse<ArrayList<StudentResponse>> students = new RestApiResponse<ArrayList<StudentResponse>>(studentResponses);

        return students;
    }

    public RestApiResponse<Page<StudentResponse>> getPageStudents(int pageIndex, int sizeOfPage, String searchInput){
        PageRequest pageable = new PageRequest(pageIndex, sizeOfPage);
        Page<Student> studentPage = null;
        if(searchInput == null || "".equals(searchInput)){
            studentPage = studentRepository.findAll(pageable);
        }else{
            searchInput = FormatSearchInput.formatSearchInput(searchInput);
            studentPage = studentRepository.findBySearchInput(pageable, searchInput);
        }
        Page<StudentResponse> studentResponsePage = convertStudentEntityPageToStudentResponsePage(studentPage, pageable);

        RestApiResponse<Page<StudentResponse>> studentResponseRestApiResponse = new RestApiResponse<Page<StudentResponse>>(studentResponsePage);
        return  studentResponseRestApiResponse;
    }

    private Page<StudentResponse> convertStudentEntityPageToStudentResponsePage(Page<Student> studentPage, PageRequest pageable){
        ArrayList<StudentResponse> studentResponses = convertStudentEntitiesToStudentResponses(new ArrayList<Student>(studentPage.getContent()));
        Page<StudentResponse> studentResponsePage = new PageImpl<StudentResponse>(studentResponses, pageable, studentPage.getTotalElements());
        return studentResponsePage;
    }

    public RestApiResponse<StudentResponse> getStudent(String id) {
        Student studentEntities = studentRepository.findOne(id);
        StudentResponse studentResponse = null;
        if(studentEntities != null)
            studentResponse = convertStudentEntityToStudentResponse(studentEntities);
        RestApiResponse<StudentResponse> student = new RestApiResponse<>(studentResponse);

        return student;
    }

    public RestApiResponse<StudentResponse> addStudent(StudentRequest studentRequest) {
        Student studentEntity = convertStudentRequestToStudentEntity(studentRequest);
        studentEntity = studentRepository.save(studentEntity);
        StudentResponse studentResponse = convertStudentEntityToStudentResponse(studentEntity);
        RestApiResponse<StudentResponse> student = new RestApiResponse<StudentResponse>(studentResponse);

        return student;
    }

    public RestApiResponse<StudentResponse> deleteStudent(StudentRequest studentRequest) {
        if(studentIsExist(studentRequest.getId()))
            studentRepository.delete(studentRequest.getId());

        return new RestApiResponse<>();
    }

    private boolean studentIsExist(String id){
        Student student = studentRepository.findOne(id);
        if(student != null)
            return true;

        return false;
    }

    public RestApiResponse<StudentResponse> updateStudent(StudentRequest studentRequest) {
        Student studentEntity = convertStudentRequestToStudentEntity(studentRequest);
        studentEntity = studentRepository.save(studentEntity);
        StudentResponse studentResponse = convertStudentEntityToStudentResponse(studentEntity);
        RestApiResponse<StudentResponse> student = new RestApiResponse<StudentResponse>(studentResponse);

        return student;
    }

    public RestApiResponse<StudentResponse> lockStudent(StudentRequest studentRequest) {
        Student studentEntity = convertStudentRequestToStudentEntity(studentRequest);
        studentEntity.setStatus(UserStatusConstant.BLOCK);
        studentEntity = studentRepository.save(studentEntity);
        StudentResponse studentResponse = convertStudentEntityToStudentResponse(studentEntity);
        RestApiResponse<StudentResponse> student = new RestApiResponse<StudentResponse>(studentResponse);

        return student;
    }

    public RestApiResponse<StudentResponse> unlockStudent(StudentRequest studentRequest) {
        Student studentEntity = convertStudentRequestToStudentEntity(studentRequest);
        studentEntity.setStatus(UserStatusConstant.ACTIVE);
        studentEntity = studentRepository.save(studentEntity);
        StudentResponse studentResponse = convertStudentEntityToStudentResponse(studentEntity);
        RestApiResponse<StudentResponse> student = new RestApiResponse<StudentResponse>(studentResponse);

        return student;
    }

    private ArrayList<StudentResponse> convertStudentEntitiesToStudentResponses(ArrayList<Student> studentEntities){
        ArrayList<StudentResponse> students = new ArrayList<StudentResponse>();
        StudentResponse studentResponse;
        for (int i = 0; i < studentEntities.size(); i++) {
            studentResponse = convertStudentEntityToStudentResponse(studentEntities.get(i));
            students.add(studentResponse);
        }

        return  students;
    }

    private StudentResponse convertStudentEntityToStudentResponse(Student studentEntity){
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(studentEntity.getId());
        studentResponse.setUsername(studentEntity.getUsername());
        studentResponse.setName(studentEntity.getName());
        studentResponse.setBirthday(DateFormatUtil.convertDateToString(studentEntity.getBirthday()));
        studentResponse.setDescription(studentEntity.getDescription());
        studentResponse.setEmail(studentEntity.getEmail());
        studentResponse.setGender(studentEntity.getGender().getValue());
        if(studentEntity.getTypeOfUser() != null)
            studentResponse.setTypeOfUser(studentEntity.getTypeOfUser().getValue());
        studentResponse.setPhoneNumber(studentEntity.getPhoneNumber());
        studentResponse.setStatus(studentEntity.getStatus());
        studentResponse.setClass(studentEntity.getClass_());

        return  studentResponse;
    }

    private Student convertStudentRequestToStudentEntity(StudentRequest studentRequest){
        Student studentEntity = new Student();

        if(studentRequest.getId() != null){
            if(!"".equals(studentRequest.getId())){
                studentEntity.setId(studentRequest.getId());
            }
        }
        studentEntity.setUsername(studentRequest.getUsername());
        studentEntity.setBirthday(DateFormatUtil.convertStringToDate(studentRequest.getBirthday()));
        studentEntity.setDescription(studentRequest.getDescription());
        studentEntity.setEmail(studentRequest.getEmail());
        studentEntity.setGender(Gender.valueOfKey(studentRequest.getGender()));
        studentEntity.setName(studentRequest.getName());
        studentEntity.setPhoneNumber(studentRequest.getPhoneNumber());
        studentEntity.setStatus(studentRequest.getStatus());
        studentEntity.setClass_(studentRequest.getClass_().toUpperCase());

//        Default password is username.
        Student obj = studentRepository.findOne(studentEntity.getId());
        String password;
        if(obj == null){
            password = BCrypt.hashpw(studentRequest.getUsername(), BCrypt.gensalt(12));
        }else {
            password = obj.getPassword();
        }
        studentEntity.setPassword(password);

        return studentEntity;
    }

}
