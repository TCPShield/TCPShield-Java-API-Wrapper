package net.tcpshield.tcpshieldapi.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BackendSetPostRequest {

    @JsonProperty
    private final String name;
    @JsonProperty
    private final String[] backends;

    public BackendSetPostRequest(String name, String[] backends) {
        this.name = name;
        this.backends = backends;
    }
}
