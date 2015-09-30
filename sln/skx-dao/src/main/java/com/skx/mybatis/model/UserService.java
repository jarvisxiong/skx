package com.skx.mybatis.model;

/**
 * Created by vj on 2015/09/20.
 */
import java.util.Date;
import java.util.List;

import com.skx.mybatis.model.dao.UserMapper;
import com.skx.mybatis.model.pojo.User;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;


public class UserService
{
    private static final Log LOGGER = LogFactory.getLog(UserService.class);
    public List<User> findAll()
    {
        SqlSession sqlSession =
                MyBatisSqlSessionFactory.openSession();
        try
        {
            UserMapper UserMapper =
                    sqlSession.getMapper(com.skx.mybatis.model.dao.UserMapper.class);
            return UserMapper.findAll();
        }
        finally
        {
            //If sqlSession is not closed
            //then database Connection associated this sqlSession will not be returned to pool
            //and application may run out of connections.
            sqlSession.close();
        }
    }
    public User findUserById(Integer studId)
    {
        LOGGER.debug(String.format("Select User By ID :{%s}", studId));
        SqlSession sqlSession =
                MyBatisSqlSessionFactory.openSession();
        try
        {
            UserMapper UserMapper =
                    sqlSession.getMapper(com.skx.mybatis.model.dao.UserMapper.class);
            return UserMapper.find(studId);
        }
        finally
        {
            sqlSession.close();
        }
    }
    public void createUser(User User)
    {
        SqlSession sqlSession =
                MyBatisSqlSessionFactory.openSession();
        try
        {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException ex) {

            }
            UserMapper UserMapper =
                    sqlSession.getMapper(com.skx.mybatis.model.dao.UserMapper.class);
            UserMapper.insert(User);
            sqlSession.commit();
        }
        finally
        {
            sqlSession.close();
        }
    }


    public void update(User User)
    {
        SqlSession sqlSession =
                MyBatisSqlSessionFactory.openSession();
        try
        {
            UserMapper UserMapper =
                    sqlSession.getMapper(com.skx.mybatis.model.dao.UserMapper.class);
            UserMapper.update(User);
            sqlSession.commit();
        }
        finally
        {
            sqlSession.close();
        }
    }

}