<%--
  Desc: order list page
  User: wukong(wukonggg@139.com)
--%>
<%@ page import="band.wukong.mz.g.sale.bean.Order" %>
<%@ page import="org.nutz.dao.QueryResult" %>
<%@ page import="java.util.Map" %>
<%@ page import="band.wukong.mz.base.bean.Period" %>
<%@ page import="java.util.List" %>
<%@ page import="band.wukong.mz.g.sale.bean.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="one" uri="/WEB-INF/tld/one.tld"  %>

<%
    Map retMap = (Map) request.getAttribute("obj");
    QueryResult qr = (QueryResult) retMap.get("result");
    List<Order> olist = qr.getList(Order.class);

    request.setAttribute("p", (Period)retMap.get("period"));
    request.setAttribute("qcond_c", retMap.get("qcond_c"));
    request.setAttribute("olist", qr.getList(Order.class));
    request.setAttribute("pager", qr.getPager());

%>

<!doctype html>
<html class="no-js">
<head>
    <%@ include file="../base/head.jsp" %>
    <link rel="stylesheet" href="${base}/assets/css/fixed.css">
    <style type="text/css">
        .mz-ic-panel-payment-total {
            font-size: 12px;
            float: right;
            padding-top: 5px;
        }
    </style>
</head>
<body>
<%@ include file="../base/b_ie_lte_ie9.jsp" %>

<%@ include file="../base/admin_topbar.jsp" %>

