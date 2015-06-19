<%--
  Desc: order list page
  User: wukong(wukonggg@139.com)
--%>
<%@ page import="band.wukong.mz.g.sale.bean.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="o" uri="/WEB-INF/tld/order.tld" %>

<%
    //CASE jstl:el Map<String, List<Object>>的遍历
    Map<String, List<Cart>> cartsMap = (Map<String, List<Cart>>) request.getAttribute("obj");
    pageContext.setAttribute("cartsMap", cartsMap);

%>

<!doctype html>
<html class="no-js">
<head>
    <%@ include file="../base/head.jsp" %>
    <link rel="stylesheet" href="${base}/assets/css/fixed.css">
    <style type="text/css">
        .mz-ic-cust-panel {
            cursor: pointer;
            font-size: 12px;
        }

        .mz-ic-table-hidden {
            display: none;
        }

        .mz-ic-cart-tbody-img {
            max-height: 30px;
            width: auto;
            padding: 0;
            margin: 0
        }

        .mz-ic-cart-tbody-count {
            text-align: center;
        }

        .mz-ic-cart-tbody-payment {
            width: 100px !important;
            text-align: left;
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
    <form id="frmMain" name="frmMain" action="${base}/sale/order/pay.io" class="am-form am-form-inline" method="post">
        <input type="hidden" id="carts" name="carts" value="">
        <div class="admin-content">
            <jsp:include page="../base/admin_breadcrumb.jsp">
                <jsp:param name="info1" value="SALE"/>
                <jsp:param name="info2" value="Cart"/>
            </jsp:include>

            <div class="am-g">
                <div class="am-u-md-6 am-cf">
                    <div class="am-fl am-cf">
                        <div class="am-btn-toolbar am-fl">
                            <div class="am-btn-group am-btn-group-xs">
                                <button id="btn_pay" class="am-btn am-btn-primary">
                                    <span class="am-icon-cart-arrow-down"></span>&nbsp;&nbsp;结&nbsp;帐
                                </button>
                                <button id="btn_collapse" class="am-btn am-btn-default">
                                    <span class="am-icon-minus">&nbsp;&nbsp;收缩全部</span>
                                </button>
                                <button id="btn_clear" class="am-btn am-btn-default">
                                    <span class="am-icon-trash-o"></span>&nbsp;&nbsp;清空购物车
                                </button>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="am-u-md-3 am-cf">
                </div>
            </div>

            <br>

            <div class="am-g">
                <div class="am-u-sm-12 am-u-sm-centered">
                    <c:forEach items="${cartsMap}" var="cartsKV" varStatus="status">
                        <div class="am-panel am-panel-default" id="div_accordion_${cartsKV.key}">
                            <div class="am-panel-hd mz-ic-cust-panel">
                                <span class="am-icon-minus">&nbsp;&nbsp;</span>
                                <span class="mz-panel-text">&nbsp;&nbsp;会员：&nbsp;&nbsp;${cartsKV.value[0].cid} / ${cartsKV.value[0].msisdn} / ${cartsKV.value[0].name}</span>
                                <span class="am-fr" style="padding-top: 2px; margin-right: 4px;">
                                    <a id="btn_cust_clear_${cartsKV.key}_${cartsKV.value[0].custId}" href="#">清空</a>
                                </span>
                            </div>
                            <table id="tbl_${cartsKV.key}" class="am-table am-table-striped am-table-hover mz-order-panel-table">
                                <thead>
                                <tr>
                                    <th class="mz-admin-tbl-check">
                                        <input type="checkbox" class="mz-ic-cust-carts-chk-all"
                                               value="${cartsKV.key}">
                                    </th>
                                    <th class="mz-admin-tbl-th-img">图片</th>
                                    <th>名称</th>
                                    <th>款型</th>
                                    <th>尺码</th>
                                    <th>库存</th>
                                    <th>零售价</th>
                                    <th class="am-text-left" style="width: 150px">数量</th>
                                    <th class="am-text-left" style="width: 30px"></th>
                                    <th class="am-text-left" style="width: 150px">金额</th>
                                    <th style="width: 50px">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${cartsKV.value}" var="c" varStatus="status">
                                    <tr id="tr_${cartsKV.key}_${c.skuMoreId}">
                                        <td class="mz-admin-tbl-check">
                                            <input type="checkbox" class="mz-ic-cust-cart-chk-${cartsKV.key}"
                                                   value="${c.skuMoreId}">
                                        </td>
                                        <td class="am-text-center">
                                            <img src="${base}/gimg/sku/${c.img}" class="mz-ic-cart-tbody-img"/>
                                        </td>
                                        <td>${c.gname}</td>
                                        <td>${c.model}</td>
                                        <td>${c.size}</td>
                                        <td>${c.scount}</td>
                                        <td>
                                            <%--有折扣--%>
                                            <c:if test="${o:discountInDouble(c.cateCode, c.paymentClothing) != 1.0}">
                                                <span class="mz-order-sprice">
                                                    <fmt:formatNumber type="number" value="${c.sprice}" pattern="0.00" maxFractionDigits="2"/>
                                                </span><br>
                                                <span class="mz-order-dprice-highlight">${o:calcDpriceInString(c.cateCode, c.paymentClothing, c.sprice, 2, "0.00")}</span>
                                                <span>(${o:discountInText(c.cateCode, c.paymentClothing)})</span>
                                            </c:if>
                                            <%--无折扣--%>
                                            <c:if test="${o:discountInDouble(c.cateCode, c.paymentClothing) == 1.0}">
                                                <span class="mz-order-dprice">
                                                    <fmt:formatNumber type="number" value="${c.sprice}" pattern="0.00" maxFractionDigits="2"/>
                                                </span>
                                            </c:if>
                                        </td>
                                        <td class="am-text-center">
                                            <div class="am-input-group am-input-group-sm">
                                                <span class="am-input-group-btn">
                                                  <button name="btn_count_reduce"
                                                          class="am-btn am-btn-default mz-admin-qcond-fixed" type="button">-
                                                  </button>
                                                </span>
                                                <input id="inp_count_${cartsKV.key}_${c.skuMoreId}" name="count" type="text"
                                                       value="${c.count}" class="am-form-field mz-ic-cart-tbody-count mz-ic-carts-count"/>
                                                <span class="am-input-group-btn">
                                                  <button name="btn_count_add"
                                                          class="am-btn am-btn-default mz-admin-qcond-fixed" type="button">+
                                                  </button>
                                                </span>
                                            </div>
                                            <%--<input type="hidden" name="custId" value="${c.custId}"/>--%>
                                            <input type="hidden" id="inp_custId_${cartsKV.key}_${c.skuMoreId}" value="${c.custId}"/>
                                            <input type="hidden" name="_skuMoreId" value="${c.skuMoreId}" class="mz-ic-carts-skuMoreId"/>
                                            <input type="hidden" name="_dprice" value="${o:calcDpriceInString(c.cateCode, c.paymentClothing, c.sprice, 2, "0.00")}" class="mz-ic-carts-dprice"/>
                                            <input type="hidden" name="_cid" value="${cartsKV.key}" class="mz-ic-carts-cid"/>
                                            <input type="hidden" name="_scount" value="${c.scount}" class="mz-ic-carts-scount"/>
                                        </td>
                                        <td></td>
                                        <td class="am-text-center">
                                            <input id="inp_payment_${cartsKV.key}_${c.skuMoreId}" name="payment"
                                                   type="text"
                                                   class="am-form-field am-input-sm mz-ic-cart-tbody-payment"
                                                   value="${o:calcDpriceInString(c.cateCode, c.paymentClothing, c.sprice, 2, "0.00")}"/>
                                        </td>
                                        <td>
                                            <a id="btn_cart_rm_${cartsKV.key}_${c.custId}_${c.skuMoreId}" href="#">删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </form>

    <jsp:include page="../base/admin_modal_confirm.jsp">
        <jsp:param name="msg" value="确定将该商品从购物车中删除吗？"/>
    </jsp:include>
    <!-- content end -->

</div>

<script src="${base}/component/jquery/2.1.3/jquery.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/dist/js/amazeui.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/assets/js/app.js"></script>
<script src="${base}/component/raw/raw.prototype.js"></script>
<script src="${base}/component/raw/raw.lang.js"></script>
<script src="${base}/component/raw/raw.util.js"></script>
<script src="${base}/component/raw/raw.re.js"></script>
<script src="${base}/assets/js/mess.sidebar.js"></script>
<script>
    // var cart
    // key: skuMoreId
    // value: {custId:custId,cid:cid,skuMoreId:skuMoreId,count:count,payment:payment}
    var carts = new raw.lang.map();
    var currCid;
    function loadCarts() {
        var cid = $("input:checked[class!='mz-ic-cust-carts-chk-all']").each(function() {
            var cid = $(this).attr("class").substring(20);  //mz-ic-cust-cart-chk-xxx
            var skuMoreId = $(this).val();
            var custId = $("#inp_custId_" + cid +"_"+ skuMoreId).val();
            var count = $("#inp_count_" + cid +"_"+ skuMoreId).val();
            var payment = $("#inp_payment_" + cid +"_"+ skuMoreId).val();
            var cart = {custId:custId,cid:cid,skuMoreId:skuMoreId,count:count,payment:payment};
//            console.log("cart = " + JSON.stringify(cart));
            carts.put(skuMoreId, cart);
        });
    }

    $(function () {
        mess.sidebar.load(mess.sidebar.bars.SALE);
        //折叠购物车所有内容
        $("#btn_collapse").click(function () {
            var iconNode = $(this).children(":first");
            if (iconNode.attr("class") == "am-icon-plus") {
                $("table[id^='tbl_']").removeClass("mz-ic-table-hidden");
                iconNode.removeClass("am-icon-plus");
                iconNode.addClass("am-icon-minus");
                iconNode.html("&nbsp;&nbsp;收缩全部")
            } else {
                $("table[id^='tbl_']").addClass("mz-ic-table-hidden");
                iconNode.removeClass("am-icon-minus");
                iconNode.addClass("am-icon-plus");
                iconNode.html("&nbsp;&nbsp;展开全部")
            }
            return false;
        });

        /**
         *选择某一个会员的购物车:
         * 1、显示该会员的购物车
         * 2、同时隐藏其他会员的购物车
         */
        $(".am-panel-hd").click(function () {
            console.log("enter");
            var cid = $(this).parent().attr("id").substring(14);
            console.log("cid=" + cid);
            console.log("cid class=" + $("#tbl_" + cid).attr("class"));
            $("#tbl_" + cid).toggleClass("mz-ic-table-hidden");
            console.log("cid class=" + $("#tbl_" + cid).attr("class"));
            var iconNode = $(this).children(":first");
            console.log("$(iconNode).attr('class')" + $(iconNode).attr("class"));
            if(iconNode.attr("class") == "am-icon-plus") {
                iconNode.removeClass("am-icon-plus");
                iconNode.addClass("am-icon-minus");
            } else {
                iconNode.removeClass("am-icon-minus");
                iconNode.addClass("am-icon-plus");
            }
        });

        //全选/全反选某个顾客选购的所有商品。不允许同时选择多个顾客的购物车商品
        $(".mz-ic-cust-carts-chk-all").click(function () {
            var cid = $(this).val();
            var checked = $(this).prop("checked");
            $(".mz-ic-cust-cart-chk-" + cid).prop("checked", checked);
            $("input[type='checkbox'][class!='mz-ic-cust-cart-chk-"+cid+"']").prop("checked", false);
            $(this).prop("checked", checked);

            //初次点选，设cid
            if (currCid == undefined && checked) {
                currCid = cid;

            } else if (!checked) {  //反选：清cid && 清map
                currCid = undefined;
                carts = new raw.lang.map();

            } else if(currCid != cid) {//选中其他：改cid，清map
                currCid = cid;
                carts = new raw.lang.map();
            }
        });
        //择行某个会员的商品。校验：不允许同时选择多个顾客的购物车商品
        $("input[class^='mz-ic-cust-cart-chk-']").click(function (e) {
            var clazz = $(this).attr("class");
            $("input[type='checkbox'][class!="+clazz+"]").prop("checked", false);

            var cid = $(this).attr("class").substring(20);
            var skuMoreId = $(this).val();
            if (!$(this).prop("checked")) {
                carts.remove(skuMoreId);
            } else {
                currCid = cid;
            }
        });


        //增加商品数量
        $("button[name='btn_count_add']").click(function () {
            //console.log($(this).parent().prev().val());
            var countNode = $(this).parent().prev();
            var count = countNode.val();
            var scount = $(this).parent().parent().nextAll(".mz-ic-carts-scount").val();

            if (scount < parseInt(count) + 1) {
                alert("购买数量超出库存");
                return false;
            }

            $(countNode).val(parseInt(count) + 1);
            $(countNode).change();
        });
        //减少商品数量
        $("button[name='btn_count_reduce']").click(function () {
            var countNode = $(this).parent().next();
            var count = countNode.val();
            if (count > 1) {
                $(countNode).val(count - 1);
            }
            $(countNode).change();
        });


        //商品数量改变时的事件绑定
        $(".mz-ic-carts-count").on("change input", function () {
            var cid = $(this).parent().nextAll(".mz-ic-carts-cid").val();
            var skuMoreId = $(this).parent().nextAll(".mz-ic-carts-skuMoreId").val();
            var dprice = $(this).parent().nextAll(".mz-ic-carts-dprice").val();
//            console.log(cid.val());
//            console.log(skuMoreId.val());
//            console.log(dprice.val());
            var currDcount = $(this).val();
            if (!raw.re.pattern.integer_gt0.test(currDcount)) {
                currDcount = 1;
                $(this).val(currDcount);
            }
            var payment = dprice * currDcount;
            $("#inp_payment_" + cid + "_" + skuMoreId).val(payment.toFixed(2));
        });


        //清空整个购物车
        $("#btn_clear").click(function (e) {
            e.preventDefault();
            $("#" + mess.modal.confirmDivId).modal({
                onConfirm: function (options) {
                    var params = {custId: 0};
                    $.post("${base}/sale/order/cart/clear.io",
                            params,
                            function (data) {
                                if (data == "done") {
                                    $("div[id^='div_accordion_']").remove();
                                } else {
                                    alert("出错啦！快去找悟空！");
                                    console.log(data);
                                }
                            });
                },
                onCancel: function () {}
            });
        });

        //清空某个cust的cart
        $("a[id^='btn_cust_clear_']").click(function (e) {
            //CASE jquery:event 父子dom节点都有click事件的情况:    e.preventDefault();return false;
            e.preventDefault();
            $("#" + mess.modal.confirmDivId).modal({
                relatedTarget: this,
                onConfirm: function (options) {
                    var $link = $(this.relatedTarget);
                    var exp = $link.attr("id").substring(15).split("_");
                    var cid = exp[0];
                    var custId = exp[1];
                    var params = {custId: custId};
                    $.post("${base}/sale/order/cart/clear.io",
                            params,
                            function (data) {
                                if (data == "done") {
                                    $("#div_accordion_" + cid).remove();
                                } else {
                                    alert("出错啦！快去找悟空！");
                                    console.log(data);
                                }
                            });
                },
                onCancel: function () {}
            });
            return false;
        });

        //删除某个cart
        $("a[id^='btn_cart_rm_']").click(function (e) {
            e.preventDefault();
            $("#" + mess.modal.confirmDivId).modal({
                relatedTarget: this,
                onConfirm: function (options) {
                    var $link = $(this.relatedTarget);
                    var exp = $link.attr("id").substring(12).split("_");
                    var cid = exp[0];
                    var custId = exp[1];
                    var skuMoreId = exp[2];
                    var params = {custId: custId, skuMoreId: skuMoreId};
                    var $tr = $("#tr_" + cid + "_" + skuMoreId);
//                    console.log("tr: " + "#tr_" + cid + "_" + skuMoreId);
//                    console.log("this: " + + $tr.length);
                    $.post("${base}/sale/order/cart/rm.io",
                            params,
                            function (data) {
                                if (data == "done") {
//                                    console.log("prev: " + $tr.prev("tr").length);
//                                    console.log("next: " + $tr.next("tr").length);
                                    if ($tr.prev("tr").length == 0 && $tr.next("tr").length == 0) {
                                        $("#div_accordion_" + cid).remove();
                                    } else {
                                        $tr.remove();
                                    }
                                } else {
                                    alert("出错啦！快去找悟空！");
                                    console.log(data);
                                }
                            });
                },
                onCancel: function () {}
            });
        });





        $("#btn_pay").click(function(e){
            e.preventDefault();

            if (currCid == undefined || currCid == "") {
                alert("请先选择商品再结帐");
                return false;
            }

            loadCarts();
            console.log("carts.size():" + carts.size());
            var ja = "[";
            carts.each(function(key, value, i){
                ja += JSON.stringify(value) + ","
            });
            ja = ja.substring(0, ja.length - 1) + "]";
            console.log("ja = \n" + ja);
            $("#carts").val(ja);
            $("#frmMain").submit();
        });

    });
</script>
</body>
</html>
