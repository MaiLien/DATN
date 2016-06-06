package datn.interfaces.response;

import java.util.ArrayList;

public class AssignmentDispalyedInStudentResponse {

    StudentResponse student;
    ArrayList<TeacherResponse> teacher;

    public StudentResponse getStudent() {
        return student;
    }

    public void setStudent(StudentResponse student) {
        this.student = student;
    }

    public ArrayList<TeacherResponse> getTeacher() {
        return teacher;
    }

    public void setTeacher(ArrayList<TeacherResponse> teacher) {
        this.teacher = teacher;
    }
}
