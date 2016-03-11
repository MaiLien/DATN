package datn.service;

import datn.interfaces.request.TeacherRequest;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.TeacherResponse;

import java.util.ArrayList;

public interface ITeacherService {

    public ArrayList<RestApiResponse<TeacherResponse>> getTeachers();

    public RestApiResponse<TeacherResponse> getTeacher(String id);

    public RestApiResponse<TeacherResponse> addTeacher(TeacherRequest teacherRequest);

    public RestApiResponse<TeacherResponse> deleteTeacher(TeacherRequest studentRequest);

    public RestApiResponse<TeacherResponse> updateTeacher(TeacherRequest studentRequest);

}
