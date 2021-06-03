package com.example.task2.repository.post;

import com.example.task2.repository.AbsBaseRepository;
import com.example.task2.tables.pojos.Post;
import com.example.task2.tables.records.PostRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.example.task2.Tables.POST;

@Repository
public class PostRepository extends AbsBaseRepository<Post, PostRecord, String> implements IPostRepository {

    protected PostRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    protected TableImpl<PostRecord> getTable() {
        return POST;
    }

    @Override
    protected Field<String> getFieldId() {
        return POST.ID;
    }
}
