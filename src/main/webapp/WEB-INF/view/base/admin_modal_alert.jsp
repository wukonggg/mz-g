<%--
  Desc: admin alert
  User: wukong(wukonggg@139.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!-- alert modal start -->
<div class="am-modal am-modal-alert" tabindex="-1" id="mz-alert">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">MZ-G</div>
        <div class="am-modal-bd" id="mess-model-alert-msg"></div>
        <div class="am-modal-footer">
            <span class="am-modal-btn">好的</span>
        </div>
    </div>
</div>
<!-- alert modal end -->
<script>
    window.mess = window.mess || {};
    window.mess.modal = window.mess.modal || {};
    window.mess.modal.alert = function(msg) {
        $('#mess-model-alert-msg').text(msg);
        $('#mz-alert').modal();
    };
</script>
