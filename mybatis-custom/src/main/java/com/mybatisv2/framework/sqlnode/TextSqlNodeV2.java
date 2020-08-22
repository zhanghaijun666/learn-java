package com.mybatisv2.framework.sqlnode;


import com.mybatisv2.framework.config.DynamicContextV2;
import com.utils.GenericTokenParser;
import com.utils.OgnlUtils;
import com.utils.SimpleTypeRegistry;
import com.utils.TokenHandler;

/**
 * 封装了带有${}的 SQL文本
 */
public class TextSqlNodeV2 implements SqlNodeV2 {
    private String sqlText;

    public TextSqlNodeV2(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContextV2 context) {
        // 处理${}
        GenericTokenParser tokenParser = new GenericTokenParser("${","}",new BindingTokenHandler(context));
        String sql = tokenParser.parse(sqlText);
        context.appendSql(sql);
    }

    public boolean isDynamic() {
        return sqlText.indexOf("${") > -1;
    }

    /**
     * 处理${}中的内容
     */
    class BindingTokenHandler implements TokenHandler {

        // 为了获取入参对象
        private DynamicContextV2 context;

        public BindingTokenHandler(DynamicContextV2 context) {
            this.context = context;
        }

        /**
         *
         * @param content 这就是${}中的参数名称
         * @return
         */
        @Override
        public String handleToken(String content) {
            Object parameter = context.getBindings().get("_parameter");
            if (SimpleTypeRegistry.isSimpleType(parameter.getClass())){
                return parameter.toString();
            }
            // 使用ONGL表达式获取值
            Object value = OgnlUtils.getValue(content, parameter);
            return value == null ? "": value.toString();
        }
    }
}
