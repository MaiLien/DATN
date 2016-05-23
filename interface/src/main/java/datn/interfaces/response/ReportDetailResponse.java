package datn.interfaces.response;

import java.util.UUID;

public class ReportDetailResponse {

    private String id = UUID.randomUUID().toString();
    private String startTimeAndEndTime;
    private String workContent;
    private String note;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
