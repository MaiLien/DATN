package datn.interfaces.response;

import java.io.Serializable;

public class RestApiResponse<T> implements Serializable {

    private static final long serialVersionUID = 655915097213118500L;
    private RestApiResponseHeaders headers = new RestApiResponseHeaders();
    private T body;

    public RestApiResponse() {
        this.headers = new RestApiResponseHeaders();
    }

    public RestApiResponse(T body) {
        this.body = body;
    }

    public RestApiResponseHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(RestApiResponseHeaders headers) {
        this.headers = headers;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
