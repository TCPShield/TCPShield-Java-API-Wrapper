package de.tcpshield.tcpshieldapi;

import net.tcpshield.tcpshieldapi.*;
import net.tcpshield.tcpshieldapi.exception.status.NotFoundException;
import net.tcpshield.tcpshieldapi.impl.APIClientImpl;
import net.tcpshield.tcpshieldapi.response.BackendSetPostResponse;
import net.tcpshield.tcpshieldapi.response.DomainPostResponse;
import net.tcpshield.tcpshieldapi.response.NetworkPostRepsonse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for [c]reating, [u]pdating and [d]eleting
 */
public class CUDTest {

    private static final APIClient API_CLIENT = new APIClientImpl(TestConstants.API_KEY);

    @Test
    public void testCUD() {
        /* === Create === */

        // Network
        NetworkPostRepsonse addNetworkResponse = API_CLIENT.addNetwork("TemporaryTestNetwork");
        assertEquals(0, addNetworkResponse.getStatus());

        int networkID = addNetworkResponse.getData().getNetworkID();
        Network network = API_CLIENT.getNetwork(networkID);

        assertEquals("TemporaryTestNetwork", network.getName());

        // Backend Set
        BackendSetPostResponse addBackendSetResponse = API_CLIENT.addBackendSet(networkID, "TestSet", "127.0.0.1:25565", "127.0.0.1:25566");
        assertEquals(0, addBackendSetResponse.getStatus());

        int setID = addBackendSetResponse.getData().getID();
        BackendSet set = API_CLIENT.getBackendSet(networkID, setID);

        assertEquals("TestSet", set.getName());
        assertEquals(2, set.getBackends().size());
        assertEquals("127.0.0.1:25565", set.getBackends().get(0));
        assertEquals("127.0.0.1:25566", set.getBackends().get(1));

        // Domain
        DomainPostResponse addDomainResponse = API_CLIENT.addDomain(networkID, TestConstants.TEST_DOMAIN_2, setID, true);
        assertEquals(0, addDomainResponse.getStatus());

        int domainID = addDomainResponse.getData().getID();
        Domain domain = API_CLIENT.getDomain(networkID, domainID);

        assertEquals(setID, domain.getBackendSetID());
        assertEquals(TestConstants.TEST_DOMAIN_2, domain.getName());
        assertTrue(domain.isBACEnabled());

        /* === Update === */

        // Network
        API_CLIENT.patchNetwork(networkID, "TemporaryTestNetwork-Rename", 10, 70, 500, "TestMitigationMessage");
        network = API_CLIENT.getNetwork(networkID);
        MitigationSettings mitigationSettings = network.getMitigationSettings();

        assertEquals("TemporaryTestNetwork-Rename", network.getName());
        assertEquals(10, mitigationSettings.getConnectionsPerSecondThreshold());
        assertEquals(70, mitigationSettings.getClientBanSeconds());
        assertEquals(500, mitigationSettings.getClientAllowSeconds());
        assertEquals("{\"text\":\"TestMitigationMessage\"}", mitigationSettings.getMitigationMessage());

        // Backend Set
        API_CLIENT.patchBackendSet(networkID, setID, "TestSet-Rename", "127.0.0.1:25564");
        set = API_CLIENT.getBackendSet(networkID, setID);

        assertEquals("TestSet-Rename", set.getName());
        assertEquals(1, set.getBackends().size());
        assertEquals("127.0.0.1:25564", set.getBackends().get(0));

        // Domain
        API_CLIENT.patchDomain(networkID, domainID, "rename-" + TestConstants.TEST_DOMAIN_2, setID, false);
        domain = API_CLIENT.getDomain(networkID, domainID);

        assertEquals("rename-" + TestConstants.TEST_DOMAIN_2, domain.getName());
        assertEquals(setID, domain.getBackendSetID());
        assertFalse(domain.isBACEnabled());

        /* === Delete === */

        // Backend Set
        API_CLIENT.deleteBackendSet(networkID, setID);
        assertThrows(NotFoundException.class, () -> API_CLIENT.getBackendSet(networkID, setID));

        // Domain
        API_CLIENT.deleteDomain(networkID, domainID);
        assertThrows(NotFoundException.class, () -> API_CLIENT.getDomain(networkID, domainID));

        // Network
        API_CLIENT.deleteNetwork(networkID);
        assertThrows(NotFoundException.class, () -> API_CLIENT.getNetwork(networkID));
    }

    /**
     * Clean up test networks if any tests fail and they aren't automatically deleted
     */
    @AfterEach
    public void cleanUp() {
        for (Network network : API_CLIENT.getNetworks()) {
            if (!network.getName().equals("TemporaryTestNetwork") && !network.getName().equals("TemporaryTestNetwork-Rename")) continue;

            API_CLIENT.deleteNetwork(network.getID());
        }
    }
}
