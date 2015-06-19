<%--
  Desc: admin breadcrumb
  User: wukong(wukonggg@139.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="am-cf" style="height: 60px;">
    <ol class="am-breadcrumb">
        <li class="breadcrumb am-text-lg"><a href="#">&nbsp;&nbsp;&nbsp;${param.info1}&nbsp;</a></li>
        <li class="breadcrumb">${param.info2}</li>
        <c:if test="${param.info3 != null}">
            <li class="breadcrumb">${param.info3}</li>
            <c:if test="${param.info4 != null}">
                <li class="breadcrumb">${param.info3}</li>
            </c:if>
        </c:if>
    </ol>
</div>

<script>
    function mz_breadcrumb_load() {
        $(".breadcrumb:last").addClass("am-active");
    }
</script>
<%--
<div class="am-cf am-padding">
    <div class="am-fl am-cf">
        <small class="am-text-primary" style="font-size: 1.5rem !important;">${param.info1}</small>&nbsp;
        /&nbsp; <small>${param.info2}</small>
    <c:if test="${param.info3 != null}">
        / <small>${param.info3}</small>
        <c:if test="${param.info4 != null}">
        / <small>${param.info4}</small>
        </c:if>
    </c:if>
    </div>
</div>
--%>
