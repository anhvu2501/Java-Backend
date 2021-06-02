package com.example.task2.repository.authors;

import com.example.task2.tables.pojos.Authors;

import java.util.List;
import java.util.Optional;

public interface IAuthorsRepository {
    Optional<Authors> find(String id);

    List<Authors> findByIds(List<String> id);

    void insert(Authors authors);

    void insertMany(List<Authors> authors);

    void update(Authors authors, String id);

    void deleteById(String id);
}
