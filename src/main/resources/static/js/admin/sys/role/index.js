$(function () {
    /**
     * 加载角色分页列表
     */
    $('#roleDatagrid').datagrid({
        url: '/admin/sys/role/findRoleListByPage',
        method: 'get',
        loadFilter: function (data) {
            if (data.resultCode == 1) {
                var rolePageInfo = data.rolePageInfo;
                //分页数据
                var pageData = {
                    total: rolePageInfo.total,
                    rows: rolePageInfo.list
                };
                return pageData;
            } else {
                $.messager.alert('错误提示', data.notice, 'error');
            }
        }
    });

    /**
     * 点击搜索查询角色列表
     */
    $(document).on('click', '#roleDatagridSearchBtn', function () {
        $('#roleDatagrid').datagrid({
            queryParams: $('#roleDatagridSearchForm').serializeJSON()
        });
        $('#roleDatagrid').datagrid('reload');
    });

    /**
     * 回车提交角色搜索表单
     */
    $('#roleDatagridSearchForm input').bind('keyup', function (event) {
        if (event.keyCode == '13') {
            $('#roleDatagridSearchBtn').trigger('click');
        }
    });

    /**
     * 回车提交角色详情表单
     */
    $('#roleDetailForm').bind('keyup', function (event) {
        if (event.keyCode == '13') {
            $('#roleDetailDlgToolBar .save-btn').trigger('click');
        }
    });

    /**
     * 角色详情对话框设置
     */
    $('#roleDetailDlg').dialog({
        onOpen: function () {
            //清空表单
            $('#roleDetailForm').form('reset');
        }
    });

    /**
     * 点击添加显示角色详情对话框
     */
    $(document).on('click', '#roleDatagridToolbar .addroleBtn', function () {
        $('#roleDetailForm input[name=operation]').val('add');
        $('#roleDetailDlgToolBar .save-btn').show();//显示保存按钮
        $('#roleDetailDlg').dialog('open');
    });

    /**
     * 点击保存添加或修改角色
     */
    $(document).on('click', '#roleDetailDlgToolBar .save-btn', function () {
        //判断添加或修改
        var oper = $('#roleDetailForm input[name=operation]').val();
        //非删除或修改时不提交表单
        if (oper == '') {
            return false;
        }
        //表单验证并提交
        if ($('#roleDetailForm').form('validate')) {
            //添加或修改的url
            var url;
            if (oper == 'add') {//添加url
                url = '/admin/sys/role/addRole';
            } else if (oper == 'edit') {//修改url
                url = '/admin/sys/role/editRoleByRoleId';
            }
            $.ajax({
                url: url,
                type: 'post',
                data: $('#roleDetailForm').serializeJSON(),
                success: function (data) {
                    if (data.resultCode == 1) {
                        $.messager.alert('消息通知', '保存成功', 'info');
                        $('#roleDetailDlg').dialog('close');
                        $('#roleDatagrid').datagrid('reload');
                    } else {
                        $.messager.alert('错误提示', data.notice, 'error');
                    }
                },
                error: function () {
                    $.messager.alert('错误提示', '删除角色失败，请稍后重试或联系管理员！', 'error');
                }
            });

        }
    });

    /**
     * 点击关闭角色详情对话框
     */
    $(document).on('click', '#roleDetailDlgToolBar .cancel-btn', function () {
        $('#roleDetailDlg').dialog('close');
    });

    /**
     * 点击关闭菜单授权对话框
     */
    $(document).on('click', '#roleMenuPermissionDlgToolBar .cancel-btn', function () {
        $('#roleMenuPermissionDlg').dialog('close');
    });
});

/**
 * 点击修改展示角色详情
 * @param roleId 角色编号
 */
function openRoleDetailDlgOfEdit(roleId) {
    $('#roleDetailForm input[name=operation]').val('edit');
    $('#roleDetailDlgToolBar .save-btn').show();//显示保存按钮
    $('#roleDetailForm input[name=roleId]').val(roleId);//角色标号
    //打开详情对话框，显示详情
    openRoleDetailDlg(roleId);
}

/**
 * 点击详情角色详情展示
 * @param roleId 角色编号
 */
function openRoleDetailDlgOfDetail(roleId) {
    $('#roleDetailForm input[name=operation]').val('');
    $('#roleDetailDlgToolBar .save-btn').hide();//影藏保存按钮
    //打开详情对话框，显示详情
    openRoleDetailDlg(roleId);
}

/**
 * 弹出角色详情表单（显示详情）
 * @param roleId 角色编号
 */
function openRoleDetailDlg(roleId) {
    $('#roleDetailDlg').dialog('open');
    $.ajax({
        url: '/admin/sys/role/findRoleByRoleId',
        type: 'get',
        data: {roleId: roleId},
        success: function (data) {
            if (data.resultCode == 1) {
                //回显
                $('#roleDetailForm').form('load', data.roleDetail);
            } else {
                $.messager.alert('错误提示', data.notice, 'error');
            }
        },
        error: function () {
            $.messager.alert('错误提示', '获取角色详情失败，请稍后重试或联系管理员！', 'error');
        }
    });
}

/**
 * 根据角色编号删除角色
 * @param roleId 角色编号
 */
function removeRoleByRoleId(roleId) {
    $.messager.confirm('删除提示', '确认删除？', function (r) {
        if (r) {
            $.ajax({
                url: '/admin/sys/role/removeRoleByRoleId',
                type: 'post',
                data: {roleId: roleId},
                success: function (data) {
                    if (data.resultCode == 1) {
                        $('#roleDatagrid').datagrid('reload');
                    } else {
                        $.messager.alert('错误提示', data.notice, 'error');
                    }
                },
                error: function () {
                    $.messager.alert('错误提示', '删除角色失败，请稍后重试或联系管理员！', 'error');
                }
            });
        }
    });
}

/**
 * 打开菜单授权对话框
 * @param roleId 角色编号
 */
function openRoleMenuPermissionAuthDlg(roleId) {
    $('#roleMenuPermissionDlg').dialog('open');
    console.log(roleId)
    //加载菜单权限树
    $('#menuPermissionTreegrid').treegrid({
        url: '/admin/sys/role/findRoleMenuPermissionAuthTree',
        method: 'get',
        queryParams: {roleId: roleId},
        loadFilter: function (data) {
            if (data.menuTree == null && data.menuTree.length == 0) {
                return [];
            } else if (data.resultCode == 1) {
                console.log(data.menuTree)
                return data.menuTree;
            } else {
                $.messager.alert('错误提示', data.notice, 'error');
            }
        }
    });
}

/**
 * 格式化菜单授权treegrid，权限列表
 * @param value
 * @param row
 * @return {string}
 */
function formatterRoleMenuPermissionList(value,row){
    var strArr = [];
    if (!(row.permissionList == null && row.permissionList.length == 0)){
        $.each(row.permissionList,function (i,permission) {
            strArr.push("<label><input type='checkbox' value='"+permission.permissionId+"'/>" + permission.permissionName + "</label>");
        });
    }
    return strArr.join('&emsp;');
}


/**
 * 表单控件验证框验证
 */
$.extend($.fn.validatebox.defaults.rules, {
    //角色类型
    checkRoleType: {
        validator: function (value) {
            var reg = /(^[bf][bmo]$)|(^su$)/;
            return reg.test(value);
        },
        message: 'su超级管理员。其他（第一位：b后台、f前台；第二位：b基础角色、m菜单角色、o其他角色）'
    }
});