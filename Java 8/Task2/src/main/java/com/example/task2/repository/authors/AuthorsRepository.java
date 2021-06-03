package com.example.task2.repository.authors;

import com.example.task2.repository.AbsBaseRepository;
import com.example.task2.tables.pojos.Authors;
import com.example.task2.tables.records.AuthorsRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorsRepository extends AbsBaseRepository<Authors, AuthorsRecord, String> implements IAuthorsRepository {

    protected AuthorsRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    protected TableImpl<AuthorsRecord> getTable() {
        return null;
    }

    @Override
    protected Field<String> getFieldId() {
        return null;
    }
}
