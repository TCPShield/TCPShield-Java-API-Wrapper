package net.tcpshield.tcpshieldapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

public class NetworkResponse {

    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private boolean premium;
    @JsonProperty("protected_cname")
    private String protectedCNAME;
    @JsonProperty("txt_verification")
    private String txtVerification;
    @JsonProperty("connections_per_second_threshold")
    private int connectionsPerSecondThreshold;
    @JsonProperty("client_ban_seconds")
    private int clientBanSeconds;
    @JsonProperty("client_allow_seconds")
    private int clientAllowSeconds;
    @JsonProperty("mitigation_message")
    private String mitigationMessage;
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

    public boolean isPremium() {
        return premium;
    }

    public String getProtectedCNAME() {
        return protectedCNAME;
    }

    public String getTXTVerification() {
        return txtVerification;
    }

    public int getConnectionsPerSecondThreshold() {
        return connectionsPerSecondThreshold;
    }

    public int getClientBanSeconds() {
        return clientBanSeconds;
    }

    public int getClientAllowSeconds() {
        return clientAllowSeconds;
    }

    public String getMitigationMessage() {
        return mitigationMessage;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
