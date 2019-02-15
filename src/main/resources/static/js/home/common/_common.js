/*
* 前台公共 js
* */

//登录用户信息
var userInfo = null;
$(function () {
    //获取登录用户信息
    getLoginUserInfo();
});

/**
 * jquery validate自定义验证方法
 */
jQuery.validator.addMethod('checkPhone', function (value, element) {
    if (/^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\d{8}$/.test(value) || /^1[34578][0-9]\d{8}$/.test(value)) {
        return true;
    }
    return false;
}, '（电话号码格式不正确）');

function getLoginUserInfo() {
    $.ajax({
        url: '/admin/guest/findLoginUserInfo',
        type: 'get',
        success: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.resultCode == 1) {
                userInfo = data.userInfo;
            }
        },
        error: function () {
            $.messager.alert('错误提示', '用户登录信息获取异常', 'error');
        }
    });
}