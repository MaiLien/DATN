package datn.controller;

import datn.request.TeacherRequest;
import datn.response.RestApiResponse;
import datn.response.TeacherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import datn.service.ITeacherService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/API")
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    @RequestMapping(value = "/teacher", method = RequestMethod.POST)
    public RestApiResponse<TeacherResponse> addTeacher(@RequestBody TeacherRequest teacherRequest){
        return teacherService.addTeacher(teacherRequest);
    }

}
