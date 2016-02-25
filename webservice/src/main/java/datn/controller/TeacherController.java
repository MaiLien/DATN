package datn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import javax.validation.Valid;

import datn.request.TeacherRequest;
import datn.response.TeacherResponse;
import datn.response.RestApiResponse;
import datn.service.ITeacherService;

@Controller
public class TeacherController {

    @Autowired
    ITeacherService teacherService;

    @RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
//    public RestApiResponse<TeacherResponse> addTeacher(@RequestBody @Valid TeacherRequest teacherRequest){
    public RestApiResponse<TeacherResponse> addTeacher(@RequestBody TeacherRequest teacherRequest){
        return teacherService.addTeacher(teacherRequest);
    }

}
