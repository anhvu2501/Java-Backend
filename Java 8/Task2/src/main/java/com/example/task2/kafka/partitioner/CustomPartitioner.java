package com.example.task2.kafka.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class CustomPartitioner implements Partitioner {
    private static final int PARTITION_COUNT = 50;
    //Returns the partition number in which the record will go
    @Override
    public int partition(String s, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        Integer keyInt = Integer.parseInt(key.toString());
        return keyInt % PARTITION_COUNT;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
