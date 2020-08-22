package com.mybatisv2.framework.sqlsource;


import com.mybatisv2.framework.config.BoundSqlV2;

public interface SqlSourceV2 {
    /**
     * 针对封装的SQL信息，去进行解析，获取可以直接执行的SQL语句
     * @param param
     * @return
     */
    BoundSqlV2 getBoundSql(Object param);
}
