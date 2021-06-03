package com.example.task2.repository.accounts;

import com.example.task2.tables.pojos.Accounts;
import com.example.task2.tables.records.AccountsRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.InsertSetMoreStep;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Accounts> findByIds(List<String> ids) {
        return dslContext.select()
                .from(ACCOUNTS)
                .where(ACCOUNTS.ID.in(ids))
                .fetchInto(Accounts.class);
    }

    @Override
    public void insert(Accounts accounts) {
        Map<Field<?>, Object> fieldObjectMap = getFieldObjectMap(accounts);
        dslContext.insertInto(ACCOUNTS)
                .set(fieldObjectMap)
                .execute();
    }

    private Map<Field<?>, Object> getFieldObjectMap(Accounts accounts) {
        Map<Field<?>, Object> fieldObjectMap = new HashMap<>();
        fieldObjectMap.put(ACCOUNTS.ID, accounts.getId());
        fieldObjectMap.put(ACCOUNTS.DISPLAY_NAME, accounts.getDisplayName());
        fieldObjectMap.put(ACCOUNTS.USERNAME, accounts.getUsername());
        fieldObjectMap.put(ACCOUNTS.PASSWORD, accounts.getPassword());
        fieldObjectMap.put(ACCOUNTS.ACCOUNT_TYPE, accounts.getAccountType());
        fieldObjectMap.put(ACCOUNTS.CREATED_TIME, accounts.getCreatedTime());
        fieldObjectMap.put(ACCOUNTS.EMAIL, accounts.getEmail());
        fieldObjectMap.put(ACCOUNTS.DOB, accounts.getDob());
        return fieldObjectMap;
    }

    @Override
    public void insertMany(List<Accounts> accounts) {
        List<InsertSetMoreStep<AccountsRecord>> insertSetMoreSteps = accounts.stream()
                .map(accounts1 -> dslContext.insertInto(ACCOUNTS)
                        .set(getFieldObjectMap(accounts1)))
                .collect(Collectors.toList());
        dslContext.batch(insertSetMoreSteps).execute();
    }

    @Override
    public void update(Accounts accounts, String id) {
        dslContext.update(ACCOUNTS)
                .set(getFieldObjectMap(accounts))
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
