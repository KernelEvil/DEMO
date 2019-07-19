package com.yang.service;

import com.yang.entity.UserEn;
import com.yang.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String userName = req.getParameter("username");
        String passWord = req.getParameter("password");
        UserDao userDao = new UserDao();
        UserEn userEn = userDao.userLogin(userName,passWord);
        if(userEn != null){
            req.getRequestDispatcher("success.jsp").forward(req,resp);
        }
        else{
            req.getRequestDispatcher("default.jsp").forward(req,resp);
        }
    }
}
