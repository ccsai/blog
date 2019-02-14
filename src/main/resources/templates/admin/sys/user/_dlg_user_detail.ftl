<div id="userDetailDlg" class="easyui-dialog" title="用户详情"
     data-options="buttons:'#userDetailDlgToolBar',closed:true,modal:true,inline:true" style="padding: 10px">
<#-- 用户详情表单 -->
    <form id="userDetailForm" method="post">
    <#-- 判断用户修改或添加 -->
        <input type="hidden" name="operation">
    <#-- 用户编号 -->
        <input type="hidden" name="userId">
        <table>
            <tr>
                <td>用户名</td>
                <td>
                    <input class="easyui-textbox" name="loginName" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>姓名</td>
                <td>
                    <input class="easyui-textbox" name="realName"/>
                </td>
            </tr>
            <tr>
                <td>密码</td>
                <td>
                    <input class="easyui-passwordbox" name="password"/>
                </td>
            </tr>
            <tr>
                <td>手机</td>
                <td>
                    <input class="easyui-textbox" name="phone" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>邮箱</td>
                <td>
                    <input class="easyui-textbox" name="email" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>管理员</td>
                <td>
                    <select class="easyui-combobox" name="loginType" data-options="panelHeight:'auto',editable:false"
                            style="width: 175px">
                        <option value="1" selected>是</option>
                        <option value="0">否</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>状态</td>
                <td>
                    <select class="easyui-combobox" name="status" data-options="panelHeight:'auto',editable:false"
                            style="width: 175px">
                        <option value="1" selected>有效</option>
                        <option value="0">无效</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>

<#-- 用户详情工具栏 -->
<div id="userDetailDlgToolBar">
    <a class="easyui-linkbutton save-btn" data-options="iconCls:'icon-save'">保存</a>
    <a class="easyui-linkbutton cancel-btn" data-options="iconCls:'icon-cancel'">关闭</a>
</div>