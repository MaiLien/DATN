package datn.service;


import datn.entity.Student;
import datn.request.StudentRequest;
import datn.response.RestApiResponse;
import datn.response.StudentResponse;

import java.util.ArrayList;

public interface IStudentService {

    public RestApiResponse<ArrayList<StudentResponse>> getStudents();

    public RestApiResponse<StudentResponse> getStudent(String id);

    public  RestApiResponse<StudentResponse> addStudent(StudentRequest studentRequest);

    public RestApiResponse<StudentResponse> deleteStudent(StudentRequest studentRequest);

    public RestApiResponse<StudentResponse> updateStudent(StudentRequest studentRequest);

}
