<%--
  Desc: cust add
  User: wukong(wukonggg@139.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="../base/head.jsp" %>
    <link rel="stylesheet" href="${base}/assets/css/fixed.css">
</head>
<body>
<%@ include file="../base/b_ie_lte_ie9.jsp" %>

<%@ include file="../base/admin_topbar.jsp" %>


<div class="am-cf admin-main">
    <!-- sidebar start -->
    <%@ include file="../base/admin_sidebar.jsp" %>
    <!-- sidebar end -->

    <!-- content start -->
    <div class="admin-content">
        <jsp:include page="../base/admin_breadcrumb.jsp">
            <jsp:param name="info1" value="Customer"/>
            <jsp:param name="info2" value="粑粑麻麻"/>
        </jsp:include>

        <form id="fmMain" name="fmMain" class="am-form am-form-horizontal"
              action="${base}/customer/cust/save.io" method="post">
        <div class="am-tabs am-margin">
            <ul class="am-tabs-nav am-nav am-nav-tabs">
                <li class="am-active"><a href="#tab3">Basic</a></li>
            </ul>

            <div class="am-tabs-bd">
                <div class="am-tab-panel am-fade am-in am-active" id="tab3">
                    <div class="am-g am-margin-top-sm">
                        <label for="cid" class="am-u-sm-2 am-form-label mz-admin-label-fixed">会员卡号</label>
                        <div class="am-u-sm-4 am-u-end">
                            <input id="cid" name="cid" type="text" class="am-input-sm">
                        </div>
                    </div>
                    <div class="am-g am-margin-top-sm">
                        <label for="name" class="am-u-sm-2 am-form-label mz-admin-label-fixed">姓名</label>
                        <div class="am-u-sm-4 am-u-end">
                            <input id="name" name="name" type="text" class="am-input-sm">
                        </div>
                    </div>
                    <div class="am-g am-margin-top-sm">
                        <label for="msisdn" class="am-u-sm-2 am-form-label mz-admin-label-fixed">移动电话</label>
                        <div class="am-u-sm-4 am-u-end">
                            <input id="msisdn" name="msisdn" type="text" class="am-input-sm">
                        </div>
                    </div>
                    <div class="am-g am-margin-top-sm">
                        <label for="birth" class="am-u-sm-2 am-form-label mz-admin-label-fixed">宝宝生日</label>
                        <div class="am-u-sm-4 am-u-end">
                            <input id="birth" name="birth" value=""
                                   type="text" class="am-input-sm" data-am-datepicker readonly>
                        </div>
                    </div>
                    <div class="am-g am-margin-top-sm">
                        <label for="address" class="am-u-sm-2 am-form-label mz-admin-label-fixed">地址</label>
                        <div class="am-u-sm-7 am-u-end">
                            <input id="address" name="address" type="text" class="am-input-sm">

                        </div>
                    </div>
                    <div class="am-g am-margin-top-sm">
                        <label for="remark" class="am-u-sm-2 am-form-label mz-admin-label-fixed">备注</label>
                        <div class="am-u-sm-4 am-u-end">
                            <input id="remark" name="remark" type="text" class="am-input-sm">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="am-margin">
            <button id="btnList" type="button" class="am-btn am-btn-xs">返 回</button>&nbsp;&nbsp;
            <button id="btnSubmit" type="button" class="am-btn am-btn-primary am-btn-xs">保 存</button>
        </div>
        </form>
    </div>
    <!-- content end -->
</div>
<%@ include file="../base/loading.jsp" %>


<script src="${base}/component/jquery/2.1.3/jquery.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/dist/js/amazeui.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/assets/js/app.js"></script>
<script>
    $(function () {
        $('#btnSubmit').click(function() {
            $('#fmMain').submit();
        });
        $("#btnList").click(function() {
            document.location.href = "${base}/customer/cust/list.io";
        });
    });

</script>

</body>
</html>
