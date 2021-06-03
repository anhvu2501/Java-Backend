package com.example.task2.repository.tags;

import com.example.task2.tables.pojos.Tags;
import com.example.task2.tables.records.TagsRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.InsertSetMoreStep;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.task2.Tables.TAGS;

@Repository
public class TagsRepository implements ITagsRepository {
    private final DSLContext dslContext;

    public TagsRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<Tags> find(String id) {
        Tags tags = dslContext.select()
                .from(TAGS)
                .where(TAGS.ID.eq(id))
                .fetchOneInto(Tags.class);
        return Optional.ofNullable(tags);
    }

    @Override
    public List<Tags> findByIds(List<String> ids) {
        return dslContext.select()
                .from(TAGS)
                .where(TAGS.ID.in(ids))
                .fetchInto(Tags.class);
    }

    private Map<Field<?>, Object> getFieldObjectMap(Tags tags) {
        Map<Field<?>, Object> fieldObjectMap = new HashMap<>();
        fieldObjectMap.put(TAGS.ID, tags.getId());
        fieldObjectMap.put(TAGS.DESCRIPTION, tags.getDescription());
        fieldObjectMap.put(TAGS.NAME, tags.getName());
        return fieldObjectMap;
    }

    @Override
    public void insert(Tags tags) {
        Map<Field<?>, Object> fieldObjectMap = getFieldObjectMap(tags);
        dslContext.insertInto(TAGS)
                .set(fieldObjectMap)
                .execute();
    }

    @Override
    public void insertMany(List<Tags> tags) {
        List<InsertSetMoreStep<TagsRecord>> insertSetMoreSteps = tags.stream()
                .map(tags1 -> dslContext.insertInto(TAGS)
                        .set(getFieldObjectMap(tags1)))
                .collect(Collectors.toList());
        dslContext.batch(insertSetMoreSteps).execute();
    }

    @Override
    public void update(Tags tags, String id) {
        dslContext.update(TAGS)
                .set(getFieldObjectMap(tags))
                .where(TAGS.ID.eq(id))
                .execute();
    }

    @Override
    public void deleteById(String id) {
        dslContext.delete(TAGS)
                .where(TAGS.ID.eq(id))
                .execute();
    }
}
