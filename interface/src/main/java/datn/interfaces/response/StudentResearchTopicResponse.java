package datn.interfaces.response;

import datn.interfaces.request.ResearchTopic;

public class StudentResearchTopicResponse {

    private  StudentResponse student;
    private  ProjectWaveResponse projectWave;
    private ResearchTopic researchTopic;

    public StudentResponse getStudent() {
        return student;
    }

    public void setStudent(StudentResponse student) {
        this.student = student;
    }

    public ProjectWaveResponse getProjectWave() {
        return projectWave;
    }

    public void setProjectWave(ProjectWaveResponse projectWave) {
        this.projectWave = projectWave;
    }

    public ResearchTopic getResearchTopic() {
        return researchTopic;
    }

    public void setResearchTopic(ResearchTopic researchTopic) {
        this.researchTopic = researchTopic;
    }

}
