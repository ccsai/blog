<div id="friendlyLinkDetailDlg" class="easyui-dialog" title="友情链接详情"
     data-options="buttons:'#friendlyLinkDetailDlgToolBar',closed:true,modal:true,inline:true" style="padding: 10px">
<#-- 友情链接详情表单 -->
    <form id="friendlyLinkDetailForm" method="post">
    <#-- 判断友情链接修改或添加 -->
        <input type="hidden" name="operation">
    <#-- 友情链接编号 -->
        <input type="hidden" name="friendlyLinkId">
        <table>
            <tr>
                <td>友情链接名称</td>
                <td>
                    <input class="easyui-textbox" name="linkName" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>友情链接url</td>
                <td>
                    <input class="easyui-textbox" name="url" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>logo</td>
                <td>
                    <input class="easyui-textbox" name="logo""/>
                </td>
            </tr>
            <tr>
                <td>排序</td>
                <td>
                    <input class="easyui-textbox" name="sortNo" data-options="required:true,validType:'checkSortNo'"/>
                </td>
            </tr>
            <tr>
                <td>显示</td>
                <td>
                    <select class="easyui-combobox" name="isShow"
                            data-options="required:true,panelHeight:'auto',editable:false,width:175">
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>

<#-- 友情链接详情工具栏 -->
<div id="friendlyLinkDetailDlgToolBar">
    <a class="easyui-linkbutton save-btn" data-options="iconCls:'icon-save'">保存</a>
    <a class="easyui-linkbutton cancel-btn" data-options="iconCls:'icon-cancel'">关闭</a>
</div>