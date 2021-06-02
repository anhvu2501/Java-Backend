package com.example.task2.kafka.deserializer;

import com.example.task2.kafka.pojo.CustomerObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class CustomDeserializer implements Deserializer<CustomerObject> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public CustomerObject deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        CustomerObject object = null;
        try {
            object = mapper.readValue(bytes, CustomerObject.class);
        } catch (Exception e) {
            System.out.println("Error in deserializing bytes " + e);
        }
        return object;
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
