package com.example.task2.repository.comic;

import com.example.task2.tables.pojos.Comic;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;

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
    public List<Comic> findByIds(List<String> id) {
        return dslContext.select()
                .from(COMIC)
                .where(String.valueOf(id.stream().filter(s -> s.equals(COMIC.ID))))
                .fetchInto(Comic.class);
    }

    @Override
    public void insert(Comic comic) {
        dslContext.insertInto(COMIC)
                .set(COMIC.ID, comic.getId())
                .set(COMIC.NAME, comic.getName())
                .set(COMIC.AUTHOR_ID, comic.getAuthorId())
                .set(COMIC.PUBLISHER_ID, comic.getPublisherId())
                .set(COMIC.STATUS, comic.getStatus())
                .set(COMIC.FIRST_UPLOADED, comic.getFirstUploaded())
                .set(COMIC.LAST_UPLOADED, comic.getLastUploaded())
                .set(COMIC.CURRENT_CHAPTER, comic.getCurrentChapter())
                .set(COMIC.TOTAL_VIEW, comic.getTotalView())
                .set(COMIC.RATING, comic.getRating())
                .execute();
    }

    @Override
    public void insertMany(List<Comic> comics) {
        comics.stream()
                .map(comic -> dslContext.insertInto(COMIC)
                        .set(COMIC.ID, comic.getId())
                        .set(COMIC.NAME, comic.getName())
                        .set(COMIC.AUTHOR_ID, comic.getAuthorId())
                        .set(COMIC.PUBLISHER_ID, comic.getPublisherId())
                        .set(COMIC.STATUS, comic.getStatus())
                        .set(COMIC.FIRST_UPLOADED, comic.getFirstUploaded())
                        .set(COMIC.LAST_UPLOADED, comic.getLastUploaded())
                        .set(COMIC.CURRENT_CHAPTER, comic.getCurrentChapter())
                        .set(COMIC.TOTAL_VIEW, comic.getTotalView())
                        .set(COMIC.RATING, comic.getRating())
                        .execute());
    }

    @Override
    public void update(Comic comic, String id) {
        dslContext.update(COMIC)
                .set(COMIC.ID, comic.getId())
                .set(COMIC.NAME, comic.getName())
                .set(COMIC.AUTHOR_ID, comic.getAuthorId())
                .set(COMIC.PUBLISHER_ID, comic.getPublisherId())
                .set(COMIC.STATUS, comic.getStatus())
                .set(COMIC.FIRST_UPLOADED, comic.getFirstUploaded())
                .set(COMIC.LAST_UPLOADED, comic.getLastUploaded())
                .set(COMIC.CURRENT_CHAPTER, comic.getCurrentChapter())
                .set(COMIC.TOTAL_VIEW, comic.getTotalView())
                .set(COMIC.RATING, comic.getRating())
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
