package com.mybatisv3.parser;


import com.mybatisv3.sqlsource.SqlSource;
import com.mybatisv3.sqlsource.StaticSqlSource;
import com.utils.GenericTokenParser;
import com.utils.ParameterMappingTokenHandler;

/**
 * 用来处理#{}之后，获取StaticSqlSource
 *
 */
public class SqlSourceParser {
    public SqlSource parse(String sqlText){
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser tokenParser = new GenericTokenParser("#{","}",tokenHandler);
        String sql = tokenParser.parse(sqlText);

        return new StaticSqlSource(sql,tokenHandler.getParameterMappings());
    }
}
