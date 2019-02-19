$(function () {
    //加载Ueditor（发送留言）
    var ue = UE.getEditor('leaveMessageUE', {zIndex: 999999, maximumWords: 5000});

    /**
     * 加载用户分页列表
     */
    $('#lmUserDatagrid').datagrid({
        url: '/admin/blog/leaveMessage/findIsHaveNewLeaveMessageByManager',
        method: 'get',
        queryParams: $('#lmUserDatagridSearchForm').serializeJSON(),
        rowStyler: function (index, row) {
            if (row.leaveMessageNumber > 0) {
                return 'background-color:#3399CC';
            }
        },
        loadFilter: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.resultCode == 1) {
                return data.userList;
            }
        },
        //选中用户加载聊天框和内容
        onSelect: function (rowIndex, rowData) {
            $('#checkAllLeaveMessageBtn').prop('checked', false);
            //获取选中的用户名
            var userName = rowData.loginName;
            var realName = rowData.realName;
            var userNameAndRealName = '';
            if (userName) {
                userNameAndRealName += userName;
            }
            if (realName) {
                userNameAndRealName += '（' + realName + ')';
            }
            //显示留言的用户名加姓名
            $('#leaveMessageUserName').html(userNameAndRealName);
            //显示留言信息，影藏未选择用户前的提示
            $('#leaveMessageNotice').hide();
            $('#leaveMessageDiv').show();

            //获取留言用户的用户编号
            var sendUser = rowData.userId;
            var leaveMessageParam = {
                targetUser: 'manager',
                sendUser: sendUser
            };
            //加载留言列表
            if (rowData.leaveMessageNumber > 0) {
                reloadLeaveMessageList(leaveMessageParam, true);
            } else {
                reloadLeaveMessageList(leaveMessageParam, false);
            }
        }
    });

    /**
     * 点击搜索查询用户列表
     */
    $(document).on('click', '#lmUserDatagridSearchBtn', function () {
        $('#lmUserDatagrid').datagrid({
            queryParams: $('#lmUserDatagridSearchForm').serializeJSON(),
        });
        $('#lmUserDatagrid').datagrid('reload');
    });

    /**
     * 回车提交用户搜索表单
     */
    $('#lmUserDatagridSearchForm input').bind('keyup', function (event) {
        if (event.keyCode == '13') {
            $('#lmUserDatagridSearchBtn').trigger('click');
        }
    });

    /**
     * 刷新留言列表
     */
    $(document).on('click', '#reloadLeaveMessageBtn', function () {
        //获取选中用户数据行
        var selectUser = $('#lmUserDatagrid').datagrid('getSelected');
        //获取留言列表请求参数
        var leaveMessageParam = {
            targetUser: 'manager',
            sendUser: selectUser.userId
        };
        reloadLeaveMessageList(leaveMessageParam, false);
    });

    /**
     * 删除留言
     */
    $(document).on('click', '#removeLeaveMessageBtn', function () {
        //获取要删除的留言
        var removeLeaveMessageCheckboxs = $('#leaveMessageList input[name=leaveMessageRemoveCheckbox]:checked');
        if (removeLeaveMessageCheckboxs == null || removeLeaveMessageCheckboxs.length == 0) {
            $.messager.alert('提示', '请选择要删除的留言', 'warning');
            return false;
        }
        //要删除的留言编号数组
        var leaveMessageIdsArr = [];
        $.each(removeLeaveMessageCheckboxs, function (i, r) {
            leaveMessageIdsArr.push(r.value);
        });
        //所有编号拼接成字符串
        leaveMessageIds = "'" + leaveMessageIdsArr.join("','") + "'";
        //删除留言
        $.messager.confirm('删除提示', '是否删除选中留言', function (r) {
            if (r) {
                $.ajax({
                    url: '/admin/blog/leaveMessage/removeLeaveMessagesByLeaveMessageIds',
                    type: 'post',
                    data: {leaveMessageIds: leaveMessageIds},
                    success: function (data) {
                        if (data.resultCode == -1 || data.resultCode == 0) {
                            $.messager.alert('错误提示', data.notice, 'error');
                        } else if (data.resultCode == 1) {
                            //获取选中用户数据行
                            var selectUser = $('#lmUserDatagrid').datagrid('getSelected');
                            //获取留言列表请求参数
                            var leaveMessageParam = {
                                targetUser: 'manager',
                                sendUser: selectUser.userId
                            };
                            //刷新留言列表
                            reloadLeaveMessageList(leaveMessageParam, false);
                        }
                    },
                    error: function () {
                        $.messager.alert('错误提示', '留言删除失败，请稍后重试或联系管理员！', 'error');
                    }
                });
            }
        });
    });

    /**
     * 弹出发送留言对话框
     */
    $(document).on('click', '#sendLeaveMessageBtn', function () {
        $('#leaveMessageEditorDlg').dialog('open');
    });

    /**
     * 关闭留言发送对话框
     */
    $(document).on('click', '#leaveMessageEditorDlgToolBar .cancel-btn', function () {
        $('#leaveMessageEditorDlg').dialog('close');
    });

    /**
     * 发送留言对话框打开时清空ueditor
     */
    $('#leaveMessageEditorDlg').dialog({
        onOpen: function () {
            ue.setContent('');
        }
    });

    /**
     * 保存要发送的留言
     */
    $(document).on('click', '#leaveMessageEditorDlgToolBar .save-btn', function () {
        //获取要发送的内容
        var leaveMessageContent = ue.getContent();
        if (leaveMessageContent == '') {
            $.messager.alert('警告', '你输入的字符长度不能为0！', 'warning');
        }
        //获取选中的接受人
        var targetUser = $('#lmUserDatagrid').datagrid('getSelected').userId;
        //获取所有oss的key
        var ossKeys = Utils.getOssKeyFromUE(leaveMessageContent);
        //留言提交信息
        var leaveMessageInfo = {
            message: leaveMessageContent,
            targetUser: targetUser,
            sendUser: 'manager',
            ossKeys: ossKeys
        };
        //发留言
        $.ajax({
            url: '/admin/blog/leaveMessage/leaveMessage',
            type: 'post',
            data: leaveMessageInfo,
            success: function (data) {
                if (data.resultCode == -1 || data.resultCode == 0) {
                    $.messager.alert('错误提示', data.notice, 'error');
                } else if (data.resultCode == 1) {
                    $('#leaveMessageEditorDlg').dialog('close');
                    //刷新留言列表
                    var leaveMessageParam = {
                        targetUser: 'manager',
                        sendUser: targetUser
                    }
                    reloadLeaveMessageList(leaveMessageParam, false);
                }
            },
            error: function () {
                $.messager.alert('错误提示', '发送留言失败，请稍后重试或联系管理员！', 'error');
            }
        });
    });

    /**
     * 全选全取消
     */
    $(document).on('change', '#checkAllLeaveMessageBtn', function () {
        //是否全选
        var isCheckAll = $(this).prop('checked');
        if (isCheckAll) {
            $('#leaveMessageList input[name=leaveMessageRemoveCheckbox]').prop('checked', true);
        } else {
            $('#leaveMessageList input[name=leaveMessageRemoveCheckbox]').prop('checked', false);
        }
    });
    //改变全选复选框状态
    $(document).on('change', '#leaveMessageList input[name=leaveMessageRemoveCheckbox]', function () {
        var leaveMessageRemoveCheckboxs = $('#leaveMessageList input[name=leaveMessageRemoveCheckbox]');
        var isCheckedAll = true;
        $.each(leaveMessageRemoveCheckboxs, function (i, b) {
            if (!$(b).prop('checked')) {
                isCheckedAll = false;
                return false;
            }
        });
        $('#checkAllLeaveMessageBtn').prop('checked', isCheckedAll);
    });
});

