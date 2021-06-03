package com.example.task2.repository;


import lombok.SneakyThrows;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.InsertSetMoreStep;
import org.jooq.Record;
import org.jooq.impl.TableImpl;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.task2.utils.MysqlUtils.toInsertField;

public abstract class AbsBaseRepository<Po, R extends Record, ID> implements IBaseRepository<Po, ID> {
    protected final DSLContext dslContext;
    private Class<Po> pojoClass;
    private Class<R> recordClass;
    private R record;

    @SneakyThrows
    protected AbsBaseRepository(DSLContext dslContext) {
        this.dslContext = dslContext;

        this.pojoClass = (Class<Po>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        this.recordClass = (Class<R>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        this.record = this.recordClass.getDeclaredConstructor().newInstance();
    }

    protected abstract TableImpl<R> getTable();

    protected abstract Field<ID> getFieldId();

    public R getRecord() {
        return record;
    }

    @Override
    public Optional<Po> findById(ID id) {
        Po po = dslContext.select()
                .from(getTable())
                .where(getFieldId().eq(id))
                .fetchOneInto(pojoClass);
        return Optional.ofNullable(po);
    }

    @Override
    public List<Po> findByIds(List<ID> ids) {
        return dslContext.select()
                .from(getTable())
                .where(getFieldId().in(ids))
                .fetchInto(pojoClass);
    }

    @Override
    public void insert(Po po) {
        dslContext.insertInto(getTable())
                .set(toInsertField(po, getRecord()))
                .execute();
    }

    @Override
    public void insertMany(List<Po> pos) {
        List<InsertSetMoreStep<R>> insertSetMoreSteps = pos.stream()
                .map(po -> dslContext.insertInto(getTable())
                        .set(toInsertField(po, getRecord())))
                .collect(Collectors.toList());
        dslContext.batch(insertSetMoreSteps).execute();
    }


    @Override
    public void update(Po po, ID id) {
        dslContext.update(getTable())
                .set(toInsertField(po, getRecord()))
                .where(getFieldId().eq(id))
                .execute();
    }

    @Override
    public void deleteById(ID id) {
        dslContext.delete(getTable())
                .where(getFieldId().eq(id))
                .execute();
    }
}


