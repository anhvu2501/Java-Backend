package com.example.task2.utils;

import org.jooq.Field;
import org.jooq.Record;

import java.util.HashMap;
import java.util.Map;

public class MysqlUtils {
    public static <P, T extends Record> Map<Field<?>, Object> toInsertField(P p, T record) {
        record.from(p);
        Map<Field<?>, Object> fieldObjectMap = new HashMap<>();
        for (Field<?> field : record.fields()) {
            if (record.getValue(field) != null) {
                fieldObjectMap.put(field, record.getValue(field));
            }
        }
        return fieldObjectMap;
    }
}
