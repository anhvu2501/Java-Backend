package com.example.task2.repository.post;

import com.example.task2.tables.pojos.Post;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.task2.Tables.POST;

@Repository
public class PostRepository implements IPostRepository {
    private final DSLContext dslContext;

    public PostRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<Post> find(String id) {
        Post post = dslContext.select()
                .from(POST)
                .where(POST.ID.eq(id))
                .fetchOneInto(Post.class);
        return Optional.ofNullable(post);
    }

    @Override
    public List<Post> findByIds(List<String> ids) {
        return dslContext.select()
                .from(POST)
                .where(String.valueOf(ids.stream().filter(s -> s.equals(POST.ID))))
                .fetchInto(Post.class);
    }

    @Override
    public void insert(Post post) {
        dslContext.insertInto(POST)
                .set(POST.ID, post.getId())
                .set(POST.PUBLISHER_ID, post.getPublisherId())
                .set(POST.ACCOUNT_ID, post.getAccountId())
                .set(POST.DATE, post.getDate())
                .set(POST.COMIC_ID, post.getComicId())
                .set(POST.CHAPTER_ID, post.getChapterId())
                .execute();
    }

    @Override
    public void insertMany(List<Post> posts) {
        posts.stream()
                .map(post -> dslContext.insertInto(POST)
                        .set(POST.ID, post.getId())
                        .set(POST.PUBLISHER_ID, post.getPublisherId())
                        .set(POST.ACCOUNT_ID, post.getAccountId())
                        .set(POST.DATE, post.getDate())
                        .set(POST.COMIC_ID, post.getComicId())
                        .set(POST.CHAPTER_ID, post.getChapterId())
                        .execute());
    }

    @Override
    public void update(Post post, String id) {
        dslContext.update(POST)
                .set(POST.ID, post.getId())
                .set(POST.PUBLISHER_ID, post.getPublisherId())
                .set(POST.ACCOUNT_ID, post.getAccountId())
                .set(POST.DATE, post.getDate())
                .set(POST.COMIC_ID, post.getComicId())
                .set(POST.CHAPTER_ID, post.getChapterId())
                .where(POST.ID.eq(id))
                .execute();
    }

    @Override
    public void deleteById(String id) {
        dslContext.delete(POST)
                .where(POST.ID.eq(id))
                .execute();
    }
}
