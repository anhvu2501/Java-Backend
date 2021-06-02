package com.example.task2.repository.likes;

import com.example.task2.tables.pojos.Likes;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.task2.Tables.LIKES;

@Repository
public class LikeRepository implements ILikeRepository {
    private final DSLContext dslContext;

    public LikeRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<Likes> find(String id) {
        Likes likes = dslContext.select()
                .from(LIKES)
                .where(LIKES.ID.eq(id))
                .fetchOneInto(Likes.class);
        return Optional.ofNullable(likes);
    }

    @Override
    public List<Likes> findByIds(List<String> ids) {
        return dslContext.select()
                .from(LIKES)
                .where(String.valueOf(ids.stream().filter(s -> s.equals(LIKES.ID))))
                .fetchInto(Likes.class);
    }

    @Override
    public void insert(Likes likes) {
        dslContext.insertInto(LIKES)
                .set(LIKES.ID, likes.getId())
                .set(LIKES.USER_ID, likes.getUserId())
                .set(LIKES.DATE, likes.getDate())
                .execute();
    }

    @Override
    public void insertMany(List<Likes> likes) {
        likes.stream()
                .map(like -> dslContext.insertInto(LIKES)
                        .set(LIKES.ID, like.getId())
                        .set(LIKES.USER_ID, like.getUserId())
                        .set(LIKES.DATE, like.getDate())
                        .execute());

    }

    @Override
    public void update(Likes likes, String id) {
        dslContext.update(LIKES)
                .set(LIKES.ID, likes.getId())
                .set(LIKES.USER_ID, likes.getUserId())
                .set(LIKES.DATE, likes.getDate())
                .where(LIKES.ID.eq(id))
                .execute();
    }

    @Override
    public void deleteById(String id) {
        dslContext.delete(LIKES)
                .where(LIKES.ID.eq(id))
                .execute();
    }
}
