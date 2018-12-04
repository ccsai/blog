<div id="roleMenuPermissionDlg" class="easyui-dialog" title="菜单授权"
     data-options="buttons:'#roleMenuPermissionDlgToolBar',closed:true,modal:true,width: '80%',height:'80%',inline:true">
    <form id="roleMenuPermissionForm" style="height: 100%;width: 100%">
        <input type="hidden" name="roleId">
        <table id="menuPermissionTreegrid" class="easyui-treegrid"
               data-options="idField:'menuId',treeField:'menuName',fitColumns:true,singleSelect:false,height: '100%',width:'100%',fitColumns:true,striped:true,animate:true">
            <thead frozen="true">
            <tr>
                <th data-options="field:'menuName',halign:'center',align:'left'" style="width: 20%">菜单</th>
            </tr>
            </thead>
            <thead>
            <tr>
                <th data-options="field:'menuId',hidden:'true'">菜单编号</th>
                <th data-options="field:'permissionList',halign:'center',align:'left',formatter:formatterRoleMenuPermissionList">权限</th>
            </tr>
            </thead>
        </table>
    </form>
</div>

<#-- 角色详情工具栏 -->
<div id="roleMenuPermissionDlgToolBar">
    <@shiro.hasPermission name="/admin/sys/role/authorizationMenuPermission:request">
        <a class="easyui-linkbutton save-btn" data-options="iconCls:'icon-save'">保存</a>
    </@shiro.hasPermission>
    <a class="easyui-linkbutton cancel-btn" data-options="iconCls:'icon-cancel'">关闭</a>
</div>