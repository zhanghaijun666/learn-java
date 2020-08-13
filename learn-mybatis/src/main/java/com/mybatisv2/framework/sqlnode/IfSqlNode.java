package com.mybatisv2.framework.sqlnode;

import com.mybatisv2.framework.sqlsource.DynamicContext;

public class IfSqlNode implements SqlNode {
    private String test;

    private SqlNode mixedSqlNode;
    public IfSqlNode(String test, SqlNode sqlNode) {
        this.test = test;
        this.mixedSqlNode = sqlNode;
    }

    @Override
    public void apply(DynamicContext context) {

    }
}
