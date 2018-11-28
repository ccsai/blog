<div id="roleMenuPermissionDlg" class="easyui-dialog" title="菜单授权"
     data-options="buttons:'#roleMenuPermissionDlgToolBar',closed:true,modal:true,width: '80%',height:'80%',inline:true">
    <table id="menuPermissionTreegrid" class="easyui-treegrid" data-options="idField:'menuId',treeField:'menuName',checkbox:true,singleSelect:false,height: '100%',width:'100%',fitColumns:true,striped:true,animate:true">
        <thead>
        <tr>
            <th data-options="field:'',checkbox:true"></th>
            <th data-options="field:'menuId',hidden:'true'">菜单编号</th>
            <th data-options="field:'menuName',halign:'center',align:'left'" style="width: 20%">菜单</th>
            <th data-options="field:'permissionList',halign:'center',align:'left',formatter:formatterRoleMenuPermissionList" style="width: 80%">权限</th>
        </tr>
        </thead>
    </table>
</div>

<#-- 角色详情工具栏 -->
<div id="roleMenuPermissionDlgToolBar">
    <a class="easyui-linkbutton save-btn" data-options="iconCls:'icon-save'">保存</a>
    <a class="easyui-linkbutton cancel-btn" data-options="iconCls:'icon-cancel'">关闭</a>
</div>