package net.tcpshield.tcpshieldapi;

import net.tcpshield.tcpshieldapi.request.*;
import net.tcpshield.tcpshieldapi.response.BackendSetPostResponse;
import net.tcpshield.tcpshieldapi.response.DomainPostResponse;
import net.tcpshield.tcpshieldapi.response.NetworkPostRepsonse;

import java.util.List;

public interface APIClient {

    List<Network> getNetworks();

    NetworkPostRepsonse addNetwork(NetworkPostRequest request);

    default NetworkPostRepsonse addNetwork(String name) {
        return addNetwork(new NetworkPostRequest(name));
    }

    Network getNetwork(int id);

    void patchNetwork(int id, NetworkPatchRequest request);

    default void patchNetwork(int id, String name, int connectionsPerSecondThreshold, int clientBanSeconds, int clientAllowSeconds, String mitigationMessage) {
        patchNetwork(id, new NetworkPatchRequest(id, name, connectionsPerSecondThreshold, clientBanSeconds, clientAllowSeconds, mitigationMessage));
    }

    default void patchNetwork(Network network, MitigationSettings mitigationSettings) {
        patchNetwork(network.getID(), network.getName(), mitigationSettings.getConnectionsPerSecondThreshold(), mitigationSettings.getClientBanSeconds(), mitigationSettings.getClientAllowSeconds(), mitigationSettings.getMitigationMessage());
    }

    void deleteNetwork(int id);

    List<Domain> getDomains(int networkID);

    DomainPostResponse addDomain(int networkID, DomainPostRequest request);

    default DomainPostResponse addDomain(int networkID, String name, int setID, boolean bac) {
        return addDomain(networkID, new DomainPostRequest(name, setID, bac));
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

    BackendSetPostResponse addBackendSet(int networkID, BackendSetPostRequest request);

    default BackendSetPostResponse addBackendSet(int networkID, String name, String... backends) {
        return addBackendSet(networkID, new BackendSetPostRequest(name, backends));
    }

    BackendSet getBackendSet(int networkID, int setID);

    void patchBackendSet(int networkID, int setID, BackendSetPatchRequest request);

    default void patchBackendSet(int networkID, int setID, String name, String... backends) {
        patchBackendSet(networkID, setID, new BackendSetPatchRequest(name, backends));
    }

    void deleteBackendSet(int networkID, int setID);

}
