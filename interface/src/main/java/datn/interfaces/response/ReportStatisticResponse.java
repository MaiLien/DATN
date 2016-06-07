package datn.interfaces.response;

import java.util.ArrayList;

public class ReportStatisticResponse {

    private ReportOfWaveResponse report;
    private String donePercent;
    private String waitingPercent;
    private String notPercent;
    private ArrayList<StudentResponse> doneList;
    private ArrayList<StudentResponse> waitingList;
    private ArrayList<StudentResponse> notList;

    public ReportOfWaveResponse getReport() {
        return report;
    }

    public void setReport(ReportOfWaveResponse report) {
        this.report = report;
    }

    public String getDonePercent() {
        return donePercent;
    }

    public void setDonePercent(String donePercent) {
        this.donePercent = donePercent;
    }

    public String getWaitingPercent() {
        return waitingPercent;
    }

    public void setWaitingPercent(String waitingPercent) {
        this.waitingPercent = waitingPercent;
    }

    public String getNotPercent() {
        return notPercent;
    }

    public void setNotPercent(String notPercent) {
        this.notPercent = notPercent;
    }

    public ArrayList<StudentResponse> getDoneList() {
        return doneList;
    }

    public void setDoneList(ArrayList<StudentResponse> doneList) {
        this.doneList = doneList;
    }

    public ArrayList<StudentResponse> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(ArrayList<StudentResponse> waitingList) {
        this.waitingList = waitingList;
    }

    public ArrayList<StudentResponse> getNotList() {
        return notList;
    }

    public void setNotList(ArrayList<StudentResponse> notList) {
        this.notList = notList;
    }
}
