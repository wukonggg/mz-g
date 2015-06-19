/*! MessJS | mess.pagination.js | 0.1.0 | ajax工具类 | by wukong(wukonggg@139.com) */
/** 依赖：raw.util.js - raw.util.string */

window.mess = window.mess || {};
window.mess.error = window.mess.error || {};

/**
 * 检查相应是否是错误页面
 */
window.mess.error.isErrorPage = function (response) {
    return response.indexOf("detailMessage") > 0;
};

/**
 * 从response获取（错误）信息并声称alert html string
 *
 * @param response
 * @param id alert信息div的id
 * @returns {string}
 */
window.mess.error.amAlertDanger = function (response, id) {
    var msg = this.pickupErrorMsg(response);
    var html = '<div'
        + ' class="am-alert am-alert-danger am-input-sm mz-am-alert-fixed"'
        + ' data-am-alert>'
        + '<button type="button" class="am-close">&times;</button>'
        + '<p id="@id" >&nbsp;&nbsp;@msg</p>'
        + '</div>';

    if (undefined === id || null === id || "" === id) {
        html = html.replace("@id", "mz-error-msg");
    } else {
        html = html.replace("@id", id);
    }
    return html.replace("@msg", msg);
};

/**
 * 从response获取（错误）信息
 *
 * @param response
 * @returns {string}
 */
window.mess.error.pickupErrorMsg = function (response) {
    if (raw.util.string.isBlank(response) || raw.util.string.isBlank(response.msg)) {
        return "出错啦！快去找悟空!";
    }
    return response.msg;
};
