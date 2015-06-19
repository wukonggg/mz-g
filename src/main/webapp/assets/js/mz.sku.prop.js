/*! mz.JS | mz.sku.prop.js | 0.1.0 | 和app业务相关的js | by wukong(wukonggg@139.com) */

window.mz = window.mz || {};
window.mz.sku = window.mz.sku || {};
window.mz.sku.prop = window.mz.sku.prop || {};


/**
 * 载入尺码模板
 *
 * @param contextPath contextPath
 * @param cateCode cateCode
 * @param skuDictItem skuDictItem
 * @param skuDictName skuDictName
 * @param textareaId textarea的id
 * @param errorMsg errorMsg
 */
window.mz.sku.prop.loadTypeTemplate = function (contextPath, cateCode, skuDictItem, skuDictName, textareaId, errorMsg) {
    console.log("cateCode=" + cateCode);
    console.log("skuDictItem=" + skuDictItem);
    console.log("skuDictName=" + skuDictName);
    console.log("textareaId=" + textareaId);
    $.ajax({
        type: 'POST',
        url: contextPath + "/sku/prop/loadTypeTemplate.io?cateCode="
            + cateCode + "&item=" + skuDictItem + "&name=" + skuDictName,
        dataType: "text",
        success: function (data) {
            console.log("data=\n" + data);
            console.log("textareaIddd=" + textareaId);
            $("#" + textareaId).text(data);
        },
        error: function (e) {
            alert(JSON.stringify(e));
//            var msg = (errorMsg === undefined || "" === errorMsg) ? "出错啦！快去找悟空！" : errorMsg;
//            alert(msg);
        }
    });

};