package com.mybatisv2.framework.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装SQL语句和#{}解析出来的参数信息集合
 */
public class BoundSqlV2 {
    // 已经解析完成的SQL语句
    private String sql;

    private List<ParameterMappingV2> parameterMappings = new ArrayList<>();

    public BoundSqlV2(String sql, List<ParameterMappingV2> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMappingV2> getParameterMappings() {
        return parameterMappings;
    }

    public void addParameterMapping(ParameterMappingV2 parameterMapping) {
        this.parameterMappings.add(parameterMapping);
    }
}
