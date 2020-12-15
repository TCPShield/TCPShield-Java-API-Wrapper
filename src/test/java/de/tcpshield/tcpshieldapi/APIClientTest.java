package de.tcpshield.tcpshieldapi;

import net.tcpshield.tcpshieldapi.impl.APIClientImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests regarding the instantiation of the API client
 */
public class APIClientTest {

    @Test
    public void testExceptionOnNullInstantiation() {
        assertThrows(IllegalArgumentException.class, () -> new APIClientImpl(null));
    }

    @Test
    public void testNoExceptionOnCorrectInstantiation() {
        new APIClientImpl(TestConstants.API_KEY);
    }
}
