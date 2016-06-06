package datn.interfaces.response;

public class TeacherToChangeAssignmentResponse extends TeacherInProjectWaveResponse{

    private boolean guideStudent =false;

    public void setGuideStudent(boolean guideStudent) {
        this.guideStudent = guideStudent;
    }

    public boolean isGuideStudent() {
        return guideStudent;
    }
}
