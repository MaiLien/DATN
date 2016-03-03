package datn.service.impl;

import datn.entity.Teacher;
import datn.repository.TeacherRepository;
import datn.request.TeacherRequest;
import datn.response.RestApiResponse;
import datn.response.TeacherResponse;
import datn.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public RestApiResponse<TeacherResponse> addTeacher(TeacherRequest teacherRequest) {
        Teacher teacherEntity = convertTeacherRequestToTeacherEntity(teacherRequest);
        teacherEntity = teacherRepository.save(teacherEntity);

        return getResponseWhenAddTeacherSuccessfully(teacherEntity);
    }

    private Teacher convertTeacherRequestToTeacherEntity(TeacherRequest teacherRequest){
        Teacher teacherEntity =new Teacher();
        teacherEntity.setId(teacherRequest.getId());

        return teacherEntity;
    }

    private RestApiResponse<TeacherResponse> getResponseWhenAddTeacherSuccessfully(Teacher teacherEntity){
        TeacherResponse teacherResponse = convertTeacherEntityToTeacherResponse(teacherEntity);

        return  new RestApiResponse<TeacherResponse>(teacherResponse);
    }

    private TeacherResponse convertTeacherEntityToTeacherResponse(Teacher teacherEntity){
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setId(teacherEntity.getId());

        return teacherResponse;
    }

}
