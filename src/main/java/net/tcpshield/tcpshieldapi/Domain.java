package net.tcpshield.tcpshieldapi;

import java.util.Date;

public interface Domain {

    int getID();

    String getName();

    int getBackendSetID();

    boolean isBACEnabled();

    void setBACEnabled(boolean bac);

    boolean isVerified();

    Date getUpdatedAt();

    Date getCreatedAt();

}
