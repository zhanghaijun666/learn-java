package com.mybatis;

import com.model.User;
import com.mybatisv2.framework.config.BoundSql;
import com.mybatisv2.framework.config.Configuration;
import com.mybatisv2.framework.config.MappedStatement;
import com.mybatisv2.framework.sqlsource.SqlSource;
import com.mybatisv2.utils.LoadXmlUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class MyBatisV2Test {

    private Configuration configuration = new Configuration();
    private String namespace;
    private boolean isDynamic = false;

    @Test
    public void test() throws Exception {
        // 加载XML文件（全局配置文件和映射文件）
        loadXML("mybatis-config.xml");

        // 执行查询
        Map<String, Object> param = new HashMap<>();
        param.put("username", "千年老亚瑟");
        param.put("sex", "男");
        List<User> users = selectList("test.queryUserByParams", param);
        System.out.println(users);
    }

    private void loadXML(String local) throws IOException, DocumentException, ClassNotFoundException {
        //数据库链接信息
        Properties properties = LoadXmlUtils.loadDataSourceXML(local);
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(properties.getProperty("db.driver"));
        dataSource.setUrl(properties.getProperty("db.url"));
        dataSource.setUsername(properties.getProperty("db.username"));
        dataSource.setPassword(properties.getProperty("db.password"));
        configuration.setDataSource(dataSource);
        //mapper映射文件
        List<String> mapperList = LoadXmlUtils.loadMapperXml(local);
        Iterator<String> iterator = mapperList.iterator();
        while (iterator.hasNext()) {
            try (InputStream inputStream = LoadXmlUtils.getResourceAsStream(iterator.next())) {
                Document document = new SAXReader().read(inputStream);
                Element rootElement = document.getRootElement();
                String space = rootElement.attributeValue("namespace");
                //动态获取sql
                List<Element> select = rootElement.elements("select");
                for (Element selectElement : select) {
                    String statementId = selectElement.attributeValue("id");
                    if (statementId == null || statementId.equals("")) {
                        continue;
                    }
                    // 一个CURD标签对应一个MappedStatement对象
                    // 一个MappedStatement对象由一个statementId来标识，所以保证唯一性
                    // statementId = namespace + "." + CRUD标签的id属性
                    statementId = namespace + "." + statementId;

                    // 注意：parameterType参数可以不设置也可以不解析
                    String parameterType = selectElement.attributeValue("parameterType");
                    Class<?> parameterClass = Class.forName(parameterType);

                    String resultType = selectElement.attributeValue("resultType");
                    Class<?> resultClass = Class.forName(resultType);

                    String statementType = selectElement.attributeValue("statementType");
                    statementType = statementType == null || statementType == "" ? "prepared" : statementType;
                    //SqlSource和SqlNode的封装过程
                    SqlSource sqlSource = LoadXmlUtils.createSqlSource(selectElement);
                    //建议使用构建者模式去优化
                    MappedStatement mappedStatement = new MappedStatement(statementId, resultClass, statementType, sqlSource);
                    configuration.addMappedStatement(statementId, mappedStatement);
                }
            }
        }
    }

    private List<User> selectList(String statementId, Map<String, Object> param) throws SQLException {
        Statement statement = null;
        ResultSet rs = null;
        // 获取statement相关的信息MappedStatement
        MappedStatement mappedStatement = configuration.getMappedStatementById(statementId);
        // 连接的获取
        try (Connection connection = configuration.getDataSource().getConnection()) {
            // TODO SQL的获取(SqlSource和SqlNode的处理流程)
            SqlSource sqlSource = mappedStatement.getSqlSource();
            // 触发SqlSource和SqlNode的解析处理流程
            BoundSql boundSql = sqlSource.getBoundSql(param);
            String sql = boundSql.getSql();
            // 创建statement
            statement = createStatement(mappedStatement, sql, connection);
            // TODO 设置参数（使用的是BoundSql中的参数集合）
            setParameters(param, statement, boundSql);
            // 执行statement
            rs = executeQuery(statement);
        } finally {

        }


        return (List<User>) Collections.EMPTY_MAP;
    }


}
