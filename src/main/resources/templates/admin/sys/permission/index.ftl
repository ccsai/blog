<!DOCTYPE>
<html>
<head>
    <meta>
    <title>权限管理</title>
    <#include '../../depend/_head.ftl'/>
    <#include '../../depend/_jquery_serializejson.ftl'/>
    <script src="${basePath}/js/admin/sys/permission/index.js"></script>
</head>
<body>
<@shiro.hasPermission name="/admin/sys/permission/findPermissionsListByPage:request">
    <div style="padding: 3px;height: 94.8%">
        <div id="permissionDatagridToolbar" style="height: 26px">
            <@shiro.hasPermission name="/admin/sys/permission/addPermission:request">
                <div style="float: left">
                    <a class="easyui-linkbutton addPermissionBtn" data-options="iconCls:'icon-add'">添加</a>
                </div>
            </@shiro.hasPermission>
            <div style="float: right">
                <form id="permissionDatagridSearchForm">
                    关键字&nbsp;<input type="text" class="easyui-textbox" name="keyWord"
                                    data-options="prompt:'权限名称/url/url类型'"/>
                    &nbsp;&nbsp;
                    所属菜单&nbsp;
                    <@shiro.hasPermission name="/admin/sys/menu/findAllMenuTree:request">
                        <input type="text" id="permissionDatagridSearchFormMenuId" class="easyui-combotree"
                               name="menuId"
                               data-options="panelHeight:'auto',editable:false"/>
                    </@shiro.hasPermission>
                    <@shiro.lacksPermission name="/admin/sys/menu/findAllMenuTree:request">
                        <input type="text" class="easyui-textbox" value="你需要有访问菜单树表的权限" data-options="readonly:true"/>
                    </@shiro.lacksPermission>
                    &nbsp;&nbsp;
                    <a class="easyui-linkbutton" id="permissionDatagridSearchBtn"
                       data-options="iconCls:'icon-search'">查询</a>
                </form>
            </div>
        </div>

        <table id="permissionDatagrid" class="easyui-datagrid" style="width: 100%;height: 100%"
               data-options="idField:'permissionId',pagination:true,toolbar:'permissionDatagridToolbar',singleSelect:true,pageSize:50,rownumbers:true,striped:true">
            <thead>
            <tr>
                <th data-options="field:'permissionId',hidden:'true'"></th>
                <th data-options="field:'permissionName',halign:'center',align:'left'" style="width: 12%">权限名称</th>
                <th data-options="field:'permissionDes',halign:'center',align:'left'" style="width: 16%">权限描述</th>
                <th data-options="field:'url',halign:'center',align:'left'" style="width: 32%">权限url</th>
                <th data-options="field:'urlType',align:'center'" style="width: 10%">权限url类型</th>
                <th data-options="field:'menuName',halign:'center',align:'left'" style="width: 13%">所属菜单</th>
                <th data-options="field:'permissionOperation',align:'center',formatter:permissionDatagridOperation"
                    style="width: 17%">操作
                </th>
            </tr>
            </thead>
        </table>
    </div>
</@shiro.hasPermission>

<#-- 权限详情对话框 -->
<#include '_dlg_permission_detail.ftl'/>

<script>
    /**
     * 格式化权限列表操作栏
     * @param value
     * @param row
     */
    function permissionDatagridOperation(value, row) {
        var strArr = [];
        strArr.push("<@shiro.hasPermission name='/admin/sys/permission/editPermissionByPermissionId:request'>" +
                "<a href='javascript:void(0)' onclick='openPermissionDetailDlgOfEdit(\"" + row.permissionId + "\")'>修改</a>" +
                "</@shiro.hasPermission>");
        strArr.push("<@shiro.hasPermission name='/admin/sys/permission/findPermissionByPermissionId:request'>" +
                "<a href='javascript:void(0)' onclick='openPermissionDetailDlgOfDetail(\"" + row.permissionId + "\")'>详情</a>" +
                "</@shiro.hasPermission>");
        strArr.push("<@shiro.hasPermission name='/admin/sys/permission/removePermissionByPermissionId:request'>" +
                "<a href='javascript:void(0)' onclick='removePermissionByPermissionId(\"" + row.permissionId + "\")'>删除</a>" +
                "</@shiro.hasPermission>");
        return strArr.join(' ');
    }
</script>
</body>
</html>