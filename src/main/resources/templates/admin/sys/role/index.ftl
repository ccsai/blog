<!DOCTYPE>
<html>
<head>
    <meta>
    <title>角色管理</title>
    <#include '../../depend/_head.ftl'/>
    <#include '../../../common/depend/_jquery_serializejson.ftl'/>
    <script src="${basePath!}/js/admin/sys/role/index.js"></script>
</head>
<body>
<@shiro.hasPermission name="/admin/sys/role/findRoleListByPage:request">
<div style="padding: 3px;height: 94.8%">
<#-- 角色分页列表工具栏 -->
    <div id="roleDatagridToolbar" style="height: 26px">
        <@shiro.hasPermission name="/admin/sys/role/addRole:request">
            <div style="float: left">
                <a class="easyui-linkbutton addroleBtn" data-options="iconCls:'icon-add'">添加</a>
            </div>
        </@shiro.hasPermission>
        <div style="float: right">
            <form id="roleDatagridSearchForm">
                关键字&nbsp;<input type="text" class="easyui-textbox" name="keyWord"
                                data-options="prompt:'角色名称/描述/类型'"/>
                &nbsp;&nbsp;
                <a class="easyui-linkbutton" id="roleDatagridSearchBtn" data-options="iconCls:'icon-search'">查询</a>
            </form>
        </div>
    </div>

<#-- 角色分页列表 -->
    <table id="roleDatagrid" class="easyui-datagrid" style="width: 100%;height: 100%"
           data-options="idField:'roleId',pagination:true,toolbar:'roleDatagridToolbar',singleSelect:true,pageSize:50,rownumbers:true,striped:true">
        <thead>
        <tr>
            <th data-options="field:'roleId',hidden:'true'"></th>
            <th data-options="field:'roleName',halign:'center',align:'left'" style="width: 25%">角色名称</th>
            <th data-options="field:'roleDes',halign:'center',align:'left'" style="width: 25%">角色描述</th>
            <th data-options="field:'roleType',align:'center'" style="width: 25%">角色类型</th>
            <th data-options="field:'roleOperation',align:'center',formatter:roleDatagridOperation"
                style="width: 25%">操作
            </th>
        </tr>
        </thead>
    </table>
</div>

<#-- 角色详情对话框 -->
<#include '_dlg_role_detail.ftl'/>
<#-- 菜单权限授权对话框 -->
<#include  '_dlg_role_menu_permission_dlg.ftl'/>
<#-- 通用权限授权 -->
<#include  '_dlg_role_common_permission_dlg.ftl'/>

<script>
    /**
     * 格式化角色列表操作栏
     * @param value
     * @param row
     */
    function roleDatagridOperation(value, row) {
        var strArr = [];
        strArr.push("<@shiro.hasPermission name='/admin/sys/role/editRoleByRoleId:request'>" +
                "<a href='javascript:void(0)' onclick='openRoleDetailDlgOfEdit(\"" + row.roleId + "\")'>修改</a>" +
                "</@shiro.hasPermission>");
        strArr.push("<@shiro.hasPermission name='/admin/sys/role/findRoleByRoleId:request'>" +
                "<a href='javascript:void(0)' onclick='openRoleDetailDlgOfDetail(\"" + row.roleId + "\")'>详情</a>" +
                "</@shiro.hasPermission>");
        strArr.push("<@shiro.hasPermission name='/admin/sys/role/findRoleCommonPermissionAuthListByRole:request'>" +
                "<a href='javascript:void(0)' onclick='openRoleCommonPermissionAuthDlg(\"" + row.roleId + "\")'>通用授权</a>" +
                "</@shiro.hasPermission>");
        strArr.push("<@shiro.hasPermission name='/admin/sys/role/findRoleMenuPermissionAuthTree:request'>" +
                "<a href='javascript:void(0)' onclick='openRoleMenuPermissionAuthDlg(\"" + row.roleId + "\")'>菜单授权</a>" +
                "</@shiro.hasPermission>");
        strArr.push("<@shiro.hasPermission name='/admin/sys/role/removeRoleByRoleId:request'>" +
                "<a href='javascript:void(0)' onclick='removeRoleByRoleId(\"" + row.roleId + "\")'>删除</a>" +
                "</@shiro.hasPermission>");
        return strArr.join(' ');
    }
</script>

</@shiro.hasPermission>
</body>
</html>