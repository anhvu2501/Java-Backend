package com.example.task2.repository.tags;

import com.example.task2.repository.AbsBaseRepository;
import com.example.task2.tables.pojos.Tags;
import com.example.task2.tables.records.TagsRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.example.task2.Tables.TAGS;

@Repository
public class TagsRepository extends AbsBaseRepository<Tags, TagsRecord, String> implements ITagsRepository {
    protected TagsRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    protected TableImpl<TagsRecord> getTable() {
        return TAGS;
    }

    @Override
    protected Field<String> getFieldId() {
        return TAGS.ID;
    }
}
