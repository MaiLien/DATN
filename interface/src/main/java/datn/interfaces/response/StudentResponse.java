package datn.interfaces.response;

public class StudentResponse extends UserResponse {

    private String class_;

    public String getClass_() {
        return class_;
    }

    public void setClass(String class_) {
        this.class_ = class_;
    }
}
