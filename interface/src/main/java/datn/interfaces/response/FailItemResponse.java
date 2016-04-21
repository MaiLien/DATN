package datn.interfaces.response;

import java.util.List;

public class FailItemResponse<T> {
    private int row;
    private T errorItem;
    private String reason;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public T getErrorItem() {
        return errorItem;
    }

    public void setErrorItem(T errorItem) {
        this.errorItem = errorItem;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
