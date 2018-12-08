<div id="userRoleAuthDlg" class="easyui-dialog" title="用户角色选择"
     data-options="buttons:'#userRoleAuthDlgToolBar',closed:true,modal:true,width: '80%',height:'80%',inline:true">

<#-- 用户编号 -->
    <input type="hidden" name="userId">
<#-- 角色列表 -->
    <table id="userRoleAuthDatagrid" class="easyui-datagrid"
           data-options="idField:'roleId',width:'100%',height:'100%',striped:true">
        <thead>
        <tr>
            <th data-options="field:'checkbox',checkbox:true"></th>
            <th data-options="field:'roleId',hidden:'true'">角色编号</th>
            <th data-options="field:'roleName',halign:'center',align:'left'" style="width: 33%">角色名称</th>
            <th data-options="field:'roleDes',halign:'center',align:'left'" style="width: 33%">角色描述</th>
            <th data-options="field:'roleType',align:'center'" style="width: 33%">角色类型</th>
        </tr>
        </thead>
    </table>
</div>

<#-- 角色详情工具栏 -->
<div id="userRoleAuthDlgToolBar">
<@shiro.hasPermission name="/admin/sys/user/addUserRoleAuth:request">
    <a class="easyui-linkbutton save-btn" data-options="iconCls:'icon-save'">保存</a>
</@shiro.hasPermission>
    <a class="easyui-linkbutton cancel-btn" data-options="iconCls:'icon-cancel'">关闭</a>
</div>