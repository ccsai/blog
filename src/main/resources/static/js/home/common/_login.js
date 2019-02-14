/*
* 登录、注册、找回密码
* */
$(function () {

    //登录模态框打开
    $('#login').on('show.bs.modal', function () {
        $('#login-form').resetForm();
    });

    //注册模态框打开
    $('#register').on('show.bs.modal', function () {
        $('#register-form button[type=submit]').attr('disabled', false);
        $('#register-form').resetForm();
    });

    //忘记密码模态框打开
    $('#forget-pwd').on('show.bs.modal', function () {
        //只显示忘记密码表单一
        $('#forget-pwd-user').show();
        $('#forget-pwd-reset').hide();
        $('#forget-pwd-user-form').resetForm();
        $('#forget-pwd-reset-form').clearForm(true);
    });

    //修改用户信息模态框打开
    $('#modify-user-info').on('show.bs.modal', function () {
        $('#modify-user-info-form').resetForm();
        //显示当前用户信息
        $.ajax({
            url: '/s.u/findcurUserInfo',
            type: 'get',
            success: function (data) {
                if (data.resultCode == -1 || data.resultCode == 0) {
                    layer.msg('获取用户信息失败！', {icon: 2});
                } else if (data.resultCode == 1) {
                    //用户信息
                    var userInfo = data.userInfo;
                    $.each(userInfo, function (i, ui) {
                        if (ui == null)
                            userInfo[i] = '';
                    });
                    $('#modify-user-info-form').loadJSON(userInfo);
                }
            },
            error: function () {
                layer.msg('获取用户信息失败！', {icon: 2});
            }
        });
    });

    /**
     * 登录-->注册
     */
    $(document).on('click', '.register-href', function () {
        $('#register').modal('show');
        $('#login').modal('hide');
    });

    /**
     * 注册-->登录
     * 忘记密码-->登录
     */
    $(document).on('click', '.login-href', function () {
        $('#login').modal('show');
        $('#register').modal('hide');
        $('#forget-pwd').modal('hide');
    });

    /**
     * 登录-->忘记密码
     */
    $(document).on('click', '.forget-pwd-href', function () {
        $('#forget-pwd').modal('show');
        $('#login').modal('hide');
    });

    //登录提交
    $('#login-form').ajaxForm({
        url: '/login',
        type: 'post',
        success: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                layer.msg(data.notice, {icon: 2});
            } else if (data.resultCode == 1) {
                //获取当前相对路径
                var relPath = Utils.getUrlRelativePath();
                if (relPath == '/g.lm/list') {
                    window.location.reload();
                } else {
                    $('#login').modal('hide');
                    $('#nav-login-div').html('<span>欢迎&nbsp;' + data.userInfo.loginName + '</span>&nbsp;|&nbsp;\n' +
                        '                    <a class="text-warning logout-href" href="javascript:void(0)">注销</a>&nbsp;|&nbsp;\n' +
                        '                    <a class="text-warning modify-href" href="javascript:void(0)" data-toggle="modal"\n' +
                        '                       data-target="#modify-user-info">修改</a>');
                }
            }
            //获取登录用户信息
            getLoginUserInfo();
        },
        error: function () {
            layer.msg('登录失败！', {icon: 2});
        }
    });

    /**
     * 退出帐号
     */
    $(document).on('click', '#nav-login-div .logout-href', function () {
        layer.confirm('确认退出当前用户？', {icon: 3, title: '提示'}, function (index) {
            $.ajax({
                url: '/logOut',
                type: 'get',
                success: function (data) {
                    if (data.resultCode == -1 || data.resultCode == 0) {
                        layer.msg('退出失败！', {icon: 2});
                    } else if (data.resultCode == 1) {
                        //获取当前相对路径
                        var relPath = Utils.getUrlRelativePath();
                        if (relPath == '/g.lm/list') {
                            window.location.reload();
                        } else {
                            layer.msg('退出成功！', {icon: 1});
                            $('#nav-login-div').html('<a class="text-warning login-href" href="javascript:void(0)" data-toggle="modal"\n' +
                                '                       data-target="#login">登录</a>&nbsp;|&nbsp;\n' +
                                '                    <a class="text-warning register-href" href="javascript:void(0)" data-toggle="modal"\n' +
                                '                       data-target="#register">注册</a>');
                        }
                    }
                    //获取登录用户信息
                    getLoginUserInfo();
                },
                error: function () {
                    layer.msg('退出失败！', {icon: 2});
                }
            });
            layer.close(index);
        });
    });

    //验证注册表单
    $('#register-form').validate({
        errorPlacement: function (error, element) {
            $(element).closest('form')
                .find("label[for='" + element.attr("id") + "']")
                .append(error);
        },
        errorElement: 'span',
        rules: {
            loginName: {
                required: true,
                rangelength: [1, 20]
            },
            password: {
                required: true,
                rangelength: [6, 20]
            },
            twicePassword: {
                required: true,
                equalTo: '#r-pwd'
            },
            phone: {
                required: true,
                checkPhone: true
            },
            email: {
                required: true,
                email: true
            }
        },
        messages: {
            loginName: {
                required: '（用户名不能为空）',
                rangelength: '（用户名长度为1到20之间）'
            },
            password: {
                required: '（密码不能为空）',
                rangelength: '（密码长度为6到20之间）'
            },
            twicePassword: {
                required: '（密码不能为空）',
                equalTo: '（两次密码不一致）'
            },
            phone: {
                required: '（手机号码不能为空）',
                checkPhone: '（手机号码格式不正确）'
            },
            email: {
                required: '（邮箱不能为空）',
                email: '（邮箱格式不正确）'
            }
        }
    });

    //注册提交
    $('#register-form').ajaxForm({
        url: '/g.u/register',
        type: 'post',
        beforeSubmit: function () {
            //禁用按钮
            $('#register-form button[type=submit]').attr('disabled', true);
        },
        success: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $('#register-form button[type=submit]').attr('disabled', false);
                layer.msg(data.notice, {icon: 2});
            } else if (data.resultCode == 1) {
                $('#register').modal('hide');
                layer.msg('注册成功', {icon: 1});
            }
        },
        error: function () {
            layer.msg('注册失败', {icon: 2});
        }
    });

    //验证修改信息表单
    $('#modify-user-info-form').validate({
        errorPlacement: function (error, element) {
            $(element).closest('form')
                .find("label[for='" + element.attr("id") + "']")
                .append(error);
        },
        errorElement: 'span',
        rules: {
            loginName: {
                required: true,
                rangelength: [1, 20]
            },
            password: {
                required: false,
                rangelength: [6, 20]
            },
            twicePassword: {
                required: false,
                equalTo: '#mui-pwd'
            },
            phone: {
                required: true,
                checkPhone: true
            },
            email: {
                required: true,
                email: true
            }
        },
        messages: {
            loginName: {
                required: '（用户名不能为空）',
                rangelength: '（用户名长度为1到20之间）'
            },
            password: {
                rangelength: '（密码长度为6到20之间）'
            },
            twicePassword: {
                equalTo: '（两次密码不一致）'
            },
            phone: {
                required: '（手机号码不能为空）',
                checkPhone: '（手机号码格式不正确）'
            },
            email: {
                required: '（邮箱不能为空）',
                email: '（邮箱格式不正确）'
            }
        }
    });

    //修改信息表单提交
    $('#modify-user-info-form').ajaxForm({
        url: '/s.u/editCurUserInfo',
        type: 'post',
        success: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                layer.msg(data.notice, {icon: 2});
            } else if (data.resultCode == 1) {
                $('#modify-user-info').modal('hide');
                layer.msg('修改成功', {icon: 1});
                $('#nav-login-div').html('<span>欢迎&nbsp;' + data.userInfo.loginName + '</span>&nbsp;|&nbsp;\n' +
                    '                    <a class="text-warning logout-href" href="javascript:void(0)">注销</a>&nbsp;|&nbsp;\n' +
                    '                    <a class="text-warning modify-href" href="javascript:void(0)" data-toggle="modal"\n' +
                    '                       data-target="#modify-user-info">修改</a>');
            }
        },
        error: function () {
            layer.msg('修改失败', {icon: 2});
        }
    });

    //找回用户
    $('#forget-pwd-user-form').ajaxForm({
        url: '/g.u/findBackAccount',
        type: 'get',
        success: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                layer.msg(data.notice, {icon: 2});
            } else if (data.resultCode == 1) {
                $('#forget-pwd-user').hide();
                $('#forget-pwd-reset').show();
                $('#forget-pwd-reset-form input[name=loginName]').val(data.userInfo.loginName);
                $('#forget-pwd-reset-form input[name=userId]').val(data.userInfo.userId);
            }
        },
        error: function () {
            layer.msg('请求参数错误', {icon: 2});
        }
    });

    //忘记密码找回后重新设置密码表单验证
    $('#forget-pwd-reset-form').validate({
        errorPlacement: function (error, element) {
            $(element).closest('form')
                .find("label[for='" + element.attr("id") + "']")
                .append(error);
        },
        errorElement: 'span',
        rules: {
            loginName: {
                required: true,
                rangelength: [1, 20]
            },
            password: {
                required: true,
                rangelength: [6, 20]
            },
            twicePassword: {
                required: true,
                equalTo: '#fpr-pwd'
            }
        },
        messages: {
            loginName: {
                required: '（用户名不能为空）',
                rangelength: '（用户名长度为1到20之间）'
            },
            password: {
                required: '（密码不能为空）',
                rangelength: '（密码长度为6到20之间）'
            },
            twicePassword: {
                required: '（密码不能为空）',
                equalTo: '（两次密码不一致）'
            }
        }
    });

    //找回用户后重置密码
    $('#forget-pwd-reset-form').ajaxForm({
        url: '/g.u/modifyUserInfoByForgetPwd',
        type: 'post',
        success: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                layer.msg(data.notice, {icon: 2});
            } else if (data.resultCode == 1) {
                $('#forget-pwd').modal('hide');
                layer.msg('找回密码成功', {icon: 1});
            }
        },
        error: function () {
            layer.msg('修改用户信息失败', {icon: 2});
        }
    });
});