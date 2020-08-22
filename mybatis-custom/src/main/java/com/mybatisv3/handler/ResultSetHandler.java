package com.mybatisv3.handler;


import com.mybatisv3.config.MappedStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * 专门处理结果集的
 */
public interface ResultSetHandler {
    List<Object> handleResultSet(PreparedStatement preparedStatement, ResultSet rs, MappedStatement mappedStatement) throws Exception;
}
