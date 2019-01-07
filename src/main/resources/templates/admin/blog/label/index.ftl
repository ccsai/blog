<!DOCTYPE>
<html>
<head>
    <meta>
    <title>标签管理</title>
    <#include '../../depend/_head.ftl'/>
    <#include '../../../common/depend/_jquery_serializejson.ftl'/>
    <script src="${basePath!}/js/admin/blog/label/index.js"></script>
</head>
<body>
<@shiro.hasPermission name="/admin/blog/label/findLabelList:request">
<div style="padding: 3px;height: 94.8%">
<#-- 标签分页列表工具栏 -->
    <div id="labelDatagridToolbar" style="height: 26px">
        <div style="float: left">
        <@shiro.hasPermission name="/admin/blog/label/addLabel:request">
            <a class="easyui-linkbutton addLabelBtn" data-options="iconCls:'icon-add'">添加</a>
        </@shiro.hasPermission>
        </div>
        <div style="float: right">
            <form id="labelDatagridSearchForm">
                标签&nbsp;<input type="text" class="easyui-textbox" name="label" data-options="prompt:'标签'"/>
                &nbsp;&nbsp;
                <a class="easyui-linkbutton" id="labelDatagridSearchBtn" data-options="iconCls:'icon-search'">查询</a>
            </form>
        </div>
    </div>

<#-- 标签分页列表 -->
    <table id="labelDatagrid" class="easyui-datagrid" style="width: 100%;height: 100%"
           data-options="idField:'labelId',toolbar:'labelDatagridToolbar',singleSelect:true,rownumbers:true,striped:true">
        <thead>
        <tr>
            <th data-options="field:'labelId',hidden:'true'"></th>
            <th data-options="field:'label',halign:'center',align:'left'" style="width: 35%">标签</th>
            <th data-options="field:'sortNo',halign:'center',align:'right'" style="width: 30%">排序</th>
            <th data-options="field:'operation',align:'center',formatter:labelDatagridOperation"
                style="width: 35%">操作
            </th>
        </tr>
        </thead>
    </table>
</div>

<#-- 标签详情对话框 -->
<#include '_label_detail_dlg.ftl'/>

<script>

    /**
     * 格式化友链表格操作列
     * @param value
     * @param row
     * @return {string}
     */
    function labelDatagridOperation(value, row) {
        var strArr = [];
        strArr.push("<@shiro.hasPermission name='/admin/blog/label/editLabelByLabelId:request'>" +
                "<a href='javascript:void(0)' onclick='openLabelDetailDlgOfEdit(\"" + row.labelId + "\")'>修改</a>" +
                "</@shiro.hasPermission>");
        strArr.push("<@shiro.hasPermission name='/admin/blog/label/findLabelByLabelId:request'>" +
                "<a href='javascript:void(0)' onclick='showLabelDetail(\"" + row.labelId + "\")'>详情</a>" +
                "</@shiro.hasPermission>");
        strArr.push("<@shiro.hasPermission name='/admin/blog/label/removeLabelByLabelId:request'>" +
                "<a href='javascript:void(0)' onclick='removeLabelByLinkId(\"" + row.labelId + "\")'>删除</a>" +
                "</@shiro.hasPermission>");
        return strArr.join(' ');
    }
</script>
</@shiro.hasPermission>
</body>
</html>