package datn.controller;

import datn.entity.Student;
import datn.request.StudentRequest;
import datn.response.RestApiResponse;
import datn.response.StudentResponse;
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

}
