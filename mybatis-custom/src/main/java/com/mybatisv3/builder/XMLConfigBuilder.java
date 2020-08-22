package com.mybatisv3.builder;

import com.mybatisv3.config.Configuration;
import com.mybatisv3.io.Resources;
import com.utils.DocumentUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * 该类就是用来解析全局配置文件的
 */
public class XMLConfigBuilder {
    private Configuration configuration;

    public XMLConfigBuilder() {
        configuration = new Configuration();
    }

    public Configuration parseConfiguration(Element rootElement) {
        Element environments = rootElement.element("environments");
        parseEnvironments(environments);
        Element mappers = rootElement.element("mappers");
        parseMappers(mappers);

        return configuration;
    }

    /**
     * 解析全局配置文件中的mappers标签
     * @param mappers <mappers></mappers>
     */
    private void parseMappers(Element mappers) {
        List<Element> list = mappers.elements("mapper");
        for (Element element : list) {
            String resource = element.attributeValue("resource");
            // 根据xml的路径，获取对应的输入流
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 将流对象，转换成Document对象
            Document document = DocumentUtils.getDocument(inputStream);
            // 针对Document对象，按照Mybatis的语义去解析Document
            XMLMapperBuilder mapperBuilder = new XMLMapperBuilder(configuration);
            mapperBuilder.parseMapper(document.getRootElement());
        }
    }

    private void parseEnvironments(Element environments) {
        String aDefault = environments.attributeValue("default");
        List<Element> elements = environments.elements("environment");
        for (Element element : elements) {
            String id = element.attributeValue("id");
            if (aDefault.equals(id)){
                parseDataSource(element.element("dataSource"));
            }
        }
    }

    private void parseDataSource(Element dataSource) {
        String type = dataSource.attributeValue("type");
        if (type.equals("DBCP")){
            BasicDataSource ds = new BasicDataSource();
            Properties properties = parseProperties(dataSource);
            ds.setDriverClassName(properties.getProperty("db.driver"));
            ds.setUrl(properties.getProperty("db.url"));
            ds.setUsername(properties.getProperty("db.username"));
            ds.setPassword(properties.getProperty("db.password"));

            configuration.setDataSource(ds);
        }
    }

    private Properties parseProperties(Element dataSource) {
        Properties properties = new Properties();
        List<Element> list = dataSource.elements("property");
        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.put(name,value);
        }
        return properties;
    }
}
