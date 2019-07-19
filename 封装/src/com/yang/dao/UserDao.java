package com.yang.dao;

import com.yang.entity.UserEn;
import com.yang.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public UserEn userLogin(String username, String password){
        UserEn userEn = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getconn();
            String sql = "select * from user where username=? and password=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                userEn = new UserEn();
                userEn.setUserName(resultSet.getString("username"));
                userEn.setPassWord(resultSet.getString("password"));
                System.out.println("登录成功");
            }else{
                System.out.println("用户名或密码错误");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JdbcUtils.close(preparedStatement,connection);
        }
        return userEn;
    }

    public void userReg(UserEn userEn){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getconn();
            String sql = "insert into user (id,username,password)values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userEn.getId());
            preparedStatement.setString(2, userEn.getUserName());
            preparedStatement.setString(3, userEn.getPassWord());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            JdbcUtils.close(preparedStatement,connection);
        }
    }
}
