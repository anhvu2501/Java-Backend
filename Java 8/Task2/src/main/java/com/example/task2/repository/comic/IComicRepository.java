package com.example.task2.repository.comic;

import com.example.task2.tables.pojos.Comic;

import java.util.List;
import java.util.Optional;

public interface IComicRepository {
    Optional<Comic> find(String id);

    List<Comic> findByIds(List<String> id);

    void insert(Comic comic);

    void insertMany(List<Comic> comics);

    void update(Comic comic, String id);

    void deleteById(String id);
}
