<%@ page import="band.wukong.mz.g.sale.bean.Item" %>
<%--
  Desc: order return
  User: wukong(wukonggg@139.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Item item = (Item) request.getAttribute("obj");
    pageContext.setAttribute("item", item);
%>

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
            <jsp:param name="info1" value="SALE"/>
            <jsp:param name="info2" value="Order"/>
            <jsp:param name="info3" value="return"/>
        </jsp:include>

        <form id="fmMain" name="fmMain" action="${base}/sale/order/return/s2.io" class="am-form am-form-horizontal">
        <input type="hidden" name="id" value="${item.id}">
        <div class="am-tabs am-margin">
            <ul class="am-tabs-nav am-nav am-nav-tabs">
                <li class="am-active"><a href="#tab3">Basic</a></li>
            </ul>

            <div class="am-tabs-bd">
                <div class="am-tab-panel am-fade am-in am-active" id="tab3">
                    <div class="am-g am-margin-top-sm">
                        <label for="inpDcount" class="am-u-sm-2 am-form-label mz-admin-label-fixed">退货数量</label>
                        <div class="am-u-sm-4 am-u-end">
                            <input id="inpDcount" name="dcount" value="${item.dcount}" type="text" class="am-input-sm">
                        </div>
                    </div>
                    <div class="am-g am-margin-top-sm">
                        <label for="inpPayment" class="am-u-sm-2 am-form-label mz-admin-label-fixed">退货金额</label>
                        <div class="am-u-sm-4 am-u-end">
                            <input id="inpPayment" name="lin_dprice" value="${item.dcount * item.dprice}" type="text" class="am-input-sm" readonly>
                        </div>
                    </div>
                    <div class="am-g am-margin-top-sm">
                        <label for="returnReason" class="am-u-sm-2 am-form-label mz-admin-label-fixed">退货原因</label>
                        <div class="am-u-sm-4 am-u-end">
                            <select id="returnReason" name="returnReason" class="am-input-sm mz-admin-select-fixed">
                                <option value="<%=Item.RETURN_REASON_QUANLITY%>" selected>质量问题</option>
                                <option value="<%=Item.RETURN_REASON_SIZE%>">尺码问题</option>
                            </select>
                        </div>
                    </div>
                    <div class="am-g am-margin-top-sm">
                        <label for="returnDesc" class="am-u-sm-2 am-form-label mz-admin-label-fixed">退货描述</label>
                        <div class="am-u-sm-6 am-u-end">
                            <textarea id="returnDesc" name="returnDesc" rows="3" placeholder="退货描述" class="am-input-sm"></textarea>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="am-margin">
            <button id="btnList" type="button" class="am-btn am-btn-xs">返 回</button>&nbsp;&nbsp;
            <button id="btnSubmit" type="submit" class="am-btn am-btn-primary am-btn-xs">退 货</button>
        </div>
        </form>
    </div>
    <!-- content end -->
</div>
<%@ include file="../base/loading.jsp" %>


<script src="${base}/component/jquery/2.1.3/jquery.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/dist/js/amazeui.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/assets/js/app.js"></script>
<script src="${base}/assets/js/mess.sidebar.js"></script>
<script src="${base}/component/raw/raw.re.js"></script>
<script>
    $(function () {
        mess.sidebar.load(mess.sidebar.bars.SALE);
        var dprice = "${item.dprice}";
        $('#inpDcount').keyup(function(){
            if (!raw.re.patterns.integer_gt0.test($(this).val())) {
                alert("请输入正整数!");
                $(this).val("");
                $(this).focus();
                return false;
            }
            $("#inpPayment").val($(this).val() * dprice);
        });
    });

</script>

</body>
</html>
