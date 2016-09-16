/*! MessJS | mess.sidebar.js | 0.1.0 | sidebar折叠 | by wukong(wukonggg@139.com) */
/** 依赖：jQuery */

window.mess = window.mess || {};
window.mess.sidebar = window.mess.sidebar || {};


window.mess.sidebar.bars = {
    SALE : "collapse-nav-sale",
    SALE_BUY : "collapse-nav-sale-buy",
    SALE_CART : "collapse-nav-sale-cart",
    SALE_ORDER : "collapse-nav-sale-order",
    SKU : "collapse-nav-sku",
    SKU_GOODS : "collapse-nav-sku-goods",
    SKU_STOCK : "collapse-nav-sku-stock",
    SKU_REST : "collapse-nav-sku-rest",
    CUSTOMER : "collapse-nav-cust"
};

/**
 * 载入时打开传入的节点，折叠其他节点
 * @param displayId 需要展开的一级节点
 */
window.mess.sidebar.load = function(displayId) {
    var node = $("#" + displayId);
    node.removeClass("am-collapse");
    node.addClass("am-in");
};

/**
 * 载入时打开传入的节点，折叠其他节点
 * @param displayId 需要展开的一级节点
 * @param highlightId 需要高亮的id
 */
window.mess.sidebar.load = function(displayId, highlightId) {
    var node = $("#" + displayId);
    node.removeClass("am-collapse");
    node.addClass("am-in");

    //$("#" + highlightId).siblings().css("background-color","#fff");
    $("#" + highlightId).css("background-color","gainsboro");

};
