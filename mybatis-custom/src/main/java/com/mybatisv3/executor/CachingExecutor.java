package com.mybatisv3.executor;


import com.mybatisv3.config.Configuration;
import com.mybatisv3.config.MappedStatement;

import java.util.List;

/**
 * 可以处理二级缓存
 */
public class CachingExecutor implements Executor{
    // 委托给真正的执行器去做处理
    private Executor executor;

    public CachingExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public <T> List<T> query(Configuration configuration, MappedStatement mappedStatement, Object param) {
        // 先获取二级缓存对象

        // 二级缓存中没有，则继续调用执行器的逻辑
        return executor.query(configuration,mappedStatement,param);
    }
}
