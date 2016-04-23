package datn.interfaces.util;

import datn.dao.entity.Student;
import datn.interfaces.response.StudentResponse;

public class ConvertStudentObject {
    public static StudentResponse convertStudentEntityToStudentResponse(Student studentEntity){
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(studentEntity.getId());
        studentResponse.setUsername(studentEntity.getUsername());
        studentResponse.setName(studentEntity.getName());
        studentResponse.setBirthday(DateFormatUtil.convertDateToString(studentEntity.getBirthday()));
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
}
