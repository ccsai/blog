<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta>
    <title>角色管理</title>
    <#include '../../depend/_head.ftl'/>
    <#include '../../depend/_jquery_serializejson.ftl'/>
    <script src="${basePath!}/js/admin/sys/user/index.js"></script>
</head>
<body>
<@shiro.hasPermission name='/admin/sys/user/findUserListByPage:request'>
<div style="padding: 3px;height: 94.8%">
<#-- 用户分页列表工具栏 -->
    <div id="userDatagridToolbar" style="height: 26px">
    <@shiro.hasPermission name="/admin/sys/user/addUser:request">
        <div style="float: left">
            <a class="easyui-linkbutton addUserBtn" data-options="iconCls:'icon-add'">添加</a>
        </div>
    </@shiro.hasPermission>
        <div style="float: right">
            <form id="userDatagridSearchForm">
                关键字&nbsp;<input type="text" class="easyui-textbox" name="keyWord"
                                data-options="prompt:'登录名/姓名/电话/邮箱'"/>
                &nbsp;&nbsp;
                管理员&nbsp;
                <select class="easyui-combobox" name="loginType" data-options="panelHeight:'auto',editable:false"
                        style="width: 50px">
                    <option value="1" selected>是</option>
                    <option value="0">否</option>
                </select>
                &nbsp;&nbsp;
                状态&nbsp;
                <select class="easyui-combobox" name="status" data-options="panelHeight:'auto',editable:false"
                        style="width: 100px">
                    <option value="">所有状态</option>
                    <option value="1">有效</option>
                    <option value="0">无效</option>
                </select>
                &nbsp;&nbsp;
                <a class="easyui-linkbutton" id="userDatagridSearchBtn" data-options="iconCls:'icon-search'">查询</a>
            </form>
        </div>
    </div>

<#-- 用户分页列表 -->
    <table id="userDatagrid" class="easyui-datagrid" style="width: 100%;height: 100%"
           data-options="idField:'userId',pagination:true,toolbar:'userDatagridToolbar',singleSelect:true,pageSize:50,rownumbers:true,striped:true">
        <thead>
        <tr>
            <th data-options="field:'userId',hidden:'true'"></th>
            <th data-options="field:'loginName',halign:'center',align:'left'" style="width: 15%">用户名</th>
            <th data-options="field:'realName',halign:'center',align:'left'" style="width: 15%">姓名</th>
            <th data-options="field:'phone',halign:'center',align:'left'" style="width: 15%">电话</th>
            <th data-options="field:'email',halign:'center',align:'left'" style="width: 15%">邮箱</th>
            <th data-options="field:'loginType',align:'center',formatter:formatterUserLoginType" style="width: 10%">
                管理员
            </th>
            <th data-options="field:'status',halign:'center',align:'left',formatter:formatterUserStatus"
                style="width: 10%">状态
            </th>
            <th data-options="field:'userOperation',align:'center',formatter:userDatagridOperation" style="width: 20%">
                操作
            </th>
        </tr>
        </thead>
    </table>
</div>

<#-- 用户详情对话框 -->
<#include '_dlg_user_detail.ftl'/>
<#-- 用户角色授权表 -->
<#include '_dlg_user_role_auth.ftl'/>

<script>
    /**
     * 格式化用户分页列表操作列
     * @param value
     * @param row
     * @return {string}
     */
    function userDatagridOperation(value, row) {
        var strArr = [];
        strArr.push("<@shiro.hasPermission name='/admin/sys/user/editUserByUserId:request'>" +
                "<a href='javascript:void(0)' onclick='openUserDetailDlgOfEdit(\"" + row.userId + "\")'>修改</a>" +
                "</@shiro.hasPermission>");
        strArr.push("<@shiro.hasPermission name='/admin/sys/user/findUserByUserId:request'>" +
                "<a href='javascript:void(0)' onclick='openUserDetailDlgOfDetail(\"" + row.userId + "\")'>详情</a>" +
                "</@shiro.hasPermission>");
        strArr.push("<@shiro.hasPermission name='/admin/sys/user/findUserRoleAuthListByUserId:request'>" +
                "<a href='javascript:void(0)' onclick='openUserRoleAuthDlg(\"" + row.userId + "\")'>添加角色</a>" +
                "</@shiro.hasPermission>");
        strArr.push("<@shiro.hasPermission name='/admin/sys/user/removeUserByUserId:request'>" +
                "<a href='javascript:void(0)' onclick='removeUserByRoleId(\"" + row.userId + "\")'>删除</a>" +
                "</@shiro.hasPermission>");
        return strArr.join(' ');
    }
</script>
</@shiro.hasPermission>
</body>
</html>