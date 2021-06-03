package com.example.task2.repository.chapter;

import com.example.task2.repository.AbsBaseRepository;
import com.example.task2.tables.pojos.Chapter;
import com.example.task2.tables.records.ChapterRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.example.task2.Tables.CHAPTER;

@Repository
public class ChapterRepository extends AbsBaseRepository<Chapter, ChapterRecord, String> implements IChapterRepository {

    protected ChapterRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    protected TableImpl<ChapterRecord> getTable() {
        return CHAPTER;
    }

    @Override
    protected Field<String> getFieldId() {
        return CHAPTER.ID;
    }
}
