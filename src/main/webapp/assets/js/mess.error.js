/*! MessJS | mess.pagination.js | 0.1.0 | ajax工具类 | by wukong(wukonggg@139.com) */
/** 依赖：raw.util.js - raw.util.string */

window.mess = window.mess || {};
window.mess.error = window.mess.error || {};
window.mess.error.type = window.mess.error.type || {};
window.mess.error.type.danger = "mz-alert-danger";


/**
 * 从response获取（错误）信息并声称alert html string
 *
 * @param type mess.error.type.xxx
 * @param msg
 * @param id alert信息div的id
 * @returns {string}
 */
window.mess.error.genAmzDangerAlert = function (type, msg, id) {
    if (type != window.mess.error.type.danger) {
        alert("暂不支持！");
        return "";
    }
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

    if (undefined === msg || null === msg || "" === msg) {
        html = html.replace("@msg", "出错啦！快去找悟空!");
    } else {
        html = html.replace("@msg", msg)
    }

    return html;
};