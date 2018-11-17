$(function () {

    /**
     * 后台登录
     */
    $(document).on('click', '#loginBtn', function () {
        $.ajax({
            url: '/login',
            type: 'post',
            data: $('#loginForm').serializeJSON(),
            success: function (result) {
                if (result.resultCode == 1) {
                    //登录成功
                    window.location.href = '/admin/index'
                } else {
                    //登录失败
                    $('#loginFailMessageTd').text(result.notice);
                }
            },
            error: function () {
                $('#loginFailMessageTd').text('请求出错，登录失败！');
            }
        });
    });

    /**
     * 回车提交登录表单
     */
    $('#loginForm input').bind('keyup', function (event) {
        if (event.keyCode == '13') {
            $('#loginBtn').trigger('click');
        }
    });
});