package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequestHeader {
    private static final String DEFAULT_CONTENT_LENGTH = "0";

    private Map<String, String> headers;

    private HttpRequestHeader(Map<String, String> headers) {
        this.headers = headers;
    }

    public static HttpRequestHeader of(List<String> headers) {
        Map<String, String> headerMap = new HashMap<>();
        for (String header : headers) {
            String[] nameAndHeader = header.split(": ");
            headerMap.put(nameAndHeader[0], nameAndHeader[1]);
        }
        return new HttpRequestHeader(headerMap);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public int getContentLength() {
        return Integer.parseInt(headers.getOrDefault(HttpHeaders.CONTENT_LENGTH, DEFAULT_CONTENT_LENGTH));
    }

    public boolean containsLoginCookie() {
        String cookie = headers.get(HttpHeaders.SET_COOKIE);
        return cookie != null && cookie.contains("logined=true");
    }
}
