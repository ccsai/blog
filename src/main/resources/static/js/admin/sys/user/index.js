$(function () {
    /**
     * 加载用户分页列表
     */
    $('#userDatagrid').datagrid({
        url: '/admin/sys/user/findUserListByPage',
        method: 'get',
        queryParams: $('#userDatagridSearchForm').serializeJSON(),
        loadFilter: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.resultCode == 1) {
                var userPageInfo = data.userPageInfo;
                //分页数据
                var pageData = {
                    total: userPageInfo.total,
                    rows: userPageInfo.list
                };
                return pageData;
            }
        }
    });

    /**
     * 点击搜索查询用户列表
     */
    $(document).on('click', '#userDatagridSearchBtn', function () {
        $('#userDatagrid').datagrid({
            queryParams: $('#userDatagridSearchForm').serializeJSON(),
        });
        $('#userDatagrid').datagrid('reload');
    });

    /**
     * 用户详情对话框设置
     */
    $('#userDetailDlg').dialog({
        onOpen: function () {
            //清空表单
            $('#userDetailForm').form('reset');
        }
    });

    /**
     * 回车提交用户搜索表单
     */
    $('#userDatagridSearchForm input').bind('keyup', function (event) {
        if (event.keyCode == '13') {
            $('#userDatagridSearchBtn').trigger('click');
        }
    });

    /**
     * 点击添加显示用户详情对话框
     */
    $(document).on('click', '#userDatagridToolbar .addUserBtn', function () {
        $('#userDetailForm input[name=operation]').val('add');
        $('#userDetailDlgToolBar .save-btn').show();//显示保存按钮
        $('#userDetailDlg').dialog('open');
    });

    /**
     * 回车提交用户详情表单
     */
    $('#userDetailForm').bind('keyup', function (event) {
        if (event.keyCode == '13') {
            $('#userDetailDlgToolBar .save-btn').trigger('click');
        }
    });

    /**
     * 点击保存添加或修改用户
     */
    $(document).on('click', '#userDetailDlgToolBar .save-btn', function () {
        //判断添加或修改
        var oper = $('#userDetailForm input[name=operation]').val();
        //非删除或修改时不提交表单
        if (oper == '') {
            return false;
        }
        //表单验证并提交
        if ($('#userDetailForm').form('validate')) {
            //添加或修改的url
            var url;
            if (oper == 'add') {//添加url
                url = '/admin/sys/user/addUser';
            } else if (oper == 'edit') {//修改url
                url = '/admin/sys/user/editUserByUserId';
            }
            $.ajax({
                url: url,
                type: 'post',
                data: $('#userDetailForm').serializeJSON(),
                success: function (data) {
                    if (data.resultCode == -1 || data.resultCode == 0) {
                        $.messager.alert('错误提示', data.notice, 'error');
                    } else if (data.resultCode == 1) {
                        $.messager.alert('消息通知', '保存成功', 'info');
                        $('#userDetailDlg').dialog('close');
                        $('#userDatagrid').datagrid('reload');
                    }
                },
                error: function () {
                    $.messager.alert('错误提示', '保存用户失败，请稍后重试或联系管理员！', 'error');
                }
            });
        }
    });

    /**
     * 点击保存添加用户角色
     */
    $(document).on('click', '#userRoleAuthDlgToolBar .save-btn', function () {
        //用户角色编号关联对象
        var userIdRoleIdsObj = {};
        userIdRoleIdsObj.userId = $('#userRoleAuthDlg input[name=userId]').val();
        //获取选的角色
        selectRoles = $('#userRoleAuthDatagrid').datagrid('getSelections');
        //角色编号数组
        var roleIds = [];
        if (selectRoles.length > 0) {
            $.each(selectRoles, function (i, selectRole) {
                roleIds.push(selectRole.roleId);
            });
        }
        userIdRoleIdsObj.roleIds = JSON.stringify(roleIds);
        //保存
        $.ajax({
            url: '/admin/sys/user/addUserRoleAuth',
            type: 'post',
            data: userIdRoleIdsObj,
            success: function (data) {
                if (data.resultCode == -1 || data.resultCode == 0) {
                    $.messager.alert('错误提示', data.notice, 'error');
                } else if (data.resultCode == 1) {
                    $.messager.alert('消息通知', '保存成功', 'info');
                    $('#userRoleAuthDlg').dialog('close');
                }
            },
            error: function () {
                $.messager.alert('错误提示', '添加角色失败，请稍后重试或联系管理员！', 'error');
            }
        });
    });

    /**
     * 点击关闭用户详情对话框
     */
    $(document).on('click', '#userDetailDlgToolBar .cancel-btn', function () {
        $('#userDetailDlg').dialog('close');
    });

    /**
     * 点击关闭用户添加角色的对话框
     */
    $(document).on('click', '#userRoleAuthDlgToolBar .cancel-btn', function () {
        $('#userRoleAuthDlg').dialog('close');
    });

});

