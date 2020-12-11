package net.tcpshield.tcpshieldapi.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDeserializer extends StdDeserializer<Date> {

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM'T'hh:mm:ss'Z'");

    public DateDeserializer() {
        this(null);
    }

    public DateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            return format.parse(p.getText());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
