package com.mybatisv2;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class Utils {

    //读取xml中mapper信息
    protected static void loadMapperXml(String location) {
        try (InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream(location)) {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            List<Element> mapperList = rootElement.element("mappers").elements("mapper");
            for (Element element : mapperList) {
                System.out.println(element.attributeValue("resource"));
            }
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    //读取xml中数据配置信息
    protected static Properties loadDataSourceXML(String location) throws IOException, DocumentException {
        // 获取全局配置文件对应的流对象
        try (InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream(location)) {
            // 获取Document对象
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            Element environments = rootElement.element("environments");
            String attribute = environments.attributeValue("default");
            List<Element> elements = environments.elements("environment");
            if (null == elements || elements.isEmpty()) {
                throw new RuntimeException("无法找到environment节点,无法获取到数据配置信息");
            }
            Optional<Element> optional = elements.stream().filter(element -> null == attribute || attribute.equals(element.attributeValue("id"))).findFirst();
            Element dataSource = (optional.isPresent() ? optional.get() : elements.get(0)).element("dataSource");
            switch (dataSource.attributeValue("type")) {
                case "DBCP":
                default:
                    List<Element> sourceElement = dataSource.elements("property");
                    Properties properties = new Properties();
                    for (Element source : sourceElement) {
                        properties.put(source.attributeValue("name"), source.attributeValue("value"));
                    }
                    return properties;
            }
        }
    }
}