/**
 * 点击修改查看用户详情
 * @param userId 用户编号
 */
function openUserDetailDlgOfEdit(userId) {
    $('#userDetailForm input[name=operation]').val('edit');
    $('#userDetailDlgToolBar .save-btn').show();//显示保存按钮
    $('#userDetailForm input[name=roleId]').val(userId);//用户编号
    //打开详情对话框，显示详情
    openUserDetailDlg(userId);
}

/**
 * 点击详情
 * @param userId
 */
function openUserDetailDlgOfDetail(userId) {
    $('#userDetailForm input[name=operation]').val('');
    $('#userDetailDlgToolBar .save-btn').hide();//影藏保存按钮
    //打开详情对话框，显示详情
    openUserDetailDlg(userId);
}

/**
 * 打开并加载用户详情对话框
 * @param userId
 */
function openUserDetailDlg(userId) {
    $('#userDetailDlg').dialog('open');
    $.ajax({
        url: '/admin/sys/user/findUserByUserId',
        type: 'get',
        data: {userId: userId},
        success: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.resultCode == 1) {
                //回显
                $('#userDetailForm').form('load', data.userDetail);
            }
        },
        error: function () {
            $.messager.alert('错误提示', '获取用户详情失败，请稍后重试或联系管理员！', 'error');
        }
    });
}

/**
 * 打开添加角色对话框，根据用户编号查看用户角色关联的详情
 * @param userId 用户编号
 */
function openUserRoleAuthDlg(userId) {
    //打开对话框
    $('#userRoleAuthDlg').dialog('open');
    $('#userRoleAuthDlg input[name=userId]').val(userId);
    $('#userRoleAuthDatagrid').datagrid('unselectAll');
    $('#userRoleAuthDatagrid').datagrid({
        url: '/admin/sys/user/findUserRoleAuthListByUserId',
        method: 'get',
        queryParams: {userId: userId},
        loadFilter: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.resultCode == 1) {
                var roleList = data.roleList;
                if (roleList == null && roleList.length == 0) {
                    return false;
                } else {
                    return roleList;
                }
            }
        },
        onLoadSuccess: function (data) {
            if (data.total > 0) {
                $.each(data.rows, function (i, role) {
                    //选中历史拥有的角色
                    if (role.roleChecked) {
                        $('#userRoleAuthDatagrid').datagrid('selectRecord', role.roleId);
                    }
                });
            }
        }
    });
}

/**
 * 根据用户编号删除用户
 * @param userId 用户编号
 */
function removeUserByRoleId(userId) {
    $.messager.confirm('删除提示', '确认删除？', function (r) {
        if (r) {
            $.ajax({
                url: '/admin/sys/user/removeUserByUserId',
                type: 'post',
                data: {userId: userId},
                success: function (data) {
                    if (data.resultCode == -1 || data.resultCode == 0) {
                        $.messager.alert('错误提示', data.notice, 'error');
                    } else if (data.resultCode == 1) {
                        $('#userDatagrid').datagrid('reload');
                    }
                },
                error: function () {
                    $.messager.alert('错误提示', '删除用户失败，请稍后重试或联系管理员！', 'error');
                }
            });
        }
    });
}

/**
 * 格式化用户类型（是否管理员）
 * @param value
 * @return {string}
 */
function formatterUserLoginType(value) {
    return value ? '是' : '否';
}

/**
 * 格式化用户状态
 * @param value
 * @return {*}
 */
function formatterUserStatus(value) {
    var str;
    switch (value) {
        case 1:
            str = "有效";
            break;
        case 0:
            str = "无效";
            break;
    }
    return str;
}