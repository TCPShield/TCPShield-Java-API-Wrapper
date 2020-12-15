package net.tcpshield.tcpshieldapi;

import java.util.List;

public interface BackendSet {

    int getID();

    String getName();

    List<String> getBackends();

    void setBackends(List<String> backends);

}
