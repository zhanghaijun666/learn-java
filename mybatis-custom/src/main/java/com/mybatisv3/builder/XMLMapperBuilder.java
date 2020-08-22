package com.mybatisv3.builder;

import com.mybatisv3.config.Configuration;
import org.dom4j.Element;

import java.util.List;

/**
 * 专门解析映射文件
 */
public class XMLMapperBuilder {
    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parseMapper(Element rootElement) {
        // statementid是由namespace+statement标签的id值组成的。
        String namespace = rootElement.attributeValue("namespace");
        // TODO 获取动态SQL标签，比如<sql>
        // TODO 获取其他标签
        List<Element> selectElements = rootElement.elements("select");
        for (Element selectElement : selectElements) {
            XMLStatementBuilder statementBuilder = new XMLStatementBuilder(configuration);
            statementBuilder.parseStatementElement(selectElement,namespace);
        }
    }
}
