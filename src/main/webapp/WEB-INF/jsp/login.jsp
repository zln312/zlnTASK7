<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/22
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文档的标题</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;">
    <meta name="format-detection" content="telephone=no">
    <title>首页</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/base.css">
    <link rel="stylesheet" href="../css/task-91.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<div style="width:100%;text-align:center">


<form action="/login" method="POST" id="log"  >

    <input type="text" name="username" required pattern="^[a-zA-Z0-9]{6,12}$" placeholder="请输入用户名" style="width: 200px;height: 35px"><br>

    <br>
    <input type="password" name="password" required pattern="^[a-zA-Z0-9]{6,12}$" placeholder="请输入密码" style="width: 200px;height: 35px">
    <br><br>

    <input class="button" type="submit" value="登录">
    <a href="/phoneRegister" class="btn">去注册</a>
    <a href="/firstPage" class="btn">去首页</a>

</form>

</div>
<script>
    var userName=document.getElementById("userName");
    userName.onblur=function(){
        if(userName.validity.patternMismatch){
            userName.setCustomValidity("用户名只能是英文或者数字,长度6到12位");
        }else if(userName.validity.valueMissing){//valueMissing目的：确保表单控件中的值已填写 用法：在表单控件中将required特性设置为true。
            userName.setCustomValidity("用户名不能为空");
        }else{
            userName.setCustomValidity("");//现将有输入时的提示设置为空
        }
    }
</script>
</html>
