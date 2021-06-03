package com.example.task2.repository.authors;

import com.example.task2.tables.pojos.Authors;
import com.example.task2.tables.records.AuthorsRecord;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.InsertSetMoreStep;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Authors> findByIds(List<String> ids) {
        return dslContext.select()
                .from(AUTHORS)
                .where(AUTHORS.ID.in(ids))
                .fetchInto(Authors.class);
    }

    @Override
    public void insert(Authors authors) {
        Map<Field<?>, Object> fieldObjectMap = getFieldObjectMap(authors);
        dslContext.insertInto(AUTHORS)
                .set(fieldObjectMap)
                .execute();
    }

    private Map<Field<?>, Object> getFieldObjectMap(Authors authors) {
        Map<Field<?>, Object> fieldObjectMap = new HashMap<>();
        fieldObjectMap.put(AUTHORS.ID, authors.getId());
        fieldObjectMap.put(AUTHORS.NAME, authors.getName());
        fieldObjectMap.put(AUTHORS.NATIONALITY, authors.getNationality());
        fieldObjectMap.put(AUTHORS.DOB, authors.getDob());
        return fieldObjectMap;
    }

    @Override
    public void insertMany(List<Authors> authors) {
        List<InsertSetMoreStep<AuthorsRecord>> insertSetMoreSteps = authors.stream()
                .map(authors1 -> dslContext.insertInto(AUTHORS)
                        .set(getFieldObjectMap(authors1)))
                .collect(Collectors.toList());
        dslContext.batch(insertSetMoreSteps).execute();
    }

    @Override
    public void update(Authors authors, String id) {
        dslContext.update(AUTHORS)
                .set(getFieldObjectMap(authors))
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
