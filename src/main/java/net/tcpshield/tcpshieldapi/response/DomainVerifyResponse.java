package net.tcpshield.tcpshieldapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DomainVerifyResponse {

    @JsonProperty
    private String message;
    @JsonProperty
    private String error;
    @JsonProperty
    private int status;

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public int getStatus() {
        return status;
    }

}
