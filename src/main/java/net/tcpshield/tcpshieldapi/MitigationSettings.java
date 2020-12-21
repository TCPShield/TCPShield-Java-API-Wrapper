package net.tcpshield.tcpshieldapi;

/**
 * Represents the mitigation settings of a network.
 */
public interface MitigationSettings {

    /**
     * @return how many total connections your server should receive before TCPShield starts mitigating
     */
    int getConnectionsPerSecondThreshold();

    /**
     * Sets how many total connections your server should receive before TCPShield starts mitigating.
     * @param threshold how many total connections your server should receive before TCPShield starts mitigating; 1 <= x <= 100
     * @throws IllegalArgumentException if the {@code threshold} is not between 1 and 100
     */
    void setConnectionsPerSecondThreshold(int threshold);

    /**
     * @return the total amount of time blocked attempts should be banned for
     */
    int getClientBanSeconds();

    /**
     * Sets the total amount of time blocked attempts should be banned for.
     * @param seconds the total amount of time blocked attempts should be banned for; 60 <= x <= 3600
     * @throws IllegalArgumentException if {@code seconds} is not between 60 and 3600
     */
    void setClientBanSeconds(int seconds);

    /**
     * @return the total amount of time valid connections should be accepted
     */
    int getClientAllowSeconds();

    /**
     * Sets the total amount of time valid connections should be accepted.
     *
     * @param seconds the total amount of time valid connections should be accepted; 30 <= x <= 600
     * @throws IllegalArgumentException if {@code seconds} is not between 30 and 600
     */
    void setClientAllowSeconds(int seconds);

    /**
     * @return the message to be displayed after successful validation
     */
    String getMitigationMessage();

    /**
     * Sets the message to be displayed after successful validation.
     *
     * @param message the message to be displayed after successful validation; max. length: 489 characters
     * @implNote this is the raw mitigation message. Before sending the request to the server, it has to be wrapped in {@code {"text":"..."}}
     */
    void setMitigationMessage(String message);

}
