package com.mybatisv2.framework.sqlsource;


import com.mybatisv2.framework.config.BoundSqlV2;
import com.mybatisv2.framework.config.DynamicContextV2;
import com.mybatisv2.framework.sqlnode.SqlNodeV2;
import com.utils.GenericTokenParser;
import com.utils.ParameterMappingTokenHandlerV2;

/**
 * 封装了不带有${}和动态标签的SQL信息，并提供对他们的处理接口
 * 注意事项：
 * 当处理#{}时，只需要处理一次就可以获取对应的SQL语句了。
 * select * from user where id = #{}
 * select * from user where id = ?
 */
public class RawSqlSourceV2 implements SqlSourceV2 {
    // 一个静态的SqlSource（StaticSqlSource）
    private SqlSourceV2 sqlSource ;

    public RawSqlSourceV2(SqlNodeV2 mixedSqlNode) {
        // 真正处理#{}的逻辑要放在该构造方法中
        // 把处理之后的结果，封装成一个静态的SqlSource（StaticSqlSource）

        // 1.处理所有的SQL节点，获取合并之后的完整的SQL语句（可能还带有#{}）
        DynamicContextV2 context = new DynamicContextV2(null);
        mixedSqlNode.apply(context);
        String sqlText = context.getSql();
        // 2.调用SqlSource的处理逻辑，对于#{}进行处理
        // 处理#{}
        ParameterMappingTokenHandlerV2 tokenHandler = new ParameterMappingTokenHandlerV2();
        GenericTokenParser tokenParser = new GenericTokenParser("#{","}",tokenHandler);
        String sql = tokenParser.parse(sqlText);

        sqlSource = new StaticSqlSourceV2(sql,tokenHandler.getParameterMappings());
    }

    @Override
    public BoundSqlV2 getBoundSql(Object param) {
        return sqlSource.getBoundSql(param);
    }
}
