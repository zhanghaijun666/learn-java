package com.jdbc;

import java.sql.*;

/**
 * 原生的JDBC代码存在几个问题：
 * 1.存在硬编码问题（获取连接、执行statement）
 * 2.连接频繁创建和销毁会浪费资源
 */
public class JDBCDemo {

  public static void main(String[] args) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet result = null;
    try {
      // 1.加载数据库驱动
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/learn?useSSL=false", "root", "123456");

      // 2.SQL语句
      String sql = "SELECT * FROM user where id = ? ";

      // 3.预处理 statement
      preparedStatement = connection.prepareStatement(sql);

      // 4.SQL参数处理
      preparedStatement.setInt(1, 1);
      result = preparedStatement.executeQuery();
      if (null != result) {
        // 5.处理结果集
        while (result.next()) {
          System.out.println("------------------");
          System.out.println(result.getInt("id"));
          System.out.println(result.getString("name"));
          System.out.println(result.getString("nickname"));
          System.out.println(result.getString("email"));
          System.out.println(result.getString("phone"));
        }
      }
      System.out.println();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (result != null) {
          result.close();
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
}
