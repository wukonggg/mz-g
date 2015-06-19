/*! RawJS | raw.util.js | 0.1.0 | 常用工具 | by wukong(wukonggg@139.com) */
/** 注意：无 */

window.raw = window.raw || {};
window.raw.util = window.raw.util || {};

window.raw.util.string = (function (ns, undefined) {
    var isBlank = function (s) {
        return s == undefined || s == null || s.trim() == "";
    };
    return {
        isBlank: isBlank   //如果此字符串为undefined 或 null 或为空串（""），则返回 true
    };
})(window.raw.util.string);

window.raw.util.json = (function (ns, undefined) {
    /**
     * 是否是json数据
     * @param obj
     * @returns {boolean}
     */
    var isJson = function (obj) {
        return typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;;
    };
    return {
        isJson: isJson,
        toString: toString
    };
})(window.raw.util.json);

window.raw.util.date = (function (ns, undefined) {
    var now = function () {
        var base = new Date();
        return new Date(base.getFullYear(), base.getMonth(), base.getDate(), 0, 0, 0, 0);
    };
    return {
        now: now //获取当前时间
    };
})(window.raw.util.date);


function testRawUtil(dt) {
    //dt = {"aa":"bb","cc":"{'1':'2', '3':'4'}"};
    alert(raw.util.jsonToString(dt));
}