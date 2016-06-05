package datn.interfaces.response;

import java.util.ArrayList;

public class ReportToApproveResponse {

    private String id;//student_report_id

    private String studentId;
    private String studentUserName;
    private String studentName;
    private String studentClass;

    private String topic;
    private String description;
    private boolean timeSubmitReport;
    private String timeSubmitReportString;
    private int ordinal;

    private int status;//0 : chua nop, 1:dang cho duyet, 2 da nop, 3 Đã phản hồi ý kiến
    private String createdDate;
    private String studentOpinion;
    private String teacherOpinion;
    private int percentFinish;
    private ArrayList<ReportDetailResponse> reportDetails;

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

    public String getStudentUserName() {
        return studentUserName;
    }

    public void setStudentUserName(String studentUserName) {
        this.studentUserName = studentUserName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTimeSubmitReport() {
        return timeSubmitReport;
    }

    public void setTimeSubmitReport(boolean timeSubmitReport) {
        this.timeSubmitReport = timeSubmitReport;
    }

    public String getTimeSubmitReportString() {
        return timeSubmitReportString;
    }

    public void setTimeSubmitReportString(String timeSubmitReportString) {
        this.timeSubmitReportString = timeSubmitReportString;
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

    public int getPercentFinish() {
        return percentFinish;
    }

    public void setPercentFinish(int percentFinish) {
        this.percentFinish = percentFinish;
    }

    public ArrayList<ReportDetailResponse> getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(ArrayList<ReportDetailResponse> reportDetails) {
        this.reportDetails = reportDetails;
    }
}
