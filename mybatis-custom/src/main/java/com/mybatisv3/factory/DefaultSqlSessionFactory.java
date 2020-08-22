package com.mybatisv3.factory;


import com.mybatisv3.config.Configuration;
import com.mybatisv3.sqlsession.DefaultSqlSession;
import com.mybatisv3.sqlsession.SqlSession;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
