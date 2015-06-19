/*! MessJS | mess.select_category.js | 0.1.0 | 选择商品类别JS | by wukong(wukonggg@139.com) */
/** 依赖：jquery.js - ajax、dom等 */

window.mz = window.mz || {};

/**
 * 载入商品品类选项
 *
 * @param contextPath contextPath
 * @param selectId 页面select元素的id
 * @param errorMsg 错误提示信息
 * @param currCateCode 当前选中的cateCode，非必须
 * @param pcode 父节点id
 */
window.mz.loadCategory = function(contextPath, selectId, errorMsg, currCateCode, pcode) {
  $.ajax({
    type: 'POST',
    url: contextPath + "/category/list.io?pcode=" + pcode,
    dataType: "json",
    success: function(data){
//      raw.log.debug("typeof data: " + typeof data);
//      raw.log.debug("JSON.stringify(data): " + JSON.stringify(data));
      var template = "<option value='#code#' selected>#title#</option>  ";
      var options = "";
      $.each(data, function(i, lin){
//        raw.log.debug("JSON.stringify(data[i]): " + JSON.stringify(data[i]));
//        raw.log.debug("JSON.stringify(lin): " + JSON.stringify(lin));
        var option = template.replace("#code#", lin.code);
        option = option.replace("#title#", lin.title);
        if (lin.code != currCateCode) {
          option = option.replace("selected", "");
        }
        options = options + option;
      });
//      raw.log.debug("options: " + options);
      $("#" + selectId).append(options);
    },
    error: function() {
      var msg = (errorMsg === undefined || ""=== errorMsg) ? "出错啦！快去找悟空！" : errorMsg;
      alert(msg);
    }
  });
};
