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

    Network getNetwork(int networkID);

    /**
     * Sets the network to the specified settings.
     *
     * @param networkID      the network id
     * @param request the request which is to be executed
     * @throws net.tcpshield.tcpshieldapi.exception.status.NoPermissionException if your plan doesn't have the capability to patch the network settings
     * @throws net.tcpshield.tcpshieldapi.exception.status.NotFoundException     if the network can't be found
     * @see NetworkPatchRequest
     * @see net.tcpshield.tcpshieldapi.APIClient#patchNetwork(int, String, int, int, int, String)
     * @see net.tcpshield.tcpshieldapi.APIClient#patchNetwork(Network, MitigationSettings)
     */
    void patchNetwork(int networkID, NetworkPatchRequest request);

    /**
     * Sets the network to the specified settings.
     *
     * @param networkID                            the network id
     * @param name                          the name of the network; max. length: 50 characters
     * @param connectionsPerSecondThreshold how many total connections your server should receive before TCPShield starts mitigating; 1 <= x <= 100
     * @param clientBanSeconds              total amount of time blocked attempts should be banned for; 60 <= x <= 3600
     * @param clientAllowSeconds            total amount of time valid connections should be accepted; 30 <= x <= 600
     * @param mitigationMessage             the message to be displayed after successful validation; max. length: 489 characters
     * @throws IllegalArgumentException                                          if the conditions for the parameters aren't met
     * @throws net.tcpshield.tcpshieldapi.exception.status.NoPermissionException if your plan doesn't have the capability to patch the network settings
     * @throws net.tcpshield.tcpshieldapi.exception.status.NotFoundException     if the network can't be found
     * @see net.tcpshield.tcpshieldapi.APIClient#patchNetwork(int, NetworkPatchRequest)
     * @see net.tcpshield.tcpshieldapi.APIClient#patchNetwork(int, String, int, int, int, String)
     * @see net.tcpshield.tcpshieldapi.APIClient#patchNetwork(Network, MitigationSettings)
     */
    default void patchNetwork(int networkID, String name, int connectionsPerSecondThreshold, int clientBanSeconds, int clientAllowSeconds, String mitigationMessage) {
        patchNetwork(networkID, new NetworkPatchRequest(networkID, name, connectionsPerSecondThreshold, clientBanSeconds, clientAllowSeconds, mitigationMessage));
    }

    /**
     * Sets the mitigation settings of the network to the specified mitigation settings
     *
     * @param network            the network to be patched
     * @param mitigationSettings the new mitigation settings
     * @throws IllegalArgumentException                                          if the conditions for the parameters aren't met
     * @throws net.tcpshield.tcpshieldapi.exception.status.NoPermissionException if your plan doesn't have the capability to patch the network settings
     * @throws net.tcpshield.tcpshieldapi.exception.status.NotFoundException     if the network can't be found
     * @see MitigationSettings
     * @see net.tcpshield.tcpshieldapi.APIClient#patchNetwork(int, NetworkPatchRequest)
     * @see net.tcpshield.tcpshieldapi.APIClient#patchNetwork(int, String, int, int, int, String)
     */
    default void patchNetwork(Network network, MitigationSettings mitigationSettings) {
        patchNetwork(network.getID(), network.getName(), mitigationSettings.getConnectionsPerSecondThreshold(), mitigationSettings.getClientBanSeconds(), mitigationSettings.getClientAllowSeconds(), mitigationSettings.getMitigationMessage());
    }

    void deleteNetwork(int networkID);

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
