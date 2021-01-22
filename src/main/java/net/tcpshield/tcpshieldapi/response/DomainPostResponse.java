package net.tcpshield.tcpshieldapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class DomainPostResponse {

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
        @JsonProperty
        private String name;
        @JsonProperty("backend_set_id")
        private int setID;
        @JsonProperty
        private boolean bac;
        @JsonProperty
        private boolean verified;
        @JsonProperty("updated_at")
        private Date updatedAt;
        @JsonProperty("created_at")
        private Date createdAt;

        public int getID() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getSetID() {
            return setID;
        }

        public boolean isBACEnabled() {
            return bac;
        }

        public boolean isVerified() {
            return verified;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", setID=" + setID +
                    ", bac=" + bac +
                    ", verified=" + verified +
                    ", updatedAt=" + updatedAt +
                    ", createdAt=" + createdAt +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DomainPostResponse{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
