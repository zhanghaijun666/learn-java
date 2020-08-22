package com.mybatisv2.framework.sqlnode;


import com.mybatisv2.framework.config.DynamicContext;

public interface SqlNode {
    /**
     *
     * @param context
     */
    void apply(DynamicContext context);
}
