package com.example.task2.repository.publish;

import com.example.task2.repository.AbsBaseRepository;
import com.example.task2.tables.pojos.Publishment;
import com.example.task2.tables.records.PublishmentRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.example.task2.Tables.PUBLISHMENT;

@Repository
public class PublishRepository extends AbsBaseRepository<Publishment, PublishmentRecord, String> implements IPublishRepository {

    protected PublishRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    protected TableImpl<PublishmentRecord> getTable() {
        return PUBLISHMENT;
    }

    @Override
    protected Field<String> getFieldId() {
        return PUBLISHMENT.ID;
    }
}
