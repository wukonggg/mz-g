<%--
  Desc: As you see...
  User: wukong(wukonggg@139.com)
--%>
<%@ page import="band.wukong.mz.g.category.SimpleCateConst" %>
<%@ page import="band.wukong.mz.g.sku.bean.Sku" %>
<%@ page import="org.nutz.dao.QueryResult" %>
<%@ page import="org.nutz.dao.pager.Pager" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="band.wukong.mz.g.category.bean.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="one" uri="/WEB-INF/tld/one.tld"  %>
<%@ taglib prefix="cate" uri="/WEB-INF/tld/cate.tld"  %>

<%
    Map retMap = (Map) request.getAttribute("obj");
    QueryResult qr = (QueryResult) retMap.get("result");
    List<Sku> scList = qr.getList(Sku.class);
    Pager pager = qr.getPager();
    request.setAttribute("scList", scList);
    request.setAttribute("pager", pager);
    request.setAttribute("cateCode", retMap.get("cateCode"));
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
            <jsp:param name="info1" value="Stock"/>
            <jsp:param name="info2" value="SKU"/>
        </jsp:include>

        <div class="am-g">
            <div class="am-u-md-5 am-cf">
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
            <div class="am-u-sm-12 am-u-md-3">
                <div class="am-form-group">
                    <select id="cateCode" name="cateCode" class="am-input-sm mz-admin-qcond-fixed">
                        <option value="<%=Category.CATE_CODE_TYPE_SIMPLE%>">所有类别</option>
                    </select>
                </div>
            </div>
            <div class="am-u-md-4 am-cf">
                <div class="am-fr">
                    <div class="am-input-group am-input-group-sm">
                        <input id="qcond" name="qcond" type="text" value="${qcond}" class="am-form-field"
                               placeholder="在这里可以输入商品名称/出厂货号">
                        <span class="am-input-group-btn">
                          <button id="btnList" class="am-btn am-btn-default mz-admin-qcond-fixed" type="button">查 询</button>
                        </span>
                    </div>
                </div>
            </div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th class="mz-admin-tbl-th-count">#</th>
                        <th class="mz-admin-tbl-th-img">图片</th>
                        <th>品类</th>
                        <th>名称</th>
                        <th>款型</th>
                        <th>SKU编码</th>
                        <th style="width: 150px;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${scList}" var="sc" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td class="mz-admin-tbl-td-img"><img src="${base}/gimg/sku/${sc.img}" class="mz-admin-tbl-td-image"/></td>
                        <td>${cate:title(sc.goods.cateCode, applicationScope["APP_CATE_FLAT_MAP"])}</td>  <!-- TODO -OPT 这里如何能做到不用字面字符串？-->
                        <td class="mz-tooltip " title="${sc.goods.gname}">${one:string4short(sc.goods.gname, 16)}</td>
                        <td>${sc.model}</td>
                        <td>${sc.sid}</td>
                        <td>
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <button name="btnMod_${sc.id}" value="${sc.id}"
                                        class="am-btn am-btn-default am-btn-xs am-text-secondary">
                                        <span class="am-icon-pencil-square-o"></span> 编辑
                                    </button>
                                    <button name="btnRm_${sc.id}" value="${sc.id}"
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

<jsp:include page="../base/admin_modal_confirm.jsp"/>


<script src="${base}/component/jquery/2.1.3/jquery.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/dist/js/amazeui.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/assets/js/app.js"></script>
<script src="${base}/component/jquery/plugins/poshytip/1.0/src/jquery.poshytip.noie.js"></script>
<script src="${base}/assets/js/mess.sidebar.js"></script>
<script src="${base}/assets/js/mess.pagination.js"></script>
<script src="${base}/assets/js/mz.select_category.js"></script>

<script>
    $(function () {
        mess.sidebar.load(mess.sidebar.bars.SKU);
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
        $("#btnList").click(function() {
            var form = $("#frmMain");
            form.attr("action", "${base}/stock/sku/list.io");
            form.submit();
        });
        $("#btnAdd").click(function() {
            document.location.href = "${base}/stock/sku/add_s1.io";
        });
        //绑定修改事件
        $("button[name^='btnMod_']").click(function() {
            $("#id").val($(this).val());
            var form = $("#frmMain");
            form.attr("action", "${base}/stock/sku/mod.io");
            form.submit();
        });
        //绑定删除事件
        $("button[name^='btnRm_']").click(function(e) {
            e.preventDefault();
            $("#id").val($(this).val());
            mess.modal.confirmAndSubmitForm("mz-modal-confirm", "frmMain", "${base}/stock/sku/rm.io");
        });

        mess.pagination.load();
        mz.loadCategory("${base}", "cateCode", "查询商品分类时出错啦！快去找悟空！", "${cateCode}", '<%=Category.CATE_CODE_TYPE_SIMPLE%>');
    });

</script>
</body>
</html>
