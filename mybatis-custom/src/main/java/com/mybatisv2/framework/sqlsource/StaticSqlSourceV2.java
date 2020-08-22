package com.mybatisv2.framework.sqlsource;

import com.mybatisv2.framework.config.BoundSqlV2;
import com.mybatisv2.framework.config.ParameterMappingV2;

import java.util.List;

/**
 * 静态的SqlSource
 */
public class StaticSqlSourceV2 implements SqlSourceV2 {

    private String sql;

    private List<ParameterMappingV2> parameterMappings;

    public StaticSqlSourceV2(String sql, List<ParameterMappingV2> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    @Override
    public BoundSqlV2 getBoundSql(Object param) {
        return new BoundSqlV2(sql,parameterMappings);
    }
}
