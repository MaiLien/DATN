package datn.interfaces.response;

import java.io.Serializable;

public class RestApiResponseHeaders implements Serializable {

    private static final long serialVersionUID = 1171838699250220428L;
    private String responseTimestamp;
    private String resultCode = "0";
    private String resultDescription = "Success";

    public String getResponseTimestamp() {
        return responseTimestamp;
    }

    public void setResponseTimestamp(String responseTimestamp) {
        this.responseTimestamp = responseTimestamp;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }
}
