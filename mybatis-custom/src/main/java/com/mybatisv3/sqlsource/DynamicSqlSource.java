package com.mybatisv3.sqlsource;


import com.mybatisv3.config.BoundSql;
import com.mybatisv3.config.DynamicContext;
import com.mybatisv3.parser.SqlSourceParser;
import com.mybatisv3.sqlnode.SqlNode;

/**
 * 封装了${}和动态标签的SQL信息，并提供对他们的处理接口
 * 注意事项：
 * 每一次处理${}或者动态标签，都要根据入参对象，重新去生成新的SQL语句，所以说每一次都要调用getBoundSql方法
 * select * from user where id = ${id}
 * select * from user where id = 1
 * select * from user where id = 2
 */
public class DynamicSqlSource implements SqlSource{
    // 封装了带有${}或者动态标签的SQL脚本（树状结构）
    private SqlNode mixedSqlNode ;

    public DynamicSqlSource(SqlNode mixedSqlNode) {
        this.mixedSqlNode = mixedSqlNode;
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        // 1.处理所有的SQL节点，获取合并之后的完整的SQL语句（可能还带有#{}）
        DynamicContext context = new DynamicContext(param);
        mixedSqlNode.apply(context);
        String sqlText = context.getSql();
        // 2.调用SqlSource的处理逻辑，对于#{}进行处理
        // 处理#{}
        SqlSourceParser parser = new SqlSourceParser();
        SqlSource staticSqlSource = parser.parse(sqlText);

        return staticSqlSource.getBoundSql(param);
    }
}
