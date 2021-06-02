package com.example.task2.repository.read;

import com.example.task2.tables.pojos.Reads;

import java.util.List;
import java.util.Optional;

public interface IReadRepository {
    Optional<Reads> find(String id);

    List<Reads> findByIds(List<String> ids);

    void insert(Reads read);

    void insertMany(List<Reads> reads);

    void update(Reads read, String id);

    void deleteById(String id);
}
