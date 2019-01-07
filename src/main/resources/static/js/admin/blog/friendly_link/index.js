$(function () {
    /**
     * 加载友链列表
     */
    $('#friendlyLinkDatagrid').datagrid({
        url: '/admin/blog/friendlyLink/findFriendlyLinkList',
        method: 'get',
        loadFilter: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.resultCode == 1) {
                return data.friendlyLinkList;
            }
        }
    });

    /**
     * 点击搜索查询友链列表
     */
    $(document).on('click', '#friendlyLinkDatagridSearchBtn', function () {
        $('#friendlyLinkDatagrid').datagrid({
            queryParams: $('#friendlyLinkDatagridSearchForm').serializeJSON()
        });
        $('#articleCommentDatagrid').datagrid('reload');
    });

    /**
     * 回车提交友链分页搜索表单
     */
    $('#friendlyLinkDatagridSearchForm input').bind('keyup', function (event) {
        if (event.keyCode == '13') {
            $('#friendlyLinkDatagridSearchBtn').trigger('click');
        }
    });

    /**
     * 友链详情对话框设置
     */
    $('#friendlyLinkDetailDlg').dialog({
        onOpen: function () {
            //清空表单
            $('#friendlyLinkDetailForm').form('reset');
        }
    });

    /**
     * 点击添加显示友链详情对话框
     */
    $(document).on('click', '#friendlyLinkDatagridToolbar .addFriendlyLinkBtn', function () {
        $('#friendlyLinkDetailForm input[name=operation]').val('add');
        $('#friendlyLinkDetailDlgToolBar .save-btn').show();//显示保存按钮
        $('#friendlyLinkDetailDlg').dialog('open');
    });

    /**
     * 点击保存添加或修改友情链接
     */
    $(document).on('click', '#friendlyLinkDetailDlgToolBar .save-btn', function () {
        //判断添加或修改
        var oper = $('#friendlyLinkDetailForm input[name=operation]').val();
        //非删除或修改时不提交表单
        if (oper == '') {
            return false;
        }
        if ($('#friendlyLinkDetailForm').form('validate')) {
            //添加或修改的url
            var url;
            if (oper == 'add') {//添加url
                url = '/admin/blog/friendlyLink/addFriendlyLink';
            } else if (oper == 'edit') {//修改url
                url = '/admin/blog/friendlyLink/editFriendlyLinkByFriendlyLinkId';
            }
            $.ajax({
                url: url,
                type: 'post',
                data: $('#friendlyLinkDetailForm').serializeJSON(),
                success: function (data) {
                    if (data.resultCode == -1 || data.resultCode == 0) {
                        $.messager.alert('错误提示', data.notice, 'error');
                    } else if (data.resultCode == 1) {
                        $.messager.alert('消息通知', '保存成功', 'info');
                        $('#friendlyLinkDetailDlg').dialog('close');
                        $('#friendlyLinkDatagrid').datagrid('reload');
                    }
                },
                error: function () {
                    $.messager.alert('错误提示', '保存友情链接失败，请稍后重试或联系管理员！', 'error');
                }
            });
        }
    });

    /**
     * 回车提交友链搜索表单和详情表单
     */
    $('#friendlyLinkDetailForm input').bind('keyup', function (event) {
        if (event.keyCode == '13') {
            $('#friendlyLinkDetailDlgToolBar .save-btn').trigger('click');
        }
    });

    /**
     * 点击关闭友情链接详情对话框
     */
    $(document).on('click', '#friendlyLinkDetailDlgToolBar .cancel-btn', function () {
        $('#friendlyLinkDetailDlg').dialog('close');
    });
});

/**
 * 点击修改友情链接详情展示
 * @param friendlyLinkId 友链编号
 */
function openFriendlyLinkDetailDlgOfEdit(friendlyLinkId) {
    $('#friendlyLinkDetailForm input[name=operation]').val('edit');
    $('#friendlyLinkDetailDlgToolBar .save-btn').show();//显示保存按钮
    $('#friendlyLinkDetailForm input[name=friendlyLinkId]').val(friendlyLinkId);//友情链接编号
    //打开详情对话框，显示详情
    openFriendlyLinkDetailDlg(friendlyLinkId);
}

/**
 * 点击详情友链详情展示
 * @param friendlyLinkId 友链编号
 */
function showFriendlyLinkDetail(friendlyLinkId) {
    $('#friendlyLinkDetailForm input[name=operation]').val('');
    $('#friendlyLinkDetailDlgToolBar .save-btn').hide();//影藏保存按钮
    //打开详情对话框，显示详情
    openFriendlyLinkDetailDlg(friendlyLinkId);
}

/**
 * 友链详情请求展示
 * @param friendlyLinkId 友链编号
 */
function openFriendlyLinkDetailDlg(friendlyLinkId) {
    $('#friendlyLinkDetailDlg').dialog('open');
    $.ajax({
        url: '/admin/blog/friendlyLink/findFriendlyLinkByFriendlyLinkId',
        type: 'get',
        data: {friendlyLinkId: friendlyLinkId},
        success: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.resultCode == 1) {
                //回显
                $('#friendlyLinkDetailForm').form('load', data.friendlyLinkDetail);
            }
        },
        error: function () {
            $.messager.alert('错误提示', '获取友情链接详情失败，请稍后重试或联系管理员！', 'error');
        }
    });
}

/**
 * 格式化友链列表显示列
 * @param value
 * @return {string}
 */
function formatterFriendlyLinkDatagridIsShow(value) {
    return value ? "是" : "否";
}

/**
 * 根据编号删除友链
 * @param friendlyLinkId 友链编号
 */
function removeFriendlyLinkByLinkId(friendlyLinkId) {
    $.messager.confirm('删除提示', '确认删除？', function (r) {
        if (r) {
            $.ajax({
                url: '/admin/blog/friendlyLink/removeFriendlyLinkByFriendlyLinkId',
                type: 'post',
                data: {friendlyLinkId: friendlyLinkId},
                success: function (data) {
                    if (data.resultCode == -1 || data.resultCode == 0) {
                        $.messager.alert('错误提示', data.notice, 'error');
                    } else if (data.resultCode == 1) {
                        $('#friendlyLinkDatagrid').datagrid('reload');
                    }
                },
                error: function () {
                    $.messager.alert('错误提示', '删除友情链接失败，请稍后重试或联系管理员！', 'error');
                }
            });
        }
    });
}

/**
 * 表单控件验证框验证
 */
$.extend($.fn.validatebox.defaults.rules, {
    //排序号
    checkSortNo: {
        validator: function (value) {
            var reg = /^\d+$/;
            return reg.test(value);
        },
        message: '请填写整数'
    }
});