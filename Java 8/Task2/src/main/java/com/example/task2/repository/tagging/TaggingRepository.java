package com.example.task2.repository.tagging;

import com.example.task2.tables.pojos.Tagging;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
                .where(String.valueOf(ids.stream().filter(s -> s.equals(TAGGING.ID))))
                .fetchInto(Tagging.class);
    }

    @Override
    public void insert(Tagging tagging) {
        dslContext.insertInto(TAGGING)
                .set(TAGGING.ID, tagging.getId())
                .set(TAGGING.TAG_ID, tagging.getTagId())
                .set(TAGGING.COMIC_ID, tagging.getComicId())
                .execute();
    }

    @Override
    public void insertMany(List<Tagging> taggings) {
        taggings.stream()
                .map(tagging -> dslContext.insertInto(TAGGING)
                        .set(TAGGING.ID, tagging.getId())
                        .set(TAGGING.TAG_ID, tagging.getTagId())
                        .set(TAGGING.COMIC_ID, tagging.getComicId())
                        .execute());
    }

    @Override
    public void update(Tagging tagging, String id) {
        dslContext.update(TAGGING)
                .set(TAGGING.ID, tagging.getId())
                .set(TAGGING.TAG_ID, tagging.getTagId())
                .set(TAGGING.COMIC_ID, tagging.getComicId())
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
