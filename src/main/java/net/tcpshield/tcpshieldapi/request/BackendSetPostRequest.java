package net.tcpshield.tcpshieldapi.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class BackendSetPostRequest {

    @JsonProperty
    private final String name;
    @JsonProperty
    private final String[] backends;

    public BackendSetPostRequest(String name, String[] backends) {
        this.name = name;
        this.backends = backends;
    }

    @Override
    public String toString() {
        return "BackendSetPostRequest{" +
                "name='" + name + '\'' +
                ", backends=" + Arrays.toString(backends) +
                '}';
    }
}
