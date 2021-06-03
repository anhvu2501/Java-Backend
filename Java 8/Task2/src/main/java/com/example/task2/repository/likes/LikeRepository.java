package com.example.task2.repository.likes;

import com.example.task2.tables.pojos.Likes;
import com.example.task2.tables.records.LikesRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.InsertSetMoreStep;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .where(LIKES.ID.in(ids))
                .fetchInto(Likes.class);
    }

    @Override
    public void insert(Likes likes) {
        Map<Field<?>, Object> fieldObjectMap = getFieldObjectMap(likes);
        dslContext.insertInto(LIKES)
                .set(fieldObjectMap)
                .execute();
    }

    private Map<Field<?>, Object> getFieldObjectMap(Likes likes) {
        Map<Field<?>, Object> fieldObjectMap = new HashMap<>();
        fieldObjectMap.put(LIKES.ID, likes.getId());
        fieldObjectMap.put(LIKES.USER_ID, likes.getUserId());
        fieldObjectMap.put(LIKES.DATE, likes.getDate());
        return fieldObjectMap;
    }

    @Override
    public void insertMany(List<Likes> likes) {
        List<InsertSetMoreStep<LikesRecord>> insertSetMoreSteps = likes.stream()
                .map(like -> dslContext.insertInto(LIKES)
                        .set(getFieldObjectMap(like)))
                .collect(Collectors.toList());
        dslContext.batch(insertSetMoreSteps).execute();

    }

    @Override
    public void update(Likes likes, String id) {
        dslContext.update(LIKES)
                .set(getFieldObjectMap(likes))
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
