package com.example.task2.repository.comic;

import com.example.task2.tables.pojos.Comic;
import com.example.task2.tables.records.ComicRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.InsertSetMoreStep;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.task2.Tables.COMIC;

public class ComicRepository implements IComicRepository {
    private final DSLContext dslContext;

    public ComicRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<Comic> find(String id) {
        Comic comic = dslContext.select()
                .from(COMIC)
                .where(COMIC.ID.eq(id))
                .fetchOneInto(Comic.class);
        return Optional.ofNullable(comic);
    }

    @Override
    public List<Comic> findByIds(List<String> ids) {
        return dslContext.select()
                .from(COMIC)
                .where(COMIC.ID.in(ids))
                .fetchInto(Comic.class);
    }

    @Override
    public void insert(Comic comic) {
        Map<Field<?>, Object> fieldObjectMap = getFieldObjectMap(comic);
        dslContext.insertInto(COMIC)
                .set(fieldObjectMap)
                .execute();
    }

    private Map<Field<?>, Object> getFieldObjectMap(Comic comic) {
        Map<Field<?>, Object> fieldObjectMap = new HashMap<>();
        fieldObjectMap.put(COMIC.ID, comic.getId());
        fieldObjectMap.put(COMIC.NAME, comic.getName());
        fieldObjectMap.put(COMIC.AUTHOR_ID, comic.getAuthorId());
        fieldObjectMap.put(COMIC.PUBLISHER_ID, comic.getPublisherId());
        fieldObjectMap.put(COMIC.STATUS, comic.getStatus());
        fieldObjectMap.put(COMIC.FIRST_UPLOADED, comic.getFirstUploaded());
        fieldObjectMap.put(COMIC.LAST_UPLOADED, comic.getLastUploaded());
        fieldObjectMap.put(COMIC.CURRENT_CHAPTER, comic.getCurrentChapter());
        fieldObjectMap.put(COMIC.TOTAL_VIEW, comic.getTotalView());
        fieldObjectMap.put(COMIC.RATING, comic.getRating());
        return fieldObjectMap;
    }

    @Override
    public void insertMany(List<Comic> comics) {
        List<InsertSetMoreStep<ComicRecord>> insertSetMoreSteps = comics.stream()
                .map(comic -> dslContext.insertInto(COMIC)
                        .set(getFieldObjectMap(comic)))
                .collect(Collectors.toList());
        dslContext.batch(insertSetMoreSteps).execute();
    }

    @Override
    public void update(Comic comic, String id) {
        dslContext.update(COMIC)
                .set(getFieldObjectMap(comic))
                .where(COMIC.ID.eq(id))
                .execute();
    }

    @Override
    public void deleteById(String id) {
        dslContext.delete(COMIC)
                .where(COMIC.ID.eq(id))
                .execute();
    }
}
