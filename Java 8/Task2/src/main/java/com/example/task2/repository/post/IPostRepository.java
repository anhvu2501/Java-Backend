package com.example.task2.repository.post;

import com.example.task2.tables.pojos.Post;

import java.util.List;
import java.util.Optional;

public interface IPostRepository {
    Optional<Post> find (String id);

    List<Post> findByIds (List<String> ids);

    void insert (Post post);

    void insertMany (List<Post> posts);

    void update (Post post, String id);

    void deleteById (String id);
}
