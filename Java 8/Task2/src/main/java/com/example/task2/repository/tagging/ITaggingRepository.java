package com.example.task2.repository.tagging;

import com.example.task2.tables.pojos.Tagging;

import java.util.List;
import java.util.Optional;

public interface ITaggingRepository {
    Optional<Tagging> find(String id);

    List<Tagging> findByIds(List<String> ids);

    void insert(Tagging tagging);

    void insertMany(List<Tagging> taggings);

    void update(Tagging tagging, String id);

    void deleteById(String id);
}
