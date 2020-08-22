package com.mybatis.test;

import com.model.User;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 解决硬编码问题（properties文件）
 * properties文件中的内容，最终会被【加载】到Properties集合中
 */
public class MybatisV1 {
    private Properties properties = new Properties();

    // 根据用户ID，查询用户信息
    @Test
    public void test(){
        // 加载properties文件中的内容
        loadProperties("jdbc.properties");

        List<User> users = selectList("queryUserById",1);
//        List<User> users = selectList("queryUserByName","千年老亚瑟");

        /*Map<String,Object> param = new HashMap<>();
        param.put("username","千年老亚瑟");
        param.put("sex","男");
        List<User> users = selectList("queryUserByParams",param);*/
        System.out.println(users);
    }

    private void loadProperties(String location) {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(location);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // TODO 释放IO流
        }
    }

    /**
     * 抽取一个通用的查询方法
     * @param param 只能传递一个参数对象
     * @return
     */
    private <T> List<T> selectList(String statementId,Object param) {
        List<T> results = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            // 解决连接获取时的硬编码问题和频繁连接的问题
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(properties.getProperty("db.driver"));
            dataSource.setUrl(properties.getProperty("db.url"));
            dataSource.setUsername(properties.getProperty("db.username"));
            dataSource.setPassword(properties.getProperty("db.password"));

            connection = dataSource.getConnection();

            // 定义sql语句 ?表示占位符
            // String sql = "select * from user where id = ?";
            String sql = properties.getProperty("db.sql."+statementId);

            // 获取预处理 statement
            preparedStatement = connection.prepareStatement(sql);

            // 设置参数，第一个参数为 sql 语句中参数的序号（从 1 开始），第二个参数为设置的
            // preparedStatement.setObject(1, param);
            // 如果入参是简单类型，那么我们不关心参数名称
            if(param instanceof Integer || param instanceof String){
                preparedStatement.setObject(1,param);
            }else if (param instanceof Map){
                Map<String ,Object> map = (Map<String, Object>) param;

                String columnnames = properties.getProperty("db.sql." + statementId + ".columnnames");
                String[] nameArray = columnnames.split(",");
                if (nameArray != null && nameArray.length > 0){
                    for (int i = 0 ; i<nameArray.length;i++) {
                        String name = nameArray[i];
                        Object value = map.get(name);
                        // 给map集合中的参数赋值
                        preparedStatement.setObject(i+1,value);
                    }
                }
                // map集合中的key和要映射的参数名称要一致
            }else {
                //TODO
            }

            // 向数据库发出 sql 执行查询，查询出结果集
            rs = preparedStatement.executeQuery();

            // 遍历查询结果集
            String resultType = properties.getProperty("db.sql." + statementId + ".resulttype");
            Class<?> clazz = Class.forName(resultType);
            /*this.getClass()
            MybatisV1.class*/

            // 一般都是通过构造器去创建对象
            Constructor<?> constructor = clazz.getDeclaredConstructor();

            Object result = null;
            while (rs.next()) {
                // result = clazz.newInstance();

                result = constructor.newInstance();

                // 获取结果集中列的信息
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i + 1);

                    // 通过反射给指点列对应的属性名称赋值
                    // 列名和属性名要一致
                    Field field = clazz.getDeclaredField(columnName);
                    // 暴力破解，破坏封装，可以访问私有成员
                    field.setAccessible(true);
                    field.set(result,rs.getObject(columnName));
                }
                results.add((T) result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return results;
    }
}
