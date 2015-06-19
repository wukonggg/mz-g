/*! RawJS | raw.log.js | 0.1.0 | log工具 | by wukong(wukonggg@139.com) */
/** 注意：部分方法依赖raw.prototype.js。要用到date格式化的方法 */

window.raw = window.raw || {};
window.raw.log = (function($, undefined){
  /**
   * 原样输出
   * @param msg
   */
  $.raw = function (msg) {
    console.log(msg);
  };

  /**
   * debug输出。
   * @param msg
   */
  $.debug = function (msg) {
    var fmt = "yyyy-MM-dd hh:mm:ss.S";
    console.log("[DEBUG] [" + new Date().format(fmt) + "] " + msg);
  };
  return $;
})(window.raw.log || {});