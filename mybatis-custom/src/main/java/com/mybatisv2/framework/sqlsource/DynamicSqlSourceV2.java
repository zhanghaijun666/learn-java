package com.mybatisv2.framework.sqlsource;


import com.mybatisv2.framework.config.BoundSqlV2;
import com.mybatisv2.framework.config.DynamicContextV2;
import com.mybatisv2.framework.sqlnode.SqlNodeV2;
import com.utils.GenericTokenParser;
import com.utils.ParameterMappingTokenHandler;
import com.utils.ParameterMappingTokenHandlerV2;

/**
 * 封装了${}和动态标签的SQL信息，并提供对他们的处理接口
 * 注意事项：
 * 每一次处理${}或者动态标签，都要根据入参对象，重新去生成新的SQL语句，所以说每一次都要调用getBoundSql方法
 * select * from user where id = ${id}
 * select * from user where id = 1
 * select * from user where id = 2
 */
public class DynamicSqlSourceV2 implements SqlSourceV2 {
    // 封装了带有${}或者动态标签的SQL脚本（树状结构）
    private SqlNodeV2 mixedSqlNode ;

    public DynamicSqlSourceV2(SqlNodeV2 mixedSqlNode) {
        this.mixedSqlNode = mixedSqlNode;
    }

    @Override
    public BoundSqlV2 getBoundSql(Object param) {
        // 1.处理所有的SQL节点，获取合并之后的完整的SQL语句（可能还带有#{}）
        DynamicContextV2 context = new DynamicContextV2(param);
        mixedSqlNode.apply(context);
        String sqlText = context.getSql();
        // 2.调用SqlSource的处理逻辑，对于#{}进行处理
        // 处理#{}
        ParameterMappingTokenHandlerV2 tokenHandler = new ParameterMappingTokenHandlerV2();
        GenericTokenParser tokenParser = new GenericTokenParser("#{","}",tokenHandler);
        String sql = tokenParser.parse(sqlText);

        return new BoundSqlV2(sql,tokenHandler.getParameterMappings());
    }
}
