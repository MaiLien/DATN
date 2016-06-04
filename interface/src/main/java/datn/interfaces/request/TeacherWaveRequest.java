package datn.interfaces.request;

public class TeacherWaveRequest {

    private String teacherId;
    private int maxGuide;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public int getMaxGuide() {
        return maxGuide;
    }

    public void setMaxGuide(int maxGuide) {
        this.maxGuide = maxGuide;
    }

}
