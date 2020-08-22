package com.mybatis;

import com.mybatis.demo.mapper.UserMapper;
import com.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class MybatisTest1 {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws Exception {
        // 加载全局配置文件（同时把映射文件也加载了）
        String resource = "config/SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // sqlsessionFactory需要通过sqlsessionFactoryBuilder读取全局配置文件信息之后
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testFindUserById01() {
        //创建UserMapper对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用UserMapper对象的API
        User user = mapper.selectByPrimaryKey(1);
        System.out.println(user);
    }
//    @Test
//    public void test() {
//        // 创建UserMapper对象
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//
//        List<User> list = mapper.selectByExample(new User());
//        System.out.println(list);
//    }
}
