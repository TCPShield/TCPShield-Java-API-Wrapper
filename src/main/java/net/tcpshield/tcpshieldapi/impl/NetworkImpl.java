package net.tcpshield.tcpshieldapi.impl;

import net.tcpshield.tcpshieldapi.MitigationSettings;
import net.tcpshield.tcpshieldapi.Network;
import net.tcpshield.tcpshieldapi.response.NetworkResponse;

import java.util.Date;
import java.util.Objects;

class NetworkImpl implements Network {

    private final int id;
    private final String name;
    private final boolean premium;
    private final String protectedCNAME;
    private final String txtVerification;
    private final MitigationSettings mitigationSettings;
    private final Date updatedAt;
    private final Date createdAt;

    NetworkImpl(int id, String name, boolean premium, String protectedCNAME, String txtVerification, MitigationSettings mitigationSettings, Date updatedAt, Date createdAt) {
        this.id = id;
        this.name = name;
        this.premium = premium;
        this.protectedCNAME = protectedCNAME;
        this.txtVerification = txtVerification;
        this.mitigationSettings = mitigationSettings;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    NetworkImpl(NetworkResponse response) {
        this.id = response.getID();
        this.name = response.getName();
        this.premium = response.isPremium();
        this.protectedCNAME = response.getProtectedCNAME();
        this.txtVerification = response.getTXTVerification();
        this.mitigationSettings = new MitigationSettingsImpl(response.getConnectionsPerSecondThreshold(), response.getClientBanSeconds(), response.getClientAllowSeconds(), response.getMitigationMessage());
        this.updatedAt = response.getUpdatedAt();
        this.createdAt = response.getCreatedAt();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isPremium() {
        return premium;
    }

    @Override
    public String getProtectedCNAME() {
        return protectedCNAME;
    }

    @Override
    public String getTXTVerification() {
        return txtVerification;
    }

    @Override
    public MitigationSettings getMitigationSettings() {
        return mitigationSettings;
    }

    @Override
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetworkImpl network = (NetworkImpl) o;
        return id == network.id && premium == network.premium && Objects.equals(name, network.name) && Objects.equals(protectedCNAME, network.protectedCNAME) && Objects.equals(txtVerification, network.txtVerification) && Objects.equals(mitigationSettings, network.mitigationSettings) && Objects.equals(updatedAt, network.updatedAt) && Objects.equals(createdAt, network.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, premium, protectedCNAME, txtVerification, mitigationSettings, updatedAt, createdAt);
    }

    @Override
    public String toString() {
        return "NetworkImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", premium=" + premium +
                ", protectedCNAME='" + protectedCNAME + '\'' +
                ", txtVerification='" + txtVerification + '\'' +
                ", mitigationSettings=" + mitigationSettings +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                '}';
    }
}
