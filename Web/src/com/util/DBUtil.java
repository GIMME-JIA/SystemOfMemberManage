package com.util;

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtil {

    /*
     * 设置数据库连接变量，获取resourses.properties
     * 驱动driver，url，user，password
     * 其变量均为私有且静态，防止外界访问及在类加载的同时先加载静态变量
     *
     */
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("resourses.jdbc");
    // 读取驱动
    private static String driver = resourceBundle.getString("driver");
    // 读取数据库地址
    private static String url = resourceBundle.getString("url");
    // 读取数据库用户名
    private static String user = resourceBundle.getString("user");
    // 读取密码
    private static String password = resourceBundle.getString("password");

    // 因为注册驱动只需注册一次，因此放在静态代码块中类加载时直接执行
    /*
    com.mysql.jdbc.Driver是连接数据库的驱动，不能写死，
    因为以后可能还会用其他数据库，为了保证代码的复用性，且不能违背ocp原则
     */
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 调用方法获取数据库连接对象
     * 传入参数：无
     * 返回值：Connection对象
     *
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 调用方法释放资源：最先开的最后关
     * <p>
     * 传入参数：连接对象connection,
     * 预编译对象preparedStatement,
     * 结果集对象resultset
     * 返回值：无
     *
     * @param connection
     * @param preparedStatement
     * @param resultSet
     */
    public static void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
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
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
}
