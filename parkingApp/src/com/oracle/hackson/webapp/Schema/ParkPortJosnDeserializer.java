package com.oracle.hackson.webapp.Schema;

import com.google.gson.*;
import com.oracle.hackson.webapp.simulation.ParkPort;
import org.apache.kafka.common.serialization.Deserializer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ParkPortJosnDeserializer implements Deserializer<ParkPort> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey)
    { }

    @Override public ParkPort deserialize(String topic, byte[] data)
    {
        Gson gson = new Gson();
        String str = new String(data, StandardCharsets.UTF_8);
        return gson.fromJson(str, ParkPort.class);
    }

    @Override
    public void close()
    { }

}
