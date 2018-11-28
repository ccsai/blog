<div id="roleDetailDlg" class="easyui-dialog" title="角色详情"
     data-options="buttons:'#roleDetailDlgToolBar',closed:true,modal:true,inline:true" style="padding: 10px">
<#-- 角色详情表单 -->
    <form id="roleDetailForm" method="post">
    <#-- 判断角色修改或添加 -->
        <input type="hidden" name="operation">
    <#-- 角色编号 -->
        <input type="hidden" name="roleId">
        <table>
            <tr>
                <td>角色名称</td>
                <td>
                    <input class="easyui-textbox" name="roleName" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>角色描述</td>
                <td>
                    <input class="easyui-textbox" name="roleDes" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>角色类型</td>
                <td>
                    <input class="easyui-textbox" name="roleType" data-options="required:true,validType:'checkRoleType'"/>
                </td>
            </tr>
        </table>
    </form>
</div>

<#-- 角色详情工具栏 -->
<div id="roleDetailDlgToolBar">
    <a class="easyui-linkbutton save-btn" type="submit" form="roleDetailForm" data-options="iconCls:'icon-save'">保存</a>
    <a class="easyui-linkbutton cancel-btn" data-options="iconCls:'icon-cancel'">关闭</a>
</div>