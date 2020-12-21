# Test Setup
In order to get testing to work, there are some preconditions which have to be met:
* TCPShield with the Premium plan or higher (in order to get a proper API Key & test setting custom mitigation settings)
* a custom setup network with predefined variables
* one additional free space for another network
* a domain
* variables in `gradle.properties`

## Network setup
We need to create a custom network with specific variables as that network is used for the read testing.

**Name**: `TestNetwork`<br>
**Domains:** `unverifiedtestdomain.com` and one custom domain with the verification TXT record already setup; 
both domains pointing to the backend set `TestSet`<br>
**Backend Sets:** `TestSet` with the backends `127.0.0.1:25565` and `127.0.0.1:25566`<br>
**Mitigation Settings:** CPS: `10 c/s`; Ban Seconds: `70`; Allow Seconds: `500`; Mitigation Message: `TestMitigationMessage` 

## gradle.properties
```
testing.api-key=API_KEY # the TCPShield API Key 
testing.domain1=test.example.net # an own domain with the TXT verification record already setup
testing.domain2=test2.example.net # another own domain; whether the TXT verification is setup or not is irrelevant
```