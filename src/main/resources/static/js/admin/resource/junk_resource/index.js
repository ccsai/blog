$(function () {
    /**
     * 安全垃圾数据清理
     */
    $(document).on('click', '#clearSecurityJunkDataBtn', function () {
        $('#junkDataClearNoticeDlg').dialog('open');
        $.ajax({
            url: '/admin/resource/junkResource/removeSecurityJunkData',
            type: 'post',
            success: function (data) {
                if (data.resultCode == -1 || data.resultCode == 0) {
                    $('#junkDataClearNoticeDlg').dialog('close');
                    $.messager.alert('错误提示', data.notice, 'error');
                } else if (data.resultCode == 1) {
                    $('#junkDataClearNoticeDlg').dialog('close');
                    $.messager.alert('消息通知', '清理成功！', 'info');
                }
            },
            error: function () {
                $('#junkDataClearNoticeDlg').dialog('close');
                $.messager.alert('错误提示', '清理失败，请稍后重试或联系管理员！', 'error');
            }
        });
    });

    /**
     * 博客业务垃圾数据清理
     */
    $(document).on('click', '#clearBlogBusiJunkDataBtn', function () {
        $('#junkDataClearNoticeDlg').dialog('open');
        $.ajax({
            url: '/admin/resource/junkResource/removeBlogBusiJunkData',
            type: 'post',
            success: function (data) {
                if (data.resultCode == -1 || data.resultCode == 0) {
                    $('#junkDataClearNoticeDlg').dialog('close');
                    $.messager.alert('错误提示', data.notice, 'error');
                } else if (data.resultCode == 1) {
                    $('#junkDataClearNoticeDlg').dialog('close');
                    $.messager.alert('消息通知', '清理成功！', 'info');
                    $('#clearJunkOssDataBtn').linkbutton('enable');
                }
            },
            error: function () {
                $('#junkDataClearNoticeDlg').dialog('close');
                $.messager.alert('错误提示', '清理失败，请稍后重试或联系管理员！', 'error');
            }
        });
    });

    /**
     * oss垃圾数据文件清理
     */
    $(document).on('click', '#clearJunkOssDataBtn', function () {
        $('#junkDataClearNoticeDlg').dialog('open');
        $.ajax({
            url: '/admin/resource/junkResource/removeJunkOssFromOssAndDB',
            type: 'post',
            success: function (data) {
                if (data.resultCode == -1 || data.resultCode == 0) {
                    $('#junkDataClearNoticeDlg').dialog('close');
                    $.messager.alert('错误提示', data.notice, 'error');
                } else if (data.resultCode == 1) {
                    $('#junkDataClearNoticeDlg').dialog('close');
                    $.messager.alert('消息通知', '清理成功！', 'info');
                }
            },
            error: function () {
                $('#junkDataClearNoticeDlg').dialog('close');
                $.messager.alert('错误提示', '清理失败，请稍后重试或联系管理员！', 'error');
            }
        });
    });
});