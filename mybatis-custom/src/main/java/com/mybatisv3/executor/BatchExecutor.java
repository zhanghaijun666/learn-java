package com.mybatisv3.executor;

import com.mybatisv3.config.BoundSql;
import com.mybatisv3.config.Configuration;
import com.mybatisv3.config.MappedStatement;

import java.util.List;

public class BatchExecutor extends BaseExecutor{
    @Override
    protected List<Object> queryFromDataBase(Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql, Object param) {
        return null;
    }
}
