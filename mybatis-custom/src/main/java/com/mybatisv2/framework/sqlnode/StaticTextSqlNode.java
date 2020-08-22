package com.mybatisv2.framework.sqlnode;


import com.mybatisv2.framework.config.DynamicContext;

/**
 * 封装了不带有${}的 SQL文本
 */
public class StaticTextSqlNode implements SqlNode{
    private String sqlText;

    public StaticTextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext context) {
        context.appendSql(sqlText);
    }
}
