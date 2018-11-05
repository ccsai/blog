<!DOCTYPE>
<html>
<head>
    <meta>
     <#include '../../depend/_head.ftl'>
    <title>系統后台登录</title>
</head>
<body>

<#-- 登录对话框 -->
<div class="easyui-dialog" data-options="title:'登录',buttons:'#buttons'" style="padding: 5px 5px">
   <table>
       <tr>
           <td style="text-align: right">用户名</td>
           <td style="text-align: left">
               <input type="text" name="userName">
           </td>
       </tr>
       <tr>
           <td style="text-align: right">密&emsp;码</td>
           <td style="text-align: left">
               <input type="password" name="password">
           </td>
       </tr>
       <tr style="height: 5px">
           <td colspan="2" style="text-align: right;color: red;font-size: 10px">
                12ddDf得到
           </td>
       </tr>
   </table>
</div>

<#-- 工具栏 -->
<div id="buttons">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">确认登录</a>
</div>
</body>
</html>