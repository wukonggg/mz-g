<%@ page import="band.wukong.mz.g.category.bean.Category" %>
<%--
  Desc: admin sidebar
  User: wukong(wukonggg@139.com)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="admin-sidebar">
    <ul class="am-list admin-sidebar-list">
        <%--<li><a href="${base}/gallery/landing.io"><span class="am-icon-home"></span> Home</a></li>--%>
        <li><a href="${base}/main.io"><span class="am-icon-home"></span> HOME</a></li>
        <li class="admin-parent">
            <a class="am-cf" data-am-collapse="{target: '#collapse-nav-sale'}">
                <span class="am-icon-briefcase"></span> SALE
                <span class="am-icon-angle-right am-fr am-margin-right"></span>
            </a>
            <ul id="collapse-nav-sale" class="am-list admin-sidebar-sub am-collapse">
                <li><a href="${base}/sale/order/buy.io"><span class="am-icon-cart-plus"></span> Buy</a></li>
                <li><a href="${base}/sale/order/cart/list.io"><span class="am-icon-cart-arrow-down"></span> Cart</a></li>
                <li><a href="${base}/sale/order/list.io"><span class="am-icon-history"></span> Order</a></li>
            </ul>
        </li>
        <li class="admin-parent">
            <a class="am-cf " data-am-collapse="{target: '#collapse-nav-sku'}">
                <span class="am-icon-pencil-square-o"></span> STOCK
                <span class="am-icon-angle-right am-fr am-margin-right"></span>
            </a>
            <ul id="collapse-nav-sku" class="am-list admin-sidebar-sub am-collapse">
                <li><a href="${base}/stock/goods/list.io"><span class="am-icon-th"></span> Goods</a></li>
                <li><a href="${base}/stock/sku/list.io?cateCode=<%=Category.CATE_CODE_TYPE_SIMPLE%>"><span class="am-icon-table"></span> SKU</a></li>
            </ul>
        </li>
        <li><a href="${base}/customer/cust/list.io"><span class="am-icon-users"></span> CUSTOMER</a></li>
        <li><a href="${base}/logout.io"><span class="am-icon-sign-out"></span> EXIT</a></li>
    </ul>

    <div class="am-panel am-panel-default admin-sidebar-panel">
        <div class="am-panel-bd">
            <p><span class="am-icon-bookmark"></span> Words：</p>
            <p><span style="font-weight: bold">@Yolanda：</span>
                <br>时光静好，与君语；细水流年，与君同。
                <span style="font-weight: bold">From:Wukong</span>
            </p>
        </div>
    </div>
</div>
