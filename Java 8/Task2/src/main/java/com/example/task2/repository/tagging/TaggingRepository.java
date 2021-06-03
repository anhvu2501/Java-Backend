package com.example.task2.repository.tagging;

import com.example.task2.repository.AbsBaseRepository;
import com.example.task2.tables.pojos.Tagging;
import com.example.task2.tables.records.TaggingRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.example.task2.Tables.TAGGING;

@Repository
public class TaggingRepository extends AbsBaseRepository<Tagging, TaggingRecord, String> implements ITaggingRepository {
    protected TaggingRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    protected TableImpl<TaggingRecord> getTable() {
        return TAGGING;
    }

    @Override
    protected Field<String> getFieldId() {
        return TAGGING.ID;
    }
}
