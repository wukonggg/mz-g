<%--
  Desc: customer list page
  User: wukong(wukonggg@139.com)
--%>
<%@ page import="band.wukong.mz.g.customer.bean.Customer" %>
<%@ page import="org.nutz.dao.QueryResult" %>
<%@ page import="org.nutz.dao.pager.Pager" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="one" uri="/WEB-INF/tld/one.tld" %>

<%
    Map retMap = (Map) request.getAttribute("obj");
    QueryResult qr = (QueryResult) retMap.get("result");
    List<Customer> clist = qr.getList(Customer.class);
    Pager pager = qr.getPager();
    request.setAttribute("clist", clist);
    request.setAttribute("pager", pager);
    request.setAttribute("qcond", retMap.get("qcond"));

%>

<html>
<head>
    <%@ include file="../base/head.jsp" %>
    <link rel="stylesheet" href="${base}/assets/css/fixed.css">
    <link rel="stylesheet" href="${base}/component/jquery/plugins/poshytip/1.0/src/tip-yellowsimple/tip-yellowsimple.css">
</head>
<body>
<%@ include file="../base/b_ie_lte_ie9.jsp" %>

<%@ include file="../base/admin_topbar.jsp" %>

<div class="am-cf admin-main">
    <!-- sidebar start -->
    <%@ include file="../base/admin_sidebar.jsp" %>
    <!-- sidebar end -->

    <!-- content start -->
    <form id="frmMain" name="frmMain" class="am-form" method="post">
    <input type="hidden" id="id" name="id" value="">
    <div class="admin-content">
        <jsp:include page="../base/admin_breadcrumb.jsp">
            <jsp:param name="info1" value="Customer"/>
            <jsp:param name="info2" value="粑粑麻麻"/>
        </jsp:include>

        <div class="am-g">
            <div class="am-u-md-6 am-cf">
                <div class="am-fl am-cf">
                    <div class="am-btn-toolbar am-fl">
                        <div class="am-btn-group am-btn-group-xs">
                            <button type="button" id="btnAdd" name="btnAdd" class="am-btn am-btn-default">
                                <span class="am-icon-plus"></span>&nbsp;&nbsp;新 增
                            </button>
                            <%--<button type="button" id="btnDel" name="btnDel" class="am-btn am-btn-default">--%>
                                <%--<span class="am-icon-trash-o"></span> 删除--%>
                            <%--</button>--%>
                        </div>
                    </div>
                </div>
            </div>

            <div class="am-u-md-5 am-cf">
                <div class="am-fr">
                    <div class="am-input-group am-input-group-sm">
                        <input id="qcond" name="qcond" type="text" value="${qcond}" class="am-form-field"
                               placeholder="在这里可以输入会员卡号/顾客姓名/手机号码">
                        <span class="am-input-group-btn">
                          <button id="btnList" class="am-btn am-btn-default mz-admin-qcond-fixed" type="button">你说呢</button>
                        </span>
                    </div>
                </div>
            </div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12">
                <table id="mz-table" class="am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th style="width:3%">ID</th>
                        <th style="width:15%">会员卡号</th>
                        <th style="width:12%">姓名</th>
                        <th style="width:15%">手机号</th>
                        <th style="width:10%">宝宝生日</th>
                        <th style="width:10%">服消</th>
                        <th style="width:15%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${clist}" var="cust" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td class="mz-tooltip" title="${cust.cid}">${cust.cid}</td>
                        <td>${cust.name}</td>
                        <td>${cust.msisdn}</td>
                        <td>${one:dateToStringShort(cust.birth)}</td>
                        <td>${cust.paymentClothing}</td>
                        <td>
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <button name="btnMod_${cust.id}" value="${cust.id}"
                                        class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                        <span class="am-icon-pencil-square-o"></span> 编辑
                                    </button>
                                    <button name="btnRm_${cust.id}" value="${cust.id}"
                                        class="am-btn am-btn-default am-btn-xs am-text-danger">
                                        <span class="am-icon-trash-o"></span> 删除
                                    </button>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <%@ include file="../base/admin_pagination.jsp" %>
            </div>
        </div>
    </div>
    </form>
    <!-- content end -->
</div>

<%-- //CASE 删除模态窗口 --%>
<jsp:include page="../base/admin_modal_confirm.jsp"/>


<script src="${base}/component/jquery/2.1.3/jquery.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/dist/js/amazeui.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/assets/js/app.js"></script>
<script src="${base}/component/jquery/plugins/poshytip/1.0/src/jquery.poshytip.noie.js"></script>
<script src="${base}/assets/js/mess.pagination.js"></script>

<script>
    $(function () {
        <%-- //CASE 页面分页组件 --%>
        mess.pagination.load();
        $('.mz-tooltip').poshytip({
            className: 'tip-yellowsimple',
            showTimeout: 300,
            hideTimeout: 0,
            alignTo: 'target',
            alignX: 'center',
            offsetY: 5,
            allowTipHover: true,
            fade: false,
            slide: false
        });
        $("#btnAdd").click(function() {
            document.location.href = "${base}/customer/cust/add.io";
        });
        $("#btnList").click(function() {
            var form = $("#frmMain");
            form.attr("action", "${base}/customer/cust/list.io");
            form.submit();
        });
        //绑定修改事件
        $("button[name^='btnMod_']").click(function() {
            $("#id").val($(this).val());
            var form = $("#frmMain");
            form.attr("action", "${base}/customer/cust/mod.io");
            form.submit();
        });
        //绑定删除事件
        $("button[name^='btnRm_']").click(function(e) {
            e.preventDefault();
            $("#id").val($(this).val());
            mess.modal.confirmAndSubmitForm("mz-modal-confirm", "frmMain", "${base}/customer/cust/rm.io");
        });
    });

</script>
</body>
</html>
