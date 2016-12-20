package com.tyler.test;

import com.tyler.dao.UserMapper;
import com.tyler.model.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * Created by tyler on 2016/12/20.
 */
public class MyBatisTest {
    private static SqlSession sqlSession;
    @Test
    public void query(){
        try {
            sqlSession = SqlSessionFactoryUtil.openSessionFactory();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectByPrimaryKey(1);
            sqlSession.commit();
            //重写User类的toString()
            System.out.println(user.toString());
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }
}
