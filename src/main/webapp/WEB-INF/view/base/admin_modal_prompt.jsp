<%--
  Desc: admin modal prompt
  User: wukong(wukonggg@139.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- admin modal prompt start -->
<div id="mz-modal-prompt" class="am-modal am-modal-prompt" tabindex="-1">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">MZ-G</div>
        <div class="am-modal-bd">
            <c:choose>
                <c:when test="${param.msg != null}">${param.msg}</c:when>
                <c:otherwise>确定要删除这条记录吗？</c:otherwise>
            </c:choose>
            <input id="mz-modal-prompt-input" name="${param.inputName}" type="text" class="am-modal-prompt-input">
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-confirm>是</span>
            <span class="am-modal-btn" data-am-modal-cancel>否</span>
        </div>
    </div>
</div>
<!-- admin modal prompt end -->
<script src="${base}/assets/js/mess.modal.js"></script>

