package datn.interfaces.response;

import java.util.ArrayList;
import java.util.UUID;

public class ReportResponse {

    private String id;//id student report
    private boolean timeSubmitReport;
    private String timeSubmitReportString;
    private int ordinal;
    private int status;//0 : chua nop, 1:nhap, 2 da nop
    private String createdDate;
    private String studentOpinion;
    private String teacherOpinion;
    private ArrayList<ReportDetailResponse> reportDetails;

    public String getTimeSubmitReportString() {
        return timeSubmitReportString;
    }

    public void setTimeSubmitReportString(String timeSubmitReportString) {
        this.timeSubmitReportString = timeSubmitReportString;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isTimeSubmitReport() {
        return timeSubmitReport;
    }

    public void setTimeSubmitReport(boolean timeSubmitReport) {
        this.timeSubmitReport = timeSubmitReport;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
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

    public ArrayList<ReportDetailResponse> getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(ArrayList<ReportDetailResponse> reportDetails) {
        this.reportDetails = reportDetails;
    }
}
