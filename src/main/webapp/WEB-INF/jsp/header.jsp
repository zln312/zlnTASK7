<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>header</title>
</head>
<body>
<head>


    <!-- 头部 -->
    <header>
        <div class="top w">
            <div class="num">客服热线：010-594-78634</div>
            <div class="logos">

                <c:choose>
                    <c:when test="${cookie.name.value==null}">
                        <a href="/login" class="btn">登录</a>|
                        <a href="/phoneRegister" class="btn">注册</a>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${cookie.picture.value.equals('')}">
                                <a><img src="/images/kang.jpg"
                                        height="50" width="50"></a>
                            </c:when>
                            <c:otherwise>
                                <img src="${cookie.picture.value}">
                            </c:otherwise>
                        </c:choose>
                        <a href="/headShot">${cookie.name.value}</a>|
                        <a href="/logout" class="btn">退出</a>
                    </c:otherwise>
                </c:choose>

                <img src="/images/wx.png" alt="">
                <img src="/images/qq.png" alt="">
                <img src="/images/xl.jpg" alt="">
            </div>
        </div>
        <div class="top1">

            登陆&nbsp<span>|</span>&nbsp注册
        </div>
        <nav>
            <ul class=" nav1 w">
                <img src="/images/logo.png" alt="">
                <li><a href="/firstPage">首页</a></li>
                <li><a href="/u/thirdPage">职业</a></li>
                <li><a href="/secondPage">推荐</a></li>
                <li><a href="">关于</a></li>
            </ul>
            <div class="dropdown">
                <img class="ji" src="/images/logo.png" alt="">
                <button class="btn dropdown-toggle clearfix" type="button" id="dropdownMenu1" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="true">
                    <span>
                        <img src="/images/btn1.png" alt="">
                    </span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <li><a href="task-91.html">首页</a></li>
                    <li><a href="task-93.html">职业</a></li>
                    <li><a href="task-92.html">推荐</a></li>
                    <li><a href="#">关于</a></li>
                </ul>
            </div>
        </nav>
    </header>

</body>
</html>
