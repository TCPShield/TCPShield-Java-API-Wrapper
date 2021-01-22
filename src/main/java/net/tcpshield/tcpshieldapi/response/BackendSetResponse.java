package net.tcpshield.tcpshieldapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Arrays;
import java.util.Date;

public class BackendSetResponse {

    @JsonProperty("id")
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private boolean active;
    @JsonProperty("updated_at")
    private Date updatedAt;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("deleted_at")
    private Date deletedAt;
    @JsonProperty
    private String[] backends;
    @JsonProperty
    private int status;
    @JsonProperty
    private String message;

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public String[] getBackends() {
        return backends;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "BackendSetResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", deletedAt=" + deletedAt +
                ", backends=" + Arrays.toString(backends) +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
