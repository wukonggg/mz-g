<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%--
  Desc: goods list page
  User: wukong(wukonggg@139.com)
--%>

<!doctype html>
<html class="no-js">
<head>
    <%@ include file="base/head.jsp" %>
</head>
<body>
<%@ include file="base/b_ie_lte_ie9.jsp" %>

<%@ include file="base/admin_topbar.jsp" %>

<div class="am-cf admin-main">
    <!-- sidebar start -->
    <%@ include file="base/admin_sidebar.jsp" %>
    <!-- sidebar end -->

    <!-- content start -->
    <div class="admin-content">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">首页</strong></div>
        </div>
        <ul class="am-avg-sm-1 am-avg-md-4 am-margin am-padding am-text-center admin-content-list ">
            <li><a href="${base}/milkpowder/calc.io" class="am-text-secondary"><span class="am-icon-btn am-icon-pencil-square-o"></span><br/>奶粉计算器<br/>妈妈再也不用担心我的数学了</a></li>
            <li><a href="#" class="am-text-success"><span class="am-icon-btn am-icon-file-text"></span><br/>新增页面<br/>2300</a></li>
            <li><a href="#" class="am-text-warning"><span class="am-icon-btn am-icon-briefcase"></span><br/>成交订单<br/>308</a></li>
            <li><a href="#" class="am-text-danger"><span class="am-icon-btn am-icon-recycle"></span><br/>昨日访问<br/>80082</a></li>
        </ul>
        <br><br><br><br><br><br><br><br><br>
    </div>
    <!-- content end -->
</div>

<script src="${base}/component/jquery/2.1.3/jquery.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/dist/js/amazeui.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/assets/js/app.js"></script>
</body>
</html>
