<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
    <style type="text/css">
        body{
            background-position: center;
            background-repeat: no-repeat;
        }
    </style>
</head>
<body>
<div>
    <form action="/LoginServlet" method="post">
        <table style="margin-left:40%">
            <marquee width="200" scrolldelay="250">用户登录</marquee>
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="username" size="21"/></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="text" name="password" size="21"/></td>
            </tr>
        </table>
        <input type="submit" value="登录"/>
        <input type="reset" value="重置"/>
    </form>
    <br>
    <a href="reg.jsp">注册</a>
</div>

</body>
</html>
