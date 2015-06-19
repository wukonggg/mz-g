<%@ page import="band.wukong.mz.g.privilege.bean.User" %>
<%--
  Desc: topbar of frontend
  User: wukong(wukonggg@139.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    User u = (User) session.getAttribute("me");
    pageContext.setAttribute("u", u);
%>

<html>
<head>
    <style>
        .mz-topbar {
            height: 35px;
            width:100%;
            /*background-color: #009cda;*/
        }
        .mz-topbar .weather {
            float: left;
            height: 35px;
            /*background-color: #d2620b;*/
        }
        .mz-topbar .links {
            float: right;
            height: 35px;
            /*background-color: #d2620b;*/
        }
        .mz-topbar .links a {
            /* 当使用嵌入jsp时，如果不加display将内联元素转换成块元素，padding-top就会无效*/
            /*display: block;*/
            color: #000000;
            font-size: 12px;
            line-height: 35px;
            cursor: pointer;
            padding: 0 10px 0 10px;
            /*background-color: #5eb95e; */
        }
    </style>
</head>
<body>
<div class="mz-topbar">
<div class="weather">
    <img src="<%=request.getContextPath()%>/assets/i/t/weather.png" style="margin:5px; ">
</div>
<div class="links">
<%
    if (null == u) {
%>
    <a href="<%=request.getContextPath()%>/entry.io">登&nbsp;录</a>
<%
    } else {
%>
    <a href="${base}/main.io">${u.loginName}</a>
    <a href="<%=request.getContextPath()%>/logout.io">退&nbsp;出</a>
<%
    }
%>

</div>
</div>

</body>
</html>
