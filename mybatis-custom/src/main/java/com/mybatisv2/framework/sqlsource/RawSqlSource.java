package com.mybatisv2.framework.sqlsource;

import com.mybatisv2.framework.config.BoundSql;
import com.mybatisv2.framework.sqlnode.SqlNode;

public class RawSqlSource implements SqlSource {
    public RawSqlSource(SqlNode mixedSqlNode) {

    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return null;
    }
}
