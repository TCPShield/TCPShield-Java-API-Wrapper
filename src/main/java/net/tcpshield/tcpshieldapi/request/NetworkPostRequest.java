package net.tcpshield.tcpshieldapi.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NetworkPostRequest {

    @JsonProperty
    private final String name;

    public NetworkPostRequest(String name) {
        this.name = name;
    }
}
