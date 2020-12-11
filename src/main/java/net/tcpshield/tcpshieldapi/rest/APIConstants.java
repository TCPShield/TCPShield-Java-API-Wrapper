package net.tcpshield.tcpshieldapi.rest;

public class APIConstants {

    private static final String BASE_URL = "https://api.tcpshield.com";

    /* === ENDPOINTS === */

    /* Networks */
    public static final String NETWORKS_ENDPOINT = BASE_URL + "/networks";
    public static final String NETWORK_SPECIFIC_ENDPOINT = NETWORKS_ENDPOINT + "/{networkId}";

    /* Domains */
    public static final String DOMAINS_ENDPOINT = NETWORK_SPECIFIC_ENDPOINT + "/domains";
    public static final String DOMAINS_PREVERIFY_ENDPOINT = DOMAINS_ENDPOINT + "/preverify";
    public static final String DOMAIN_SPECIFIC_ENDPOINT = DOMAINS_ENDPOINT + "/{domainId}";
    public static final String DOMAIN_VERIFY_ENDPOINT = DOMAIN_SPECIFIC_ENDPOINT + "/verify";

    /* Backends */
    public static final String BACKENDS_ENDPOINT = NETWORK_SPECIFIC_ENDPOINT + "/backendSets";
    public static final String BACKENDS_SPECIFIC_ENDPOINT = BACKENDS_ENDPOINT + "/{setId}";

}
