package net.tcpshield.tcpshieldapi.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NetworkPatchRequest {

    @JsonProperty
    private final String name;
    @JsonProperty("connections_per_second_threshold")
    private final int connectionsPerSecondThreshold;
    @JsonProperty("client_ban_seconds")
    private final int clientBanSeconds;
    @JsonProperty("client_allow_seconds")
    private final int clientAllowSeconds;
    @JsonProperty("mitigation_message")
    private final String mitigationMessage;

    public NetworkPatchRequest(String name, int connectionsPerSecondThreshold, int clientBanSeconds, int clientAllowSeconds, String mitigationMessage) {
        this.name = name;
        this.connectionsPerSecondThreshold = connectionsPerSecondThreshold;
        this.clientBanSeconds = clientBanSeconds;
        this.clientAllowSeconds = clientAllowSeconds;
        this.mitigationMessage = mitigationMessage;
    }
}
