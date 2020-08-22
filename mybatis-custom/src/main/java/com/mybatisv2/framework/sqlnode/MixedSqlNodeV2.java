package com.mybatisv2.framework.sqlnode;

import com.mybatisv2.framework.config.DynamicContextV2;

import java.util.List;

/**
 * 封装了一组SqlNode
 */
public class MixedSqlNodeV2 implements SqlNodeV2 {
    private List<SqlNodeV2> sqlNodeList;

    public MixedSqlNodeV2(List<SqlNodeV2> sqlNodeList) {
        this.sqlNodeList = sqlNodeList;
    }

    @Override
    public void apply(DynamicContextV2 context) {
        for(SqlNodeV2 sqlNode : sqlNodeList){
            sqlNode.apply(context);
        }
    }
}
