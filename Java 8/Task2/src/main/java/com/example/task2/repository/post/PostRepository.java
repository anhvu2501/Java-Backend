package com.example.task2.repository.post;

import com.example.task2.tables.pojos.Post;
import com.example.task2.tables.records.PostRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.InsertSetMoreStep;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .where(POST.ID.in(ids))
                .fetchInto(Post.class);
    }

    @Override
    public void insert(Post post) {
        Map<Field<?>, Object> fieldObjectMap = getFieldObjectMap(post);
        dslContext.insertInto(POST)
                .set(fieldObjectMap)
                .execute();
    }

    private Map<Field<?>, Object> getFieldObjectMap(Post post) {
        Map<Field<?>, Object> fieldObjectMap = new HashMap<>();
        fieldObjectMap.put(POST.ID, post.getId());
        fieldObjectMap.put(POST.PUBLISHER_ID, post.getPublisherId());
        fieldObjectMap.put(POST.ACCOUNT_ID, post.getAccountId());
        fieldObjectMap.put(POST.DATE, post.getDate());
        fieldObjectMap.put(POST.COMIC_ID, post.getComicId());
        fieldObjectMap.put(POST.CHAPTER_ID, post.getChapterId());
        return fieldObjectMap;
    }

    @Override
    public void insertMany(List<Post> posts) {
        List<InsertSetMoreStep<PostRecord>> insertSetMoreSteps = posts.stream()
                .map(post -> dslContext.insertInto(POST)
                        .set(getFieldObjectMap(post)))
                .collect(Collectors.toList());
        dslContext.batch(insertSetMoreSteps).execute();
    }

    @Override
    public void update(Post post, String id) {
        dslContext.update(POST)
                .set(getFieldObjectMap(post))
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
