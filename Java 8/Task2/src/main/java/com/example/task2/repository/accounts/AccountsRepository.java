package com.example.task2.repository.accounts;

import com.example.task2.tables.pojos.Accounts;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;

import static com.example.task2.Tables.ACCOUNTS;

public class AccountsRepository implements IAccountsRepository {
    private final DSLContext dslContext;

    public AccountsRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<Accounts> find(String id) {
        Accounts accounts = dslContext.select()
                .from(ACCOUNTS)
                .where(ACCOUNTS.ID.eq(id))
                .fetchOneInto(Accounts.class);
        return Optional.ofNullable(accounts);
    }

    @Override
    public List<Accounts> findByIds(List<String> id) {
        return dslContext.select()
                .from(ACCOUNTS)
                .where(String.valueOf(id.stream().filter(s -> s.equals(ACCOUNTS.ID))))
                .fetchInto(Accounts.class);
    }

    @Override
    public void insert(Accounts accounts) {
        dslContext.insertInto(ACCOUNTS)
                .set(ACCOUNTS.ID, accounts.getId())
                .set(ACCOUNTS.DISPLAY_NAME, accounts.getDisplayName())
                .set(ACCOUNTS.USERNAME, accounts.getUsername())
                .set(ACCOUNTS.PASSWORD, accounts.getPassword())
                .set(ACCOUNTS.ACCOUNT_TYPE, accounts.getAccountType())
                .set(ACCOUNTS.CREATED_TIME, accounts.getCreatedTime())
                .set(ACCOUNTS.EMAIL, accounts.getEmail())
                .set(ACCOUNTS.DOB, accounts.getDob())
                .execute();
    }

    @Override
    public void insertMany(List<Accounts> accounts) {
        accounts.stream()
                .map(accounts1 -> dslContext.insertInto(ACCOUNTS)
                        .set(ACCOUNTS.ID, accounts1.getId())
                        .set(ACCOUNTS.DISPLAY_NAME, accounts1.getDisplayName())
                        .set(ACCOUNTS.USERNAME, accounts1.getUsername())
                        .set(ACCOUNTS.PASSWORD, accounts1.getPassword())
                        .set(ACCOUNTS.ACCOUNT_TYPE, accounts1.getAccountType())
                        .set(ACCOUNTS.CREATED_TIME, accounts1.getCreatedTime())
                        .set(ACCOUNTS.EMAIL, accounts1.getEmail())
                        .set(ACCOUNTS.DOB, accounts1.getDob())
                        .execute());
    }

    @Override
    public void update(Accounts accounts, String id) {
        dslContext.update(ACCOUNTS)
                .set(ACCOUNTS.ID, accounts.getId())
                .set(ACCOUNTS.DISPLAY_NAME, accounts.getDisplayName())
                .set(ACCOUNTS.USERNAME, accounts.getUsername())
                .set(ACCOUNTS.PASSWORD, accounts.getPassword())
                .set(ACCOUNTS.ACCOUNT_TYPE, accounts.getAccountType())
                .set(ACCOUNTS.CREATED_TIME, accounts.getCreatedTime())
                .set(ACCOUNTS.EMAIL, accounts.getEmail())
                .set(ACCOUNTS.DOB, accounts.getDob())
                .where(ACCOUNTS.ID.eq(id))
                .execute();
    }

    @Override
    public void deleteById(String id) {
        dslContext.delete(ACCOUNTS)
                .where(ACCOUNTS.ID.eq(id))
                .execute();
    }
}
