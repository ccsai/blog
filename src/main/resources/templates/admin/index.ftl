<!DOCTYPE>
<html>
<head>
    <meta>
    <#include 'depend/_head.ftl'>
    <link href="${basePath}/css/admin/index.css" rel="stylesheet" type="text/css">
    <script src="${basePath}/js/admin/index.js"></script>
    <title>系統管理</title>
</head>
<body class="easyui-layout">

<#-- 头部 -->
<div class="easyui-layout" data-options="region:'north',split:true"
     style="height: 50px;margin: 0;border: none;background: rgb(149, 184, 231)">
    <div data-options="region:'west'"
         style="width: 200px;text-align: center;vertical-align: middle;background: rgb(149, 184, 231);font-size: 32px">
        系统控制台
    </div>
    <div data-options="region:'center'"
         style="background: rgb(149, 184, 231);display: table-cell;vertical-align: middle">
        <div style="float: right;padding: 0 50px;vertical-align: center">
            欢迎你【XXX】&nbsp;&nbsp;&nbsp;
            <div id="loginOutIcon" style="float: right">
                <a id="dd" href="javascript:void(0)">
                    <img src="${basePath}/plugin/easyui/themes/icons/more.png">
                </a>
            </div>
        </div>
    </div>
</div>

<#-- 顶部间距 -->
<div style="margin: 1px 0"></div>

<#-- 目录 -->
<div id="navMenu" data-options="region:'west',split:true,title:'菜单导航'" style="width: 200px">
    <div id="aa" class="easyui-accordion" data-options="fit:true">
        <div data-options="title:'模块一'" style="height: 100%">
            <ul id="tree"></ul>
        </div>
        <div data-options="title:'模块二'" style="height: 100%">
            树
        </div>
    </div>
</div>

<#-- 主体 -->
<div id="contentTabs" class="easyui-tabs" data-options="region:'center',animate:true,tabWidth:'auto'"
     style="border: none">
    <div title="Tab1">
        hellow
    </div>
</div>

<#-- 底部 -->
<div data-options="region:'south'" style="display:table-cell; height: 30px;text-align: center;vertical-align: middle">
    作者：陈川&nbsp;&nbsp;&nbsp;项目作用：系统控制台
</div>
</body>
</html>