package net.tcpshield.tcpshieldapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BackendSetPostResponse {

    @JsonProperty
    private Data data;
    @JsonProperty
    private String message;
    @JsonProperty
    private int status;

    public Data getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public static class Data {

        @JsonProperty
        private int id;

        public int getID() {
            return id;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BackendSetPostResponse{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
