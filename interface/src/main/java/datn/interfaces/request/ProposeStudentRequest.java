package datn.interfaces.request;

import java.util.ArrayList;

public class ProposeStudentRequest {

    private String teacherId;
    private String projectWaveId;
    private ArrayList<String> studentsProposed;

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

    public ArrayList<String> getStudentsProposed() {
        return studentsProposed;
    }

    public void setStudentsProposed(ArrayList<String> studentsProposed) {
        this.studentsProposed = studentsProposed;
    }
}
