package datn.interfaces.request;

public class StudentResearchTopicRequest {

    private  String studentId;
    private  String projectWaveId;
    private  ResearchTopic researchTopic;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getProjectWaveId() {
        return projectWaveId;
    }

    public void setProjectWave(String projectWave) {
        this.projectWaveId = projectWave;
    }

    public ResearchTopic getResearchTopic() {
        return researchTopic;
    }

    public void setResearchTopic(ResearchTopic researchTopic) {
        this.researchTopic = researchTopic;
    }
}
