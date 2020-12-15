package net.tcpshield.tcpshieldapi.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NetworkPatchRequest {

    @JsonProperty("id")
    private final int networkID;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("connections_per_second_threshold")
    private final int connectionsPerSecondThreshold;
    @JsonProperty("client_ban_seconds")
    private final int clientBanSeconds;
    @JsonProperty("client_allow_seconds")
    private final int clientAllowSeconds;
    @JsonProperty("mitigation_message")
    private final String mitigationMessage;

    public NetworkPatchRequest(int networkID, String name, int connectionsPerSecondThreshold, int clientBanSeconds, int clientAllowSeconds, String mitigationMessage) {
        this.networkID = networkID;
        this.name = name;
        this.connectionsPerSecondThreshold = connectionsPerSecondThreshold;
        this.clientBanSeconds = clientBanSeconds;
        this.clientAllowSeconds = clientAllowSeconds;
        this.mitigationMessage = "{\"text\":\"" + mitigationMessage + "\"}";
    }
}
