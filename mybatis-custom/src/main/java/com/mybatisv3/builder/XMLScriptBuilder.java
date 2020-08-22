package com.mybatisv3.builder;

import com.mybatisv3.sqlnode.*;
import com.mybatisv3.sqlsource.DynamicSqlSource;
import com.mybatisv3.sqlsource.RawSqlSource;
import com.mybatisv3.sqlsource.SqlSource;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * 专门用来解析sqlnode信息
 */
public class XMLScriptBuilder {

    private boolean isDynamic = false;

    public SqlSource parseScriptNode(Element selectElement) {
        //解析所有SQL节点，最终封装到MixedSqlNode中
        SqlNode mixedSqlNode = parseDynamicTags(selectElement);

        SqlSource sqlSource;
        //如果带有${}或者动态SQL标签
        if (isDynamic){
            sqlSource = new DynamicSqlSource(mixedSqlNode);
        }else{
            sqlSource = new RawSqlSource(mixedSqlNode);
        }
        return sqlSource;
    }


    private SqlNode parseDynamicTags(Element selectElement) {
        List<SqlNode> sqlNodes = new ArrayList<>();

        //获取select标签的子元素 ：文本类型或者Element类型
        int nodeCount = selectElement.nodeCount();
        for (int i = 0; i < nodeCount; i++) {
            Node node = selectElement.node(i);
            if (node instanceof Text){
                String text = node.getText();
                if (text==null){
                    continue;
                }
                if ("".equals(text.trim())){
                    continue;
                }
                // 先将sql文本封装到TextSqlNode中
                TextSqlNode textSqlNode = new TextSqlNode(text.trim());
                if (textSqlNode.isDynamic()){
                    sqlNodes.add(textSqlNode);
                    isDynamic = true;
                }else{
                    sqlNodes.add(new StaticTextSqlNode(text.trim()));
                }
            }else if (node instanceof Element){
                isDynamic = true;
                Element element  = (Element) node;
                String name = element.getName();

                if ("if".equals(name)){
                    String test = element.attributeValue("test");
                    //递归去解析子元素
                    SqlNode sqlNode = parseDynamicTags(element);
                    IfSqlNode ifSqlNode = new IfSqlNode(test,sqlNode);
                    sqlNodes.add(ifSqlNode);
                }else{
                    // TODO
                }
            }else{
                //TODO
            }
        }
        return new MixedSqlNode(sqlNodes);
    }
}
