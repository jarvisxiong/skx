package com.skx.mybatis.model.dao;

import java.util.List;

/**
 * Created by vj on 2015/09/20.
 */
public interface BaseMapper<T> {
    T find(Integer id);
    List<T> findAll();
    List<T> findAll(Object object);
    void insert(T entity);
    void update(T entity);
}
