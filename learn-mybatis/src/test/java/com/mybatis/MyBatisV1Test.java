package com.mybatis;

import com.mybatisv1.MybatisV1;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MyBatisV1Test {

    @Test
    public void test() throws Exception {
        System.out.println(MybatisV1.getSingletonInstance().selectList("queryUserById", 1));

        Map<String, Object> param = new HashMap<>();
        param.put("nickname", "亚瑟无敌");
        param.put("sex", "男");
        System.out.println(MybatisV1.getSingletonInstance().selectList("queryUserByParams", param));

    }
}
