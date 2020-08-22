package com.mybatis.test;

import com.model.User;
import com.mybatisv3.builder.SqlSessionFactoryBuilder;
import com.mybatisv3.factory.SqlSessionFactory;
import com.mybatisv3.io.Resources;
import com.mybatisv3.sqlsession.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1.以面向对象的思维去改造mybatis手写框架 2.将手写的mybatis的代码封装一个通用的框架（java工程）给程序员使用
 */
public class MybatisV3 {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before(){
        // 全局配置文件的路径
        String location = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(location);
        // 创建SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test(){
        // 规定selectUserList方法的参数只有 两个。
        Map param = new HashMap();
        param.put("username","千年老亚瑟");
        param.put("sex","男");

        // 调用公共方法，查询用户信息
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("test.queryUserByParams",param);
        System.out.println(users);
    }
}
