package datn.webservice.controller;

import datn.interfaces.request.StudentRequest;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.StudentResponse;
import datn.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/API")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public RestApiResponse<ArrayList<StudentResponse>> getStudents(){
        return studentService.getStudents();
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public RestApiResponse<StudentResponse> getStudent(String id){
        return studentService.getStudent(id);
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public RestApiResponse<StudentResponse> addStudent(@RequestBody StudentRequest studentRequest){
        return studentService.addStudent(studentRequest);
    }

    @RequestMapping(value = "/student", method = RequestMethod.PUT)
    public RestApiResponse<StudentResponse> updateStudent(@RequestBody StudentRequest studentRequest){
        return studentService.updateStudent(studentRequest);
    }

    @RequestMapping(value = "/student", method = RequestMethod.DELETE)
    public RestApiResponse<StudentResponse> deleteStudent(StudentRequest studentRequest){
        return studentService.deleteStudent(studentRequest);
    }

}
