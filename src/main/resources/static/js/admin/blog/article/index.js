$(function () {
    //实例ueditor
    var ue = UE.getEditor('articleUE', {zIndex: 999999});

    /**
     * 加载文章列表
     */
    $('#articleDatagrid').datagrid({
        url: '/admin/blog/article/findArticleByPage',
        method: 'get',
        loadFilter: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.resultCode == 1) {
                var articlePageInfo = data.articlePageInfo;
                //分页数据
                var pageData = {
                    total: articlePageInfo.total,
                    rows: articlePageInfo.list
                };
                return pageData;
            }
        }
    });

    /**
     * 点击搜索查询文章列表
     */
    $(document).on('click', '#articleDatagridSearchBtn', function () {
        $('#articleDatagrid').datagrid({
            queryParams: $('#articleDatagridSearchForm').serializeJSON()
        });
        $('#articleDatagrid').datagrid('reload');
    });

    /**
     * 回车提交文章列表搜索表单
     */
    $('#articleDatagridSearchForm input').bind('keyup', function (event) {
        if (event.keyCode == '13') {
            $('#articleDatagridSearchBtn').trigger('click');
        }
    });

    /**
     * 文章详情对话框设置
     */
    $('#articleDetailDlg').dialog({
        onOpen: function () {
            //清空表单
            ue.setContent('');
            $('#articleDetailForm input[name=articleId]').val('');
            $('#articleDetailForm').form('reset');
            //加载标签选择列表
            $.ajax({
                url: '/admin/blog/article/findLabelInArticle',
                type: 'get',
                async: false,
                success: function (data) {
                    if (data.resultCode == -1 || data.resultCode == 0) {
                        $.messager.alert('错误提示', data.notice, 'error');
                    } else if (data.resultCode == 1) {
                        $('#labelListInArticle').empty();
                        //标签列表
                        var labelList = data.labelList;
                        //加载
                        if (labelList != null && labelList.length > 0) {
                            var chooseLabelListArr = [];
                            $.each(labelList, function (i, l) {
                                chooseLabelListArr.push('<label>' +
                                    '<input type="checkbox" name="label[]" value="' + l.labelId + '">' + l.label +
                                    '</label>');
                            });
                            $('#labelListInArticle').append(chooseLabelListArr.join('&emsp;'));
                        }
                    }
                },
                error: function () {
                    $.messager.alert('错误提示', '标签加载失败，请稍后重试或联系管理员！', 'error');
                }
            });
        }
    });

    /**
     * 点击关闭文章详情对话框
     */
    $(document).on('click', '#articleDetailDlgToolBar .cancel-btn', function () {
        $('#articleDetailDlg').dialog('close');
    });

    /**
     * 点击添加显示文章详情对话框
     */
    $(document).on('click', '#articleDatagridToolbar .addArticleBtn', function () {
        //统计隐藏
        $('#articleStatistical').hide();
        $('#articleDetailForm input[name=operation]').val('add');
        $('#articleDetailDlgToolBar .save-btn').show();//显示保存按钮
        $('#articleDetailDlg').dialog('open');
    });

    /**
     * 点击保存添加或修改文章
     */
    $(document).on('click', '#articleDetailDlgToolBar .save-btn', function () {
        //判断添加或修改
        var oper = $('#articleDetailForm input[name=operation]').val();
        //非删除或修改时不提交表单
        if (oper == '') {
            return false;
        }
        if ($('#articleDetailForm').form('validate')) {
            //添加或修改的url
            var url;
            if (oper == 'add') {//添加url
                url = '/admin/blog/article/addArticle';
            } else if (oper == 'edit') {//修改url
                url = '/admin/blog/article/editArticleByArticleId';
            }
            //要提交的数据
            var data = $('#articleDetailForm').serializeJSON();
            //文章内容
            delete data['editorValue'];
            var content = ue.getContent()
            data.content = content;
            if (content == null || content == '') {
                $.messager.alert('提示', '请填写文章内容！', 'warning');
                return false;
            }
            //标签
            var labelList = data.label;
            if (labelList != null && labelList.length > 0) {
                data.label = labelList.join(',');
            }
            //oss key
            var ossKeys = Utils.getOssKeyFromUE(content);
            if (ossKeys.length > 0) {
                data.ossKeys = ossKeys;
            }
            $.ajax({
                url: url,
                type: 'post',
                data: data,
                success: function (data) {
                    if (data.resultCode == -1 || data.resultCode == 0) {
                        $.messager.alert('错误提示', data.notice, 'error');
                    } else if (data.resultCode == 1) {
                        $.messager.alert('消息通知', '保存成功', 'info');
                        $('#articleDetailDlg').dialog('close');
                        $('#articleDatagrid').datagrid('reload');
                    }
                },
                error: function () {
                    $.messager.alert('错误提示', '保存文章失败，请稍后重试或联系管理员！', 'error');
                }
            });
        }
    });

    /**
     * 回车提交文章详情表单
     */
    $('#articleDetailForm input').bind('keyup', function (event) {
        if (event.keyCode == '13') {
            $('#articleDetailDlgToolBar .save-btn').trigger('click');
        }
    });

    /**
     * 点击预览文章内容
     */
    $(document).on('click', '#articleDetailDlgToolBar .previe-btn', function () {
        $('#articlePreview').remove();
        var content = ue.getContent();
        var form = $('<form id="articlePreview" style="display: none" ' +
            'action="/admin/blog/article/previewArticle"' +
            'target="_blank" method="post">' +
            '<textarea name="content">' + content + '</textarea>' +
            '</form>');
        $(document.body).append(form);
        form.submit();
    });
});

