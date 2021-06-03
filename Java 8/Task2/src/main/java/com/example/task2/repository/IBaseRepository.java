package com.example.task2.repository;

import java.util.List;
import java.util.Optional;

public interface IBaseRepository<T, ID> {
    Optional<T> findById(ID id);

    List<T> findByIds(List<ID> id);

    void insert(T t);

    void insertMany(List<T> ts);

    void update(T t, ID id);

    void deleteById(ID id);
}
