package com.mybatisv3.handler;


import com.mybatisv3.config.BoundSql;
import com.mybatisv3.config.Configuration;
import com.mybatisv3.config.MappedStatement;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class RoutingStatementHandler implements StatementHandler {
    private StatementHandler statementHandler;

    public RoutingStatementHandler(String statementType, Configuration configuration) {
        if ("prepared".equals(statementType)){
            statementHandler = new PreparedStatementHandler(configuration);
        }//....
    }

    @Override
    public Statement prepare(String sql, Connection connection) throws Exception {
        return statementHandler.prepare(sql,connection);
    }

    @Override
    public void parameterize(Object param, Statement statement, BoundSql boundSql) throws Exception {
        statementHandler.parameterize(param,statement,boundSql);
    }

    @Override
    public List<Object> query(Statement statement, MappedStatement mappedStatement) throws Exception {
        return statementHandler.query(statement,mappedStatement);
    }
}
