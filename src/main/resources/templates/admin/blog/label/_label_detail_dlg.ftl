<div id="labelDetailDlg" class="easyui-dialog" title="标签详情"
     data-options="buttons:'#labelDetailDlgToolBar',closed:true,modal:true,inline:true" style="padding: 10px">
<#-- 标签详情表单 -->
    <form id="labelDetailForm" method="post">
    <#-- 判断标签修改或添加 -->
        <input type="hidden" name="operation">
    <#-- 标签编号 -->
        <input type="hidden" name="labelId">
        <table>
            <tr>
                <td>标签</td>
                <td>
                    <input class="easyui-textbox" name="label" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>排序</td>
                <td>
                    <input class="easyui-textbox" name="sortNo" data-options="required:true,validType:'checkSortNo'"/>
                </td>
            </tr>
        </table>
    </form>
</div>

<#-- 标签详情工具栏 -->
<div id="labelDetailDlgToolBar">
    <a class="easyui-linkbutton save-btn" data-options="iconCls:'icon-save'">保存</a>
    <a class="easyui-linkbutton cancel-btn" data-options="iconCls:'icon-cancel'">关闭</a>
</div>