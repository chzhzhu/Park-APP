package com.oracle.hackson.webapp.Schema;

import com.google.gson.*;
import java.util.Map;

import com.oracle.hackson.webapp.simulation.ParkPort;
import org.apache.kafka.common.serialization.Serializer;


public class ParkPortJosnSerializer implements Serializer<ParkPort>{
    @Override
    public void configure(Map<String, ?> configs, boolean isKey)
    { }

    @Override
    public byte[] serialize(String str, ParkPort data)
    {   Gson gson = new Gson();
        return gson.toJson(data).getBytes();
    }

    @Override
    public void close()
    { }

}
