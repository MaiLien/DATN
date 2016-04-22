package datn.interfaces.response;

import java.util.ArrayList;
import java.util.List;

public class ProjectWaveResponse {

    private String id;
    private String schoolYear;
    private int semester;
    private String startTimeAndEndTime;
    private String timeForTeacherProposesStudent;
    private String timeForStudentRegisterTeacher;
    private List<String> reportTimes;
    private String timeForStudentSubmitProject;
    private String timeForStudentDefend;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getStartTimeAndEndTime() {
        return startTimeAndEndTime;
    }

    public void setStartTimeAndEndTime(String startTimeAndEndTime) {
        this.startTimeAndEndTime = startTimeAndEndTime;
    }

    public String getTimeForTeacherProposesStudent() {
        return timeForTeacherProposesStudent;
    }

    public void setTimeForTeacherProposesStudent(String timeForTeacherProposesStudent) {
        this.timeForTeacherProposesStudent = timeForTeacherProposesStudent;
    }

    public String getTimeForStudentRegisterTeacher() {
        return timeForStudentRegisterTeacher;
    }

    public void setTimeForStudentRegisterTeacher(String timeForStudentRegisterTeacher) {
        this.timeForStudentRegisterTeacher = timeForStudentRegisterTeacher;
    }

    public List<String> getReportTimes() {
        return reportTimes;
    }

    public void setReportTimes(List<String> reportTimes) {
        this.reportTimes = reportTimes;
    }

    public String getTimeForStudentSubmitProject() {
        return timeForStudentSubmitProject;
    }

    public void setTimeForStudentSubmitProject(String timeForStudentSubmitProject) {
        this.timeForStudentSubmitProject = timeForStudentSubmitProject;
    }

    public String getTimeForStudentDefend() {
        return timeForStudentDefend;
    }

    public void setTimeForStudentDefend(String timeForStudentDefend) {
        this.timeForStudentDefend = timeForStudentDefend;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
