<%--
  Desc: As you see...
  User: wukong(wukonggg@139.com)
--%>
<%@ page import="band.wukong.mz.g.sku.SkuMoreHelper" %>
<%@ page import="band.wukong.mz.g.sku.bean.Sku" %>
<%@ page import="band.wukong.mz.g.sku.bean.SkuPropType" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="one" uri="/WEB-INF/tld/one.tld"  %>

<%
    Map retMap = (Map) request.getAttribute("obj");
    Sku sc = (Sku) retMap.get("sc");
    List<SkuPropType> sptList = (List<SkuPropType>) retMap.get("sptList");
    request.setAttribute("sc", sc);

    //如果sptList为null，说明该产品/catecode没有dict
    if (null != sptList && sptList.size() > 0) {
        request.setAttribute("sptList", sptList);
    }

%>

<!doctype html>
<html class="no-js">
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
            <jsp:param name="info1" value="Stock"/>
            <jsp:param name="info2" value="SKU"/>
            <jsp:param name="info3" value="modify"/>
        </jsp:include>

        <form id="fmMain" name="fmMain" class="am-form am-form-horizontal" enctype="multipart/form-data">
        <input type="hidden" id="type" name="type" value="">
        <div class="am-tabs am-margin">
            <ul class="am-tabs-nav am-nav am-nav-tabs">
                <li class="am-active"><a href="">修改产品SKU</a></li>
            </ul>

            <div class="am-tabs-bd">
                <div class="am-tab-panel am-fade am-in am-active" id="tab3">
                    <div class="am-g am-margin-top-sm">
                        <label for="lbl_sid" class="am-u-sm-2 am-form-label mz-admin-label-fixed">SKU编码</label>
                        <div class="am-u-sm-4 am-u-end">
                            <input id="lbl_sid" name="lin_sid" value="${sc.sid}" type="text" class="am-input-sm" readonly="readonly">
                        </div>
                        <div class="am-hide-sm-only am-u-md-6 mz-admin-input-desc-fixed"> * 必填</div>
                    </div>
                    <div class="am-g am-margin-top-sm">
                        <label for="model" class="am-u-sm-2 am-form-label mz-admin-label-fixed">款型</label>
                        <div class="am-u-sm-4 am-u-end">
                            <input id="model" name="model" value="${sc.model}" type="text" class="am-input-sm">
                        </div>
                        <div class="am-hide-sm-only am-u-md-6 mz-admin-input-desc-fixed"> * 必填</div>
                    </div>
                    <div class="am-g am-margin-top-sm">
                        <label for="ptime" class="am-u-sm-2 am-form-label mz-admin-label-fixed">入库日期</label>
                        <div class="am-u-sm-4 am-u-end">
                            <input id="ptime" name="ptime" value="${one:dateToStringShort(sc.ptime)}"
                                   type="text" class="am-input-sm" data-am-datepicker readonly>
                        </div>
                        <div class="am-hide-sm-only am-u-md-6 mz-admin-input-desc-fixed"> * 必填</div>
                    </div>
                    <div class="am-g am-margin-top">
                        <label for="pprice" class="am-u-sm-2 am-form-label mz-admin-label-fixed">入库价格</label>
                        <div class="am-u-sm-4 am-u-end">
                            <input id="pprice" name="pprice" value="${sc.pprice}" type="text" class="am-input-sm" placeholder="RMB">
                        </div>
                        <div class="am-hide-sm-only am-u-md-6 mz-admin-input-desc-fixed"> * 必填</div>
                    </div>
                    <div class="am-g am-margin-top">
                        <label for="sprice" class="am-u-sm-2 am-form-label mz-admin-label-fixed">零售价格</label>
                        <div class="am-u-sm-4 am-u-end">
                            <input id="sprice" name="sprice" value="${sc.sprice}" type="text" class="am-input-sm" placeholder="RMB">
                        </div>
                        <div class="am-hide-sm-only am-u-md-6 mz-admin-input-desc-fixed"> * 必填</div>
                    </div>
                    <div class="am-g am-margin-top-sm">
                        <label for="imgtext" class="am-u-sm-2 am-form-label mz-admin-label-fixed">商品图片</label>
                        <div class="am-u-sm-4 am-u-end">
                            <input id="imgtext" type="text" style="cursor: pointer"
                                   class="am-input-sm" readonly="readonly" placeholder="点击选择图片...">
                        </div>
                        <div class="am-hide-sm-only am-u-md-6 mz-admin-input-desc-fixed"> * 必填</div>
                    </div>
                    <c:if test="${sptList != null}">
                    <div class="am-g am-margin-top-sm">
                        <div class="am-u-sm-2 am-form-label mz-admin-label-fixed">尺码标准</div>
                        <div class="am-u-sm-4 am-u-end">
                            <div class="am-btn-group" data-am-button>
                                <c:forEach items="${sptList}" var="sd" varStatus="status">
                                    <c:if test="${sd.name == sc.type}">
                                    <label id="labelSizeStandard-${sd.name}" class="am-btn am-btn-default am-btn-xs">
                                        <input type="radio" readonly> ${sd.title}
                                    </label>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="am-hide-sm-only am-u-md-6 mz-admin-input-desc-fixed"> * 必填</div>
                    </div>
                    </c:if>
                    <div class="am-g am-margin-top-sm">
                        <label for="more" class="am-u-sm-2 am-form-label mz-admin-label-fixed">尺码详情</label>
                        <div class="am-u-sm-7 am-u-end">
                            <textarea id="more" name="more" rows="5" class="am-input-sm"><%=SkuMoreHelper.convert2Text(sc.getMoreList())%></textarea>
                        </div>
                    </div>
                    <div class="am-g am-margin-top-sm">
                        <label for="sizeSample" class="am-u-sm-2 am-form-label mz-admin-label-fixed">尺码详情样例</label>
                        <div class="am-u-sm-4 am-u-end">
                            <textarea id="sizeSample" name="sizeSample" rows="5" class="am-input-sm" readonly>S###小号###0###备注备注备注
