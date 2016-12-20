package com.tyler.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tyler on 2016/12/20.
 */
public class SqlSessionFactoryUtil {
    private static SqlSessionFactory sqlSessionFactory;
    private static final Class CLASS_LOCK = SqlSessionFactoryUtil.class;
    private SqlSessionFactoryUtil(){}
    private static SqlSessionFactory initSqlSessionFactory(){
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        synchronized (CLASS_LOCK){
            if(sqlSessionFactory==null){
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            }
        }
            return sqlSessionFactory;
    }
    public static SqlSession openSessionFactory(){
        if(sqlSessionFactory==null){
            initSqlSessionFactory();
        }
        return sqlSessionFactory.openSession();
    }
}
