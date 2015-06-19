/*! RawJS | raw.prototype.js | 0.1.0 | 为针对原生js对象的prototype扩展，没有命名空间raw | by wukong(wukonggg@139.com) */

/* trim的都没测试！！！！！！ */
String.prototype.trim = function() {
  return this.replace(/(^\s*)|(\s*$)/g, "");
};

String.prototype.trimLeft = function(){
  return this.replace(/(^\s*)/g,"");
};
String.prototype.trimRight = function(){
  return this.replace(/(\s*$)/g,"");
};

/**
 * array的扩展
 *
 * @param s
 */
Array.prototype.remove = function (s) {
  for (var i = 0; i < this.length; i++) {
    if (s == this[i])
      this.splice(i, 1);
  }
};

/**
 * 对Date的扩展，将 Date 转化为指定格式的String
 * @param fmt 格式
 */
Date.prototype.format = function (fmt) { //author: meizz
  var o = {
    "M+": this.getMonth() + 1, //月份
    "d+": this.getDate(), //日
    "h+": this.getHours(), //小时
    "m+": this.getMinutes(), //分
    "s+": this.getSeconds(), //秒
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
    "S": this.getMilliseconds() //毫秒
  };
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  }
  for (var k in o) {
    if (new RegExp("(" + k + ")").test(fmt)) {
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    }
  }
  return fmt;
  // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
  // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
  // 例子：
  // (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
  // (new Date()).format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
};
