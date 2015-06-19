<%--
  Desc: admin modal confirm
  User: wukong(wukonggg@139.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- admin modal confirm start -->
<div id="mz-modal-confirm" class="am-modal am-modal-confirm" tabindex="-1">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">MZ-G</div>
        <div class="am-modal-bd">
            <c:choose>
                <c:when test="${param.msg != null}">${param.msg}</c:when>
                <c:otherwise>确定要删除这条记录吗？</c:otherwise>
            </c:choose>
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-confirm>是</span>
            <span class="am-modal-btn" data-am-modal-cancel>否</span>
        </div>
    </div>
</div>

<!-- admin modal confirm end -->
<script src="${base}/assets/js/mess.modal.js"></script>

