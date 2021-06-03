package com.example.task2.repository.chapter;

import com.example.task2.tables.pojos.Chapter;
import com.example.task2.tables.records.ChapterRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.InsertSetMoreStep;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.task2.Tables.CHAPTER;

public class ChapterRepository implements IChapterRepository {
    private final DSLContext dslContext;

    public ChapterRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<Chapter> find(String id) {
        Chapter chapter = dslContext.select()
                .from(CHAPTER)
                .where(CHAPTER.ID.eq(id))
                .fetchOneInto(Chapter.class);
        return Optional.ofNullable(chapter);
    }

    @Override
    public List<Chapter> findByIds(List<String> ids) {
        return dslContext.select()
                .from(CHAPTER)
                .where(CHAPTER.ID.in(ids))
                .fetchInto(Chapter.class);
    }

    @Override
    public void insert(Chapter chapter) {
        Map<Field<?>, Object> fieldObjectMap = getFieldObjectMap(chapter);
        dslContext.insertInto(CHAPTER)
                .set(fieldObjectMap)
                .execute();
    }

    private Map<Field<?>, Object> getFieldObjectMap(Chapter chapter) {
        Map<Field<?>, Object> fieldObjectMap = new HashMap<>();
        fieldObjectMap.put(CHAPTER.ID, chapter.getId());
        fieldObjectMap.put(CHAPTER.COMIC_ID, chapter.getComicId());
        fieldObjectMap.put(CHAPTER.NUM_PAGES, chapter.getNumPages());
        fieldObjectMap.put(CHAPTER.VIEWS, chapter.getViews());
        fieldObjectMap.put(CHAPTER.LIKES, chapter.getLikes());
        fieldObjectMap.put(CHAPTER.COMMENTS, chapter.getComments());
        fieldObjectMap.put(CHAPTER.LINK, chapter.getLink());
        return fieldObjectMap;
    }

    @Override
    public void insertMany(List<Chapter> chapters) {
        List<InsertSetMoreStep<ChapterRecord>> insertSetMoreSteps = chapters.stream()
                .map(chapter -> dslContext.insertInto(CHAPTER)
                        .set(getFieldObjectMap(chapter)))
                .collect(Collectors.toList());
        dslContext.batch(insertSetMoreSteps).execute();
    }

    @Override
    public void update(Chapter chapter, String id) {
        dslContext.update(CHAPTER)
                .set(getFieldObjectMap(chapter))
                .where(CHAPTER.ID.eq(id))
                .execute();
    }

    @Override
    public void deleteById(String id) {
        dslContext.delete(CHAPTER)
                .where(CHAPTER.ID.eq(id))
                .execute();
    }
}
