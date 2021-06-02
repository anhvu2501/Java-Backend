package com.example.task2.repository.publish;

import com.example.task2.tables.pojos.Publishment;

import java.util.List;
import java.util.Optional;

public interface IPublishRepository {
    Optional<Publishment> find(String id);

    List<Publishment> findByIds(List<String> ids);

    void insert(Publishment publishment);

    void insertMany(List<Publishment> publishments);

    void update(Publishment publishment, String id);

    void deleteById(String id);
}
