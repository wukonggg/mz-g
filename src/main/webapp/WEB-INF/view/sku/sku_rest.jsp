<%--
  Desc: goods list page
  User: wukong(wukonggg@139.com)
--%>
<%@ page import="band.wukong.mz.g.sku.bean.SkuMoreView" %>
<%@ page import="org.nutz.dao.QueryResult" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="band.wukong.mz.g.category.bean.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="one" uri="/WEB-INF/tld/one.tld"  %>
<%@ taglib prefix="cate" uri="/WEB-INF/tld/cate.tld"  %>

<%
    Map retMap = (Map) request.getAttribute("obj");
    QueryResult qr = (QueryResult) retMap.get("result");
    request.setAttribute("smvList", qr.getList(SkuMoreView.class));
    request.setAttribute("pager", qr.getPager());
    request.setAttribute("counts", retMap.get("counts"));
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
        <input type="hidden" id="cateCodes" name="cateCodes" value="">
        <div class="admin-content">
            <br>
            <div class="am-g">
                <div class="am-u-md-8">
                    <div id="btnGroupCateCodes" class="am-btn-group" data-am-button>
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
                    <table id="mz-table" class="am-table am-table-striped am-table-hover table-main">
                        <thead>
                        <tr>
                            <th class="mz-admin-tbl-th-count">#</th>
                            <th class="mz-admin-tbl-th-img">图片</th>
                            <th>商品</th>
                            <th>SKU</th>
                            <th>剩余库存量</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${smvList}" var="smv" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td class="mz-admin-tbl-td-img"><img src="${base}/gimg/goods/${smv.img}" class="mz-admin-tbl-td-image"/></td>
                                <td class="mz-tooltip" title="${smv.gname}"><a href="#">${one:string4short(smv.gname, 18)}</a></td>
                                <td>${smv.model}&nbsp;&nbsp;${smv.type}</td>
                                <td>${smv.count}</td>
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
<script src="${base}/assets/js/mess.sidebar.js"></script>
<script src="${base}/assets/js/mess.pagination.js"></script>
<script src="${base}/assets/js/mz.select_category.js"></script>
<script>
    $(function () {
        <%-- //CASE sidebar --%>
        mess.sidebar.load(mess.sidebar.bars.SKU, mess.sidebar.bars.SKU_REST);
        <%-- //CASE 页面分页组件 --%>
        mess.pagination.load();
        <%-- //CASE 品类查询组件 --%>
        mz.loadCategoryIntoButtonGroup({
            contextPath: "${base}",
            selectId : "btnGroupCateCodes",
            currCateCodes: "<%=retMap.get("cateCodes")%>",
            pcode: "<%=Category.CATE_CODE_TYPE_SIMPLE%>",
            errorMsg: "查询商品分类时出错啦！快去找悟空！"
        });

        <%-- //CASE 悬浮提示 --%>
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
//            console.log($("#cateCodes").val());
            var form = $("#frmMain");
            form.attr("action", "${base}/stock/sku/rest.io");
            form.submit();
        });
    });


</script>
</body>
</html>
