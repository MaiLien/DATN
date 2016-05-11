package datn.interfaces.response;

public class TeacherInProjectWaveResponse extends TeacherResponse{

    int maxGuide;
    int actualGuide;

    public int getMaxGuide() {
        return maxGuide;
    }

    public void setMaxGuide(int maxGuide) {
        this.maxGuide = maxGuide;
    }

    public int getActualGuide() {
        return actualGuide;
    }

    public void setActualGuide(int actualGuide) {
        this.actualGuide = actualGuide;
    }

}
