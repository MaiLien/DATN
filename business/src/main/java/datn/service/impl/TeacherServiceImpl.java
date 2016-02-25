package datn.service.impl;

import datn.request.TeacherRequest;
import datn.response.RestApiResponse;
import datn.response.TeacherResponse;
import datn.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements ITeacherService {


    public RestApiResponse<TeacherResponse> addTeacher(TeacherRequest teacherRequest) {
        return null;
    }

}
