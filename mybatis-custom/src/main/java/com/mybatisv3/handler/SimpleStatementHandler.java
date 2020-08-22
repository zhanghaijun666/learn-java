package com.mybatisv3.handler;


import com.mybatisv3.config.BoundSql;
import com.mybatisv3.config.MappedStatement;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * 专门处理PreparedStatement对象的
 */
public class SimpleStatementHandler implements StatementHandler{
    @Override
    public Statement prepare(String sql, Connection connection) {
        return null;
    }

    @Override
    public void parameterize(Object param, Statement statement, BoundSql boundSql) {

    }

    @Override
    public List<Object> query(Statement statement, MappedStatement mappedStatement) {
        return null;
    }
}
