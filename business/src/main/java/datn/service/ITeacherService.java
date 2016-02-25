package datn.service;

import datn.request.TeacherRequest;
import datn.response.RestApiResponse;
import datn.response.TeacherResponse;

public interface ITeacherService {

    RestApiResponse<TeacherResponse> addTeacher(TeacherRequest teacherRequest);

}
