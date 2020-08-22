package com.mybatisv3.builder;

import com.mybatisv3.config.Configuration;
import com.mybatisv3.factory.DefaultSqlSessionFactory;
import com.mybatisv3.factory.SqlSessionFactory;
import com.utils.DocumentUtils;
import org.dom4j.Document;

import java.io.InputStream;
import java.io.Reader;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) {
        // 获取Configuration对象（XMLConfigBuilder）
        Document document = DocumentUtils.getDocument(inputStream);

        XMLConfigBuilder configBuilder = new XMLConfigBuilder();
        Configuration configuration = configBuilder.parseConfiguration(document.getRootElement());
        // 创建SqlSessionFactory对象
        return build(configuration);
    }
    public SqlSessionFactory build(Reader reader) {
        return null;
    }

    private SqlSessionFactory build(Configuration configuration){
        return new DefaultSqlSessionFactory(configuration);
    }
}
