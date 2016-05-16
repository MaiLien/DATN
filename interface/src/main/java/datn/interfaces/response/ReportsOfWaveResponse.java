package datn.interfaces.response;

import datn.dao.entity.Report;

import java.util.ArrayList;
import java.util.UUID;

public class ReportsOfWaveResponse {

    private String id = UUID.randomUUID().toString();
    StudentResponse student;
    ProjectWaveResponse projectWave;
    ArrayList<ReportResponse> reports;

    public ArrayList<ReportResponse> getReports() {
        return reports;
    }

    public void setReports(ArrayList<ReportResponse> reports) {
        this.reports = reports;
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
