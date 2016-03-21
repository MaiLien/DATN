package datn.service.impl;

import datn.dao.constant.Gender;
import datn.dao.entity.Teacher;
import datn.dao.repository.TeacherRepository;
import datn.interfaces.request.TeacherRequest;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.TeacherResponse;
import datn.interfaces.util.DateFormatUtil;
import datn.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public ArrayList<RestApiResponse<TeacherResponse>> getTeachers() {
        return null;
    }

    public RestApiResponse<TeacherResponse> getTeacher(String id) {
        Teacher teacherEntity = teacherRepository.findOne(id);
        TeacherResponse teacherResponse = convertTeacherEntityToTeacherResponse(teacherEntity);
        RestApiResponse<TeacherResponse> teacherResponseRestApiResponse = new RestApiResponse<TeacherResponse>(teacherResponse);

        return teacherResponseRestApiResponse;
    }

    public RestApiResponse<TeacherResponse> addTeacher(TeacherRequest teacherRequest) {
        Teacher teacherEntity = convertTeacherRequestToTeacherEntity(teacherRequest);
        teacherEntity = teacherRepository.save(teacherEntity);
        TeacherResponse teacherResponse = convertTeacherEntityToTeacherResponse(teacherEntity);
        RestApiResponse<TeacherResponse> teacherResponseRestApiResponse = new RestApiResponse<TeacherResponse>(teacherResponse);

        return teacherResponseRestApiResponse;
    }

    public RestApiResponse<TeacherResponse> deleteTeacher(TeacherRequest studentRequest) {
        return null;
    }

    public RestApiResponse<TeacherResponse> updateTeacher(TeacherRequest studentRequest) {
        return null;
    }

    private Teacher convertTeacherRequestToTeacherEntity(TeacherRequest teacherRequest){
        Teacher teacherEntity =new Teacher();
        teacherEntity.setId(teacherRequest.getId());
        teacherEntity.setBirthday(DateFormatUtil.convertStringToDate(teacherRequest.getBirthday()));
        teacherEntity.setDescription(teacherRequest.getDescription());
        teacherEntity.setEmail(teacherRequest.getEmail());
        teacherEntity.setGender(Gender.valueOfKey(teacherRequest.getGender()));
        teacherEntity.setName(teacherRequest.getName());
        teacherEntity.setPhoneNumber(teacherRequest.getPhoneNumber());
        teacherEntity.setStatus(teacherRequest.getStatus());
        teacherEntity.setDegree(teacherRequest.getDegree());
        teacherEntity.setMajor(teacherRequest.getMajor());
        teacherEntity.setResearchDirection(teacherRequest.getResearchDirection());

        return teacherEntity;
    }

    private TeacherResponse convertTeacherEntityToTeacherResponse(Teacher teacherEntity){
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setId(teacherEntity.getId());
        teacherResponse.setTypeOfUser(teacherEntity.getTypeOfUser().getValue());
        teacherResponse.setBirthday(DateFormatUtil.convertDateToString(teacherEntity.getBirthday()));
        teacherResponse.setDeleted(teacherEntity.getDeleted());
        teacherResponse.setDescription(teacherEntity.getDescription());
        teacherResponse.setEmail(teacherEntity.getEmail());
        teacherResponse.setGender(teacherEntity.getGender().getValue());
        teacherResponse.setName(teacherEntity.getName());
        teacherResponse.setPhoneNumber(teacherEntity.getPhoneNumber());
        teacherResponse.setStatus(teacherEntity.getStatus());
        teacherResponse.setDegree(teacherEntity.getDegree());
        teacherResponse.setMajor(teacherEntity.getMajor());
        teacherResponse.setResearchDirection(teacherEntity.getResearchDirection());

        return teacherResponse;
    }

}
