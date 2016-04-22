package datn.service;

import datn.interfaces.request.TeacherRequest;
import datn.interfaces.response.RestApiResponse;
import datn.interfaces.response.StudentResponse;
import datn.interfaces.response.TeacherResponse;
import org.springframework.data.domain.Page;

import java.util.ArrayList;

public interface ITeacherService {

    RestApiResponse<ArrayList<TeacherResponse>> getTeachers();

    RestApiResponse<Page<TeacherResponse>> getPageTeachers(int pageIndex, int sizeOfPage, String searchInput);

    RestApiResponse<TeacherResponse> getTeacher(String id);

    RestApiResponse<TeacherResponse> addTeacher(TeacherRequest teacherRequest);

    RestApiResponse<TeacherResponse> deleteTeacher(TeacherRequest studentRequest);

    RestApiResponse<TeacherResponse> updateTeacher(TeacherRequest studentRequest);

}
