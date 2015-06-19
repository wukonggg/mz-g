<%--
  Desc:
  User: wukong(wukonggg@139.com)
--%>
<%@ page import="band.wukong.mz.base.exception.AppRuntimeException" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
    Object o = request.getAttribute("obj");
    String msg = "不知道为啥，感觉整个人都不好了。。。";
    String href = request.getContextPath();
    if (o instanceof AppRuntimeException) {
        AppRuntimeException ae = (AppRuntimeException) o;
        msg = ae.getMessage();
        if (null != ae.getUrl()) {
            href = ae.getUrl();
        }
    }
%>

<html>
<head>
    <%@ include file="head.jsp" %>
    <style>
        body {
            background-image:url("${base}/component/raw/wc/error/boneman/bg-tile.jpg");
            margin:auto 0;
        }
        .mz-error-main {
            background-image:url("${base}/component/raw/wc/error/boneman/PageNotFound.png");
            margin:0 auto;
            width:1208px;
            height:600px;
        }
        .mz-error-msg {
            font-size: 20px;
            font-weight: 900;

        }
        #button {
            background-image: url("${base}/component/raw/wc/error/boneman/PageNotFoundButton.png");
            width: 185px;
            height: 34px;
            float: left;
            cursor: pointer;
            margin-left: 520px;
            margin-top: 465px
        }

        #button:hover {
            background-image: url("${base}/component/raw/wc/error/boneman/PageNotFoundButton-over.png");
            width: 185px;
            height: 34px;
            float: left;
            cursor: pointer;
            margin-left: 520px;
            margin-top: 465px
        }
    </style>
</head>

<body>
<%-- class="div-error-main"不能改，mess.error.js会用到--%>
<div class="mz-error-main">
    <br><br><br>
    <div class="am-g">
        <div class="am-u-sm-4 am-u-sm-centered mz-error-msg"><%=msg%></div>
    </div>
    <a href="<%=href%>"><div id="button"></div></a>
</div>


<!--
<%
    if (o instanceof AppRuntimeException) {
        AppRuntimeException ae = (AppRuntimeException) o;
        out.println(ae.getMessage());

    } else if (o instanceof RuntimeException) {
        RuntimeException re = (RuntimeException) o;
        out.println(re.getMessage());

    } else if (o instanceof Exception) {
        Exception re = (Exception) o;
        out.println(re.getMessage());
    }
%>
-->
</body>
</html>
