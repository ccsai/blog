$(function () {

    //实例ueditor
    var ue = UE.getEditor('articleCommentUE', {zIndex: 999999});

    /**
     * 文章评论列表分页查询
     */
    $('#articleCommentDatagrid').datagrid({
        url: '/admin/blog/articleComment/findArticleCommentByPage',
        method: 'get',
        loadFilter: function (data) {
            //所有评论数据行不选择
            $('#articleCommentDatagrid').datagrid('unselectAll');
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.resultCode == 1) {
                var articleCommentPageInfo = data.articleCommentPageInfo;
                //分页数据
                var pageData = {
                    total: articleCommentPageInfo.total,
                    rows: articleCommentPageInfo.list
                };
                return pageData;
            }
        }
    });

    /**
     * 点击搜索查询文章评论列表
     */
    $(document).on('click', '#articleCommentDatagridSearchBtn', function () {
        $('#articleCommentDatagrid').datagrid({
            queryParams: $('#articleCommentDatagridSearchForm').serializeJSON()
        });
        $('#articleCommentDatagrid').datagrid('reload');
    });

    /**
     * 回车提交权限分页搜索表单
     */
    $('#articleCommentDatagridSearchForm input').bind('keyup', function (event) {
        if (event.keyCode == '13') {
            $('#articleCommentDatagridSearchBtn').trigger('click');
        }
    });

    /**
     * 文章评论过审
     */
    $(document).on('click', '#articleCommentDetailDlgToolBar .checked-btn', function () {
        //参数
        var params = {};
        params.isChecked = 2;
        params.commentIds = '"' + $('#articleCommentDetailForm input[name=commentId]').val() + '"';
        //审核操作
        articleCommentcheckOperation(params);
    });
    $(document).on('click', '#articleCommentDatagridToolbar .checkedArticleCommentBtn', function () {
        //选中的文章评论
        var selectRows = $('#articleCommentDatagrid').datagrid('getSelections');
        if (selectRows.length == 0) {
            $.messager.alert('提示', '请选择评论过审！', 'warning');
            return false;
        }
        //审核操作
        $.messager.confirm('过审提示', '确认将选中评论通过审核？', function (r) {
            if (r) {
                //参数
                var params = {};
                params.isChecked = 2;
                var commentIdsArr = [];
                $.each(selectRows, function (i, row) {
                    commentIdsArr.push(row.commentId);
                });
                var commentIds = "'" + commentIdsArr.join("','") + "'";
                params.commentIds = commentIds;
                articleCommentcheckOperation(params);
            }
        });
    });

    /**
     * 文章评论拒审
     */
    $(document).on('click', '#articleCommentDetailDlgToolBar .no-checked-btn', function () {
        //参数
        var params = {};
        params.isChecked = -1;
        params.commentIds = '"' + $('#articleCommentDetailForm input[name=commentId]').val() + '"';
        //审核操作
        articleCommentcheckOperation(params);
    });
    $(document).on('click', '#articleCommentDatagridToolbar .noCheckedArticleCommentBtn', function () {
        //获取选中的文章评论
        var selectRows = $('#articleCommentDatagrid').datagrid('getSelections');
        if (selectRows.length == 0) {
            $.messager.alert('提示', '请选择评论拒审！', 'warning');
            return false;
        }
        //审核操作
        $.messager.confirm('拒审提示', '确认将选中评论拒绝审核？', function (r) {
            if (r) {
                //参数
                var params = {};
                params.isChecked = -1;
                var commentIdsArr = [];
                $.each(selectRows, function (i, row) {
                    commentIdsArr.push(row.commentId);
                });
                var commentIds = "'" + commentIdsArr.join("','") + "'";
                params.commentIds = commentIds;
                articleCommentcheckOperation(params);
            }
        });
    });

    /**
     * 批量删除文章评论
     */
    $(document).on('click', '#articleCommentDatagridToolbar .removeArticleCommentBtn', function () {
        //获取选中的文章评论
        var selectRows = $('#articleCommentDatagrid').datagrid('getSelections');
        if (selectRows.length == 0) {
            $.messager.alert('提示', '请选择要删除的评论！', 'warning');
            return false;
        }
        //确认删除
        $.messager.confirm('删除提示', '确认将选中评论删除？', function (r) {
            if (r) {
                //获取要删除的评论编号
                var commentIds = [];
                $.each(selectRows, function (i, row) {
                    commentIds.push(row.commentId);
                });
                $.ajax({
                    url: '/admin/blog/articleComment/removeArticleCommentByArticleCommentIds',
                    type: 'post',
                    data: {commentIds: commentIds},
                    success: function (data) {
                        if (data.resultCode == -1 || data.resultCode == 0) {
                            $.messager.alert('错误提示', data.notice, 'error');
                        } else if (data.resultCode == 1) {
                            $.messager.alert('消息通知', '删除成功', 'info');
                            $('#articleCommentDetailDlg').dialog('close');
                            $('#articleCommentDatagrid').datagrid('reload');
                        }
                    },
                    error: function () {
                        $.messager.alert('错误提示', '评论删除失败，请稍后重试或联系管理员！', 'error');
                    }
                });
            }
        });
    });

    /**
     * 点击关闭文章评论详情对话框
     */
    $(document).on('click', '#articleCommentDetailDlgToolBar .cancel-btn', function () {
        $('#articleCommentDetailDlg').dialog('close');
    });

    /**
     * 点击添加弹出评论添加对话框
     */
    $(document).on('click', '#articleCommentDatagridToolbar .addArticleCommentBtn', function () {
        $('#articleCommentAddDlg').dialog('open');
    });

    //添加文章评论对话框设置
    $('#articleCommentAddDlg').dialog({
        onOpen: function () {
            //加载文章选择列表
            $('#acArticleId').combobox({
                valueField: 'articleId',
                textField: 'title',
                url: '/admin/blog/articleComment/findArticleList',
                method: 'get',
                loadFilter: function (data) {
                    if (data.resultCode == -1 || data.resultCode == 0) {
                        $.messager.alert('错误提示', data.notice, 'error');
                    } else if (data.resultCode == 1) {
                        //文章列表
                        var articleList = data.articleList;
                        //默认显示
                        var defaultSelect = {
                            articleId: '',
                            title: ''
                        }
                        articleList.unshift(defaultSelect);
                        return articleList;
                    }
                }
            });
            //清空新增表单数据
            $('#acArticleId').combobox('setValue', '');
            ue.setContent('');
        }
    });

    /**
     * 点击保存新增文章评论
     */
    $(document).on('click', '#articleCommentAddDlgToolBar .save-btn', function () {
        //获取输入的评论内容
        var commentContent = ue.getContent();
        if (commentContent == '') {
            $.messager.alert('警告', '你输入的字符长度不能为0！', 'warning');
        }
        //获取所有oss的key
        var ossKeys = Utils.getOssKeyFromUE(commentContent);
        //添加提交的参数
        var submitParams = {
            comment: commentContent,
            articleId: $('#acArticleId').combobox('getValue'),
            commentUserId: 'manager',
            ossKeys: ossKeys
        }
        //添加文章评论
        if ($('#articleCommentAddForm').form('validate')) {
            $.ajax({
                url: '/admin/blog/articleComment/addArticleComment',
                type: 'post',
                data: submitParams,
                success: function (data) {
                    if (data.resultCode == -1 || data.resultCode == 0) {
                        $.messager.alert('错误提示', data.notice, 'error');
                    } else if (data.resultCode == 1) {
                        $.messager.alert('消息提示', '评论添加成功', 'info');
                        $('#articleCommentAddDlg').dialog('close');
                        $('#articleCommentDatagrid').datagrid('reload');
                    }
                },
                error: function () {
                    $.messager.alert('错误提示', '评论添加失败，请稍后重试或联系管理员！', 'error');
                }
            });
        }
    });

    /**
     * 点击关闭新增文章评论对话框
     */
    $(document).on('click', '#articleCommentAddDlgToolBar .cancel-btn', function () {
        $('#articleCommentAddDlg').dialog('close');
    });
});

