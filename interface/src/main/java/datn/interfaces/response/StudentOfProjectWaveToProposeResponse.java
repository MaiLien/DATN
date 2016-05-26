package datn.interfaces.response;

public class StudentOfProjectWaveToProposeResponse {

    private StudentResponse studentResponse;
    private boolean proposed = false;
    private boolean proposedByAnotherTeacher = false;
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public StudentResponse getStudentResponse() {
        return studentResponse;
    }

    public void setStudentResponse(StudentResponse studentResponse) {
        this.studentResponse = studentResponse;
    }

    public boolean isProposed() {
        return proposed;
    }

    public void setProposed(boolean proposed) {
        this.proposed = proposed;
    }

    public boolean isProposedByAnotherTeacher() {
        return proposedByAnotherTeacher;
    }

    public void setProposedByAnotherTeacher(boolean proposedByAnotherTeacher) {
        this.proposedByAnotherTeacher = proposedByAnotherTeacher;
    }
}
