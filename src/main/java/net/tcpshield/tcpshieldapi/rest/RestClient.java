package net.tcpshield.tcpshieldapi.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.tcpshield.tcpshieldapi.deserializer.DateDeserializer;
import net.tcpshield.tcpshieldapi.exception.APIConnectionException;
import net.tcpshield.tcpshieldapi.exception.status.NoPermissionException;
import net.tcpshield.tcpshieldapi.exception.status.NotFoundException;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

public class RestClient {

    private final String apiKey;
    private final ObjectMapper mapper = new ObjectMapper();

    public RestClient(String apiKey) {
        this.apiKey = apiKey;

        SimpleModule module = new SimpleModule();
        module.addDeserializer(Date.class, new DateDeserializer());

        mapper.registerModule(module);
    }

    public <T> T makeRequest(RestRequest<T> restRequest) {
        try {
            RestResponse<T> response = internalRequest(restRequest);

            int statusCode = response.getStatusCode();

            switch (statusCode) {
                case 403:
                    throw new NoPermissionException();
                case 404:
                    throw new NotFoundException();
                default:
                    return response.getData();
            }
        } catch (IOException e) {
            throw new APIConnectionException(e);
        }
    }

    private <T> RestResponse<T> internalRequest(RestRequest<T> request) throws IOException {
        String data = toJson(request.getData());

        URL url = new URL(request.getURL());

        HttpsURLConnection connection = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod(request.getRequestType().name());

            connection.setRequestProperty("X-API-Key", apiKey);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", data == null ? "0" : String.valueOf(data.length()));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.connect();

            if (data != null) {
                try (OutputStream outputStream = connection.getOutputStream();
                     OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
                    writer.write(data);
                }
            }

            try (InputStream inputStream = connection.getInputStream();
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                String response = bufferedReader.lines().filter(str -> !str.isEmpty()).collect(Collectors.joining("\n"));
                int statusCode = connection.getResponseCode();

                T parsed = parseJson(response, request.getResponseClass());
                return new RestResponse<>(statusCode, parsed);
            }
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }

    private String toJson(Object data) throws JsonProcessingException {
        if (data == null) return null;

        return mapper.writeValueAsString(data);
    }

    private <T> T parseJson(String data, Class<T> targetClass) throws IOException {
        if (targetClass == null) return null;

        return mapper.readValue(data, targetClass);
    }

}
