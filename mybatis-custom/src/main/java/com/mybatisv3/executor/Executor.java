package com.mybatisv3.executor;


import com.mybatisv3.config.Configuration;
import com.mybatisv3.config.MappedStatement;

import java.util.List;

/**
 * 用来执行JDBC逻辑
 */
public interface Executor {
    <T> List<T> query(Configuration configuration, MappedStatement mappedStatement, Object param);
}
