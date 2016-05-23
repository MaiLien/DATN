package datn.interfaces.response;

import java.util.UUID;

public class StudentProjectInfoOfWaveResponse {

    private String id = UUID.randomUUID().toString();
    private StudentResponse student;
    private ProjectWaveResponse projectWave;
    private StudentWaveResponse projectInforResponse;

    public StudentWaveResponse getProjectInforResponse() {
        return projectInforResponse;
    }

    public void setProjectInforResponse(StudentWaveResponse projectInforResponse) {
        this.projectInforResponse = projectInforResponse;
    }

    public ProjectWaveResponse getProjectWave() {
        return projectWave;
    }

    public void setProjectWave(ProjectWaveResponse projectWave) {
        this.projectWave = projectWave;
    }

    public StudentResponse getStudent() {
        return student;
    }

    public void setStudent(StudentResponse student) {
        this.student = student;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
