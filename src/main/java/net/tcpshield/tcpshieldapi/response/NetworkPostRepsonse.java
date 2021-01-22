package net.tcpshield.tcpshieldapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NetworkPostRepsonse {

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

    @Override
    public String toString() {
        return "NetworkPostRepsonse{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

    public static class Data {

        @JsonProperty("network_id")
        private int networkID;

        public int getNetworkID() {
            return networkID;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "networkID=" + networkID +
                    '}';
        }
    }
}
