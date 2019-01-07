<!DOCTYPE>
<html>
<head>
    <meta>
    <title>友情链接管理</title>
    <#include '../../depend/_head.ftl'/>
    <#include '../../../common/depend/_jquery_serializejson.ftl'/>
    <script src="${basePath!}/js/admin/blog/friendly_link/index.js"></script>
</head>
<body>
<@shiro.hasPermission name="/admin/blog/friendlyLink/findFriendlyLinkList:request">
<div style="padding: 3px;height: 94.8%">
<#-- 友情链接分页列表工具栏 -->
    <div id="friendlyLinkDatagridToolbar" style="height: 26px">
        <div style="float: left">
        <@shiro.hasPermission name="/admin/blog/friendlyLink/addFriendlyLink:request">
            <a class="easyui-linkbutton addFriendlyLinkBtn" data-options="iconCls:'icon-add'">添加</a>
        </@shiro.hasPermission>
        </div>
        <div style="float: right">
            <form id="friendlyLinkDatagridSearchForm">
                关键字&nbsp;<input type="text" class="easyui-textbox" name="keyWord"
                                 data-options="prompt:'友情链接/友链url'"/>
                &nbsp;&nbsp;
                显示&nbsp;
                <select class="easyui-combobox" name="isShow"
                        data-options="panelHeight:'auto',editable:false,width:70">
                    <option value="">全部</option>
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select>
                &nbsp;&nbsp;
                <a class="easyui-linkbutton" id="friendlyLinkDatagridSearchBtn"
                   data-options="iconCls:'icon-search'">查询</a>
            </form>
        </div>
    </div>

<#-- 友情链接分页列表 -->
    <table id="friendlyLinkDatagrid" class="easyui-datagrid" style="width: 100%;height: 100%"
           data-options="idField:'friendlyLinkId',toolbar:'friendlyLinkDatagridToolbar',singleSelect:true,rownumbers:true,striped:true">
        <thead>
        <tr>
            <th data-options="field:'friendlyLinkId',hidden:'true'"></th>
            <th data-options="field:'linkName',halign:'center',align:'left'" style="width: 15%">链接名称</th>
            <th data-options="field:'url',halign:'center',align:'left'" style="width: 20%">链接url</th>
            <th data-options="field:'logo',halign:'center',align:'left'" style="width: 20%">logo</th>
            <th data-options="field:'sortNo',halign:'center',align:'right'" style="width: 15%">排序</th>
            <th data-options="field:'isShow',align:'center',formatter:formatterFriendlyLinkDatagridIsShow" style="width: 10%">显示</th>
            <th data-options="field:'operation',align:'center',formatter:friendlyLinkDatagridOperation"
                style="width: 20%">操作
            </th>
        </tr>
        </thead>
    </table>
</div>

<#-- 友情链接详情对话框 -->
<#include '_friendly_link_detail_dlg.ftl'/>

<script>

    /**
     * 格式化友链表格操作列
     * @param value
     * @param row
     * @return {string}
     */
    function friendlyLinkDatagridOperation(value, row) {
        var strArr = [];
        strArr.push("<@shiro.hasPermission name='/admin/blog/friendlyLink/editFriendlyLinkByFriendlyLinkId:request'>" +
                "<a href='javascript:void(0)' onclick='openFriendlyLinkDetailDlgOfEdit(\"" + row.friendlyLinkId + "\")'>修改</a>" +
                "</@shiro.hasPermission>");
        strArr.push("<@shiro.hasPermission name='/admin/blog/friendlyLink/findFriendlyLinkByFriendlyLinkId:request'>" +
                "<a href='javascript:void(0)' onclick='showFriendlyLinkDetail(\"" + row.friendlyLinkId + "\")'>详情</a>" +
                "</@shiro.hasPermission>");
        strArr.push("<@shiro.hasPermission name='/admin/blog/friendlyLink/removeFriendlyLinkByFriendlyLinkId:request'>" +
                "<a href='javascript:void(0)' onclick='removeFriendlyLinkByLinkId(\"" + row.friendlyLinkId + "\")'>删除</a>" +
                "</@shiro.hasPermission>");
        return strArr.join(' ');
    }
</script>
</@shiro.hasPermission>
</body>
</html>