package net.tcpshield.tcpshieldapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DomainPreverifyResponse {

    @JsonProperty
    private String message;
    @JsonProperty
    private int status;

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

}
