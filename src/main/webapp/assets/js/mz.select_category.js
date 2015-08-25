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


/**
 * 载入商品品类选项到按钮组
 *
 * @param params json格式参数：{contextPath: contextPath, selectId: 页面select元素的id, errorMsg: 错误提示信息, currCateCodes 当前选中的cateCode，非必须, pcode: 父节点id}
 */
window.mz.loadCategoryIntoButtonGroup = function(params) {
    var contextPath = params.contextPath;
    var errorMsg = params.errorMsg;
    var currCateCodes = params.currCateCodes;
    var pcode = params.pcode;
    var selectId = params.selectId;

    $.ajax({
        type: 'POST',
        url: contextPath + "/category/list.io?pcode=" + pcode,
        dataType: "json",
        success: function(data){
            var template = '<label name="lblCateCodes" class="am-btn am-btn-default mz-tooltip" title="#title#"><input type="checkbox" value="#code#"> #simple_title#</label>';
            var buttons = "";
            $.each(data, function(i, lin){
                var button = template.replace("#code#", lin.code).replace("#title#", lin.title).replace("#simple_title#", lin.title.substring(0,1));
                buttons = buttons + button;
            });
            $("#" + selectId).append(buttons);
        },
        error: function() {
            var msg = (errorMsg === undefined || ""=== errorMsg) ? "出错啦！快去找悟空！" : errorMsg;
            alert(msg);
        }
    });

    //TODO -OPT 等待动态生成的catecodes加载完成后再去设置点击状态。暂时先用timeout
    setTimeout(function(){
        var lblCateCodes = $("label[name='lblCateCodes']");
        lblCateCodes.each(function(i) {
            if (currCateCodes.indexOf($(this).val()) >= 0) {
                $(this).trigger("click");
            }
        });
        var cc = ", " + currCateCodes;
        lblCateCodes.click(function () {
            var val = $(this).children().val();
            console.log("val=" + val);

            if (cc.indexOf(val) < 0) {
                cc = cc + ", " + val;
            } else {
                cc = cc.replace(", " + val, "");
            }
            console.log("cc=" + cc);
            $("#cateCodes").val(cc.substring(1));
        });
    },500);
};

