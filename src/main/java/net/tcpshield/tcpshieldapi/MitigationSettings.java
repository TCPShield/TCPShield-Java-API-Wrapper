package net.tcpshield.tcpshieldapi;

public interface MitigationSettings {

    int getConnectionsPerSecondThreshold();

    void setConnectionsPerSecondThreshold(int threshold);

    int getClientBanSeconds();

    void setClientBanSeconds(int seconds);

    int getClientAllowSeconds();

    void setClientAllowSeconds(int seconds);

    String getMitigationMessage();

    void setMitigationMessage(String message);

}
