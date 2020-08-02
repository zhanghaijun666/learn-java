package com.mybatis;

import com.mybatisv2.MybatisV2;
import org.junit.Test;

public class MyBatisV2Test {

    @Test
    public void test() throws Exception {
        System.out.println(MybatisV2.getSingletonInstance().selectList("queryUserById", 1));
    }
}
