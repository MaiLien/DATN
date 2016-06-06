package datn.interfaces.response;

public class AssignmentDispalyedInStudentResponse {

    StudentResponse student;
    TeacherResponse teacher;

    public StudentResponse getStudent() {
        return student;
    }

    public void setStudent(StudentResponse student) {
        this.student = student;
    }

    public TeacherResponse getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherResponse teacher) {
        this.teacher = teacher;
    }

}
