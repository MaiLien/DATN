package datn.service.impl;

import datn.dao.constant.Gender;
import datn.dao.entity.Student;
import datn.dao.entity.Teacher;
import datn.dao.repository.TeacherRepository;
import datn.interfaces.request.TeacherRequest;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.StudentResponse;
import datn.interfaces.response.TeacherResponse;
import datn.interfaces.util.DateFormatUtil;
import datn.service.ITeacherService;
import datn.service.exceptions.UserExistedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public RestApiResponse<ArrayList<TeacherResponse>> getTeachers() {
        ArrayList<Teacher> teachers = (ArrayList<Teacher>) teacherRepository.findAll();
        ArrayList<TeacherResponse> responses = convertTeacherEntitiesToTeacherResponses(teachers);
        return new RestApiResponse<>(responses);
    }

    @Override
    public RestApiResponse<Page<TeacherResponse>> getPageTeachers(int pageIndex, int sizeOfPage, String searchInput) {
        PageRequest pageable = new PageRequest(pageIndex, sizeOfPage);
        Page<Teacher> page = teacherRepository.findAll(pageable);
//        if(searchInput == null || "".equals(searchInput)){
//            page = teacherRepository.findAll(pageable);
//        }else{
//            searchInput = formatSearchInput(searchInput);
//            studentPage = studentRepository.findBySearchInput(pageable, searchInput);
//        }
        Page<TeacherResponse> responsePage = convertStudentEntityPageToStudentResponsePage(page, pageable);

        RestApiResponse<Page<TeacherResponse>> studentResponseRestApiResponse = new RestApiResponse<>(responsePage);
        return  studentResponseRestApiResponse;
    }

    public RestApiResponse<TeacherResponse> getTeacher(String id) {
        Teacher teacherEntity = teacherRepository.findOne(id);
        TeacherResponse teacherResponse = convertTeacherEntityToTeacherResponse(teacherEntity);
        RestApiResponse<TeacherResponse> teacherResponseRestApiResponse = new RestApiResponse<TeacherResponse>(teacherResponse);

        return teacherResponseRestApiResponse;
    }

    public RestApiResponse<TeacherResponse> addTeacher(TeacherRequest teacherRequest) {
        Teacher teacherEntity = convertTeacherRequestToTeacherEntity(teacherRequest);
        try{
            teacherEntity = teacherRepository.save(teacherEntity);
        }catch (Exception e){
            throw new UserExistedException(teacherEntity.getUsername());
        }

        TeacherResponse teacherResponse = convertTeacherEntityToTeacherResponse(teacherEntity);
        RestApiResponse<TeacherResponse> teacherResponseRestApiResponse = new RestApiResponse<>(teacherResponse);

        return teacherResponseRestApiResponse;
    }

    public RestApiResponse<TeacherResponse> deleteTeacher(TeacherRequest teacherRequest) {
        if(teacherIsExist(teacherRequest.getId()))
            teacherRepository.delete(teacherRequest.getId());

        return new RestApiResponse(null);
    }

    private boolean teacherIsExist(String id){
        Teacher teacher = teacherRepository.findOne(id);
        if(teacher != null)
            return true;

        return false;
    }

    public RestApiResponse<TeacherResponse> updateTeacher(TeacherRequest request) {
        Teacher teacherEntity = convertTeacherRequestToTeacherEntity(request);
        teacherEntity = teacherRepository.save(teacherEntity);
        TeacherResponse response = convertTeacherEntityToTeacherResponse(teacherEntity);
        RestApiResponse<TeacherResponse> teacher = new RestApiResponse<>(response);

        return teacher;
    }

    private Page<TeacherResponse> convertStudentEntityPageToStudentResponsePage(Page<Teacher> studentPage, PageRequest pageable){
        ArrayList<TeacherResponse> teacherResponses= convertTeacherEntitiesToTeacherResponses(new ArrayList<>(studentPage.getContent()));
        Page<TeacherResponse> responsePage= new PageImpl<>(teacherResponses, pageable, studentPage.getTotalElements());
        return responsePage;
    }

    private ArrayList<TeacherResponse> convertTeacherEntitiesToTeacherResponses(ArrayList<Teacher> teachers){
        ArrayList<TeacherResponse> responses = new ArrayList<>();
        TeacherResponse response;
        for (int i=0; i<teachers.size(); i++){
            response = convertTeacherEntityToTeacherResponse(teachers.get(i));
            responses.add(response);
        }

        return responses;
    }

    private Teacher convertTeacherRequestToTeacherEntity(TeacherRequest teacherRequest){
        Teacher teacherEntity =new Teacher();

        if(teacherRequest.getId() != null){
            if(!"".equals(teacherRequest.getId())){
                teacherEntity.setId(teacherRequest.getId());
            }
        }

        teacherEntity.setUsername(teacherRequest.getUsername());
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

        // Default password is username.
        Teacher obj = teacherRepository.findOne(teacherEntity.getId());
        String password;
        if(obj == null){
            password = BCrypt.hashpw(teacherRequest.getUsername(), BCrypt.gensalt(12));
        }else {
            password = obj.getPassword();
        }
        teacherEntity.setPassword(password);

        return teacherEntity;
    }

    private TeacherResponse convertTeacherEntityToTeacherResponse(Teacher teacherEntity){
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setId(teacherEntity.getId());
        teacherResponse.setUsername(teacherEntity.getUsername());
        teacherResponse.setBirthday(DateFormatUtil.convertDateToString(teacherEntity.getBirthday()));
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