/**
 * 点击修改文章详情展示
 * @param articleId 文章编号
 */
function openArticleDetailDlgOfEdit(articleId) {
    //显示文章统计
    $('#articleStatistical').show();
    $('#articleDetailForm input[name=operation]').val('edit');
    $('#articleDetailDlgToolBar .save-btn').show();//显示保存按钮
    //打开详情对话框，显示详情
    openArticleDetailDlg(articleId);
}

/**
 * 点击详情文章详情按钮
 * @param articleId 文章编号
 */
function openarticleDetailDlgOfDetail(articleId) {
    //显示文章统计
    $('#articleStatistical').show();
    $('#articleDetailForm input[name=operation]').val('');
    $('#articleDetailDlgToolBar .save-btn').hide();//影藏保存按钮
    //打开详情对话框，显示详情
    openArticleDetailDlg(articleId);
}

/**
 * 文章详情请求展示
 * @param articleId 文章编号
 */
function openArticleDetailDlg(articleId) {
    $('#articleDetailDlg').dialog('open');
    $.ajax({
        url: '/admin/blog/article/findArticleDetailByArticleId',
        type: 'get',
        data: {articleId: articleId},
        success: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                $.messager.alert('错误提示', data.notice, 'error');
            } else if (data.resultCode == 1) {
                //文章详情
                var articleDetail = data.articleDetail;
                //回显
                $('#articleDetailForm').form('load', articleDetail);
                //文章内容
                UE.getEditor('articleUE').setContent(articleDetail.content);
                //标签回显
                var label = articleDetail.label;
                if (label != null && label.length > 0) {
                    var labelArr = label.split(",");
                    $.each(labelArr, function (i, l) {
                        $('#articleDetailForm input[name="label[]"][value=' + l + ']').prop('checked', true);
                    });
                }
            }
        },
        error: function () {
            $.messager.alert('错误提示', '获取文章详情失败，请稍后重试或联系管理员！', 'error');
        }
    });
}

/**
 * 根据编号删除文章
 * @param articleId 文章编号
 */
function removeArticleByArticleId(articleId) {
    $.messager.confirm('删除提示', '确认删除？', function (r) {
        if (r) {
            $.ajax({
                url: '/admin/blog/article/removeArticleByArticleId',
                type: 'post',
                data: {articleId: articleId},
                success: function (data) {
                    if (data.resultCode == -1 || data.resultCode == 0) {
                        $.messager.alert('错误提示', data.notice, 'error');
                    } else if (data.resultCode == 1) {
                        $('#articleDatagrid').datagrid('reload');
                    }
                },
                error: function () {
                    $.messager.alert('错误提示', '删除文章失败，请稍后重试或联系管理员！', 'error');
                }
            });
        }
    });
}

/**
 * 格式化文章列表banner列
 * @param value
 * @return {*}
 */
function formatterArticleBanner(value) {
    //显示值
    var showValue;
    switch (value) {
        case '1':
            showValue = '技术交流';
            break;
        case '2':
            showValue = '生活日志';
            break;
        case '3':
            showValue = '兴趣爱好';
            break;
        case '4':
            showValue = '关于自己';
            break;
    }
    return showValue;
}

/**
 * 格式化文章是否私有列
 * @param value
 * @return {string}
 */
function formatterArticleIsPrivate(value) {
    return value ? "是" : "否";
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