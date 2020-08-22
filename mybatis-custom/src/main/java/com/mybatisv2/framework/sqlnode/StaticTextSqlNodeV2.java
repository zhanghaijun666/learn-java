package com.mybatisv2.framework.sqlnode;


import com.mybatisv2.framework.config.DynamicContextV2;

/**
 * 封装了不带有${}的 SQL文本
 */
public class StaticTextSqlNodeV2 implements SqlNodeV2 {
    private String sqlText;

    public StaticTextSqlNodeV2(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContextV2 context) {
        context.appendSql(sqlText);
    }
}
