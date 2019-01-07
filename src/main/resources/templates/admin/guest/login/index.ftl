<!DOCTYPE>
<html>
<head>
    <meta>
    <title>系統后台登录</title>
    <#include '../../depend/_head.ftl'>
    <#include '../../../common/depend/_jquery_serializejson.ftl'/>
    <script src="${basePath!}/js/admin/guest/login/index.js"></script>
</head>
<body>

<#-- 登录对话框 -->
<div class="easyui-dialog" data-options="title:'登录',buttons:'#buttons'" style="padding: 10px 10px 0 10px">
<#-- 登录表单 -->
    <form id="loginForm">
        <table>
            <tr>
                <td style="text-align: right">用户名</td>
                <td style="text-align: left">
                    <input type="text" name="LoginName">
                </td>
            </tr>
            <tr>
                <td style="text-align: right">密&emsp;码</td>
                <td style="text-align: left">
                    <input type="password" name="password">
                </td>
            </tr>
            <tr>
                <td colspan="2" id="loginFailMessageTd"
                    style="text-align: right;color: red;font-size: 10px;height: 18px;margin-bottom: 0">
                </td>
            </tr>
        </table>
    </form>
</div>

<#-- 工具栏 -->
<div id="buttons">
    <a id="loginBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">确认登录</a>
</div>
</body>
</html>