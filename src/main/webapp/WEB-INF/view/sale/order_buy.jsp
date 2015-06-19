<%--
  Desc: As you see...
  User: wukong(wukonggg@139.com)
--%>
<%@ page import="band.wukong.mz.g.sku.bean.SkuMoreView" %>
<%@ page import="org.nutz.dao.QueryResult" %>
<%@ page import="org.nutz.dao.pager.Pager" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="band.wukong.mz.g.customer.bean.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="one" uri="/WEB-INF/tld/one.tld"  %>
<%@ taglib prefix="cate" uri="/WEB-INF/tld/cate.tld"  %>

<%
    Map retMap = (Map) request.getAttribute("obj");
    QueryResult qr = (QueryResult) retMap.get("result");
    List<SkuMoreView> smvList = qr.getList(SkuMoreView.class);
    Pager pager = qr.getPager();
    request.setAttribute("smvList", smvList);
    request.setAttribute("pager", pager);
    request.setAttribute("sids", retMap.get("sids"));

%>

<!doctype html>
<html class="no-js">
<head>
    <%@ include file="../base/head.jsp" %>
    <link rel="stylesheet" href="${base}/assets/css/fixed.css">
    <link rel="stylesheet" href="${base}/component/jquery/plugins/poshytip/1.0/src/tip-yellowsimple/tip-yellowsimple.css">
    <link rel="stylesheet" href="${base}/component/jquery/plugins/autocomplete/jquery.autocomplete.mz.css">
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

    <div class="admin-content">
        <jsp:include page="../base/admin_breadcrumb.jsp">
            <jsp:param name="info1" value="Sale"/>
            <jsp:param name="info2" value="Choose Goods"/>
        </jsp:include>

        <div class="am-g">
            <div class="am-u-md-6 am-cf">
                <div class="am-fl am-cf">
                    <div class="am-btn-toolbar am-fl">
                        <div class="am-btn-group am-btn-group-xs">
                            <button id="btnClearCurrCheck" class="am-btn am-btn-default">
                                <span class="am-icon-trash-o"></span>&nbsp;&nbsp;清空选择
                            </button>
                            <button id="btnAddIntoCart" class="am-btn am-btn-default">
                                <span class="am-icon-plus"></span>&nbsp;&nbsp;加入购物车
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="am-u-md-3 am-cf">
                <div class="am-fr">
                    <div class="am-input-group am-input-group-sm">
                        <input id="sids" name="sids" type="text" value="${sids}"
                               style="width: 600px;" class="am-form-field" placeholder="在这里输入sid，多个时可以用空格隔开">
                        <span class="am-input-group-btn">
                          <button id="btnList" class="am-btn am-btn-default mz-admin-qcond-fixed" type="button">查询</button>
                        </span>
                    </div>
                </div>
            </div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12">
                <table id="tblSmvList" class="am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th class="mz-admin-tbl-check">选</th>
                        <th class="mz-admin-tbl-th-count">#</th>
                        <th class="mz-admin-tbl-th-img">图片</th>
                        <th>名称</th>
                        <th>款型</th>
                        <th>零售价</th>
                        <th>尺码</th>
                        <th>库存</th>
                        <th>SKU</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${smvList}" var="smv" varStatus="status">
                    <tr id="smv-${smv.skuMoreId}">
                        <td class="mz-admin-tbl-check">
                            <input type="checkbox" id="smv-check-${smv.skuMoreId}" value="${smv.skuMoreId}"
                                <c:if test="${one:isZero(smv.count)}">disabled="disabled"</c:if>
                            />
                        </td>
                        <td>${status.count}</td>
                        <td  style="margin:0;" class="mz-admin-tbl-th-img"><img src="${base}/gimg/sku/${smv.img}" class="mz-admin-tbl-td-image"/></td>
                        <td class="mz-tooltip " title="${smv.gname}">${one:string4short(smv.gname, 16)}</td>
                        <td>${smv.model}</td>
                        <td>${smv.sprice}</td>
                        <td>${smv.size}</td>
                        <td id="smv-count-${smv.skuMoreId}">${smv.count}</td>
                        <td>${smv.sid}</td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <%@ include file="../base/admin_pagination.jsp" %>
            </div>
        </div>
    </div>
    </form>

    <jsp:include page="../base/admin_modal_alert.jsp"/>
    <jsp:include page="../base/admin_modal_prompt.jsp">
        <jsp:param name="msg" value="确定将选中商品加入购物车吗？"/>
        <jsp:param name="inputName" value="keyword"/>
    </jsp:include>
    <input type="hidden" id="cid" name="cid" value="">
    <!-- content end -->
