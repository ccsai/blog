<!DOCTYPE>
<html>
<head>
    <meta>
    <title>文章管理</title>
    <#include '../../depend/_head.ftl'/>
    <#include '../../../common/depend/_jquery_serializejson.ftl'/>
    <#include '../../../common/depend/_ueditor.ftl'/>
    <script src="${basePath!}/js/common/allmayneed/Utils.js"></script>
    <script src="${basePath!}/js/admin/blog/article/index.js"></script>
</head>
<body>
<@shiro.hasPermission name="/admin/blog/article/findArticleByPage:request">
<div style="padding: 3px;height: 94.8%">
<#-- 文章分页列表工具栏 -->
    <div id="articleDatagridToolbar" style="height: 26px">
        <div style="float: left">
        <@shiro.hasPermission name="/admin/blog/article/addArticle:request">
            <a class="easyui-linkbutton addArticleBtn" data-options="iconCls:'icon-add'">添加</a>
        </@shiro.hasPermission>
        </div>
        <div style="float: right">
            <form id="articleDatagridSearchForm">
                标题&nbsp;<input type="text" class="easyui-textbox" name="title"
                               data-options="prompt:'标题'"/>
                &nbsp;&nbsp;
                标签&nbsp;<input type="text" class="easyui-textbox" name="label"
                               data-options="prompt:'标签'"/>
                &nbsp;&nbsp;
                banner&nbsp;
                <select class="easyui-combobox" name="banner"
                        data-options="panelHeight:'auto',editable:false,width:75">
                    <option value="">全部</option>
                    <option value="1">技术交流</option>
                    <option value="2">生活日志</option>
                    <option value="3">兴趣爱好</option>
                    <option value="4">关于自己</option>
                </select>
                &nbsp;&nbsp;
                私有&nbsp;
                <select class="easyui-combobox" name="isPrivate"
                        data-options="panelHeight:'auto',editable:false,width:75">
                    <option value="">全部</option>
                    <option value="0">否</option>
                    <option value="1">是</option>
                </select>
                &nbsp;&nbsp;
                <a class="easyui-linkbutton" id="articleDatagridSearchBtn" data-options="iconCls:'icon-search'">查询</a>
            </form>
        </div>
    </div>

<#-- 文章分页列表 -->
    <table id="articleDatagrid" class="easyui-datagrid" style="width: 100%;height: 100%"
           data-options="idField:'articleId',pagination:true,toolbar:'articleDatagridToolbar',singleSelect:true,rownumbers:true,pageSize:10,striped:true">
        <thead>
        <tr>
            <th data-options="field:'articleId',hidden:'true'"></th>
            <th data-options="field:'title',halign:'center',align:'left'" style="width: 15%">标题</th>
            <th data-options="field:'banner',halign:'center',align:'left',formatter:formatterArticleBanner" style="width: 15%">banner</th>
            <th data-options="field:'label',halign:'center',align:'left'" style="width: 15%">标签</th>
            <th data-options="field:'isPrivate',align:'center',formatter:formatterArticleIsPrivate" style="width: 10%">
                私有
            </th>
            <th data-options="field:'visitedNumber',halign:'center',align:'right'" style="width: 10%">访问量</th>
            <th data-options="field:'supportNumber',halign:'center',align:'right'" style="width: 10%">被赞次数</th>
            <th data-options="field:'sortNo',halign:'center',align:'right'" style="width: 10%">排序</th>
            <th data-options="field:'articleOperation',align:'center',formatter:articleDatagridOperation"
                style="width: 15%">操作
            </th>
        </tr>
        </thead>
    </table>
</div>

<#-- 文章详情对话框 -->
<#include '_article_detail_dlg.ftl'/>

<script>
    /**
     * 格式化文章操作列
     * @param value
     * @param row
     * @return {string}
     */
    function articleDatagridOperation(value, row) {
        var strArr = [];
        strArr.push("<@shiro.hasPermission name='/admin/blog/article/editArticleByArticleId:request'>" +
                "<a href='javascript:void(0)' onclick='openArticleDetailDlgOfEdit(\"" + row.articleId + "\")'>修改</a>" +
                "</@shiro.hasPermission>");
        strArr.push("<@shiro.hasPermission name='/admin/blog/article/findArticleDetailByArticleId:request'>" +
                "<a href='javascript:void(0)' onclick='openarticleDetailDlgOfDetail(\"" + row.articleId + "\")'>详情</a>" +
                "</@shiro.hasPermission>");
        strArr.push("<@shiro.hasPermission name='/admin/blog/article/removeArticleByArticleId:request'>" +
                "<a href='javascript:void(0)' onclick='removeArticleByArticleId(\"" + row.articleId + "\")'>删除</a>" +
                "</@shiro.hasPermission>");
        return strArr.join(' ');
    }
</script>
</@shiro.hasPermission>
</body>
</html>