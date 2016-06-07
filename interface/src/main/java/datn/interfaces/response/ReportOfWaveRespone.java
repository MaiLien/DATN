package datn.interfaces.response;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;

public class ReportOfWaveRespone{

    private String id;
    private String endTime;
    private int ordinal;
    private String startTime;
    private boolean timeToReport;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public boolean isTimeToReport() {
        return timeToReport;
    }

    public void setTimeToReport(boolean timeToReport) {
        this.timeToReport = timeToReport;
    }
}
