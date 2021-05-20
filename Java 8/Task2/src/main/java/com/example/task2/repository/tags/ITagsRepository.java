package com.example.task2.repository.tags;

import com.example.task2.tables.pojos.Tags;

import java.util.List;
import java.util.Optional;

public interface ITagsRepository {
    Optional<Tags> find(String id);

    List<Tags> findByIds(List<String> id);

    Tags insert(Tags tags);

    void insertMany(List<Tags> tags);

    Tags update(Tags tags);

    void deleteById(String id);

}
