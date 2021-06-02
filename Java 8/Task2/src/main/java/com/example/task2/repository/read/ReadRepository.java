package com.example.task2.repository.read;

import com.example.task2.tables.pojos.Reads;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.task2.Tables.READS;

@Repository
public class ReadRepository implements IReadRepository {
    private final DSLContext dslContext;

    public ReadRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<Reads> find(String id) {
        Reads reads = dslContext.select()
                .from(READS)
                .where(READS.ID.eq(id))
                .fetchOneInto(Reads.class);
        return Optional.ofNullable(reads);
    }

    @Override
    public List<Reads> findByIds(List<String> ids) {
        return dslContext.select()
                .from(READS)
                .where(String.valueOf(ids.stream().filter(s -> s.equals(READS.ID))))
                .fetchInto(Reads.class);
    }

    @Override
    public void insert(Reads read) {
        dslContext.insertInto(READS)
                .set(READS.ID, read.getId())
                .set(READS.COMIC_ID, read.getComicId())
                .set(READS.CHAPTER_ID, read.getChapterId())
                .set(READS.DATE, read.getDate())
                .execute();
    }

    @Override
    public void insertMany(List<Reads> reads) {
        reads.stream()
                .map(read -> dslContext.insertInto(READS)
                        .set(READS.ID, read.getId())
                        .set(READS.COMIC_ID, read.getComicId())
                        .set(READS.CHAPTER_ID, read.getChapterId())
                        .set(READS.DATE, read.getDate())
                        .execute());
    }

    @Override
    public void update(Reads read, String id) {
        dslContext.update(READS)
                .set(READS.ID, read.getId())
                .set(READS.COMIC_ID, read.getComicId())
                .set(READS.CHAPTER_ID, read.getChapterId())
                .set(READS.DATE, read.getDate())
                .where(READS.ID.eq(id))
                .execute();
    }

    @Override
    public void deleteById(String id) {
        dslContext.delete(READS)
                .where(READS.ID.eq(id))
                .execute();
    }
}
