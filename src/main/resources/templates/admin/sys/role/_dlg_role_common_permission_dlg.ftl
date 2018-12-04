<div id="roleCommonPermissionDlg" class="easyui-dialog" title="通用授权"
     data-options="buttons:'#roleCommonPermissionDlgToolBar',closed:true,modal:true,width: '80%',height:'80%',inline:true">
    <form id="roleCommonPermissionForm">
        <input type="hidden" name="roleId">
    </form>
<#-- 权限列表 -->
    <table id="commonpermissionDatagrid" class="easyui-datagrid"
           data-options="idField:'permissionId',singleSelect:false,striped:true,height: '100%',width: '100%'">
        <thead>
        <tr>
            <th data-options="field:'wq',checkbox:true"></th>
            <th data-options="field:'permissionId',hidden:'true'"></th>
            <th data-options="field:'permissionName',halign:'center',align:'left'" style="width: 25%">权限名称</th>
            <th data-options="field:'permissionDes',halign:'center',align:'left'" style="width: 25%">权限描述</th>
            <th data-options="field:'url',halign:'center',align:'left'" style="width: 32%">权限url</th>
            <th data-options="field:'urlType',align:'center'" style="width: 15%">权限url类型</th>
        </tr>
        </thead>
    </table>
</div>

<#-- 角色详情工具栏 -->
<div id="roleCommonPermissionDlgToolBar">
<@shiro.hasPermission name="/admin/sys/role/addRoleCommonPermissionAuth:request">
    <a class="easyui-linkbutton save-btn" data-options="iconCls:'icon-save'">保存</a>
</@shiro.hasPermission>
    <a class="easyui-linkbutton cancel-btn" data-options="iconCls:'icon-cancel'">关闭</a>
</div>