package net.tcpshield.tcpshieldapi.rest;

public class RestRequest<T> {

    private final Class<T> responseClass;
    private final String url;
    private final RequestType requestType;
    private final Object data;

    RestRequest(Class<T> responseClass, String url, RequestType requestType, Object data) {
        this.responseClass = responseClass;
        this.url = url;
        this.requestType = requestType;
        this.data = data;
    }

    public static <T> Builder<T> builder() {
        return new Builder<>(null);
    }

    public static <T> Builder<T> builder(Class<T> responseClass) {
        return new Builder<>(responseClass);
    }

    public Class<T> getResponseClass() {
        return responseClass;
    }

    public String getURL() {
        return url;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public Object getData() {
        return data;
    }

    public T execute(RestClient client) {
        return client.makeRequest(this);
    }

    public static class Builder<T> {

        private final Class<T> responseClass;
        private String url;
        private RequestType requestType;
        private Object data;

        public Builder(Class<T> responseClass) {
            this.responseClass = responseClass;
        }

        public Builder<T> url(String url) {
            this.url = url;
            return this;
        }

        public Builder<T> pathVariable(String key, String value) {
            this.url = this.url.replaceAll("\\{" + key + "}", value);
            return this;
        }

        public Builder<T> requestType(RequestType requestType) {
            this.requestType = requestType;
            return this;
        }

        public Builder<T> data(Object data) {
            this.data = data;
            return this;
        }

        public RestRequest<T> build() {
            return new RestRequest<>(responseClass, url, requestType, data);
        }
    }
}