/**
 * 显示文章评论详情
 * @param commentId 文章评论编号
 */
function openUserDetailDlgOfEdit(commentId) {
    $('#articleCommentDetailForm input[name=commentId]').val(commentId);
    //表格行调试
    $('#articleCommentDatagrid').datagrid('unselectAll');
    //查询评论详情
    $.ajax({
        url: '/admin/blog/articleComment/findArticleCommentDetailByArticleCommentId',
        type: 'get',
        data: {commentId: commentId},
        success: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.resultCode == 1) {
                //评论详情
                var commentDetail = data.articleCommentDetail;
                //审核按钮显示判断
                if (commentDetail.isChecked == -1 || commentDetail.isChecked == 1) {
                    $('#articleCommentDetailDlgToolBar a.checked-btn').show();
                    $('#articleCommentDetailDlgToolBar a.no-checked-btn').hide();
                } else if (commentDetail.isChecked == 2) {
                    $('#articleCommentDetailDlgToolBar a.checked-btn').hide();
                    $('#articleCommentDetailDlgToolBar a.no-checked-btn').show();
                } else {
                    $('#articleCommentDetailDlgToolBar a.checked-btn').hide();
                    $('#articleCommentDetailDlgToolBar a.no-checked-btn').hide();
                }
                $('#articleCommentDetailDlg').dialog('open').dialog('center');
                //加载评论信息
                $.each(commentDetail, function (i, c) {
                    if (i == 'isChecked') {
                        if (c == -1) {
                            $('#articleCommentDetailForm span.' + i).html('审核未通过');
                        } else if (c == 1) {
                            $('#articleCommentDetailForm span.' + i).html('待审核');
                        } else if (c == 2) {
                            $('#articleCommentDetailForm span.' + i).html('已审核');
                        }
                        return;
                    }
                    $('#articleCommentDetailForm span.' + i).html(c);
                });
                $('#articleCommentDetailForm div.comment').html(commentDetail.comment);
            }
        },
        error: function () {
            $.messager.alert('错误提示', '评论加载失败，请稍后重试或联系管理员！', 'error');
        }
    });
}

/**
 * 文章评论审核操作
 * @param params 审核参数
 */
function articleCommentcheckOperation(params) {
    $.ajax({
        url: '/admin/blog/articleComment/editArticleCommentIsCheckedByArticleCommentIds',
        type: 'post',
        data: params,
        success: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.resultCode == 1) {
                $.messager.alert('消息通知', '审核成功', 'info');
                $('#articleCommentDetailDlg').dialog('close');
                $('#articleCommentDatagrid').datagrid('reload');
            }
        },
        error: function () {
            $.messager.alert('错误提示', '评论审核失败，请稍后重试或联系管理员！', 'error');
        }
    });
}

/**
 * 格式化文章分页列表是否审核列
 * @param value
 * @return {*}
 */
function formatterArticleCommentDatagridIsChecked(value) {
    var str;
    switch (value) {
        case -1:
            str = "否";
            break;
        case 1:
            str = "待";
            break;
        case 2:
            str = "是";
            break;
    }
    return str;
}