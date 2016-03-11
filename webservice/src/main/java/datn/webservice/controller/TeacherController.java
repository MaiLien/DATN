package datn.webservice.controller;

import datn.interfaces.request.TeacherRequest;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.TeacherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import datn.service.ITeacherService;

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
