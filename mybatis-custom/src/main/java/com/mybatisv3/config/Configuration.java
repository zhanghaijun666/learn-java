package com.mybatisv3.config;


import com.mybatisv3.executor.CachingExecutor;
import com.mybatisv3.executor.Executor;
import com.mybatisv3.executor.SimpleExecutor;
import com.mybatisv3.handler.*;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装了mybatis中xml文件的所有配置信息
 */
public class Configuration {
    private DataSource dataSource;

    private boolean useCache = true;

    private Map<String,MappedStatement> mappedStatements = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public MappedStatement getMappedStatementById(String statementId) {
        return mappedStatements.get(statementId);
    }

    public void addMappedStatement(String statementId, MappedStatement mappedStatement) {
        this.mappedStatements.put(statementId,mappedStatement);
    }

    public Executor newExecutor(String executorType) {
        // 如果没有传参数，就选择默认的
        executorType = executorType == null || executorType.equals("") ? "simple":executorType;

        Executor executor = null;
        if (executorType.equals("simple")){
            executor = new SimpleExecutor();
        } // ...

        // 针对真正的执行器，进行二级缓存保证
        if (useCache){
            executor = new CachingExecutor(executor);
        }
        return executor;
    }

    public StatementHandler newStatementHandler(String statementType) {
        statementType = statementType == null || statementType.equals("") ? "prepared":statementType;
        // RoutingStatementHandler
        StatementHandler statementHandler = new RoutingStatementHandler(statementType,this);
        return statementHandler;
    }

    public ParameterHandler newParameterHandler() {
        return new DefaultParameterHandler();
    }

    public ResultSetHandler newResultSetHandler() {
        return new DefaultResultSetHandler();
    }
}
