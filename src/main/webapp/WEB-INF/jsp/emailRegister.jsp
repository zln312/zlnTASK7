<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/23
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>

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

    <form id="sighupForm"  action="/register" method="post" onsubmit="formCheck()">
        用户名：
        <input type="text" name="username" required pattern="^[a-zA-Z0-9]{6,12}$" placeholder="英文或者数字,长度6到12位"
               style="width: 200px;height: 35px"><br>

        密码：
        <input type="password" name="password" required pattern="^[a-zA-Z0-9]{6,12}$" id="pwd1" placeholder="英文或者数字,长度6到12位"
               style="width: 200px;height: 35px"><br>
        重复密码：
        <input type="password" name="password1" required="required" id="pwd2" placeholder="英文或者数字,长度6到12位"
               style="width: 200px;height: 35px"><br><br>


        邮箱地址：
        <input id="email" type="text" name="postbox" required="required" style="width: 200px;height: 35px"><br>

        验证码： <input type="text" required="required" pattern="^[0-9]{6}$" name="code"/>
        <input class="button" type="submit" value="注册">
    </form>

    <input type="button" id="btn" value="免费获取验证码" onclick="settime(this)"/><br>

    <a href="/firstPage" class="btn">去首页</a>
    <a href="/login" class="btn">去登录</a>
    <a href="/phoneRegister" class="btn">去手机注册</a>

    <script type="text/javascript">
        function formCheck() {
            var pwd1 = document.getElementById("pwd1").value;
            var pwd2 = document.getElementById("pwd2").value;
            if (pwd1 != pwd2) {
                alert("两次输入的密码不一致！");

                return false;
            }
            return true;
        }
    </script>

    <script>
        $("#btn").click(function () {
            if (checkPhone()) {
                getCode();
                var wait = 60;//时间
                function time(o, p) {//o为按钮的对象，p为可选，这里是60秒过后，提示文字的改变
                    if (wait == 0) {
                        o.removeAttr("disabled");
                        o.val("获取验证码");//改变按钮中value的值
                        p.html("如果您在1分钟内没有收到验证码，请检查您填写的邮箱是否正确或重新发送");
                        wait = 60;
                    } else {
                        o.attr("disabled", true);//倒计时过程中禁止点击按钮
                        o.val("倒数" + wait + "秒");//改变按钮中value的值
                        wait--;
                        setTimeout(function () {
                                time(o, p);//循环调用
                            },
                            1000)
                    }
                }
            }


            var get_code = $("#btn");
            time(get_code);
        })

        function checkPhone() {
            var a = document.getElementById('email').value;
            if (!(/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/.test(a))) {
                alert("邮箱地址有误，请重填");
                return false;
            }
            return true
        }


        // 获取验证码
        function getCode() {
            var postbox = $("#email").val();

            var get_code_url = "/emailCode";
            $.ajax({
                type: "POST",
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                url: get_code_url,
                async: false,
                data: {"postbox": postbox},
                dataType: "json",

                // complete: function () { },

                success: function (data) {
                    console.debug(data);

                    if (data.num == '0') {
                        alert("该邮箱已被注册")
                    } else if (data.num == '失败') {
                        alert("发送验证码失败")
                    } else {
                        alert("已成功发送验证码")
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {

                }
            });
        }

        //验证码倒计时

    </script>

</div>
</html>