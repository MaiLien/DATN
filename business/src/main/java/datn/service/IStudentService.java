package datn.service;


import datn.entity.Student;
import datn.request.StudentRequest;
import datn.response.RestApiResponse;
import datn.response.StudentResponse;

import java.util.ArrayList;

public interface IStudentService {

    public  RestApiResponse<StudentResponse> addStudent(StudentRequest studentRequest);

    public RestApiResponse<ArrayList<StudentResponse>> getStudents();

}
