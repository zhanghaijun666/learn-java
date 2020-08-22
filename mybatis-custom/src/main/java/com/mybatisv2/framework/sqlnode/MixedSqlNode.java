package com.mybatisv2.framework.sqlnode;

import com.mybatisv2.framework.config.DynamicContext;

import java.util.List;

/**
 * 封装了一组SqlNode
 */
public class MixedSqlNode implements SqlNode{
    private List<SqlNode> sqlNodeList;

    public MixedSqlNode(List<SqlNode> sqlNodeList) {
        this.sqlNodeList = sqlNodeList;
    }

    @Override
    public void apply(DynamicContext context) {
        for(SqlNode sqlNode : sqlNodeList){
            sqlNode.apply(context);
        }
    }
}
