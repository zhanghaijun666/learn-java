package com.mybatisv2.framework.sqlsource;

import com.mybatisv2.framework.config.BoundSql;
import com.mybatisv2.framework.sqlnode.SqlNode;

public class DynamicSqlSource implements SqlSource {
    private SqlNode mixedSqlNode;

    public DynamicSqlSource(SqlNode mixedSqlNode) {
        this.mixedSqlNode = mixedSqlNode;
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return null;
    }
}
