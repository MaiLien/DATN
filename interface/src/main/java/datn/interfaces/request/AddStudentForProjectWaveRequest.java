package datn.interfaces.request;

public class AddStudentForProjectWaveRequest {
    String studentUsername;
    String projectWaveId;

    public AddStudentForProjectWaveRequest(){}

    public AddStudentForProjectWaveRequest(String studentUsername, String projectWaveId) {
        this.studentUsername = studentUsername;
        this.projectWaveId = projectWaveId;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentId) {
        this.studentUsername = studentId;
    }

    public String getProjectWaveId() {
        return projectWaveId;
    }

    public void setProjectWaveId(String projectWaveId) {
        this.projectWaveId = projectWaveId;
    }

}
