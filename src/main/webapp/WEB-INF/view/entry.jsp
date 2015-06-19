<%--
  Desc:
  User: wukong(wukonggg@139.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="base/head.jsp" %>
</head>
<body>
<br>
<%@ include file="base/title.jsp" %>

<div class="am-g">
    <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
        <form method="post" class="am-form" action="${base}/login.io">
            <label for="loginName">账号:</label>
            <input type="text" name="loginName" id="loginName" value="Yolanda">
            <br>
            <label for="pwd">密码:</label>
            <input type="password" name="pwd" id="pwd" value="iwukong">
            <br>
            <div class="am-cf">
                <input type="submit" name="" value="登 录"
                       class="am-btn am-btn-primary am-btn-sm am-fl">
                <input type="submit" name="" value="忘记密码 ^_^? "
                       class="am-btn am-btn-default am-btn-sm am-fr">
            </div>
        </form>
    </div>
</div>


<%@ include file="base/copyright.jsp" %>

</body>
</html>
