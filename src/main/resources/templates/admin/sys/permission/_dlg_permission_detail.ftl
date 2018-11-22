<div id="permissionDetailDlg" class="easyui-dialog" title="权限详情"
     data-options="buttons:'#permissionDetailDlgToolBar',closed:true,modal:true,inline:true" style="padding: 10px">
<#-- 菜单详情表单 -->
    <form id="permissionDetailForm" method="post">
    <#-- 判断权限修改或添加 -->
        <input type="hidden" name="operation">
    <#-- 权限编号 -->
        <input type="hidden" name="permissionId">
        <table>
            <tr>
                <td>权限名称</td>
                <td>
                    <input class="easyui-textbox" name="permissionName" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>权限描述</td>
                <td>
                    <input class="easyui-textbox" name="permissionDes"/>
                </td>
            </tr>
            <tr>
                <td>权限url</td>
                <td>
                    <input class="easyui-textbox" name="url" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>url类型</td>
                <td>
                    <input class="easyui-textbox" name="urlType" data-options="required:true,validType:'checkPermissionUrlType'"/>
                </td>
            </tr>
            <tr>
                <td>所属菜单</td>
                <td>
                    <@shiro.hasPermission name="/admin/sys/menu/findAllMenuTree:request">
                        <input type="text" id="permissionDetailFormMenuId" class="easyui-combotree"
                               name="menuId"
                               data-options="panelHeight:'auto',editable:false"/>
                    </@shiro.hasPermission>
                    <@shiro.lacksPermission name="/admin/sys/menu/findAllMenuTree:request">
                        <input type="text" class="easyui-textbox" value="你需要有访问菜单树表的权限" data-options="readonly:true"/>
                    </@shiro.lacksPermission>
                </td>
            </tr>
        </table>
    </form>
</div>

<#-- 菜单详情工具栏 -->
<div id="permissionDetailDlgToolBar">
    <a class="easyui-linkbutton save-btn" data-options="iconCls:'icon-save'">保存</a>
    <a class="easyui-linkbutton cancel-btn" data-options="iconCls:'icon-cancel'">关闭</a>
</div>