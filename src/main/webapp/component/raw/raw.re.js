/*! RawJS | raw.re.js | 0.1.0 | 正则表达式 | by wukong(wukonggg@139.com) */

window.raw = window.raw || {};
window.raw.re = window.raw.re || {};


window.raw.re.pattern = (function (ns, undefined) {
    var number =  /^[0-9]+.?[0-9]*$/;
    var integer_gt0  = /^[1-9]+[0-9]*]*$/;
    return {
        number: number,
        integer_gt0: integer_gt0
    };
})(window.raw.re.pattern);
