package com.example.task2.kafka.pojo;

import java.io.Serializable;

public class CustomerObject implements Serializable {
    private static final long SERIAL_VERSION_UID = 1L;

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
