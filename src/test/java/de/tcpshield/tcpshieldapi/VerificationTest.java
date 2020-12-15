package de.tcpshield.tcpshieldapi;

import net.tcpshield.tcpshieldapi.APIClient;
import net.tcpshield.tcpshieldapi.Domain;
import net.tcpshield.tcpshieldapi.impl.APIClientImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests regarding domain verification
 */
public class VerificationTest {

    private static final APIClient API_CLIENT = new APIClientImpl(TestConstants.API_KEY);

    @Test
    public void testVerification() {
        int networkID = API_CLIENT.getNetworks().stream().filter(network -> network.getName().equals("TestNetwork")).findAny().orElseThrow(IllegalStateException::new).getID();

        List<Domain> domains = API_CLIENT.getDomains(networkID);
        Domain verifySuccessDomain = domains.stream().filter(domain -> domain.getName().equals(TestConstants.TEST_DOMAIN_1)).findAny().orElseThrow(IllegalStateException::new);
        Domain verifyFailDomain = domains.stream().filter(domain -> domain.getName().equals("unverifiedtestdomain.com")).findAny().orElseThrow(IllegalStateException::new);

        assertTrue(API_CLIENT.preverify(networkID, TestConstants.TEST_DOMAIN_1));
        assertFalse(API_CLIENT.preverify(networkID, "unverifiedtestdomain.com"));

        assertTrue(API_CLIENT.verify(networkID, verifySuccessDomain.getID()));
        assertFalse(API_CLIENT.verify(networkID, verifyFailDomain.getID()));
    }

    @AfterEach
    public void cleanUp() { // resets domain verification
        int networkID = API_CLIENT.getNetworks().stream().filter(network -> network.getName().equals("TestNetwork")).findAny().orElseThrow(IllegalStateException::new).getID();

        Domain verifySuccessDomain = API_CLIENT.getDomains(networkID).stream().filter(domain -> domain.getName().equals(TestConstants.TEST_DOMAIN_1)).findAny().orElseThrow(IllegalStateException::new);

        API_CLIENT.deleteDomain(networkID, verifySuccessDomain.getID());
        API_CLIENT.addDomain(networkID, TestConstants.TEST_DOMAIN_1, verifySuccessDomain.getBackendSetID(), false);
    }
}
