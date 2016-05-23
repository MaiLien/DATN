package datn.interfaces.request;

public class ReportDetailRequest {

    private String startTimeAndEndTime;
    private String workContent;
    private String note;

    public String getStartTimeAndEndTime() {
        return startTimeAndEndTime;
    }

    public void setStartTimeAndEndTime(String startTimeAndEndTime) {
        this.startTimeAndEndTime = startTimeAndEndTime;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
