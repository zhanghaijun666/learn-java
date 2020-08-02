package com.mybatisv2;

import com.mybatisv1.MybatisV1;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class MybatisV2 {
    //配置信息
    private final Properties properties;
    private final BasicDataSource dataSource;

    private static MybatisV2 singletonInstance;

    //单例模式，获取对外的唯一实例
    public static MybatisV2 getSingletonInstance() throws Exception {
        if (singletonInstance == null) {
            synchronized (MybatisV1.class) {
                if (singletonInstance == null) {
                    singletonInstance = new MybatisV2();
                }
            }
        }
        return singletonInstance;
    }

    private MybatisV2() throws Exception {
        properties = Utils.loadDataSourceXML("mybatis-config.xml");
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(properties.getProperty("db.driver"));
        dataSource.setUrl(properties.getProperty("db.url"));
        dataSource.setUsername(properties.getProperty("db.username"));
        dataSource.setPassword(properties.getProperty("db.password"));
    }

    /*
     1.加载数据库驱动
     2.SQL语句
     3.预处理 statement
     4.SQL参数处理
     5.处理结果集
     */
    public <T> List<T> selectList(String statementId, Object param) throws Exception {
        List<T> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSql = null;
        try {
            connection = dataSource.getConnection();
            String sql = properties.getProperty("db.sql." + statementId);
            preparedStatement = connection.prepareStatement(sql);
            if (param instanceof Integer || param instanceof String) {
                preparedStatement.setObject(1, param);
            } else if (param instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) param;
                String columnnames = properties.getProperty("db.sql." + statementId + ".columnnames");
                String[] nameArray = columnnames.split(",");
                if (nameArray != null && nameArray.length > 0) {
                    for (int i = 0; i < nameArray.length; i++) {
                        String name = nameArray[i];
                        Object value = map.get(name);
                        // 给map集合中的参数赋值
                        preparedStatement.setObject(i + 1, value);
                    }
                }
            } else {
                throw new RuntimeException("参数异常");
            }
            resultSql = preparedStatement.executeQuery();
            String resultType = properties.getProperty("db.sql." + statementId + ".resulttype");
            Class<?> clazz = Class.forName(resultType);
            while (resultSql.next()) {
                //通过反射拿到实体类得实例
                Object obj = clazz.newInstance();
                ResultSetMetaData metaData = resultSql.getMetaData();
                //遍历结果字段
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnName(i + 1);//从1开始
                    try {
                        //表字段和实体类字段是一致得。
                        Field field = clazz.getDeclaredField(columnName);
                        //暴力破解，破坏封装，可以访问私有变量
                        field.setAccessible(true);
                        field.set(obj, resultSql.getObject(columnName));
                    } catch (NoSuchFieldException e) {
                        //标识在实体类中没有找到对应得字段
                    }
                }
                resultList.add((T) obj);
            }
            return resultList;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (resultSql != null) {
                    resultSql.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
            }
        }
    }


    private static Properties loadProperties(String location) throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = MybatisV1.class.getClassLoader().getResourceAsStream(location)) {
            properties.load(inputStream);
        }
        return properties;
    }

}
