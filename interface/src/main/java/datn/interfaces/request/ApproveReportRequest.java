package datn.interfaces.request;

public class ApproveReportRequest {

    private String reportId;
    private String teacherOpinions;
    private int status;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getTeacherOpinions() {
        return teacherOpinions;
    }

    public void setTeacherOpinions(String teacherOpinions) {
        this.teacherOpinions = teacherOpinions;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

