<%@ page import="band.wukong.mz.g.sku.bean.Goods" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%--
  Desc: gallery search page
  User: wukong(wukonggg@139.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Map<String, Object> retMap = (Map<String, Object>) request.getAttribute("obj");
    List<Goods> glist =  null != retMap.get("glist")? (List<Goods>) retMap.get("glist") : new ArrayList<Goods>(0);

    pageContext.setAttribute("kw", retMap.get("kw"));

%>

<html>
<head>
    <%@ include file="../base/head.jsp" %>
</head>
<body>
<%@ include file="../base/topbar.jsp" %>

<br>
<div class="am-g">
    <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
        <form action="${base}/gallery/search.io" class="am-form-inline" role="form" method="post">
            <input id="kw" name="kw" type="text" class="am-form-field am-input-sm" size="56"
                   value="${kw}" placeholder="输入货号、品名或关键词">
            <button type="submit" class="am-btn am-btn-primary">&nbsp;手气还行&nbsp;</button>
        </form>
    </div>
</div>

<div class="am-g">
    <div class="am-u-lg-10 am-u-md-8 am-u-sm-4 am-u-sm-centered">
<%
    for (Goods g : glist) {
        pageContext.setAttribute("g", g);
%>
        <div class="am-u-sm-3">
            <div class="am-thumbnail">
                <img src="${base}/gimg/${g.img}" alt="${g.gname}"/>
                <div class="am-thumbnail-caption">
                    <!-- TODO -OPT 自带的文字太大，无法调小。目前暂时先用非响应式的替代方案 -->
                    <span style="font-size: xx-small;font-weight: bold">${g.gname}</span>
                    <span style="font-size: xx-small">${g.gname}</span><br>
    <%
        String keywords = g.getKw();
        if (null != keywords) {
            String[] kws = keywords.split(" ");
            for (String k : kws) {
                pageContext.setAttribute("k", k);
    %>
                    <span class="am-badge am-badge-primary">${k}</span>
    <%
            }
        }
    %>
                </div>
            </div>
        </div>
<%
    }
%>
    </div>
</div>


<br><br>
<%@ include file="../base/copyright.jsp" %>
</body>
</html>
