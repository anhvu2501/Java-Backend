package com.example.task2.repository.publish;

import com.example.task2.tables.pojos.Publishment;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
                .where(String.valueOf(ids.stream().filter(s -> s.equals(PUBLISHMENT))))
                .fetchInto(Publishment.class);
    }

    @Override
    public void insert(Publishment publishment) {
        dslContext.insertInto(PUBLISHMENT)
                .set(PUBLISHMENT.ID, publishment.getId())
                .set(PUBLISHMENT.AUTHOR_ID, publishment.getAuthorId())
                .set(PUBLISHMENT.PUBLISHER_ID, publishment.getPublisherId())
                .set(PUBLISHMENT.COMIC_ID, publishment.getComicId())
                .execute();
    }

    @Override
    public void insertMany(List<Publishment> publishments) {
        publishments.stream()
                .map(publishment -> dslContext.insertInto(PUBLISHMENT)
                        .set(PUBLISHMENT.ID, publishment.getId())
                        .set(PUBLISHMENT.AUTHOR_ID, publishment.getAuthorId())
                        .set(PUBLISHMENT.PUBLISHER_ID, publishment.getPublisherId())
                        .set(PUBLISHMENT.COMIC_ID, publishment.getComicId())
                        .execute());
    }

    @Override
    public void update(Publishment publishment, String id) {
        dslContext.update(PUBLISHMENT)
                .set(PUBLISHMENT.ID, publishment.getId())
                .set(PUBLISHMENT.AUTHOR_ID, publishment.getAuthorId())
                .set(PUBLISHMENT.PUBLISHER_ID, publishment.getPublisherId())
                .set(PUBLISHMENT.COMIC_ID, publishment.getComicId())
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
