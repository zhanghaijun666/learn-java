package com.mybatisv2.framework.sqlnode;


import com.mybatisv2.framework.config.DynamicContextV2;
import com.utils.OgnlUtils;

/**
 * 封装了带有if标签的动态标签
 */
public class IfSqlNodeV2 implements SqlNodeV2 {
    private String test;

    private SqlNodeV2 mixedSqlNode;

    public IfSqlNodeV2(String test, SqlNodeV2 mixedSqlNode) {
        this.test = test;
        this.mixedSqlNode = mixedSqlNode;
    }

    @Override
    public void apply(DynamicContextV2 context) {
        // 使用OGNL对test中的内容进行判断（test属性值写的就是ONGL表达式的语法）
        Object parameter = context.getBindings().get("_parameter");
        boolean b = OgnlUtils.evaluateBoolean(test, parameter);
        if (b){
            mixedSqlNode.apply(context);
        }
    }
}
