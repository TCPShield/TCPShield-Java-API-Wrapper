package net.tcpshield.tcpshieldapi;

import net.tcpshield.tcpshieldapi.request.*;

import java.util.List;

public interface APIClient {

    List<Network> getNetworks();

    void addNetwork(NetworkPostRequest request);

    default void addNetwork(String name) {
        addNetwork(new NetworkPostRequest(name));
    }

    Network getNetwork(int id);

    void patchNetwork(int id, NetworkPatchRequest request);

    default void patchNetwork(int id, String name, int connectionsPerSecondThreshold, int clientBanSeconds, int clientAllowSeconds, String mitigationMessage) {
        patchNetwork(id, new NetworkPatchRequest(name, connectionsPerSecondThreshold, clientBanSeconds, clientAllowSeconds, mitigationMessage));
    }

    default void patchNetwork(Network network, MitigationSettings mitigationSettings) {
        patchNetwork(network.getID(), network.getName(), mitigationSettings.getConnectionsPerSecondThreshold(), mitigationSettings.getClientBanSeconds(), mitigationSettings.getClientAllowSeconds(), mitigationSettings.getMitigationMessage());
    }

    void deleteNetwork(int id);

    List<Domain> getDomains(int networkID);

    void addDomain(int networkID, DomainPostRequest request);

    default void addDomain(int networkID, String name, int setID, boolean bac) {
        addDomain(networkID, new DomainPostRequest(name, setID, bac));
    }

    Domain getDomain(int networkID, int domainID);

    void patchDomain(int networkID, int domainID, DomainPatchRequest request);

    default void patchDomain(int networkID, int domainID, String newName, int newBackendID, boolean newBAC) {
        patchDomain(networkID, domainID, new DomainPatchRequest(newName, newBackendID, newBAC));
    }

    void deleteDomain(int networkID, int domainID);

    boolean preverify(int networkID, DomainPreverifyRequest request);

    default boolean preverify(int networkID, String domain) {
        return preverify(networkID, new DomainPreverifyRequest(domain));
    }

    boolean verify(int networkID, int domainID);

    List<BackendSet> getBackendSets(int networkID);

    void addBackendSet(int networkID, BackendSetPostRequest request);

    default void addBackendSet(int networkID, String name, String... backends) {
        addBackendSet(networkID, new BackendSetPostRequest(name, backends));
    }

    BackendSet getBackendSet(int networkID, int setID);

    default BackendSet getBackendSet(Network network, int setID) {
        return getBackendSet(network.getID(), setID);
    }

    void patchBackendSet(int networkID, int setID, BackendSetPatchRequest request);

    default void patchBackendSet(int networkID, int setID, String name, String... backends) {
        patchBackendSet(networkID, setID, new BackendSetPatchRequest(name, backends));
    }

    default void patchBackendSet(int networkID, BackendSet backendSet) {
        patchBackendSet(networkID, backendSet.getID(), backendSet.getName(), backendSet.getBackends().toArray(new String[0]));
    }

    void deleteBackendSet(int networkID, int setID);

}
