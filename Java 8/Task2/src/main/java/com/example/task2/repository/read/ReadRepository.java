package com.example.task2.repository.read;

import com.example.task2.repository.AbsBaseRepository;
import com.example.task2.tables.pojos.Reads;
import com.example.task2.tables.records.ReadsRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ReadRepository extends AbsBaseRepository<Reads, ReadsRecord, String> implements IReadRepository {
    protected ReadRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    protected TableImpl<ReadsRecord> getTable() {
        return null;
    }

    @Override
    protected Field<String> getFieldId() {
        return null;
    }
}
