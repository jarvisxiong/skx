package com.skx.mybatis.model;

import com.skx.mybatis.model.dao.BaseMapper;
import com.skx.mybatis.model.dao.UserMapper;
import com.skx.mybatis.model.pojo.User;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.Enumeration;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by vj on 2015/09/20.
 */
public class GenericService<T,TMapper> //implements IRepository<T>
{
    private static final Log LOGGER = LogFactory.getLog(GenericService.class);
    protected Class _entityClass;
    protected Class _mapperClass;
    protected SqlSession _readSession;
    protected SqlSession _changeSession;

    public GenericService(Class entityClass) {
        _entityClass = entityClass;
        _readSession = MyBatisSqlSessionFactory.openSession();
        _changeSession = MyBatisSqlSessionFactory.openSession();
    }


    public List<Object> findAll() throws ClassNotFoundException {
        Class a = User.class;

        doGetClass();
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
        try {
            Class c = _entityClass;
            Class.forName(String.format("com.skx.mybatis.model.dao.UserMapper", typeof(T)));
            BaseMapper<TMapper> BaseMapper = sqlSession.getMapper(_mapperClass);
//            return UserMapper.findAll();
            return null;
        } finally {
            //If sqlSession is not closed
            //then database Connection associated this sqlSession will not be returned to pool
            //and application may run out of connections.
            sqlSession.close();
        }
    }

    private Class<Object> clazz;

    private Class<Object> doGetClass() {
        Type genType1 = this.getClass().getGenericSuperclass();
        Type genType = this.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType1).getActualTypeArguments();
        this.clazz = (Class<Object>) params[0];
        return clazz;
    }
}
