package datn.interfaces.request;

public class RegisterTeacherRequest {

    String studentId;
    String teacherId;
    String projectWaveId;

    public RegisterTeacherRequest(){}

    public RegisterTeacherRequest(String studentId, String teacherId, String projectWaveId) {
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.projectWaveId = projectWaveId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getProjectWaveId() {
        return projectWaveId;
    }

    public void setProjectWaveId(String projectWaveId) {
        this.projectWaveId = projectWaveId;
    }
}
