package net.tcpshield.tcpshieldapi.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DomainPreverifyRequest {

    @JsonProperty("domain_name")
    private final String domain;

    public DomainPreverifyRequest(String domain) {
        this.domain = domain;
    }
}
