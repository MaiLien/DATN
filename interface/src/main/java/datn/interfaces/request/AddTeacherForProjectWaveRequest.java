package datn.interfaces.request;

public class AddTeacherForProjectWaveRequest {

    String teacherUsername;
    String projectWaveId;
    int numberOfStudent;

    public AddTeacherForProjectWaveRequest(){}

    public AddTeacherForProjectWaveRequest(String teacherUsername, int numberOfStudent, String projectWaveId) {
        this.teacherUsername = teacherUsername;
        this.projectWaveId = projectWaveId;
        this.numberOfStudent = numberOfStudent;
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
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
