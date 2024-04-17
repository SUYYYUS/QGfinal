package com.suyyyus.utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    //设置变量
    private static String url;
    private static String username;
    private static String password;
    private static String driver;
    //只用读取一次，就使用静态代码块
    static {
        Properties properties = new Properties();
        try {
            //1.读取配置文件并加载
            properties.load(new FileReader("C:\\Users\\28937\\Desktop\\QG\\QGfinal\\项目\\QGfinal_test\\src\\main\\java\\jdbc.properties"));
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("配置文件相关操作成功");
        }
    }

    //获取连接
    public static Connection getConnection() throws Exception{
        Class.forName(driver);
        Connection connection = null;
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    //释放资源
    public static void close(Connection connection, PreparedStatement preparedStatement)  {
        try {
            if(connection != null){
                connection.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("释放失败");
        }
    }

    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet)  {
        try {
            if(connection != null){
                connection.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("释放失败");
        }
    }
}
