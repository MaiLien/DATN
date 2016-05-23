package datn.interfaces.response;

import java.util.ArrayList;

public class StudentWaveResponse {

    private String id;
    private int status;
    private String topic;
    private String description;
    private ArrayList<ReportResponse> reports;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public ArrayList<ReportResponse> getReports() {
        return reports;
    }

    public void setReports(ArrayList<ReportResponse> reports) {
        this.reports = reports;
    }

}
