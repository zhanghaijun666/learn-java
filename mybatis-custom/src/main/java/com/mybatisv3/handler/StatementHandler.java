package com.mybatisv3.handler;


import com.mybatisv3.config.BoundSql;
import com.mybatisv3.config.MappedStatement;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public interface StatementHandler {
    Statement prepare(String sql, Connection connection) throws Exception;

    void parameterize(Object param, Statement statement, BoundSql boundSql) throws Exception;

    List<Object> query(Statement statement, MappedStatement mappedStatement) throws Exception;
}
