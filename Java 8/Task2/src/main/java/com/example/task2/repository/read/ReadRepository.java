package com.example.task2.repository.read;

import com.example.task2.tables.pojos.Reads;
import com.example.task2.tables.records.ReadsRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.InsertSetMoreStep;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.task2.Tables.READS;
import static java.util.stream.Collectors.toList;

@Repository
public class ReadRepository implements IReadRepository {
    private final DSLContext dslContext;

    public ReadRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<Reads> find(String id) {
        Reads reads = dslContext.select()
                .from(READS)
                .where(READS.ID.eq(id))
                .fetchOneInto(Reads.class);
        return Optional.ofNullable(reads);
    }

    @Override
    public List<Reads> findByIds(List<String> ids) {
        return dslContext.select()
                .from(READS)
                .where(READS.ID.in(ids))
                .fetchInto(Reads.class);
    }

    @Override
    public void insert(Reads read) {
        Map<Field<?>, Object> fieldObjectMap = getFieldObjectMap(read);
        dslContext.insertInto(READS)
                .set(fieldObjectMap)
                .execute();
    }

    private Map<Field<?>, Object> getFieldObjectMap(Reads read) {
        Map<Field<?>, Object> fieldObjectMap = new HashMap<>();
        fieldObjectMap.put(READS.ID, read.getId());
        fieldObjectMap.put(READS.COMIC_ID, read.getComicId());
        fieldObjectMap.put(READS.CHAPTER_ID, read.getChapterId());
        fieldObjectMap.put(READS.DATE, read.getDate());
        return fieldObjectMap;
    }

    @Override
    public void insertMany(List<Reads> reads) {
        List<InsertSetMoreStep<ReadsRecord>> insertSetMoreSteps = reads.stream()
                .map(read -> dslContext.insertInto(READS)
                        .set(getFieldObjectMap(read)))
                .collect(toList());
        dslContext.batch(insertSetMoreSteps).execute();
    }

    @Override
    public void update(Reads read, String id) {
        dslContext.update(READS)
                .set(getFieldObjectMap(read))
                .where(READS.ID.eq(id))
                .execute();
    }

    @Override
    public void deleteById(String id) {
        dslContext.delete(READS)
                .where(READS.ID.eq(id))
                .execute();
    }
}
