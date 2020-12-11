package net.tcpshield.tcpshieldapi.impl;

import net.tcpshield.tcpshieldapi.Domain;
import net.tcpshield.tcpshieldapi.Network;
import net.tcpshield.tcpshieldapi.response.DomainResponse;

import java.util.Date;
import java.util.Objects;

class DomainImpl implements Domain {

    private final int id;
    private String name;
    private final int backendSetID;
    private boolean bac;
    private final boolean verified;
    private final Date updatedAt;
    private final Date createdAt;

    DomainImpl(int id, String name, int backendSetID, boolean bac, boolean verified, Date updatedAt, Date createdAt) {
        this.id = id;
        this.name = name;
        this.backendSetID = backendSetID;
        this.bac = bac;
        this.verified = verified;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    DomainImpl(DomainResponse response) {
        this.id = response.getID();
        this.name = response.getName();
        this.backendSetID = response.getBackendSetID();
        this.bac = response.isBACEnabled();
        this.verified = response.isVerified();
        this.updatedAt = response.getUpdatedAt();
        this.createdAt = response.getCreatedAt();
    }

    @Override
    public Network getNetwork() {
        return null;
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
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getBackendSetID() {
        return backendSetID;
    }

    @Override
    public boolean isBACEnabled() {
        return bac;
    }

    @Override
    public void setBACEnabled(boolean bac) {
        this.bac = bac;
    }

    @Override
    public boolean isVerified() {
        return verified;
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
        DomainImpl domain = (DomainImpl) o;
        return id == domain.id && backendSetID == domain.backendSetID && bac == domain.bac && verified == domain.verified && Objects.equals(name, domain.name) && Objects.equals(updatedAt, domain.updatedAt) && Objects.equals(createdAt, domain.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, backendSetID, bac, verified, updatedAt, createdAt);
    }

    @Override
    public String toString() {
        return "DomainImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", backendSetID=" + backendSetID +
                ", bac=" + bac +
                ", verified=" + verified +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                '}';
    }
}