M###中号###0###备注备注备注
L###大号###0###备注备注备注
XL###特大号###0###备注备注备注
XXL###超大号###0###备注备注备注</textarea>
                        </div>
                        <div class="am-u-sm-5 am-u-end">
                            <textarea rows="5" class="am-input-sm" readonly>填写说明：
1、一行一个尺码，不同列用###隔开；
2、填写内容依次尺码、数量、备注三项。</textarea>
                        </div>
                        <div style="position: absolute;left:750px; top:30px; padding:0;">
                            <img src="${base}/gimg/sku/${sc.img}" alt="${sc.img}" style="max-height:230px; width:auto"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="am-margin">
            <button id="btnBack" type="button" class="am-btn am-btn-xs">返 回</button>&nbsp;&nbsp;
            <button id="btnSave" type="button" class="am-btn am-btn-primary am-btn-xs">保 存</button>
        </div>
        </form>
    </div>
    <!-- content end -->
</div>
<%@ include file="../base/loading.jsp" %>


<script src="${base}/component/jquery/2.1.3/jquery.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/dist/js/amazeui.min.js"></script>
<script src="${base}/component/amazeui/2.2.1/assets/js/app.js"></script>
<script src="${base}/component/aralejs/upload/1.2.0/uploader_with_form_name.js"></script>
<script src="${base}/component/raw/raw.prototype.js"></script>
<script src="${base}/component/raw/raw.util.js"></script>
<script src="${base}/assets/js/mess.sidebar.js"></script>
<script src="${base}/assets/js/mess.error.js"></script>
<script src="${base}/assets/js/mz.sku.prop.js"></script>

<script>
    $(function () {
        mess.sidebar.load(mess.sidebar.bars.SKU);
        initDatepicker();   //初始化时间控件
        initSizeStanderd(); //初始化当前的sizeStandard

        //绑定事件：文件上传。
        var uploader = new Uploader({
            trigger: '#imgtext',
            name: 'inputFile',
            action: '${base}/stock/sku/upd.io',
            accept: 'image/*',
            multiple: true,
            error: function(file) {
                $("#frmFile").empty();
                postUploadFailed(null);
                raw.wc.loading.hide();
            },
            success: function(response) {
                if (mess.error.isErrorPage(response)) {
                    postUploadFailed(response);
                    raw.wc.loading.hide();
                } else {
                    document.location.href = "${base}/stock/sku/list.io";
                }
            }
        }).change(function(filename) {
            $('#imgtext').val(filename[0].name);//添加文件后将文件名传给页面元素
        });
        //绑定事件：提交save表单
        $('#btnSave').click(function() {
            raw.wc.loading.show();
            prepearFormData();
            uploader.submit();
        });
        //绑定事件：返回上一页面
        $("#btnBack").click(function() {
            document.location.href = "${base}/stock/sku/list.io";
        });
    });

    /**
     * 初始化日期控件,并设置默认日期为当前日期
     */
    function initDatepicker() {
        var now = raw.util.date.now();
        var ptime = $("#ptime");
        ptime.attr("value", now.format("yyyy-MM-dd"));
        ptime.datepicker({
            onRender: function(date) {
                //禁止今天之后的日期
                return date.valueOf() > now.valueOf() ? 'am-disabled' : '';
            }
        });
    }

    /**
     * 初始化当前的sizeStandard。设置checked
     */
    function initSizeStanderd() {
        $("#labelSizeStandard-" + "${sc.type}").addClass("am-active");
    }

    /**
     * 准备表单提交要用的数据
     */
    function prepearFormData() {
        var id = '<input name="id" type="hidden" value="${sc.id}">';
        var sid = '<input name="sid" type="hidden" value="${sc.sid}">';
        var model = '<input name="model" type="hidden" value="' + $("#model").val() + '">';
        var type = '<input name="type" type="hidden" value="${sc.type}">';
        var ptime = '<input name="ptime" type="hidden" value="' + $("#ptime").val() + '">';
        var pprice = '<input name="pprice" type="hidden" value="' + $("#pprice").val() + '">';
        var sprice = '<input name="sprice" type="hidden" value="' + $("#sprice").val() + '">';
        var img = '<input name="img" type="hidden" value="${sc.img}">';
        var ctime = '<input name="ctime" type="hidden" value="${one:dateToStringLong(sc.ctime)}">';
        var state = '<input name="state" type="hidden" value="${sc.state}">';
        var goodsId = '<input name="goodsId" type="hidden" value="${sc.goodsId}">';
        var more = '<input name="more" type="hidden" value="' + $("#more").val() + '">';
        var form = $('#frmFile');
        form.append(id);
        form.append(sid);
        form.append(model);
        form.append(type);
        form.append(ptime);
        form.append(pprice);
        form.append(sprice);
        form.append(img);
        form.append(ctime);
        form.append(state);
        form.append(goodsId);
        form.append(more);
    }

    function postUploadFailed(response) {
        $("#frmFile").empty();
        var alertHtml = mess.error.amAlertDanger(response, "mz-error-msg");
        $(".am-tabs-bd").prepend(alertHtml);
        $("html, body").animate({ scrollTop: 0 }, 120); //滚动到顶部，这样才能看见。。。
    }

</script>

</body>
</html>
