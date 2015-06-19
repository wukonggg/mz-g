<%@ page import="band.wukong.mz.g.sku.bean.Goods" %>
<%--
  Desc: goods mod
  User: wukong(wukonggg@139.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/one.tld" prefix="one"%>

<%
    Goods g = (Goods) request.getAttribute("obj");
    pageContext.setAttribute("g", g);
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
            <jsp:param name="info1" value="STOCK"/>
            <jsp:param name="info2" value="Goods"/>
            <jsp:param name="info3" value="modify"/>
        </jsp:include>

        <form id="fmMain" name="fmMain" class="am-form am-form-horizontal">
        <div class="am-tabs am-margin">
            <ul class="am-tabs-nav am-nav am-nav-tabs">
                <li class="am-active"><a href="#tab3">Basic</a></li>
            </ul>

            <div class="am-tabs-bd">
                <div class="am-tab-panel am-fade am-in am-active" id="tab3">
                    <div class="am-g am-margin-top-sm">
                        <label for="gname" class="am-u-sm-2 am-form-label mz-admin-label-fixed">商品名称</label>
                        <div class="am-u-sm-4 am-u-end">
                            <input id="gname" name="gname" value="${g.gname}" type="text" class="am-input-sm">
                        </div>
                    </div>
                    <div class="am-g am-margin-top-sm">
                        <label for="cateCode" class="am-u-sm-2 am-form-label mz-admin-label-fixed">所属类别</label>
                        <div class="am-u-sm-4 am-u-end">
                            <select id="cateCode" name="cateCode" class="am-input-sm mz-admin-select-fixed">
                            </select>
                        </div>
                    </div>
                    <div class="am-g am-margin-top-sm">
                        <label for="words" class="am-u-sm-2 am-form-label mz-admin-label-fixed">商品描述</label>
                        <div class="am-u-sm-7 am-u-end">
                            <textarea id="words" name="words" rows="3" placeholder="写点介绍文字吧！">${g.words}</textarea>
                        </div>
                    </div>
                    <div class="am-g am-margin-top-sm">
                        <label for="imgtext" class="am-u-sm-2 am-form-label mz-admin-label-fixed">商品图片</label>
                        <div class="am-u-sm-4 am-u-end">
                            <input id="imgtext" type="text" style="cursor: pointer"
                                   class="am-input-sm" readonly="readonly" placeholder="点击选择图片...">
                        </div>
                    </div>
                    <div style="position: absolute;left:750px; top:30px; padding:0;">
                        <img src="${base}/gimg/goods/${g.img}" alt="${g.gname}" style="max-height:230px; width:auto"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="am-margin">
            <button id="btnList" type="button" class="am-btn am-btn-xs">返 回</button>&nbsp;&nbsp;
            <button id="btnSubmit" type="button" class="am-btn am-btn-primary am-btn-xs">更 新</button>
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
<script src="${base}/component/raw/raw.util.js"></script>
<script src="${base}/assets/js/mess.sidebar.js"></script>
<script src="${base}/assets/js/mess.error.js"></script>
<script src="${base}/assets/js/mz.select_category.js"></script>

<script>
    $(function () {
        mess.sidebar.load(mess.sidebar.bars.SKU);
        var uploader = new Uploader({
            trigger: '#imgtext',
            name: 'inputFile',
            action: '${base}/stock/goods/upd.io',
            accept: 'image/*',
            multiple: true,
            error: function(e) {
                $("#frmFile").empty();
                raw.wc.loading.hide();
                postUploadFailed(null);
            },
            success: function(response) {
                if (mess.error.isErrorPage(response)) {
                    postUploadFailed(response);
                    raw.wc.loading.hide();
                } else {
                    document.location.href = "${base}/stock/goods/list.io";
                }
            }
        }).change(function(filename) {
            $('#imgtext').val(filename[0].name);//添加文件后将文件名传给页面元素
        });
        $('#btnSubmit').click(function() {
            raw.wc.loading.show();
            prepearFormData();
            uploader.submit();
        });
        $("#btnList").click(function() {
            document.location.href = "${base}/stock/goods/list.io";
        });
        mz.loadCategory("${base}", "cateCode", "查询商品分类时出错啦！快去找悟空！", "${g.cateCode}", "<%=Category.CATE_CODE_TYPE_SIMPLE%>");
    });

    function prepearFormData() {
        var id = '<input name="id" type="hidden" value="${g.id}">';
        var cateCode = '<input name="cateCode" type="hidden" value="' + $("#cateCode").val() + '">';
        var gname = '<input name="gname" type="hidden" value="' + $("#gname").val() + '">';
        var words = '<input name="words" type="hidden" value="' + $("#words").val() + '">';
        var img = '<input name="img" type="hidden" value="${g.img}">';
        var ctime = '<input name="ctime" type="hidden" value="${one:dateToStringLong(g.ctime)}">';
        var state = '<input name="state" type="hidden" value="${g.state}">';
        var form = $('#frmFile');
        form.append(id);
        form.append(gname);
        form.append(cateCode);
        form.append(words);
        form.append(ctime);
        form.append(img);
        form.append(state);
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
