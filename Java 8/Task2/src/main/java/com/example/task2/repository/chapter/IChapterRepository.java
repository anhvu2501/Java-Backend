package com.example.task2.repository.chapter;

import com.example.task2.tables.pojos.Chapter;

import java.util.List;
import java.util.Optional;

public interface IChapterRepository {
    Optional<Chapter> find(String id);

    List<Chapter> findByIds(List<String> id);

    void insert(Chapter chapter);

    void insertMany(List<Chapter> chapters);

    void update(Chapter chapter, String id);

    void deleteById(String id);
}
