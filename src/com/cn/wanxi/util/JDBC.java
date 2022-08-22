package com.cn.wanxi.util;

import java.sql.*;

/**
 * 1.加载驱动
 * 2.得到连接
 * 3.编译sql
 * 4.执行sql
 * 5.得到结果集
 * 6.关闭连接
 */


public class JDBC {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;

    private static Connection getConnection() {
        String url = "JDBC:mysql://localhost:3306/clothes?serverTimezone=UTC";
        String user = "root";
        String password = "9909";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static int update(String sql) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return -1;
    }

    public static ResultSet query(String sql) {
        try {
            //        编译sql语句
            preparedStatement = getConnection().prepareStatement(sql);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getCount(String sql) {

        ResultSet resultSet = JDBC.query(sql);
        try {
//            resultSet.next()判断resultSet是否由下一条数据
            while (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new JDBC().getConnection());
    }

    public static void close() {
        if (connection != null) {
            try {
                connection.close();
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
}

