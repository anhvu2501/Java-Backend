package com.example.task2.kafka.serializer;

import com.example.task2.kafka.pojo.CustomerObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class CustomSerializer implements Serializer<CustomerObject>{

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, CustomerObject customerObject) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(customerObject).getBytes();
        } catch (Exception e) {
            System.out.println("Error in serializing object" + customerObject);
        }
        return retVal;
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
