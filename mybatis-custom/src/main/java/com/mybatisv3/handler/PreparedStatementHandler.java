package com.mybatisv3.handler;


import com.mybatisv3.config.BoundSql;
import com.mybatisv3.config.Configuration;
import com.mybatisv3.config.MappedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * 专门处理PreparedStatement对象的
 */
public class PreparedStatementHandler implements StatementHandler{
    // 四大组件中的另外两大组件
    private ParameterHandler parameterHandler;
    private ResultSetHandler resultSetHandler;

    public PreparedStatementHandler(Configuration configuration) {
        parameterHandler = configuration.newParameterHandler();
        resultSetHandler = configuration.newResultSetHandler();
    }

    @Override
    public Statement prepare(String sql, Connection connection) throws Exception{
        return connection.prepareStatement(sql);
    }

    @Override
    public void parameterize(Object param, Statement statement, BoundSql boundSql)  throws Exception{
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        parameterHandler.setParameters(param, preparedStatement, boundSql);
    }

    @Override
    public List<Object> query(Statement statement, MappedStatement mappedStatement)  throws Exception{
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        ResultSet rs = preparedStatement.executeQuery();

        List<Object> list = resultSetHandler.handleResultSet(preparedStatement,rs,mappedStatement);
        return list;
    }
}
