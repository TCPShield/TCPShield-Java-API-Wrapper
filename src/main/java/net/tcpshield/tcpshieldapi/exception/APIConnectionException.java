package net.tcpshield.tcpshieldapi.exception;

public class APIConnectionException extends APIException {

    public APIConnectionException(Throwable cause) {
        super(cause);
    }

    public APIConnectionException(String message) {
        super(message);
    }
}
