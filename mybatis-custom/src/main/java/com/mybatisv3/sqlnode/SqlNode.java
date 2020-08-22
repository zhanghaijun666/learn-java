package com.mybatisv3.sqlnode;


import com.mybatisv3.config.DynamicContext;

public interface SqlNode {
    /**
     *
     * @param context
     */
    void apply(DynamicContext context);
}
