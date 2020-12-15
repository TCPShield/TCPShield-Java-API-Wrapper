package de.tcpshield.tcpshieldapi;

import net.tcpshield.tcpshieldapi.APIClient;
import net.tcpshield.tcpshieldapi.BackendSet;
import net.tcpshield.tcpshieldapi.Domain;
import net.tcpshield.tcpshieldapi.Network;
import net.tcpshield.tcpshieldapi.impl.APIClientImpl;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for fetching information from the panel. Note that predefined names, domains and backend sets are required.
 * More infos on the testing requirements can be found <a href="https://github.com/TCPShield/TCPShield-Java-API-Wrapper/blob/master/TESTING.md">here</a>.
 */
public class FetchTest {

    private static final APIClient API_CLIENT = new APIClientImpl(TestConstants.API_KEY);

    @Test
    public void testFetching() {
        // Network fetching
        List<Network> networks = API_CLIENT.getNetworks();

        Optional<Network> networkOptional = networks.stream().filter(network -> network.getName().equals("TestNetwork")).findAny();
        assertTrue(networkOptional.isPresent());

        Network network = networkOptional.get();

        assertNotEquals(0, network.getID());
        assertNotNull(network.getTXTVerification());
        assertNotNull(network.getMitigationSettings());
        assertNotNull(network.getProtectedCNAME());
        assertNotNull(network.getCreatedAt());
        assertNotNull(network.getUpdatedAt());

        assertEquals(network, API_CLIENT.getNetwork(network.getID()));

        // Domain fetching
        List<Domain> domains = API_CLIENT.getDomains(network.getID());

        Optional<Domain> domainOptional = domains.stream().filter(domain -> domain.getName().equals(TestConstants.TEST_DOMAIN_1)).findAny();
        assertTrue(domainOptional.isPresent());

        Domain domain = domainOptional.get();

        assertNotEquals(0, domain.getID());
        assertNotEquals(0, domain.getBackendSetID());
        assertNotNull(domain.getCreatedAt());
        assertNotNull(domain.getUpdatedAt());

        assertEquals(domain, API_CLIENT.getDomain(network.getID(), domain.getID()));

        // Backend fetching
        BackendSet backendSet = API_CLIENT.getBackendSet(network.getID(), domain.getBackendSetID());

        assertEquals(domain.getBackendSetID(), backendSet.getID());
        assertEquals("TestSet", backendSet.getName());
        assertEquals(2, backendSet.getBackends().size());
        assertEquals("127.0.0.1:25565", backendSet.getBackends().get(0));
        assertEquals("127.0.0.1:25566", backendSet.getBackends().get(1));

        assertEquals(backendSet, API_CLIENT.getBackendSet(network.getID(), domain.getBackendSetID()));
        assertEquals(backendSet.getID(), API_CLIENT.getBackendSets(network.getID()).stream().filter(set -> set.getName().equals("TestSet")).findAny().orElseThrow(IllegalStateException::new).getID());
    }

}
