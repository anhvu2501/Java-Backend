package com.example.task2.repository.comments;


import com.example.task2.tables.pojos.Comments;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
                .where(String.valueOf(ids.stream().filter(s -> s.equals(COMMENTS.ID))))
                .fetchInto(Comments.class);
    }

    @Override
    public void insert(Comments comment) {
        dslContext.insertInto(COMMENTS)
                .set(COMMENTS.ID, comment.getId())
                .set(COMMENTS.COMIC_ID, comment.getComicId())
                .set(COMMENTS.DATE, comment.getDate())
                .set(COMMENTS.CHAPTER_ID, comment.getChapterId())
                .execute();
    }

    @Override
    public void insertMany(List<Comments> comments) {
        comments.stream()
                .map(comment -> dslContext.insertInto(COMMENTS)
                        .set(COMMENTS.ID, comment.getId())
                        .set(COMMENTS.COMIC_ID, comment.getComicId())
                        .set(COMMENTS.DATE, comment.getDate())
                        .set(COMMENTS.CHAPTER_ID, comment.getChapterId())
                        .execute());
    }

    @Override
    public void update(Comments comment, String id) {
        dslContext.update(COMMENTS)
                .set(COMMENTS.ID, comment.getId())
                .set(COMMENTS.COMIC_ID, comment.getComicId())
                .set(COMMENTS.DATE, comment.getDate())
                .set(COMMENTS.CHAPTER_ID, comment.getChapterId())
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
