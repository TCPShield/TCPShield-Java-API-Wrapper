package net.tcpshield.tcpshieldapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

public class DomainResponse {

    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty("backend_set_id")
    private int backendSetID;
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

    public int getBackendSetID() {
        return backendSetID;
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
