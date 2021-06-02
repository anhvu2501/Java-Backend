package com.example.task2.repository.tags;

import com.example.task2.tables.pojos.Tags;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public List<Tags> findByIds(List<String> id) {
        return dslContext.select()
                .from(TAGS)
                .where(String.valueOf(id.stream().filter(s -> s.equals(TAGS.ID))))
                .fetchInto(Tags.class);
    }

    @Override
    public void insert(Tags tags) {
        dslContext.insertInto(TAGS)
                .set(TAGS.ID, tags.getId())
                .set(TAGS.DESCRIPTION, tags.getDescription())
                .set(TAGS.NAME, tags.getName())
                .execute();
    }

    @Override
    public void insertMany(List<Tags> tags) {
        tags.stream()
                .map(tags1 -> dslContext.insertInto(TAGS)
                        .set(TAGS.ID, tags1.getId())
                        .set(TAGS.NAME, tags1.getName())
                        .set(TAGS.DESCRIPTION, tags1.getDescription())
                        .execute());
    }

    @Override
    public void update(Tags tags, String id) {
        dslContext.update(TAGS)
                .set(TAGS.ID, tags.getId())
                .set(TAGS.NAME, tags.getName())
                .set(TAGS.DESCRIPTION, tags.getDescription())
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
