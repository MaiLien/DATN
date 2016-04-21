package datn.interfaces.response;

import java.util.List;

public class ImportFromFileResponse<T, I> {

    private List<T> successItems;
    private List<FailItemResponse<I>> failItems;

    public ImportFromFileResponse(List<T> successItems, List<FailItemResponse<I>> failItems) {
        this.failItems = failItems;
        this.successItems = successItems;
    }

    public List<T> getSuccessItems() {
        return successItems;
    }

    public void setSuccessItems(List<T> successItems) {
        this.successItems = successItems;
    }

    public List<FailItemResponse<I>> getFailItems() {
        return failItems;
    }

    public void setFailItems(List<FailItemResponse<I>> failItems) {
        this.failItems = failItems;
    }
}
