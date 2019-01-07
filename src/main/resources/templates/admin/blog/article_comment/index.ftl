<!DOCTYPE>
<html>
<head>
    <meta>
    <title>文章评论管理</title>
    <#include '../../depend/_head.ftl'/>
    <#include '../../../common/depend/_jquery_serializejson.ftl'/>
    <#include '../../../common/depend/_ueditor.ftl'/>
    <script src="${basePath!}/js/common/allmayneed/Utils.js"></script>
    <script src="${basePath!}/js/admin/blog/article_comment/index.js"></script>
</head>
<body>
<@shiro.hasPermission name="/admin/blog/articleComment/findArticleCommentByPage:request">
<div style="padding: 3px;height: 94.8%">
<#-- 文章评论分页列表工具栏 -->
    <div id="articleCommentDatagridToolbar" style="height: 26px">
        <div style="float: left">
            <@shiro.hasPermission name="/admin/blog/articleComment/editArticleCommentIsCheckedByArticleCommentIds:request">
                <a class="easyui-linkbutton checkedArticleCommentBtn" data-options="iconCls:'icon-ok'">过审</a>
                <a class="easyui-linkbutton noCheckedArticleCommentBtn" data-options="iconCls:'icon-no'">拒审</a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/admin/blog/articleComment/addArticleComment:request">
                <a class="easyui-linkbutton addArticleCommentBtn" data-options="iconCls:'icon-add'">添加</a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/admin/blog/articleComment/removeArticleCommentByArticleCommentIds:request">
                <a class="easyui-linkbutton removeArticleCommentBtn" data-options="iconCls:'icon-remove'">删除</a>
            </@shiro.hasPermission>
        </div>
        <div style="float: right">
            <form id="articleCommentDatagridSearchForm">
                审核状态&nbsp;
                <select class="easyui-combobox" name="isChecked"
                        data-options="panelHeight:'auto',editable:false,width:75">
                    <option value="">全部</option>
                    <option value="-1">未通过</option>
                    <option value="1">待审</option>
                    <option value="2">通过</option>
                </select>
                &nbsp;&nbsp;
                文章标题&nbsp;<input type="text" class="easyui-textbox" name="articleTitle"
                                 data-options="prompt:'请填写完整的文章标题'"/>
                &nbsp;&nbsp;
                评论内容&nbsp;<input type="text" class="easyui-textbox" name="comment"
                                 data-options="prompt:'评论内容'"/>
                &nbsp;&nbsp;
                <a class="easyui-linkbutton" id="articleCommentDatagridSearchBtn" data-options="iconCls:'icon-search'">查询</a>
            </form>
        </div>
    </div>

<#-- 文章评论分页列表 -->
    <table id="articleCommentDatagrid" class="easyui-datagrid" style="width: 100%;height: 100%"
           data-options="idField:'commentId',pagination:true,toolbar:'articleCommentDatagridToolbar',pageSize:50,striped:true">
        <thead>
        <tr>
            <th data-options="field:'c',checkbox:true"></th>
            <th data-options="field:'commentId',hidden:'true'"></th>
            <th data-options="field:'articleTitle',halign:'center',align:'left'" style="width: 19%">文章标题</th>
            <#--<th data-options="field:'comment',halign:'center',align:'left'" style="width: 20%">评论内容</th>-->
            <th data-options="field:'isChecked',align:'center',formatter:formatterArticleCommentDatagridIsChecked"
                style="width: 10%">审核状态
            </th>
            <th data-options="field:'supportNumber',halign:'center',align:'right'" style="width: 15%">赞</th>
            <th data-options="field:'noSupportNumber',halign:'center',align:'right'" style="width: 15%">踩</th>
            <th data-options="field:'commentUser',halign:'center',align:'left'" style="width: 15%">评论人</th>
            <th data-options="field:'createTime',align:'center'" style="width: 15%">评论时间</th>
            <th data-options="field:'userOperation',align:'center',formatter:articleCommentDatagridOperation"
                style="width: 10%">操作
            </th>
        </tr>
        </thead>
    </table>
</div>

<#-- 文章评论详情对话框 -->
<#include '_article_comment_detail_dlg.ftl'/>
<#-- 文章评论添加对话框 -->
<#include '_article_comment_add_dlg.ftl'/>

<script>
    /**
     * 格式化文章评论操作列
     * @param value
     * @param row
     * @return {string}
     */
    function articleCommentDatagridOperation(value, row) {
        var strArr = [];
        strArr.push("<@shiro.hasPermission name='/admin/blog/articleComment/findArticleCommentDetailByArticleCommentId:request'>" +
                "<a href='javascript:void(0)' onclick='openUserDetailDlgOfEdit(\"" + row.commentId + "\")'>详情</a>" +
                "</@shiro.hasPermission>");
        return strArr.join(' ');
    }
</script>
</@shiro.hasPermission>
</body>
</html>