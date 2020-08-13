package com.mybatisv2.framework.sqlnode;

import com.mybatisv2.framework.sqlsource.DynamicContext;

public class TextSqlNode implements SqlNode {


    private String sqlText;

    public TextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    public boolean isDynamic() {
         return sqlText.indexOf("${") > -1;
    }

    @Override
    public void apply(DynamicContext context) {

    }
}
