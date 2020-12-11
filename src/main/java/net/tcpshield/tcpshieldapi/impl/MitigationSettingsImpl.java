package net.tcpshield.tcpshieldapi.impl;

import net.tcpshield.tcpshieldapi.MitigationSettings;

import java.util.Objects;

class MitigationSettingsImpl implements MitigationSettings {

    private int connectionsPerSecondThreshold;
    private int clientBanSeconds;
    private int clientAllowSeconds;
    private String mitigationMessage;

    MitigationSettingsImpl(int connectionsPerSecondThreshold, int clientBanSeconds, int clientAllowSeconds, String mitigationMessage) {
        this.connectionsPerSecondThreshold = connectionsPerSecondThreshold;
        this.clientBanSeconds = clientBanSeconds;
        this.clientAllowSeconds = clientAllowSeconds;
        this.mitigationMessage = mitigationMessage;
    }

    @Override
    public int getConnectionsPerSecondThreshold() {
        return connectionsPerSecondThreshold;
    }

    @Override
    public void setConnectionsPerSecondThreshold(int connectionsPerSecondThreshold) {
        this.connectionsPerSecondThreshold = connectionsPerSecondThreshold;
    }

    @Override
    public int getClientBanSeconds() {
        return clientBanSeconds;
    }

    @Override
    public void setClientBanSeconds(int clientBanSeconds) {
        this.clientBanSeconds = clientBanSeconds;
    }

    @Override
    public int getClientAllowSeconds() {
        return clientAllowSeconds;
    }

    @Override
    public void setClientAllowSeconds(int clientAllowSeconds) {
        this.clientAllowSeconds = clientAllowSeconds;
    }

    @Override
    public String getMitigationMessage() {
        return mitigationMessage;
    }

    @Override
    public void setMitigationMessage(String mitigationMessage) {
        this.mitigationMessage = mitigationMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MitigationSettingsImpl that = (MitigationSettingsImpl) o;
        return connectionsPerSecondThreshold == that.connectionsPerSecondThreshold && clientBanSeconds == that.clientBanSeconds && clientAllowSeconds == that.clientAllowSeconds && mitigationMessage.equals(that.mitigationMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectionsPerSecondThreshold, clientBanSeconds, clientAllowSeconds, mitigationMessage);
    }

    @Override
    public String toString() {
        return "MitigationSettingsImpl{" +
                "connectionsPerSecondThreshold=" + connectionsPerSecondThreshold +
                ", clientBanSeconds=" + clientBanSeconds +
                ", clientAllowSeconds=" + clientAllowSeconds +
                ", mitigationMessage='" + mitigationMessage + '\'' +
                '}';
    }
}
