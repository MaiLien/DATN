package datn.webservice.controller;

import datn.interfaces.request.StudentRequest;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.StudentResponse;
import datn.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @RequestMapping(value = "/getAllStudents", method = RequestMethod.GET)
    public RestApiResponse<ArrayList<StudentResponse>> getStudents() {
        return studentService.getStudents();
    }

    @RequestMapping(value = "/getStudentsByPage", method = RequestMethod.GET)
    public RestApiResponse<Page<StudentResponse>> getStudentsByPage(int pageIndex, int sizeOfPage, String searchInput) {
        return studentService.getPageStudents(pageIndex, sizeOfPage, searchInput);
    }

    @RequestMapping(value = "/getStudent", method = RequestMethod.GET)
    public RestApiResponse<StudentResponse> getStudent(String id){
        return studentService.getStudent(id);
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public RestApiResponse<StudentResponse> addStudent(@RequestBody StudentRequest studentRequest){
        return studentService.addStudent(studentRequest);
    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.PUT)
    public RestApiResponse<StudentResponse> updateStudent(@RequestBody StudentRequest studentRequest){
        return studentService.updateStudent(studentRequest);
    }


    /*Can not pass parameter with Postman if it has @RequestBody, otherwise website*/
    @RequestMapping(value = "/deleteStudent", method = RequestMethod.DELETE)
    public RestApiResponse<StudentResponse> deleteStudent(StudentRequest studentRequest){
        return studentService.deleteStudent(studentRequest);
    }

    @RequestMapping(value = "/lockStudent", method = RequestMethod.PUT)
    public RestApiResponse<StudentResponse> lockStudent(@RequestBody StudentRequest studentRequest){
        return studentService.lockStudent(studentRequest);
    }

    @RequestMapping(value = "/unlockStudent", method = RequestMethod.PUT)
    public RestApiResponse<StudentResponse> unlockStudent(@RequestBody StudentRequest studentRequest){
        return studentService.unlockStudent(studentRequest);
    }

}
