$(function () {
    /**
     * 加载角色分页列表
     */
    $('#roleDatagrid').datagrid({
        url: '/admin/sys/role/findRoleListByPage',
        method: 'get',
        loadFilter: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.resultCode == 1) {
                var rolePageInfo = data.rolePageInfo;
                //分页数据
                var pageData = {
                    total: rolePageInfo.total,
                    rows: rolePageInfo.list
                };
                return pageData;
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
                    if (data.resultCode == -1 || data.resultCode == 0) {
                        $.messager.alert('错误提示', data.notice, 'error');
                    } else if (data.resultCode == 1) {
                        $.messager.alert('消息通知', '保存成功', 'info');
                        $('#roleDetailDlg').dialog('close');
                        $('#roleDatagrid').datagrid('reload');
                    }
                },
                error: function () {
                    $.messager.alert('错误提示', '保存角色失败，请稍后重试或联系管理员！', 'error');
                }
            });
        }
    });

    /**
     * 完成菜单授权
     */
    $(document).on('click', '#roleMenuPermissionDlgToolBar .save-btn', function () {
        //获取角色和权限编号
        var formData = $('#roleMenuPermissionForm').serializeJSON();
        if (!formData.permissionIds) {
            formData.permissionIds = [];
        }
        formData.permissionIds = JSON.stringify(formData.permissionIds);
        //获取选中的菜单
        var selectedMemu = $('#menuPermissionTreegrid').treegrid('getSelections');
        //菜单编号数组
        var menuIds = [];
        if (selectedMemu != null && selectedMemu.length != 0) {
            $.each(selectedMemu, function (i, menu) {
                menuIds.push(menu.menuId);
            });
        }
        formData.menuIds = JSON.stringify(menuIds);
        console.log(formData)
        //保存菜单授权
        $.ajax({
            url: '/admin/sys/role/authorizationMenuPermission',
            type: 'post',
            data: formData,
            success: function (data) {
                if (data.resultCode == -1 || data.resultCode == 0) {
                    $.messager.alert('错误提示', data.notice, 'error');
                } else if (data.resultCode == 1) {
                    $.messager.alert('消息通知', '保存成功', 'info');
                    $('#roleMenuPermissionDlg').dialog('close');
                }
            },
            error: function () {
                $.messager.alert('错误提示', '菜单授权失败，请稍后重试或联系管理员！', 'error');
            }

        });
    });

    /**
     * 完成通用授权
     */
    $(document).on('click', '#roleCommonPermissionDlgToolBar .save-btn', function () {
        //角色和权限编号关联对象
        var roleIdPermissionIds = {};
        //角色编号
        roleIdPermissionIds.roleId = $('#roleCommonPermissionForm input[name=roleId]').val();
        //获取选中的权限
        var selectedPermissionRows = $('#commonpermissionDatagrid').datagrid('getSelections');
        //权限编号
        var permissionIds = [];
        if (selectedPermissionRows && selectedPermissionRows.length > 0) {
            $.each(selectedPermissionRows, function (i, permission) {
                permissionIds.push(permission.permissionId);
            });
        }
        roleIdPermissionIds.permissionIds = JSON.stringify(permissionIds);
        //保存通用授权
        $.ajax({
            url: '/admin/sys/role/addRoleCommonPermissionAuth',
            type: 'post',
            data: roleIdPermissionIds,
            success: function (data) {
                if (data.resultCode == -1 || data.resultCode == 0) {
                    $.messager.alert('错误提示', data.notice, 'error');
                } else if (data.resultCode == 1) {
                    $.messager.alert('消息通知', '保存成功', 'info');
                    $('#roleCommonPermissionDlg').dialog('close');
                }
            },
            error: function () {
                $.messager.alert('错误提示', '通用授权失败，请稍后重试或联系管理员！', 'error');
            }

        });
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

    /**
     * 点击关闭通用授权对话框
     */
    $(document).on('click', '#roleCommonPermissionDlgToolBar .cancel-btn', function () {
        $('#roleCommonPermissionDlg').dialog('close');
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
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.resultCode == 1) {
                //回显
                $('#roleDetailForm').form('load', data.roleDetail);
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
                    if (data.resultCode == -1 || data.resultCode == 0) {
                        $.messager.alert('错误提示', data.notice, 'error');
                    } else if (data.resultCode == 1) {
                        $('#roleDatagrid').datagrid('reload');
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
    $('#roleMenuPermissionForm input[name=roleId]').val(roleId);
    $('#menuPermissionTreegrid').treegrid('unselectAll');
    $('#roleMenuPermissionDlg').dialog('open');
    //加载菜单权限树
    $('#menuPermissionTreegrid').treegrid({
        url: '/admin/sys/role/findRoleMenuPermissionAuthTree',
        method: 'get',
        queryParams: {roleId: roleId},
        loadFilter: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.menuTree == null || data.menuTree.length == 0) {
                return [];
            } else if (data.resultCode == 1) {
                return data.menuTree;
            }
        },
        onLoadSuccess: function (row, data) {
            //顶级菜单
            $.each(data, function (i, r) {
                if (r.menuChecked) {
                    //选中顶级菜单
                    $('#menuPermissionTreegrid').treegrid('select', r.id);
                }
                //子孙菜单
                var childrenMenu = $('#menuPermissionTreegrid').treegrid('getChildren', r.id);
                $.each(childrenMenu, function (j, childMenu) {
                    if (childMenu.menuChecked) {
                        //选中子孙菜单
                        $('#menuPermissionTreegrid').treegrid('select', childMenu.id);
                    }
                });
            });
            //获取所有权限复选框
            var permissionCheckboxes = $('#roleMenuPermissionForm input[name="permissionIds[]"]');
            $.each(permissionCheckboxes, function (i, permissionCheckbox) {
                //选中历史菜单权限
                if ($(permissionCheckbox).data('isChecked') && $(permissionCheckbox).data('isChecked') == 1) {
                    $(permissionCheckbox).prop('checked', true);
                }
            });
        },
        onClickRow: function (row) {
            //已经选择的行
            var selectedRows = $('#menuPermissionTreegrid').treegrid('getSelections');
            //判断是否选中标志true为选中
            var isSelect = false;
            $.each(selectedRows, function (i, r) {
                if (row == r) {//选中当前菜单行
                    isSelect = true;
                    return;
                }
            });
            if (isSelect) {//如果选中
                //递归选中菜单节点
                selectParents(row);
                //递归选中所有子菜单行
                selectChildre(row);
            } else {//取消选中
                //递归取消选中父菜单行
                unSelectParents(row, selectedRows);
                //取消所有菜单
                unSelectChildren(row);
            }
        },
    });

    /**
     * 递归选中菜单节点
     * @param row 当前菜单行
     */
    var selectParents = function (row) {
        //父级菜单行
        var parentRow = $('#menuPermissionTreegrid').treegrid('getParent', row.id);
        //选中父节点
        if (parentRow) {
            $('#menuPermissionTreegrid').treegrid('select', parentRow.id);
            selectParents(parentRow);
        }
    }

    /**
     * 递归选中所有子菜单行
     * @param row 当前菜单行
     */
    var selectChildre = function (row) {
        //获取子菜单节点
        var children = $('#menuPermissionTreegrid').treegrid('getChildren', row.id);
        if (children.length > 0) {
            $.each(children, function (i, child) {
                //选中子菜单行
                $('#menuPermissionTreegrid').treegrid('select', child.id);
                selectChildre(child);
            });
        }
    }

    /**
     * 递归取消选中父菜单行
     * @param row 当前菜单行
     * @param selectedRows 除开当前菜单选中行
     * @return {boolean}
     */
    var unSelectParents = function (row, selectedRows) {
        //父级菜单行
        var parentRow = $('#menuPermissionTreegrid').treegrid('getParent', row.id);
        if (parentRow) {//如果有父菜单
            //获取父菜单行的所有子菜单行
            var parentChildrenRows = $('#menuPermissionTreegrid').treegrid('getChildren', parentRow.id);
            //父菜单行的所有子菜单选中标识
            var isBrotherSelected = false;
            //判断父菜单行的所有子菜单是否有选中
            if (parentChildrenRows.length > 0) {
                $.each(parentChildrenRows, function (j, parentChild) {
                    if (parentChild._parentId == parentRow.id && $.inArray(parentChild, selectedRows) >= 0) {
                        isBrotherSelected = true;
                        return;
                    }
                });
            }
            if (isBrotherSelected) {
                return false;
            } else {
                //父菜单取消选中
                $('#menuPermissionTreegrid').treegrid('unselect', parentRow.id);
                unSelectParents(parentRow, selectedRows);
            }
        }
    }

    /**
     * 取消所有菜单
     * @param row 当前菜单行
     */
    var unSelectChildren = function (row) {
        //获取子菜单行
        var childrenRows = $('#menuPermissionTreegrid').treegrid('getChildren', row.id);
        //取消子菜单的选中
        if (childrenRows.length > 0) {
            $.each(childrenRows, function (i, child) {
                $('#menuPermissionTreegrid').treegrid('unselect', child.id);
            });
        }
    }
}

/**
 * 打开通用授权对话框
 * @param roleId 角色编号
 */
function openRoleCommonPermissionAuthDlg(roleId) {
    $('#commonpermissionDatagrid').datagrid('unselectAll');
    $('#roleCommonPermissionForm input[name=roleId]').val(roleId);
    $('#roleCommonPermissionDlg').dialog('open');
    //加载通用权限列表
    $('#commonpermissionDatagrid').datagrid({
        url: '/admin/sys/role/findRoleCommonPermissionAuthListByRole',
        method: 'get',
        queryParams: {roleId: roleId},
        loadFilter: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.permissionList == null || data.permissionList.length == 0) {
                return [];
            } else if (data.resultCode == 1) {
                return data.permissionList;
            }
        },
        onLoadSuccess: function (data) {
            //通用权限集合
            var commonPermissionList = data.rows;
            if (commonPermissionList && commonPermissionList.length > 0) {
                $.each(commonPermissionList, function (i, permission) {
                    //选中历史通用权限
                    if (permission.permissionChecked) {
                        $('#commonpermissionDatagrid').datagrid('selectRecord', permission.permissionId);
                    }
                });
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
function formatterRoleMenuPermissionList(value, row) {
    var strArr = [];
    if (row.permissionList != null && row.permissionList.length != 0) {
        $.each(row.permissionList, function (i, permission) {
            strArr.push("<label onclick='javascript:event.stopPropagation()'><input type='checkbox' data-is-checked='" + permission.permissionChecked + "' name='permissionIds[]' value='" + permission.permissionId + "'/>" + permission.permissionName + "</label>");
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