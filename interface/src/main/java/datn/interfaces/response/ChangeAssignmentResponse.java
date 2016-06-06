package datn.interfaces.response;

import java.util.ArrayList;

public class ChangeAssignmentResponse {

    StudentResponse student;
    ArrayList<TeacherResponse> guideTeachers;
    ArrayList<TeacherToChangeAssignmentResponse> teacherOptions;

    public StudentResponse getStudent() {
        return student;
    }

    public void setStudent(StudentResponse student) {
        this.student = student;
    }

    public ArrayList<TeacherResponse> getGuideTeachers() {
        return guideTeachers;
    }

    public void setGuideTeachers(ArrayList<TeacherResponse> guideTeachers) {
        this.guideTeachers = guideTeachers;
    }

    public ArrayList<TeacherToChangeAssignmentResponse> getTeacherOptions() {
        return teacherOptions;
    }

    public void setTeacherOptions(ArrayList<TeacherToChangeAssignmentResponse> teacherOptions) {
        this.teacherOptions = teacherOptions;
    }
}