</div>

<script src="${base}/component/jquery/2.1.3/jquery.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/dist/js/amazeui.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/assets/js/app.js"></script>
<script src="${base}/component/jquery/plugins/poshytip/1.0/src/jquery.poshytip.noie.js"></script>
<script src="${base}/component/jquery/plugins/autocomplete/jquery.autocomplete.js"></script>
<script src="${base}/assets/js/mess.sidebar.js"></script>
<script src="${base}/assets/js/mess.pagination.js"></script>

<script>
    $(function () {
        mess.sidebar.load(mess.sidebar.bars.SALE);
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
        $("#btnList").click(function() {
            var form = $("#frmMain");
            form.attr("action", "${base}/sale/order/buy.io");
            form.submit();
        });

        //按行点击选择
        $("tr[id^='smv-']").click(function() {
            //CASE -js:jQuery:选择器 控制table > tr的click事件
            var domId = $(this).attr("id");
            var skuMoreId = domId.substr(4);
            var count = $("#smv-count-" + skuMoreId).text();
            if (count > 0) {
                //CASE -js:jQuery checkbox操作
                $("#smv-check-" + skuMoreId).prop("checked", function (i, val) {
                    return !val;
                });
            }
        });

        //clear current check
        $("#btnClearCurrCheck").click(function(e) {
            e.preventDefault();
            $("input[type='checkbox']").prop("checked", false);
        });

        //加入购物车
        $("#btnAddIntoCart").click(function(e) {
            e.preventDefault();
            if ($("input:checked").size() == 0) {
                mess.modal.alert("请选择至少一个商品");
                return;
            }
            var promptInput = $("#mz-modal-prompt-input");
            mess.modal.prompt("mz-modal-prompt",
                function(e){
                    var skuMoreIds = packageSkuMoreId();
                    var cid = $("#cid").val();
                    if (null == cid || "" == cid.trim()) {
//                        mess.modal.alert("请选择会员~");
//                        promptInput.val("");
//                        return;
                        cid = "<%=Customer.CID_NON_MEMBER%>";
                    }
                    var params = {cid:cid, skuMoreIds:skuMoreIds};
                    $.post("${base}/sale/order/cart/add.io",
                        params,
                        function (data) {
                            if(data != "done") {alert("出错啦！快去找悟空！");console.log(data);}
                        }
                    );
                    promptInput.val("");
                },
                function(){
                    promptInput.val("");
                });
        });

        $("#mz-modal-prompt-input").AutoComplete({
            data: "${base}/customer/cust/autocomplete.io",
            ajaxTimeout: 4000,
            ajaxDataType: 'json',
            maxItems: 3,
            width: "auto",
            afterSelectedHandler: function(data){
                var cust = data.value;
                $("#cid").val(cust.substring(0, cust.indexOf("/")));
            },
            onerror: function(msg){alert(msg);}
            <%-- //TODO -OPT 自动完成的样式还需要美化 --%>
        });
    });

    function packageSkuMoreId() {
        var skmIds = "";
        $("input:checked").each(function () {
            skmIds = skmIds + "," + $(this).val();
        });
        return skmIds.substr(1);
    }

</script>

</body>
</html>
