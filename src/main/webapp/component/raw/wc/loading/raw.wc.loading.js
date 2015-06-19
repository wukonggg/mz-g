/*! RawJS | raw.loading.js | 载入 | by wukong(wukonggg@139.com) */

window.raw = window.raw || {};
window.raw.wc = window.raw.wc || {};
window.raw.wc.loading = (function (ns, undefined) {
  var clsOver = "raw-wc-loading-background";
  var clsImg = "raw-wc-loading-img";
  var show = function () {
    document.getElementsByClassName(clsOver)[0].style.display = "block";
    document.getElementsByClassName(clsImg)[0].style.display = "block";
  };
  var hide = function () {
    document.getElementsByClassName(clsOver)[0].style.display = "none";
    document.getElementsByClassName(clsImg)[0].style.display = "none";
  };
  return {
    show: show,
    hide: hide
  };
})(window.raw.wc.loading);
