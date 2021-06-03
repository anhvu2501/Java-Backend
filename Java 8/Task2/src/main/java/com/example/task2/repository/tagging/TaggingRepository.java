package com.example.task2.repository.tagging;

import com.example.task2.tables.pojos.Tagging;
import com.example.task2.tables.records.TaggingRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.InsertSetMoreStep;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.task2.Tables.TAGGING;

@Repository
public class TaggingRepository implements ITaggingRepository {
    private final DSLContext dslContext;

    public TaggingRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<Tagging> find(String id) {
        Tagging tagging = dslContext.select()
                .from(TAGGING)
                .where(TAGGING.ID.eq(id))
                .fetchOneInto(Tagging.class);
        return Optional.ofNullable(tagging);
    }

    @Override
    public List<Tagging> findByIds(List<String> ids) {
        return dslContext.select()
                .from(TAGGING)
                .where(TAGGING.ID.in(ids))
                .fetchInto(Tagging.class);
    }

    private Map<Field<?>, Object> getFieldObjectMap(Tagging tagging) {
        Map<Field<?>, Object> fieldObjectHashMap = new HashMap<>();
        fieldObjectHashMap.put(TAGGING.ID, tagging.getId());
        fieldObjectHashMap.put(TAGGING.TAG_ID, tagging.getTagId());
        fieldObjectHashMap.put(TAGGING.COMIC_ID, tagging.getComicId());
        return fieldObjectHashMap;
    }

    @Override
    public void insert(Tagging tagging) {
        Map<Field<?>, Object> fieldObjectHashMap = getFieldObjectMap(tagging);
        dslContext.insertInto(TAGGING)
                .set(fieldObjectHashMap)
                .execute();
    }

    @Override
    public void insertMany(List<Tagging> taggings) {
        List<InsertSetMoreStep<TaggingRecord>> insertSetMoreSteps = taggings.stream()
                .map(tagging -> dslContext.insertInto(TAGGING)
                        .set(getFieldObjectMap(tagging)))
                .collect(Collectors.toList());
        dslContext.batch(insertSetMoreSteps).execute();
    }

    @Override
    public void update(Tagging tagging, String id) {
        dslContext.update(TAGGING)
                .set(getFieldObjectMap(tagging))
                .where(TAGGING.ID.eq(id))
                .execute();
    }

    @Override
    public void deleteById(String id) {
        dslContext.delete(TAGGING)
                .where(TAGGING.ID.eq(id))
                .execute();
    }
}
