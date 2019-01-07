$(function () {
    /**
     * 加载标签列表
     */
    $('#labelDatagrid').datagrid({
        url: '/admin/blog/label/findLabelList',
        method: 'get',
        loadFilter: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.resultCode == 1) {
                return data.labelList;
            }
        }
    });

    /**
     * 点击搜索查询标签列表
     */
    $(document).on('click', '#labelDatagridSearchBtn', function () {
        $('#labelDatagrid').datagrid({
            queryParams: $('#labelDatagridSearchForm').serializeJSON()
        });
        $('#labelDatagrid').datagrid('reload');
    });

    /**
     * 回车提交标签列表搜索表单
     */
    $('#labelDatagridSearchForm input').bind('keyup', function (event) {
        if (event.keyCode == '13') {
            $('#labelDatagridSearchBtn').trigger('click');
        }
    });

    /**
     * 标签详情对话框设置
     */
    $('#labelDetailDlg').dialog({
        onOpen: function () {
            //清空表单
            $('#labelDetailForm').form('reset');
        }
    });

    /**
     * 点击添加显示标签详情对话框
     */
    $(document).on('click', '#labelDatagridToolbar .addLabelBtn', function () {
        $('#labelDetailForm input[name=operation]').val('add');
        $('#labelDetailDlgToolBar .save-btn').show();//显示保存按钮
        $('#labelDetailDlg').dialog('open');
    });

    /**
     * 点击保存添加或修改标签
     */
    $(document).on('click', '#labelDetailDlgToolBar .save-btn', function () {
        //判断添加或修改
        var oper = $('#labelDetailForm input[name=operation]').val();
        //非删除或修改时不提交表单
        if (oper == '') {
            return false;
        }
        if ($('#labelDetailForm').form('validate')) {
            //添加或修改的url
            var url;
            if (oper == 'add') {//添加url
                url = '/admin/blog/label/addLabel';
            } else if (oper == 'edit') {//修改url
                url = '/admin/blog/label/editLabelByLabelId';
            }
            $.ajax({
                url: url,
                type: 'post',
                data: $('#labelDetailForm').serializeJSON(),
                success: function (data) {
                    if (data.resultCode == -1 || data.resultCode == 0) {
                        $.messager.alert('错误提示', data.notice, 'error');
                    } else if (data.resultCode == 1) {
                        $.messager.alert('消息通知', '保存成功', 'info');
                        $('#labelDetailDlg').dialog('close');
                        $('#labelDatagrid').datagrid('reload');
                    }
                },
                error: function () {
                    $.messager.alert('错误提示', '保存标签失败，请稍后重试或联系管理员！', 'error');
                }
            });
        }
    });

    /**
     * 回车提交标签搜索表单和详情表单
     */
    $('#labelDetailForm input').bind('keyup', function (event) {
        if (event.keyCode == '13') {
            $('#labelDetailDlgToolBar .save-btn').trigger('click');
        }
    });

    /**
     * 点击关闭标签详情对话框
     */
    $(document).on('click', '#labelDetailDlgToolBar .cancel-btn', function () {
        $('#labelDetailDlg').dialog('close');
    });
});

/**
 * 点击修改标签详情展示
 * @param labelId 标签编号
 */
function openLabelDetailDlgOfEdit(labelId) {
    $('#labelDetailForm input[name=operation]').val('edit');
    //显示保存按钮
    $('#labelDetailDlgToolBar .save-btn').show();
    //编号
    $('#labelDetailForm input[name=labelId]').val(labelId);
    //打开详情对话框，显示详情
    openLabelDetailDlg(labelId);
}

/**
 * 显示标签详情
 * @param labelId 标签编号
 */
function showLabelDetail(labelId) {
    $('#labelDetailForm input[name=operation]').val('');
    $('#labelDetailDlgToolBar .save-btn').hide();//影藏保存按钮
    //打开详情对话框，显示详情
    openLabelDetailDlg(labelId);
}

/**
 * 打开表标签详情对话框并显示
 * @param labelId 标签编号
 */
function openLabelDetailDlg(labelId) {
    $('#labelDetailDlg').dialog('open');
    $.ajax({
        url: '/admin/blog/label/findLabelByLabelId',
        type: 'get',
        data: {labelId: labelId},
        success: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.resultCode == 1) {
                //回显
                $('#labelDetailForm').form('load', data.labelDetail);
            }
        },
        error: function () {
            $.messager.alert('错误提示', '获取标签 详情失败，请稍后重试或联系管理员！', 'error');
        }
    });
}

/**
 * 根据编号删除标签
 * @param labelId 标签编号
 */
function removeLabelByLinkId(labelId) {
    $.messager.confirm('删除提示', '确认删除？', function (r) {
        if (r) {
            $.ajax({
                url: '/admin/blog/label/removeLabelByLabelId',
                type: 'post',
                data: {labelId: labelId},
                success: function (data) {
                    if (data.resultCode == -1 || data.resultCode == 0) {
                        $.messager.alert('错误提示', data.notice, 'error');
                    } else if (data.resultCode == 1) {
                        $('#labelDatagrid').datagrid('reload');
                    }
                },
                error: function () {
                    $.messager.alert('错误提示', '删除标签失败，请稍后重试或联系管理员！', 'error');
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