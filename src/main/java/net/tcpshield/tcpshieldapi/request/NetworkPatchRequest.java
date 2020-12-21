package net.tcpshield.tcpshieldapi.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.tcpshield.tcpshieldapi.MitigationSettings;
import net.tcpshield.tcpshieldapi.Network;

/**
 * Represents the request for patching network settings
 *
 * @see net.tcpshield.tcpshieldapi.rest.APIConstants#NETWORK_SPECIFIC_ENDPOINT
 */
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

    /**
     * Constructs a {@code NetworkPatchRequest} with the specified parameters.
     *
     * @param networkID                     the network id
     * @param name                          the name of the network; max. length: 50 characters
     * @param connectionsPerSecondThreshold how many total connections your server should receive before TCPShield starts mitigating; 1 <= x <= 100
     * @param clientBanSeconds              total amount of time blocked attempts should be banned for; 60 <= x <= 3600
     * @param clientAllowSeconds            total amount of time valid connections should be accepted; 30 <= x <= 600
     * @param mitigationMessage             the message to be displayed after successful validation; max. length: 489 characters
     * @throws IllegalArgumentException if the conditions for the parameters aren't met
     * @see net.tcpshield.tcpshieldapi.APIClient#patchNetwork(int, NetworkPatchRequest)
     * @see net.tcpshield.tcpshieldapi.APIClient#patchNetwork(int, String, int, int, int, String)
     * @see net.tcpshield.tcpshieldapi.APIClient#patchNetwork(Network, MitigationSettings)
     */
    public NetworkPatchRequest(int networkID, String name, int connectionsPerSecondThreshold, int clientBanSeconds, int clientAllowSeconds, String mitigationMessage) {
        if (name.length() > 50)
            throw new IllegalArgumentException("name cannot be longer than 50 characters. Provided value:\"" + name + "\"");
        if (1 > connectionsPerSecondThreshold || connectionsPerSecondThreshold > 100)
            throw new IllegalArgumentException("connectionsPerSecondThreshold not between 1 and 100. Provided value: \"" + connectionsPerSecondThreshold + "\"");
        if (60 > clientBanSeconds || clientBanSeconds > 3600)
            throw new IllegalArgumentException("clientBanSeconds not between 30 and 3600. Provided value: \"" + clientBanSeconds + "\"");
        if (30 > clientAllowSeconds || clientAllowSeconds > 600)
            throw new IllegalArgumentException("clientAllowSeconds not between 30 and 600. Provided value: \"" + clientAllowSeconds + "\"");
        if (mitigationMessage.length() > 500 - "{\"text\":\"\"}".length()) // 11 less characters
            throw new IllegalArgumentException("mitigationMessage cannot be longer than 489 characters. Provided value: \"" + mitigationMessage + "\" (" + " characters)");

        this.networkID = networkID;
        this.name = name;
        this.connectionsPerSecondThreshold = connectionsPerSecondThreshold;
        this.clientBanSeconds = clientBanSeconds;
        this.clientAllowSeconds = clientAllowSeconds;
        this.mitigationMessage = "{\"text\":\"" + mitigationMessage + "\"}";
    }
}
