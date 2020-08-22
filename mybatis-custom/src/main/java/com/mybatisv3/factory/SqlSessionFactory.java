package com.mybatisv3.factory;


import com.mybatisv3.sqlsession.SqlSession;

public interface SqlSessionFactory {
    SqlSession openSession();
}
