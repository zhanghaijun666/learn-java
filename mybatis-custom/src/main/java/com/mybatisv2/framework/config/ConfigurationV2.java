package com.mybatisv2.framework.config;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装了mybatis中xml文件的所有配置信息
 */
public class ConfigurationV2 {
    private DataSource dataSource;

    private Map<String, MappedStatementV2> mappedStatements = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public MappedStatementV2 getMappedStatementById(String statementId) {
        return mappedStatements.get(statementId);
    }

    public void addMappedStatement(String statementId, MappedStatementV2 mappedStatement) {
        this.mappedStatements.put(statementId,mappedStatement);
    }
}
