package datn.interfaces.request;

public class AddTeacherForProjectWaveRequest {

    String teacherUsername;
    String projectWaveId;

    public AddTeacherForProjectWaveRequest(){}

    public AddTeacherForProjectWaveRequest(String teacherUsername, String projectWaveId) {
        this.teacherUsername = teacherUsername;
        this.projectWaveId = projectWaveId;
    }

    public String getTeacherUsername() {
        return teacherUsername;
    }

    public void setTeacherUsername(String teacherUsername) {
        this.teacherUsername = teacherUsername;
    }

    public String getProjectWaveId() {
        return projectWaveId;
    }

    public void setProjectWaveId(String projectWaveId) {
        this.projectWaveId = projectWaveId;
    }

}
