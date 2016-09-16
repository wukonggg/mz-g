/*! mz.JS | mz.cart.js | 0.1.0 | 和cart业务相关的js | by wukong(wukonggg@139.com) */

window.mz = window.mz || {};
window.mz.cart = window.mz.cart || {};

/*
    1. 页面加载
        1.1. initTotalPayment
    2. 数量调整时，重新计算总价和服装类总价
        2.1. 重新计算每个sku的金额（单价*数量）
        2.2. 重新计算总价。
        2.3. 重新计算总价的折扣
        2.4. 根据总价的折扣，重新计算每个商品的单价折扣，

        计算总价
 */


window.mz.cart.biz = (function (ns, undefined) {
    /**
     * 初始化total_payment
     */
    var initTotalPayment = function (cart) {
    };

    return {
        initTotalPayment: initTotalPayment
    };

})(window.mz.cart.biz);


window.mz.cart.funcs = (function (ns, undefined) {
    /**
     * dprice是根据totalPayment来计算的
     */
    var reloadDprice = function () {

    };
    var reloadPayment = function () {

    };
    var reloadTotalPayment = function (carts) {

    };

    return {
        reloadDprice: reloadDprice,
        reloadPayment: reloadPayment,
        reloadTotalPayment: reloadTotalPayment
    };

})(window.mz.cart.funcs);

