package com.mybatisv2.framework.sqlsource;

import com.mybatisv2.framework.config.BoundSql;

public interface SqlSource {
    /**
     * 针对封装的SQL信息，去进行解析，获取可以直接执行的SQL语句
     * @param param
     * @return
     */
    BoundSql getBoundSql(Object param);
}
