package com.yang.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {

    private static String url = null;
    private static String userName = null;
    private static String passWord = null;
    private static String driver = null;

    static{
        Properties prop = new Properties();
        InputStream in = JdbcUtils.class.getResourceAsStream("/config");
        try {
            prop.load(in);
            url = prop.getProperty("url");
            userName = prop.getProperty("userName");
            passWord = prop.getProperty("passWord");
            driver = prop.getProperty("driver");
            //注册驱动
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getconn() throws SQLException {
        Connection connection = DriverManager.getConnection(url,userName,passWord);
        return connection;
    }

    public static void close(Statement statement, Connection connection){

        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