<div class="am-cf admin-main">
    <!-- sidebar start -->
    <%@ include file="../base/admin_sidebar.jsp" %>
    <!-- sidebar end -->

    <!-- content start -->
    <form id="frmMain" name="frmMain" class="am-form am-form-inline" method="post">
    <input type="hidden" id="id" name="id" value="">
    <div class="admin-content">
        <jsp:include page="../base/admin_breadcrumb.jsp">
            <jsp:param name="info1" value="销售"/>
            <jsp:param name="info2" value="订单"/>
        </jsp:include>

        <div class="am-g">
            <div class="am-u-md-2 am-cf">
                <div class="am-form-group am-input-group-sm">
                    <input name="p.startDate" type="text" value="${one:dateToStringShort(p.startDate)}"
                           class="am-form-field" placeholder="最早购买日期"  data-am-datepicker readonly>
                </div>
            </div>
            <div class="am-u-md-2 am-cf">
                <div class="am-form-group am-input-group-sm">
                    <input name="p.endDate" type="text" value="${one:dateToStringShort(p.endDate)}"
                           class="am-form-field" placeholder="最晚购买日期"  data-am-datepicker readonly>
                </div>
            </div>
            <div class="am-u-md-3 am-cf">
                <div class="am-input-group am-input-group-sm">
                    <input id="qcond_c" name="qcond_c" type="text" value="${qcond_c}" class="am-form-field" placeholder="会员卡/姓名/手机号">
                    <span class="am-input-group-btn">
                      <button id="btnList" class="am-btn am-btn-default mz-admin-qcond-fixed" type="button">查 询</button>
                    </span>
                </div>
            </div>
        </div>
        <br>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-sm-centered">
            <%
                for(Order o : olist) {
                    pageContext.setAttribute("o", o);
            %>
                <div class="am-panel am-panel-default mz-ic-panel">
                    <div class="am-panel-hd">
                        <span class="mz-panel-text-bold">${one:dateToStringShort(o.dtime)}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                        <span class="mz-panel-text">会员：&nbsp;&nbsp;${o.cust.cid} / ${o.cust.msisdn} / ${o.cust.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span class="mz-ic-panel-payment-total"></span>
                    </div>
                    <table class="am-table am-table-striped am-table-hover mz-order-panel-table">
                        <tbody>
                    <%
                        List<Item> items = o.getItems();
                        int size = items.size();
                        for(int num = 0; num < size; num++) {
                            pageContext.setAttribute("i", items.get(num));
                    %>
                        <tr>
                            <td style="width:10%;text-align:center"><img src="${base}/gimg/sku/${i.sku.img}" alt="xxx"/></td>
                            <td style="width:18%">${i.sku.gname}
                                <br><span class="mz-text-grey">款型： ${i.sku.model}</span>
                                <br><span class="mz-text-grey">尺码： ${i.sku.size}</span>
                            </td>
                            <td style="width:3%" name="td_item_dcount_${i.id}">${i.dcount}</td>
                            <td style="width:10%">
                                零售价：<span class="mz-order-sprice">${i.sku.sprice}</span><br>
                                折扣价：<span class="mz-order-sprice">${i.dprice}</span><br>
                                付&nbsp;&nbsp;&nbsp;&nbsp;款：<span class="mz-order-dprice mz-ic-payment">${i.payment}</span>
                            </td>
                            <td style="width:80px;">
                                <a href="#">订单详情</a><br>
                                <a href="#">————</a><br>
                                <a href="#" id="a_restore" val="${i.id}">退货/退款</a>
                            </td>
                            <%
                                if (num < size - 1) {
                                    num = num + 1;
                                    pageContext.setAttribute("i", items.get(num));
                            %>
                            <td style="width:10%;text-align:center"><img src="${base}/gimg/sku/${i.sku.img}" alt="xxx"/></td>
                            <td style="width:18%">${i.sku.gname}
                                <br><span class="mz-text-grey">款型： ${i.sku.model}</span>
                                <br><span class="mz-text-grey">尺码： ${i.sku.size}</span>
                            </td>
                            <td style="width:3%">${i.dcount}</td>
                            <td style="width:10%">
                                零售价：<span class="mz-order-sprice">${i.sku.sprice}</span><br>
                                折扣价：<span class="mz-order-sprice">${i.dprice}</span><br>
                                付&nbsp;&nbsp;&nbsp;&nbsp;款：<span class="mz-order-dprice mz-ic-payment">${i.payment}</span>
                            </td>
                            <td style="width:80px;">
                                <a href="#">订单详情</a><br>
                                <a href="#">————</a><br>
                                <a href="#">调换尺码</a>
                            </td>
                            <%
                               } else {
                            %>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <%
                               }
                            %>
                        </tr>
                    <%
                        }
                    %>
                        </tbody>
                    </table>
                </div>
                <%
                    }
                %>
                <%--<%@ include file="../base/admin_pagination.jsp" %>--%>
                <jsp:include page="../base/admin_pagination.jsp">
                    <jsp:param name="pageSize" value="2"/>
                </jsp:include>
            </div>
        </div>
    </div>
    </form>
    <!-- content end -->
</div>

<script src="${base}/component/jquery/2.1.3/jquery.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/dist/js/amazeui.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/assets/js/app.js"></script>
<script src="${base}/assets/js/mess.sidebar.js"></script>
<script src="${base}/assets/js/mess.pagination.js"></script>
<script>
    $(function () {
        mess.sidebar.load(mess.sidebar.bars.SALE);
        mess.pagination.load(); <%-- //CASE 页面分页组件 --%>
        $("#btnList").click(function() {
            var form = $("#frmMain");
            form.attr("action", "${base}/sale/order/list.io");
            form.submit();
        });
        //绑定修改事件
        $("button[name^='btnMod_']").click(function() {
            $("#id").val($(this).val());
            var form = $("#frmMain");
            form.attr("action", "${base}/stock/goods/mod.io");
            form.submit();
        });
        //计算每个顾客的总付款金额
        $(".mz-ic-panel").each(function() {
            var total = 0;
            $(".mz-ic-payment", this).each(function() {
                total += parseInt($(this).text());
            });
            $(".mz-ic-panel-payment-total", this).text("共付款：" + total + "元");
        });
        $("#a_restore").click(function(){
            var itemId = $(this).attr("val");
            var itemDcount = $("td[name^='td_item_dcount_" + itemId + "']").text();

        });
    });

</script>
</body>
</html>
