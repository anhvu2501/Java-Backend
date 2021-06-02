package com.example.task2.repository.accounts;

import com.example.task2.tables.pojos.Accounts;

import java.util.List;
import java.util.Optional;

public interface IAccountsRepository {
    Optional<Accounts> find(String id);

    List<Accounts> findByIds(List<String> id);

    void insert(Accounts accounts);

    void insertMany(List<Accounts> accounts);

    void update(Accounts accounts, String id);

    void deleteById(String id);
}
