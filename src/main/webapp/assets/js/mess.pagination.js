/*! MessJS | mess.pagination.js | 0.1.0 | 分页JS，配合admin_pagination.jsp使用 | by wukong(wukonggg@139.com) */
/** 依赖：jquery.js - dom等; */


window.mess = window.mess || {};
window.mess.pagination = (function (ns, undefined) {
  var pageNum = $('#pageNum');
  var pageSize = $('#pageSize');
  var recordCount = $('#recordCount');

  var hasNext = function () {
    return pageNum.val() < Math.ceil(recordCount.val() / pageSize.val());
  };

  var hasPre = function () {
    return pageNum.val() > 1;
  };

  var load = function () {
    //控制pre按钮的样式
    var pre = $('#li-page-pre');
    if (hasPre()) {
      pre.removeClass("am-disabled");
    } else {
      pre.addClass("am-disabled");
    }
    //控制next按钮的样式
    var next = $('#li-page-next');
    if (hasNext()) {
      next.removeClass("am-disabled");
    } else {
      next.addClass("am-disabled");
    }

    $('.page-pre').click(function () {
      if (hasPre()) {
        pageNum.val(parseInt(pageNum.val()) - 1);
        $('#frmMain').submit();
      }
    });
    $('.page-next').click(function () {
      if (hasNext()) {
        pageNum.val(parseInt(pageNum.val()) + 1);
        $('#frmMain').submit();
      }
    });
  };
  return {
    load: load
  };
})(window.mess.pagination);
