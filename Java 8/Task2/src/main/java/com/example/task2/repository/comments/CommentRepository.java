package com.example.task2.repository.comments;


import com.example.task2.tables.pojos.Comments;
import com.example.task2.tables.records.CommentsRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.InsertSetMoreStep;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.task2.Tables.COMMENTS;

@Repository
public class CommentRepository implements ICommentRepository {
    private final DSLContext dslContext;

    public CommentRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<Comments> find(String id) {
        Comments comment = dslContext.select()
                .from(COMMENTS)
                .where(COMMENTS.ID.eq(id))
                .fetchOneInto(Comments.class);
        return Optional.ofNullable(comment);
    }

    @Override
    public List<Comments> findByIds(List<String> ids) {
        return dslContext.select()
                .from(COMMENTS)
                .where(COMMENTS.ID.in(ids))
                .fetchInto(Comments.class);
    }

    @Override
    public void insert(Comments comment) {
        Map<Field<?>, Object> fieldObjectMap = getFieldObjectMap(comment);
        dslContext.insertInto(COMMENTS)
                .set(fieldObjectMap)
                .execute();
    }

    private Map<Field<?>, Object> getFieldObjectMap(Comments comment) {
        Map<Field<?>, Object> fieldObjectMap = new HashMap<>();
        fieldObjectMap.put(COMMENTS.ID, comment.getId());
        fieldObjectMap.put(COMMENTS.COMIC_ID, comment.getComicId());
        fieldObjectMap.put(COMMENTS.DATE, comment.getDate());
        fieldObjectMap.put(COMMENTS.CHAPTER_ID, comment.getChapterId());
        return fieldObjectMap;
    }

    @Override
    public void insertMany(List<Comments> comments) {
        List<InsertSetMoreStep<CommentsRecord>> insertSetMoreSteps = comments.stream()
                .map(comment -> dslContext.insertInto(COMMENTS)
                        .set(getFieldObjectMap(comment)))
                .collect(Collectors.toList());
        dslContext.batch(insertSetMoreSteps).execute();
    }

    @Override
    public void update(Comments comment, String id) {
        dslContext.update(COMMENTS)
                .set(getFieldObjectMap(comment))
                .where(COMMENTS.ID.eq(id))
                .execute();
    }

    @Override
    public void deleteById(String id) {
        dslContext.delete(COMMENTS)
                .where(COMMENTS.ID.eq(id))
                .execute();
    }
}
