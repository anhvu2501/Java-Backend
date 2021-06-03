package com.example.task2.repository.comic;

import com.example.task2.repository.AbsBaseRepository;
import com.example.task2.tables.pojos.Comic;
import com.example.task2.tables.records.ComicRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.example.task2.Tables.COMIC;

@Repository
public class ComicRepository extends AbsBaseRepository<Comic, ComicRecord, String> implements IComicRepository {
    public ComicRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    protected TableImpl<ComicRecord> getTable() {
        return COMIC;
    }

    @Override
    protected Field<String> getFieldId() {
        return COMIC.ID;
    }
}
