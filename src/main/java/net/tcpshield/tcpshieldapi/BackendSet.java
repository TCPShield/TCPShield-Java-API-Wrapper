package net.tcpshield.tcpshieldapi;

import java.util.List;

public interface BackendSet {

    int getID();

    void setName(String name);

    String getName();

    List<String> getBackends();

    void setBackends(List<String> backends);

}
