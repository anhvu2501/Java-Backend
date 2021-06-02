package com.example.task2.repository.comments;

import com.example.task2.tables.pojos.Comments;

import java.util.List;
import java.util.Optional;

public interface ICommentRepository {
    Optional<Comments> find(String id);

    List<Comments> findByIds(List<String> ids);

    void insert(Comments comment);

    void insertMany(List<Comments> comments);

    void update(Comments comment, String id);

    void deleteById(String id);
}
