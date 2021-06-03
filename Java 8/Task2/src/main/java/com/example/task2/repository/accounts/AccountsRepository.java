package com.example.task2.repository.accounts;

import com.example.task2.repository.AbsBaseRepository;
import com.example.task2.tables.pojos.Accounts;
import com.example.task2.tables.records.AccountsRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

@Repository
public class AccountsRepository extends AbsBaseRepository<Accounts, AccountsRecord, String> implements IAccountsRepository {

    protected AccountsRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    protected TableImpl<AccountsRecord> getTable() {
        return null;
    }

    @Override
    protected Field<String> getFieldId() {
        return null;
    }
}
