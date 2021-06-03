package com.example.task2.repository.publish;

import com.example.task2.tables.pojos.Publishment;
import com.example.task2.tables.records.PublishmentRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.InsertSetMoreStep;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.task2.Tables.PUBLISHMENT;

@Repository
public class PublishRepository implements IPublishRepository {
    private final DSLContext dslContext;

    public PublishRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<Publishment> find(String id) {
        Publishment publishment = dslContext.select()
                .from(PUBLISHMENT)
                .where(PUBLISHMENT.ID.eq(id))
                .fetchOneInto(Publishment.class);
        return Optional.ofNullable(publishment);
    }

    @Override
    public List<Publishment> findByIds(List<String> ids) {
        return dslContext.select()
                .from(PUBLISHMENT)
                .where(PUBLISHMENT.ID.in(ids))
                .fetchInto(Publishment.class);
    }

    @Override
    public void insert(Publishment publishment) {
        Map<Field<?>, Object> fieldObjectMap = getFieldObjectMap(publishment);
        dslContext.insertInto(PUBLISHMENT)
                .set(fieldObjectMap)
                .execute();
    }

    private Map<Field<?>, Object> getFieldObjectMap(Publishment publishment) {
        Map<Field<?>, Object> fieldObjectMap = new HashMap<>();
        fieldObjectMap.put(PUBLISHMENT.ID, publishment.getId());
        fieldObjectMap.put(PUBLISHMENT.AUTHOR_ID, publishment.getAuthorId());
        fieldObjectMap.put(PUBLISHMENT.PUBLISHER_ID, publishment.getPublisherId());
        fieldObjectMap.put(PUBLISHMENT.COMIC_ID, publishment.getComicId());
        return fieldObjectMap;
    }

    @Override
    public void insertMany(List<Publishment> publishments) {
        List<InsertSetMoreStep<PublishmentRecord>> insertSetMoreSteps = publishments.stream()
                .map(publishment -> dslContext.insertInto(PUBLISHMENT)
                        .set(getFieldObjectMap(publishment)))
                .collect(Collectors.toList());
        dslContext.batch(insertSetMoreSteps).execute();
    }

    @Override
    public void update(Publishment publishment, String id) {
        dslContext.update(PUBLISHMENT)
                .set(getFieldObjectMap(publishment))
                .where(PUBLISHMENT.ID.eq(id))
                .execute();
    }

    @Override
    public void deleteById(String id) {
        dslContext.delete(PUBLISHMENT)
                .where(PUBLISHMENT.ID.eq(id))
                .execute();
    }
}
