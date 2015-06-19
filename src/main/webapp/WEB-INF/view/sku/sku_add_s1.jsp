<%--
  Desc: As you see...
  User: wukong(wukonggg@139.com)
--%>
<%@ page import="band.wukong.mz.g.sku.bean.Goods" %>
<%@ page import="org.nutz.dao.QueryResult" %>
<%@ page import="org.nutz.dao.pager.Pager" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="band.wukong.mz.g.sku.module.SkuModule" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="one" uri="/WEB-INF/tld/one.tld"  %>
<%@ taglib prefix="cate" uri="/WEB-INF/tld/cate.tld"  %>

<%
    Map retMap = (Map) request.getAttribute("obj");
    QueryResult qr = (QueryResult) retMap.get("result");
    List<Goods> glist = qr.getList(Goods.class);
    Pager pager = qr.getPager();
    request.setAttribute("glist", glist);
    request.setAttribute("pager", pager);
    request.setAttribute("qcond", retMap.get("qcond"));
    System.out.println("qcond = " + retMap.get("qcond"));

%>

<!doctype html>
<html class="no-js">
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
    <div class="admin-content">
        <jsp:include page="../base/admin_breadcrumb.jsp">
            <jsp:param name="info1" value="Stock"/>
            <jsp:param name="info2" value="SKU"/>
            <jsp:param name="info3" value="step1:&nbsp;&nbsp;choose goods"/>
        </jsp:include>

        <form id="frmMain" name="frmMain" class="am-form am-form-horizontal" method="post">
            <%--<input type="hidden" id="goodsId" name="goodsId" value="">--%>
            <div class="am-tabs am-margin">
                <ul class="am-tabs-nav am-nav am-nav-tabs">
                    <li class="am-active"><a href="#">Step1：选择商品</a></li>
                    <li><a href="#">Step2：添加商品SKU</a></li>
                </ul>

                <div class="am-tabs-bd">
                    <div class="am-tab-panel am-fade am-in am-active" id="tab3">
                        <div class="am-fr">
                            <div class="am-input-group am-input-group-sm">
                                <input id="qcond" name="qcond" type="text" value="${qcond}" class="am-form-field"
                                       placeholder="在这里可以输入商品名称">
                                <span class="am-input-group-btn">
                                    <button id="btnListGoods" type="button"
                                            class="am-btn am-btn-default mz-admin-qcond-fixed" >查 询</button>
                                </span>
                            </div>
                        </div>
                        <div class="am-g">
                            <div class="am-u-sm-12">
                                <table class="am-table am-table-striped am-table-hover table-main">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>ID</th>
                                        <th>图片</th>
                                        <th>品类</th>
                                        <th>商品名称</th>
                                        <th>商品描述</th>
                                        <th>创建日期</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${glist}" var="g" varStatus="status">
                                        <tr class="mz-data-line-${g.id}">
                                            <td><input type="radio" id="goodsId-${g.id}" name="goodsId" value="${g.id}"/></td>
                                            <td>${status.count}</td>
                                            <td class="mz-admin-tbl-td-img"><img src="${base}/gimg/goods/${g.img}" alt="${g.gname}" class="mz-admin-tbl-td-image"/></td>
                                            <td>${g.cateCode}</td>
                                            <td class="mz-tooltip" title="${g.gname}"><a href="#">${one:string4short(g.gname, 18)}</a></td>
                                            <td class="mz-tooltip" title="${g.words}">${one:string4short(g.words, 30)}</td>
                                            <td>${one:dateToStringShort(g.ctime)}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <br>
                                <jsp:include page="../base/admin_pagination.jsp">
                                    <jsp:param name="pageSize" value="<%=SkuModule.SKU_ADD_S1_PAGE_SIZE%>"/>
                                </jsp:include>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="am-margin">
                <button id="btnBack" type="button" class="am-btn am-btn-xs">返 回</button>&nbsp;&nbsp;
                <button id="btnNext" type="button" class="am-btn am-btn-primary am-btn-xs">下 一 步</button>
            </div>
        </form>
    </div>
    <!-- content end -->
</div>
<%@ include file="../base/loading.jsp" %>

<%-- //CASE alert模态窗口 --%>
<jsp:include page="../base/admin_modal_alert.jsp"/>


<script src="${base}/component/jquery/2.1.3/jquery.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/dist/js/amazeui.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/assets/js/app.js"></script>
<script src="${base}/component/jquery/plugins/poshytip/1.0/src/jquery.poshytip.noie.js"></script>
<script src="${base}/assets/js/mess.sidebar.js"></script>
<script src="${base}/assets/js/mess.pagination.js"></script>

<script>
    var curr = -1;
    $(function () {
        mess.sidebar.load(mess.sidebar.bars.SKU);
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
        $("tr[class^='mz-data-line-']").click(function() {
            var idd = $(this).attr("class").replace("mz-data-line-", "");
            $("#goodsId-" + idd).prop("checked", true);
        });
        $("#btnListGoods").click(function() {
            var form = $("#frmMain");
            form.attr("action", "${base}/stock/sku/add_s1.io");
            form.submit();
        });
        $("#btnBack").click(function() {
            var form = $("#frmMain");
            form.attr("action", "${base}/stock/sku/list.io");
            form.submit();
        });
        $("#btnNext").click(function() {
            if (!validateNext()) {
                mess.modal.alert("请选择一个商品");
                return;
            }
            var form = $("#frmMain");
            form.attr("action", "${base}/stock/sku/add/s2.io");
            form.submit();
        });
    });

    function validateNext() {
        var valid = false;
        $("input[type='radio'][name='goodsId']").each(function(){
            if ($(this).prop("checked")) {
                valid = true;
                return false;
                // CASE JS:jquery: 跳出each()的循环
                // jquery中没有break和continue。return false相当于break；return true相当于continue。
            }
        });
        return valid;
    }

</script>

</body>
</html>
