package com.skx.mybatis.model;

import java.util.Enumeration;
import java.util.function.Predicate;

/**
 * Created by vj on 2015/09/20.
 */
public interface IRepository<T> {
    Enumeration<T> findAll(Predicate<T> exp);
    T find(Predicate<T> exp);
    void add(T entity);
    void delete(T entity);
    void update(T entity);
    void saveChanges();
}
