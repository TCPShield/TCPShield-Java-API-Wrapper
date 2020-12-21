# TCPShield API Wrapper

![Build badge](https://ci.fuzzlemann.de/job/TCPShield-Java-API-Wrapper/badge/icon)

This is a Java wrapper for the official [TCPShield API](https://github.com/TCPShield/api-docs). \
This wrapper supports every documented API endpoint, as seen in the [API Documentation](https://swagger.tcpshield.com).

## Download

### Option 1: Standalone Jar

Download the [latest build](https://ci.fuzzlemann.de/job/TCPShield-Java-API-Wrapper/lastBuild/) from our CI and the
latest [Jackson Databind](https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/) and add them both
to your buildpath.

### Option 2: Build automation tools

Using build automation tools like [Gradle](https://gradle.org/) or [Maven](https://maven.apache.org/) streamlines the
usage of our API.

*TODO*

## Example

Simple example of setting up a network from scratch:

```java
public class NetworkSetup {

    private final APIClient apiClient = new APIClientImpl("APIKEY"); // create an instance of the API Client
    
    public void setup(String networkName, String backend, String domainName) {
        List<Network> networks = apiClient.getNetworks(); // fetches all networks

        int networkID;
        int domainID;

        Optional<Network> foundNetwork = networks.stream().filter(network -> network.getName().equals("TestNetwork")).findAny(); // checks if any networks with the name "TestNetwork" exist

        if (foundNetwork.isPresent()) { // network found
            Network network = foundNetwork.get();
            networkID = network.getID();

            Domain foundDomain = apiClient.getDomains(networkID).stream().filter(domain -> domain.getName().equals(domainName)).findAny().orElseThrow(IllegalStateException::new); // gets the ID of the domain
            domainID = foundDomain.getID();
        } else { // no network found
            networkID = apiClient.addNetwork("TestNetwork").getData().getNetworkID(); // adds the network
            int backendSetID = apiClient.addBackendSet(networkID, networkName + " Set", backend).getData().getID(); // adds the backend set
            domainID = apiClient.addDomain(networkID, domainName, backendSetID, false).getData().getID(); // adds the domain
        }

        if (apiClient.verify(networkID, domainID)) { // verifies the domain; true if successful, false if not
            System.out.println("Successfully created the network.");
        } else {
            Network network = apiClient.getNetwork(networkID); // fetches the network in order to get the TXT verification string

            System.out.println("Create a TXT record on @ with \"" + network.getTXTVerification() + "\" and re-run the program.");
        }
    }
}
```

## Support

If any help with the usage of the API is needed, feel free to contact us on our [Discord](https://discord.gg/XKU9UpV).

## Used Libraries

* [Jackson Databind](https://github.com/FasterXML/jackson-databind)

## Contributions

We're more than happy to accept contributions! We welcome pull requests with open arms.<br>
In order to get testing to work, follow the instructions outlined [here](TESTING.md).

## ToDo

* adding a repository for build tools like Gradle and Maven
* adding documentation
