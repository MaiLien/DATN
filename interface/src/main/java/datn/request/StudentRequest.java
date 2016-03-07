package datn.request;

public class StudentRequest extends UserRequest {
    private String class_;

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }
}
