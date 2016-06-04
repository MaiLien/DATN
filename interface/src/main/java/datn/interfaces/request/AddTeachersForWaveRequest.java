package datn.interfaces.request;

import java.util.ArrayList;

public class AddTeachersForWaveRequest {

    private String projectWaveId;
    private ArrayList<TeacherWaveRequest> teachers;

    public String getProjectWaveId() {
        return projectWaveId;
    }

    public void setProjectWaveId(String projectWaveId) {
        this.projectWaveId = projectWaveId;
    }

    public ArrayList<TeacherWaveRequest> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<TeacherWaveRequest> teachers) {
        this.teachers = teachers;
    }
}

