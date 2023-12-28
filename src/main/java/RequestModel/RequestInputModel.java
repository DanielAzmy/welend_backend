package RequestModel;

import com.sun.net.httpserver.Headers;

import java.util.List;

public class RequestInputModel<T> {
    private String URL;
    private List<String[]> headers;
    private T body;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public List<String[]> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String[]> headers) {
        this.headers = headers;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
