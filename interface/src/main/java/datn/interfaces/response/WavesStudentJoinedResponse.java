package datn.interfaces.response;

import java.util.ArrayList;

public class WavesStudentJoinedResponse {

    ArrayList<ProjectWaveResponse> projectWavesJoined;
    ArrayList<ProjectWaveResponse> projectWavesJoining;

    public WavesStudentJoinedResponse(ArrayList<ProjectWaveResponse> projectWavesJoining, ArrayList<ProjectWaveResponse> projectWavesJoined) {
        this.projectWavesJoining = projectWavesJoining;
        this.projectWavesJoined = projectWavesJoined;
    }

    public ArrayList<ProjectWaveResponse> getProjectWavesJoined() {
        return projectWavesJoined;
    }

    public void setProjectWavesJoined(ArrayList<ProjectWaveResponse> projectWavesJoined) {
        this.projectWavesJoined = projectWavesJoined;
    }

    public ArrayList<ProjectWaveResponse> getProjectWavesJoining() {
        return projectWavesJoining;
    }

    public void setProjectWavesJoining(ArrayList<ProjectWaveResponse> projectWavesJoining) {
        this.projectWavesJoining = projectWavesJoining;
    }
}
