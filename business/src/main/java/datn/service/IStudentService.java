package datn.service;


import datn.dao.entity.Student;
import datn.interfaces.request.StudentRequest;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.StudentResponse;
import org.springframework.data.domain.Page;

import java.util.ArrayList;

public interface IStudentService {

    public RestApiResponse<ArrayList<StudentResponse>> getStudents();

    public RestApiResponse<StudentResponse> getStudent(String id);

    public  RestApiResponse<StudentResponse> addStudent(StudentRequest studentRequest);

    public RestApiResponse<StudentResponse> deleteStudent(StudentRequest studentRequest);

    public RestApiResponse<StudentResponse> updateStudent(StudentRequest studentRequest);

    public Student getStudentEntity(String id);

    public RestApiResponse<Page<StudentResponse>> getPageStudents(int pageIndex, int sizeOfPage);

}
