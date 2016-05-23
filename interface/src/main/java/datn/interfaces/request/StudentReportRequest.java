package datn.interfaces.request;

import datn.interfaces.response.ReportDetailResponse;

import java.util.ArrayList;

public class StudentReportRequest {

    private String id;
    private String studentId;
    private String teacherId;
    private int status;
    private int percentFinish;
    private String studentOpinion;
    private String teacherOpinion;
    private ArrayList<ReportDetailRequest> reportDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPercentFinish() {
        return percentFinish;
    }

    public void setPercentFinish(int percentFinish) {
        this.percentFinish = percentFinish;
    }

    public String getStudentOpinion() {
        return studentOpinion;
    }

    public void setStudentOpinion(String studentOpinion) {
        this.studentOpinion = studentOpinion;
    }

    public String getTeacherOpinion() {
        return teacherOpinion;
    }

    public void setTeacherOpinion(String teacherOpinion) {
        this.teacherOpinion = teacherOpinion;
    }

    public ArrayList<ReportDetailRequest> getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(ArrayList<ReportDetailRequest> reportDetails) {
        this.reportDetails = reportDetails;
    }
}
