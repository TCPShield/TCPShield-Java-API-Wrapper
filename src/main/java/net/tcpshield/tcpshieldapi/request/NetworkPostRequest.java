package net.tcpshield.tcpshieldapi.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the request for creating a new network
 *
 * @see net.tcpshield.tcpshieldapi.rest.APIConstants#NETWORKS_ENDPOINT
 */
public class NetworkPostRequest {

    @JsonProperty
    private final String name;

    /**
     * Constructs a {@code NetworkPostRequest} with the specified name.
     *
     * @param name the name of the network; max. length: 50 characters
     */
    public NetworkPostRequest(String name) {
        if (name.length() > 50)
            throw new IllegalArgumentException("name cannot be longer than 50 characters. Provided value:\"" + name + "\"");

        this.name = name;
    }

    @Override
    public String toString() {
        return "NetworkPostRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