/**
 * 加载留言列表
 * @param leaveMessageParam 加载参数 targetUser、sendUser
 * @param isReloadUserList 加载后是否刷新用户选择列表
 */
function reloadLeaveMessageList(leaveMessageParam, isReloadUserList) {
    //清空留言历史列表
    $('#leaveMessageList').empty();
    //获取留言列表
    $.ajax({
        url: '/admin/blog/leaveMessage/findLeaveMessageByUserId',
        type: 'get',
        data: leaveMessageParam,
        success: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.resultCode == 1) {
                //留言列表
                var leaveMessageList = data.leaveMessageList;
                if (leaveMessageList != null && leaveMessageList.length != 0) {
                    //加载留言列表
                    $.each(leaveMessageList, function (i, l) {
                        $('#leaveMessageList').append("<div style='margin: 0 8px;border-bottom: dashed rgb(133, 133, 133) 1px'>\n" +
                            "                    <div>\n" +
                            "                        <span style='color: rgb(254, 2, 8);font-weight: bold'>" + l.sendUserName + "</span>\n" +
                            "                        <span style='color: grey;float: right'>" + l.createTime + "</span>\n" +
                            "                    </div>\n" +
                            "                    <div style='margin-left: 20px;overflow: auto'>" + l.message + "</div>\n" +
                            "                    <div style='width: 100%;height: 20px'>\n" +
                            "                        <span style='float: right'>\n" +
                            "                            <label style='display: flex; align-items: center;color: grey'>\n" +
                            "                                <input type='checkbox' name='leaveMessageRemoveCheckbox' value='" + l.leaveMessageId + "'>删除\n" +
                            "                            </label>\n" +
                            "                        </span>\n" +
                            "                    </div>\n" +
                            "                </div>");
                    });
                    //刷新用户选择列表
                    if (isReloadUserList) {
                        $('#lmUserDatagrid').datagrid('reload');
                    }
                }
            }
        },
        error: function () {
            $.messager.alert('错误提示', '获取留言失败，请稍后重试或联系管理员！', 'error');
        }
    });
}