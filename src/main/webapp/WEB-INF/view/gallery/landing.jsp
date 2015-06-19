<%--
  Desc: gallery landing page
  User: wukong(wukonggg@139.com)
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <%@ include file="../base/head.jsp" %>
</head>
<body>
<%@ include file="../base/topbar.jsp" %>


<br>
<%@ include file="../base/title.jsp" %>

<div class="am-g">
    <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
        <!-- TODO -BUG 现在amazeui有bug，已经在github上提交issue。目前暂时先用非响应式的替代方案
            <form method="post">
              <div class="am-input-group">
                <input type="text" class="am-form-field" placeholder="输入货号或产品关键词">
                <span class="am-input-group-btn">
                  <button class="am-btn am-btn-primary" type="button">&nbsp;手气还行&nbsp;</button>
                </span>
              </div>
            </form>
        -->
        <form action="${base}/gallery/search.io" class="am-form-inline" role="form" method="post">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input id="kw" name="kw" type="text" class="am-form-field am-input-sm" size="56" placeholder="输入货号、品名或关键词">
            <button type="submit" class="am-btn am-btn-primary">&nbsp;手气还行&nbsp;</button>
        </form>
    </div>
</div>

<br><br>
<%@ include file="../base/copyright.jsp" %>


</body>
</html>
