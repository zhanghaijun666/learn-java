package com.mybatisv2.framework.sqlsource;

import com.mybatisv2.framework.config.BoundSql;
import com.mybatisv2.framework.config.ParameterMapping;

import java.util.List;

/**
 * 静态的SqlSource
 */
public class StaticSqlSource implements SqlSource{

    private String sql;

    private List<ParameterMapping> parameterMappings;

    public StaticSqlSource(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return new BoundSql(sql,parameterMappings);
    }
}
