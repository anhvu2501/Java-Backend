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
        return null;
    }

    @Override
    public Tags insert(Tags tags) {
        return null;
    }

    @Override
    public void insertMany(List<Tags> tags) {

    }

    @Override
    public Tags update(Tags tags) {
//        dslContext.insertInto(TAGS)
//                .set()
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}
