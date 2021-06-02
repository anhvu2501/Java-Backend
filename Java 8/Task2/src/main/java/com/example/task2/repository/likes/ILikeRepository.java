package com.example.task2.repository.likes;

import com.example.task2.tables.pojos.Likes;

import java.util.List;
import java.util.Optional;

public interface ILikeRepository {
    Optional<Likes> find(String id);

    List<Likes> findByIds(List<String> ids);

    void insert(Likes likes);

    void insertMany(List<Likes> likes);

    void update(Likes likes, String id);

    void deleteById(String id);
}
