$(function () {

    /**
     * 权限列表分页查询
     */
    $('#permissionDatagrid').datagrid({
        url: '/admin/sys/permission/findPermissionsListByPage',
        method: 'get',
        loadFilter: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.resultCode == 1) {
                var permissionPageInfo = data.permissionPageInfo;
                //分页数据
                var pageData = {
                    total: permissionPageInfo.total,
                    rows: permissionPageInfo.list
                };
                return pageData;
            }
        }
    });

    /**
     * 权限列表查询下拉框
     */
    $('#permissionDatagridSearchFormMenuId').combotree(createMenuTreeOfcombotree());

    /**
     * 点击搜索查询权限列表
     */
    $(document).on('click', '#permissionDatagridSearchBtn', function () {
        $('#permissionDatagrid').datagrid({
            queryParams: $('#permissionDatagridSearchForm').serializeJSON()
        });
        $('#permissionDatagrid').datagrid('reload');
    });

    /**
     * 回车提交权限搜索表单和详情表单
     */
    $('#permissionDatagridSearchForm input').bind('keyup', function (event) {
        if (event.keyCode == '13') {
            $('#permissionDatagridSearchBtn').trigger('click');
        }
    });

    /**
     * 回车提交权限详情表单
     */
    $('#permissionDetailForm').bind('keyup', function (event) {
        if (event.keyCode == '13') {
            $('#permissionDetailDlgToolBar .save-btn').trigger('click');
        }
    });

    /**
     * 权限详情对话框设置
     */
    $('#permissionDetailDlg').dialog({
        onOpen: function () {
            //清空表单
            $('#permissionDetailForm').form('reset');
            $('#permissionDetailFormMenuId').combotree(createMenuTreeOfcombotree());
        }
    });

    /**
     * 点击添加显示权限详情对话框
     */
    $(document).on('click', '#permissionDatagridToolbar .addPermissionBtn', function () {
        $('#permissionDetailForm input[name=operation]').val('add');
        $('#permissionDetailDlgToolBar .save-btn').show();//显示保存按钮
        $('#permissionDetailDlg').dialog('open');
    });

    /**
     * 点击保存添加或修改权限
     */
    $(document).on('click', '#permissionDetailDlgToolBar .save-btn', function () {
        //判断添加或修改
        var oper = $('#permissionDetailForm input[name=operation]').val();
        //非删除或修改时不提交表单
        if (oper == '') {
            return false;
        }
        if ($('#permissionDetailForm').form('validate')) {
            //添加或修改的url
            var url;
            if (oper == 'add') {//添加url
                url = '/admin/sys/permission/addPermission';
            } else if (oper == 'edit') {//修改url
                url = '/admin/sys/permission/editPermissionByPermissionId';
            }
            $.ajax({
                url: url,
                type: 'post',
                data: $('#permissionDetailForm').serializeJSON(),
                success: function (data) {
                    if (data.resultCode == -1 || data.resultCode == 0) {
                        $.messager.alert('错误提示', data.notice, 'error');
                    } else if (data.resultCode == 1) {
                        $.messager.alert('消息通知', '保存成功', 'info');
                        $('#permissionDetailDlg').dialog('close');
                        $('#permissionDatagrid').datagrid('reload');
                    }
                },
                error: function () {
                    $.messager.alert('错误提示', '保存权限失败，请稍后重试或联系管理员！', 'error');
                }
            });
        }
    });

    /**
     * 点击关闭权限详情对话框
     */
    $(document).on('click', '#permissionDetailDlgToolBar .cancel-btn', function () {
        $('#permissionDetailDlg').dialog('close');
    });
});

/**
 * easyui菜单树下拉框的生成
 * @return {{url: string, method: string, loadFilter: loadFilter}}
 */
function createMenuTreeOfcombotree() {
    return {
        url: '/admin/sys/menu/findAllMenuTree',
        method: 'get',
        loadFilter: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', '菜单选择下拉列表加载出错！</br>' + data.notice, 'error');
            } else if (data.resultCode == 1) {
                var menuTree = data.menuTree;
                var root = [{
                    id: '',
                    text: '无菜单',
                    children: menuTree
                }];
                return root;
            }
        }
    }
}

/**
 * 点击修改权限详情展示
 * @param permissionId 权限编号
 */
function openPermissionDetailDlgOfEdit(permissionId) {
    $('#permissionDetailForm input[name=operation]').val('edit');
    $('#permissionDetailDlgToolBar .save-btn').show();//显示保存按钮
    $('#permissionDetailForm input[name=permissionId]').val(permissionId);//权限标号
    //打开详情对话框，显示详情
    openPermissionDetailDlg(permissionId);
}

/**
 * 点击详情权限详情展示
 * @param permissionId
 */
function openPermissionDetailDlgOfDetail(permissionId) {
    $('#permissionDetailForm input[name=operation]').val('');
    $('#permissionDetailDlgToolBar .save-btn').hide();//影藏保存按钮
    //打开详情对话框，显示详情
    openPermissionDetailDlg(permissionId);
}

/**
 * 弹出权限详情表单（显示详情）
 * @param permissionId 权限编号
 */
function openPermissionDetailDlg(permissionId) {
    $('#permissionDetailDlg').dialog('open');
    $.ajax({
        url: '/admin/sys/permission/findPermissionByPermissionId',
        type: 'get',
        data: {permissionId: permissionId},
        success: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.resultCode == 1) {
                //回显
                $('#permissionDetailForm').form('load', data.permissionDetail);
            }
        },
        error: function () {
            $.messager.alert('错误提示', '获取权限详情失败，请稍后重试或联系管理员！', 'error');
        }
    });
}

/**
 * 根据权限编号删除权限
 * @param permissionId 权限编号
 */
function removePermissionByPermissionId(permissionId) {
    $.messager.confirm('删除提示', '确认删除？', function (r) {
        if (r) {
            $.ajax({
                url: '/admin/sys/permission/removePermissionByPermissionId',
                type: 'post',
                data: {permissionId: permissionId},
                success: function (data) {
                    if (data.resultCode == -1 || data.resultCode == 0) {
                        $.messager.alert('错误提示', data.notice, 'error');
                    } else if (data.resultCode == 1) {
                        $('#permissionDatagrid').datagrid('reload');
                    }
                },
                error: function () {
                    $.messager.alert('错误提示', '删除权限失败，请稍后重试或联系管理员！', 'error');
                }
            });
        }
    });
}

/**
 * 表单控件验证框验证
 */
$.extend($.fn.validatebox.defaults.rules, {
    //权限urlType
    checkPermissionUrlType: {
        validator: function (value) {
            var reg = /^[bf][mbdo]$/;
            return reg.test(value);
        },
        message: '第一位：b后台、f前台；第二位：m访问菜单、b基础功能、d属于菜单、o其他'
    }
});