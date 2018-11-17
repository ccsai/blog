$(function () {
    /**
     * 加载菜单树
     */
    $('#menuTreegrid').treegrid({
        url: '/admin/sys/menu/findAllMenuTree',
        method: 'get',
        loadFilter: function (data) {
            if (data.menuTree != null && data.resultCode == 1) {
                return data.menuTree;
            } else if (data.menuTree != null && (data.resultCode == 0 || data.resultCode == -1)) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.menuTree == null && data.menuTree.length == 0) {
                return [];
            }
        }
    });

    /**
     * 菜单详情对话框设置
     */
    $('#menuDetailDlg').dialog({
        onOpen: function () {
            //清空表单
            $('#menuDetailForm').form('reset');
            //加载父级菜单选择下拉框
            $('#menuDetailFormPMenuId').combotree({
                url: '/admin/sys/menu/findAllMenuTree',
                method: 'get',
                loadFilter: function (data) {
                    if (data.resultCode == 1) {
                        var menuTree = data.menuTree;
                        var root = [{
                            id: 'root',
                            text: '一级目录',
                            children: menuTree
                        }];
                        return root;
                    } else {
                        $.messager.alert('错误提示', '父级菜单选择下拉列表加载出错！</br>' + data.notice, 'error');
                    }
                }
            });
        }
    });

    /**
     * 点击添加显示菜单表单
     */
    $(document).on('click', '#menuTreegridToolbar .addMenuBtn', function () {
        $('#menuDetailForm input[name=operation]').val('add');
        $('#menuDetailDlgToolBar .save-btn').show();//显示保存按钮
        $('#menuDetailDlg').dialog('open');
    });

    /**
     * 添加或修改菜单
     */
    $(document).on('click', '#menuDetailDlgToolBar .save-btn', function () {
        //判断添加或修改
        var oper = $('#menuDetailForm input[name=operation]').val();
        if (oper == 'add') {//添加
            $('#menuDetailForm').form('submit', {
                url: '/admin/sys/menu/addMenu',
                onSubmit: function () {
                    return $(this).form('validate');
                },
                success: function (data) {
                    if (JSON.parse(data).resultCode == 1) {
                        $.messager.alert('消息通知', '保存成功', 'info');
                        $('#menuDetailDlg').dialog('close');
                        $('#menuTreegrid').treegrid('reload');
                    } else {
                        $.messager.alert('错误提示', data.notice, 'error');
                    }
                }
            });
        } else if (oper == 'edit') {//修改
            $('#menuDetailForm').form('submit', {
                url: '/admin/sys/menu/editMenuByMenuId',
                onSubmit: function () {
                    return $(this).form('validate');
                },
                success: function (data) {
                    if (JSON.parse(data).resultCode == 1) {
                        $.messager.alert('消息提示', '保存成功', 'info');
                        $('#menuDetailDlg').dialog('close');
                        $('#menuTreegrid').treegrid('reload');
                    } else if (JSON.parse(data).resultCode == 0) {
                        $.messager.alert('错误提示', data.notice, 'error');
                    }
                }
            });
        }
    });

    /**
     * 点击关闭菜单详情对话框
     */
    $(document).on('click', '#menuDetailDlgToolBar .cancel-btn', function () {
        $('#menuDetailDlg').dialog('close');
    });
});

/**
 * 点击修改菜单
 * @param menuId
 */
function openMenuDetailDlgOfEdit(menuId) {
    $('#menuDetailForm input[name=operation]').val('edit');
    $('#menuDetailDlgToolBar .save-btn').show();//显示保存按钮
    $('menuDetailForm input[name=menuId]').val(menuId);//菜单编号
    openMenuDetailDlg(menuId);//弹出菜单详情表单
}

/**
 * 点击菜单详情
 * @param menuId
 */
function openMenuDetailDlgOfDetail(menuId) {
    $('#menuDetailDlgToolBar .save-btn').hide();//隐藏保存按钮
    openMenuDetailDlg(menuId);//弹出菜单详情表单
}

/**
 * 弹出菜单详情表单
 * @param menuId 菜单编号
 */
function openMenuDetailDlg(menuId) {
    $('#menuDetailDlg').dialog('open');
    $.ajax({
        url: '/admin/sys/menu/findMenuByMenuId',
        type: 'get',
        data: {menuId: menuId},
        success: function (data) {
            if (data.resultCode == 1) {
                //回显
                $('#menuDetailForm').form('load', data.menuDetail);
            } else {
                $.messager.alert('错误提示', '获取菜单详情失败！</br>' + data.notice, 'error');
            }
        },
        error: function () {
            $.messager.alert('错误提示', '获取菜单详情失败，请稍后重试或联系管理员！', 'error');
        }
    });
}

/**
 * 根据菜单编号删除菜单
 * @param menuId
 */
function removeMenuByMenuId(menuId) {
    $.messager.confirm('删除提示', '确认删除？', function (r) {
        if (r) {
            $.ajax({
                url: '/admin/sys/menu/removeMenuByMenuId',
                type: 'post',
                data: {menuId: menuId},
                success: function (data) {
                    if (data.resultCode == 1) {
                        $('#menuTreegrid').treegrid('reload');
                    } else {
                        $.messager.alert('错误提示', data.notice, 'error');
                    }
                },
                error: function () {
                    $.messager.alert('错误提示', '删除菜单失败，请稍后重试或联系管理员！', 'error');
                }
            });
        }
    });
}

/**
 * 格式化菜单树是否显示列
 * @param value
 * @return {string}
 */
function formatterMenuTreegridMenuIsShow(value) {
    return value == 1 ? "是" : "否";
}

/**
 * 格式化菜单操作栏
 * @param val
 * @param row
 * @return {string}
 */
function formatterMenuTreegridOper(value, row) {
    var strArr = [];
    strArr.push("<a href='javascript:void(0)' onclick='openMenuDetailDlgOfEdit(\"" + row.menuId + "\")'>修改</a>");
    strArr.push("<a href='javascript:void(0)' onclick='openMenuDetailDlgOfDetail(\"" + row.menuId + "\")'>详情</a>");
    strArr.push("<a href='javascript:void(0)' onclick='removeMenuByMenuId(\"" + row.menuId + "\")'>删除</a>");
    return strArr.join(' ');
}