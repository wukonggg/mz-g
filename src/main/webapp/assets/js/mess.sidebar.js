/*! MessJS | mess.sidebar.js | 0.1.0 | sidebar折叠 | by wukong(wukonggg@139.com) */
/** 依赖：jQuery */

window.mess = window.mess || {};
window.mess.sidebar = window.mess.sidebar || {};


window.mess.sidebar.bars = {SALE:"collapse-nav-sale", SKU:"collapse-nav-sku"};

/**
 * 载入时打开传入的节点，折叠其他节点
 * @param displayId
 */
window.mess.sidebar.load = function(displayId) {
    var node = $("#" + displayId);
    node.removeClass("am-collapse");
    node.addClass("am-in");
};