package com.example.task2.repository.chapter;

import com.example.task2.tables.pojos.Chapter;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;

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
    public List<Chapter> findByIds(List<String> id) {
        return dslContext.select()
                .from(CHAPTER)
                .where(String.valueOf(id.stream().filter(s -> s.equals(CHAPTER.ID))))
                .fetchInto(Chapter.class);
    }

    @Override
    public void insert(Chapter chapter) {
        dslContext.insertInto(CHAPTER)
                .set(CHAPTER.ID, chapter.getId())
                .set(CHAPTER.COMIC_ID, chapter.getComicId())
                .set(CHAPTER.NUM_PAGES, chapter.getNumPages())
                .set(CHAPTER.VIEWS, chapter.getViews())
                .set(CHAPTER.LIKES, chapter.getLikes())
                .set(CHAPTER.COMMENTS, chapter.getComments())
                .set(CHAPTER.LIKES, chapter.getLikes())
                .execute();
    }

    @Override
    public void insertMany(List<Chapter> chapters) {
        chapters.stream()
                .map(chapter -> dslContext.insertInto(CHAPTER)
                        .set(CHAPTER.ID, chapter.getId())
                        .set(CHAPTER.COMIC_ID, chapter.getComicId())
                        .set(CHAPTER.NUM_PAGES, chapter.getNumPages())
                        .set(CHAPTER.VIEWS, chapter.getViews())
                        .set(CHAPTER.LIKES, chapter.getLikes())
                        .set(CHAPTER.COMMENTS, chapter.getComments())
                        .set(CHAPTER.LIKES, chapter.getLikes())
                        .execute());
    }

    @Override
    public void update(Chapter chapter, String id) {
        dslContext.update(CHAPTER)
                .set(CHAPTER.ID, chapter.getId())
                .set(CHAPTER.COMIC_ID, chapter.getComicId())
                .set(CHAPTER.NUM_PAGES, chapter.getNumPages())
                .set(CHAPTER.VIEWS, chapter.getViews())
                .set(CHAPTER.LIKES, chapter.getLikes())
                .set(CHAPTER.COMMENTS, chapter.getComments())
                .set(CHAPTER.LIKES, chapter.getLikes())
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
