package com.skx.mybatis.model;

/**
 * Created by vj on 2015/09/20.
 */
import java.io.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
public class MyBatisSqlSessionFactory
{
    private static SqlSessionFactory sqlSessionFactory;
    public static SqlSessionFactory getSqlSessionFactory()
    {
        if(sqlSessionFactory == null)
        {
            InputStream inputStream;
            try
            {
                inputStream = Resources.
                        getResourceAsStream("conf/mybatis-config.xml");
                sqlSessionFactory = new
                        SqlSessionFactoryBuilder().build(inputStream);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }
    public static SqlSession openSession()
    {
        return getSqlSessionFactory().openSession();
    }
}