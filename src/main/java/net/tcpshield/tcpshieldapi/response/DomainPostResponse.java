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
        private String name;
        private int setID;
        private boolean bac;
        private boolean verified;
        private Date updatedAt;
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
    }

}
