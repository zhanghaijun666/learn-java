package com.mybatisv3.executor;

import com.mybatisv3.config.BoundSql;
import com.mybatisv3.config.Configuration;
import com.mybatisv3.config.MappedStatement;
import com.mybatisv3.sqlsource.SqlSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SimpleExecutor、ReuseExecutor、BatchExecutor这三个类都会去走该类的一级缓存处理流程
 */
public abstract class BaseExecutor implements Executor {
    // 一级缓存
    private Map<String,List<Object>> oneLevelCache = new HashMap<>();
    @Override
    public <T> List<T> query(Configuration configuration, MappedStatement mappedStatement, Object param) {
        // 获取缓存的key
        SqlSource sqlSource = mappedStatement.getSqlSource();
        BoundSql boundSql = sqlSource.getBoundSql(param);
        String cacheKey = getCacheKey(boundSql);
        List<Object> list = oneLevelCache.get(cacheKey);
        if (list != null){
            return (List<T>) list;
        }

        // 查询数据库
        list = queryFromDataBase(configuration,mappedStatement,boundSql,param);

        oneLevelCache.put(cacheKey,list);
        return (List<T>) list;
    }

    protected abstract List<Object> queryFromDataBase(Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql,Object param);

    protected String getCacheKey(BoundSql boundSql){
        // TODO cacheKey要做特殊处理
        // boundSql.getSql() -- > select * from user where username = ?

        return boundSql.getSql();
    }

}
