package datn.interfaces.util;

import datn.dao.entity.Student;
import datn.dao.entity.Teacher;
import datn.interfaces.response.StudentResponse;
import datn.interfaces.response.TeacherResponse;

public class ConvertObject {

    public static StudentResponse convertStudentEntityToStudentResponse(Student studentEntity){
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(studentEntity.getId());
        studentResponse.setUsername(studentEntity.getUsername());
        studentResponse.setName(studentEntity.getName());
        if(studentEntity.getBirthday() != null)
            studentResponse.setBirthday(DateUtil.convertDateToString(studentEntity.getBirthday()));
        studentResponse.setDescription(studentEntity.getDescription());
        studentResponse.setEmail(studentEntity.getEmail());
        studentResponse.setGender(studentEntity.getGender().getValue());
        if(studentEntity.getTypeOfUser() != null)
            studentResponse.setTypeOfUser(studentEntity.getTypeOfUser().getValue());
        studentResponse.setPhoneNumber(studentEntity.getPhoneNumber());
        studentResponse.setStatus(studentEntity.getStatus());
        studentResponse.setClass(studentEntity.getClass_());

        return  studentResponse;
    }

    public  static TeacherResponse convertTeacherEntityToTeacherResponse(Teacher teacherEntity){
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setId(teacherEntity.getId());
        teacherResponse.setUsername(teacherEntity.getUsername());
        if(teacherEntity.getBirthday() != null)
            teacherResponse.setBirthday(DateUtil.convertDateToString(teacherEntity.getBirthday()));
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
