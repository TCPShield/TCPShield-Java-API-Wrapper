package net.tcpshield.tcpshieldapi.rest;

public class RestResponse<T> {

    private final int statusCode;
    private final T data;

    public RestResponse(int statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public T getData() {
        return data;
    }
}
