/*! MessJS | mess.model.js | 0.1.0 | ajax工具类 | by wukong(wukonggg@139.com) */
/** 依赖：jQuery */

window.mess = window.mess || {};
window.mess.modal = window.mess.modal || {};

window.mess.modal.alertDivId = "mz-modal-alert";
window.mess.modal.confirmDivId = "mz-modal-confirm";
window.mess.modal.promptDivId = "mz-modal-prompt";


/**
 * 模态prompt
 * @param modalDivId modal div的dom id
 * @param confirmPostFunction 点击confirm后执行的function。会传一个参数给function，为input的value
 * @param cancelPostFunction 点击cancel后执行的function。会传一个参数给function，为input的value
 */
window.mess.modal.prompt = function (modalDivId, confirmPostFunction, cancelPostFunction) {
    $('#' + modalDivId).modal({
        relatedTarget: this,
        onConfirm: function (e) {
            //alert('你输入的是：' + e.data || '');
            if (null != confirmPostFunction) {
                confirmPostFunction(e);
            }
        },
        onCancel: function (e) {
            if (null != confirmPostFunction) {
                cancelPostFunction(e);
            }
        }
    });
};

/**
 * 模态confirm，并通过Form提交请求。
 *
 * @param modalDivId modelDivId
 * @param submitFormId 提交表单的id
 * @param action 提交表单的action。当action=="ajax"时，不走提交表单，直接返回。
 */
window.mess.modal.confirmAndSubmitForm = function (modalDivId, submitFormId, action) {
    $("#" + modalDivId).modal({
        onConfirm: function (options) {
            var form = $("#" + submitFormId);
            form.attr("action", action);
            form.submit();
        },
        onCancel: function () {
        }
    });
};



/**
 * 模态confirm，并通过ajax提交请求。
 *
 * @param modalDivId modalDivId
 * @param action actin url
 * @param params params in json format
 */
/*
window.mess.modal.confirmAndSubmitAjax = function (modalDivId, action, params) {
    $("#" + modalDivId).modal({
        onConfirm: function (options) {
            $.post(action, params, function (data) {
                if (data != "done") {
                    alert("出错啦！快去找悟空！");
                    console.log(data);
                }
            });
        }, onCancel: function () {
        }
    });
};
*/


 /**
 * 模态confirm
 * @param confirmPostFunction 点击confirm后执行的function。会传一个参数给function，为input的value
 * @param cancelPostFunction 点击cancel后执行的function。会传一个参数给function，为input的value
 */
/*
window.mess.modal.confirm = function (confirmPostFunction, cancelPostFunction, target) {
    $("#" + mess.modal.confirmDivId).modal({
        relatedTarget: target,
        onConfirm: function (options) {
            var $link = $(this.relatedTarget);
            console.log(typeof this.relatedTarget);
            if (null != confirmPostFunction) {
                confirmPostFunction();
            }
        },
        onCancel: function () {
            if (null != cancelPostFunction) {
                cancelPostFunction();
            }
        }
    });
};
*/