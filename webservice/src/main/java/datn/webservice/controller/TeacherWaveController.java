package datn.webservice.controller;

import datn.interfaces.request.ProposeStudentRequest;
import datn.interfaces.request.StudentResearchTopicRequest;
import datn.interfaces.response.*;
import datn.service.IProjectWaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/API")
public class TeacherWaveController {

    @Autowired
    IProjectWaveService projectWaveService;

    @RequestMapping(value = "/getProjectWaveTeacherJoin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RestApiResponse<WavesTeacherJoinedResponse> getProjectWaveTeacherJoin(String teacherId){
        return projectWaveService.getProjectWaveTeacherJoin(teacherId);
    }

    @RequestMapping(value = "/proposeStudent", method = RequestMethod.POST)
    public RestApiResponse<ProposeStudentResponse> proposeStudent(@RequestBody ProposeStudentRequest request){
        return projectWaveService.proposeStudent(request);
    }

}
