package net.tcpshield.tcpshieldapi;

import java.util.Date;

public interface Domain {

    Network getNetwork();

    int getID();

    String getName();

    void setName(String name);

    int getBackendSetID();

    boolean isBACEnabled();

    void setBACEnabled(boolean bac);

    boolean isVerified();

    Date getUpdatedAt();

    Date getCreatedAt();

}
