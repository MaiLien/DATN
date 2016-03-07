package datn.service.impl;

import datn.entity.Student;
import datn.repository.StudentRepository;
import datn.request.StudentRequest;
import datn.response.RestApiResponse;
import datn.response.StudentResponse;
import datn.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentServiceImpl implements IStudentService{

    @Autowired
    StudentRepository studentRepository;

    public RestApiResponse<StudentResponse> addStudent(StudentRequest studentRequest) {
        return null;
    }

    public RestApiResponse<ArrayList<StudentResponse>> getStudents() {
        ArrayList<Student> studentEntities = (ArrayList<Student>) studentRepository.findAll();
        ArrayList<StudentResponse> studentResponses = convertStudentEntitiesToStudentResponses(studentEntities);
        RestApiResponse<ArrayList<StudentResponse>> students = new RestApiResponse<ArrayList<StudentResponse>>(studentResponses);

        return students;
    }

    private ArrayList<StudentResponse> convertStudentEntitiesToStudentResponses(ArrayList<Student> studentEntities){
        ArrayList<StudentResponse> studentResponses = new ArrayList<StudentResponse>();
        StudentResponse studentResponse;
        for (int i = 0; i < studentEntities.size(); i++) {
            studentResponse = assignStudentEntityToStudentResponse(studentEntities.get(i));
            studentResponses.add(studentResponse);
        }

        return  studentResponses;
    }

    private StudentResponse assignStudentEntityToStudentResponse(Student studentEntity){
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(studentEntity.getId());
        studentResponse.setName(studentEntity.getName());
        studentResponse.setBirthday(studentEntity.getBirthday());
        studentResponse.setDeleted(studentEntity.getDeleted());
        studentResponse.setDescription(studentEntity.getDescription());
        studentResponse.setEmail(studentEntity.getEmail());
        studentResponse.setDeleted(studentEntity.getDeleted());
        studentResponse.setGender(studentEntity.getGender().toString());
        studentResponse.setPhoneNumber(studentEntity.getPhoneNumber());
        studentResponse.setStatus(studentEntity.getStatus());
        studentResponse.setClass(studentEntity.getClass_());

        return  studentResponse;
    }

}
