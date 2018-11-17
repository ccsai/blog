<div id="menuDetailDlg" class="easyui-dialog" title="菜单详情"
     data-options="buttons:'#menuDetailDlgToolBar',closed:true,modal:true" style="padding: 10px">
<#-- 菜单详情表单 -->
    <form id="menuDetailForm" method="post">
    <#-- 判断添加修改 -->
        <input type="hidden" name="operation">
    <#-- 菜单编号 -->
        <input type="hidden" name="menuId">
        <table>
            <tr>
                <td>菜单名称</td>
                <td>
                    <input class="easyui-textbox" name="menuName" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>上级菜单</td>
                <td>
                    <input id="menuDetailFormPMenuId" class="easyui-combotree" name="pMenuId"
                           data-options="panelHeight:'auto',editable:false,required:true"
                           style="width: 175px">
                </td>
            </tr>
            <tr>
                <td>菜单说明</td>
                <td>
                    <input class="easyui-textbox" name="menuDes" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>排序号</td>
                <td>
                    <input class="easyui-textbox" name="sortNo" data-options="required:true,validType:'checkSortNo'"/>
                </td>
            </tr>
            <tr>
                <td>状态</td>
                <td>
                    <select class="easyui-combobox" name="status" data-options="panelHeight:'auto',editable:false"
                            style="width: 175px">
                        <option value="1">显示</option>
                        <option value="0">影藏</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>

<#-- 菜单详情工具栏 -->
<div id="menuDetailDlgToolBar">
    <a class="easyui-linkbutton save-btn" data-options="iconCls:'icon-save'">保存</a>
    <a class="easyui-linkbutton cancel-btn" data-options="iconCls:'icon-cancel'">关闭</a>
</div>