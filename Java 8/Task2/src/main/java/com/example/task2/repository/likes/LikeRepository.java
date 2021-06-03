package com.example.task2.repository.likes;

import com.example.task2.repository.AbsBaseRepository;
import com.example.task2.tables.pojos.Likes;
import com.example.task2.tables.records.LikesRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.example.task2.Tables.LIKES;

@Repository
public class LikeRepository extends AbsBaseRepository<Likes, LikesRecord, String> implements ILikeRepository {

    protected LikeRepository(DSLContext dslContext) {
        super(dslContext);
    }

    @Override
    protected TableImpl<LikesRecord> getTable() {
        return LIKES;
    }

    @Override
    protected Field<String> getFieldId() {
        return LIKES.ID;
    }
}
