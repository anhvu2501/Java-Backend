package com.example.task2.repository.authors;

import com.example.task2.tables.pojos.Authors;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;

import static com.example.task2.Tables.AUTHORS;

public class AuthorsRepository implements IAuthorsRepository {
    private final DSLContext dslContext;

    public AuthorsRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<Authors> find(String id) {
        Authors authors = dslContext.select()
                .from(AUTHORS)
                .where(AUTHORS.ID.eq(id))
                .fetchOneInto(Authors.class);
        return Optional.ofNullable(authors);
    }

    @Override
    public List<Authors> findByIds(List<String> id) {
        return dslContext.select()
                .from(AUTHORS)
                .where(String.valueOf(id.stream().filter(s -> s.equals(AUTHORS.ID))))
                .fetchInto(Authors.class);
    }

    @Override
    public void insert(Authors authors) {
        dslContext.insertInto(AUTHORS)
                .set(AUTHORS.ID, authors.getId())
                .set(AUTHORS.NAME, authors.getName())
                .set(AUTHORS.NATIONALITY, authors.getNationality())
                .set(AUTHORS.DOB, authors.getDob())
                .execute();
    }

    @Override
    public void insertMany(List<Authors> authors) {
        authors.stream()
                .map(authors1 -> dslContext.insertInto(AUTHORS)
                        .set(AUTHORS.ID, authors1.getId())
                        .set(AUTHORS.NAME, authors1.getName())
                        .set(AUTHORS.NATIONALITY, authors1.getNationality())
                        .set(AUTHORS.DOB, authors1.getDob())
                        .execute());
    }

    @Override
    public void update(Authors authors, String id) {
        dslContext.update(AUTHORS)
                .set(AUTHORS.ID, authors.getId())
                .set(AUTHORS.NAME, authors.getName())
                .set(AUTHORS.NATIONALITY, authors.getNationality())
                .set(AUTHORS.DOB, authors.getDob())
                .where(AUTHORS.ID.eq(id))
                .execute();
    }

    @Override
    public void deleteById(String id) {
        dslContext.delete(AUTHORS)
                .where(AUTHORS.ID.eq(id))
                .execute();
    }
}
