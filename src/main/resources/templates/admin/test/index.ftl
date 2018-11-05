<!DOCTYPE>
<html>
<head>
    <meta>
    <#include '../depend/_head.ftl'>
    <title>系統管理</title>
</head>
<body class="easyui-layout">
<#-- 头部 -->
<div class="easyui-layout" data-options="region:'north',border: false" style="height: 50px;margin: 0">系统控制台</div>

<#-- 目录 -->
<div data-options="region:'west'" style="width: 200px;"></div>

<#-- 主体 -->
<div data-options="region:'center'">
    欢迎登陆
    <#assign a='${a}' />
</div>

<#-- 底部 -->
<div data-options="region:'south'" style="display:table-cell; height: 30px;text-align: center;vertical-align: middle">
    作者：陈川&nbsp;&nbsp;&nbsp;项目作用：系统控制台
</div>

<script>
    var list = [
        <#list userList as user>
            ${user}
            <#if user_index != userList?size-1 >,</#if>
        </#list>
    ]
</script>
</body>
</html>