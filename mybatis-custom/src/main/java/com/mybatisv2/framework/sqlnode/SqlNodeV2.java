package com.mybatisv2.framework.sqlnode;


import com.mybatisv2.framework.config.DynamicContextV2;

public interface SqlNodeV2 {
    /**
     *
     * @param context
     */
    void apply(DynamicContextV2 context);
}
