package com.example.task2.repository.comments;


import com.example.task2.repository.AbsBaseRepository;
import com.example.task2.tables.pojos.Comments;
import com.example.task2.tables.records.CommentsRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.example.task2.Tables.COMMENTS;

@Repository
public class CommentRepository extends AbsBaseRepository<Comments, CommentsRecord, String> implements ICommentRepository {

    protected CommentRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    protected TableImpl<CommentsRecord> getTable() {
        return COMMENTS;
    }

    @Override
    protected Field<String> getFieldId() {
        return COMMENTS.ID;
    }
}
