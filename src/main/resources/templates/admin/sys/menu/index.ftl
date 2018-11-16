<!DOCTYPE>
<html>
<head>
    <meta>
    <title>菜单管理</title>
    <#include '../../depend/_base.ftl'/>
</head>
<body>
<div style="padding: 3px">
<#-- 菜单表工具 -->
    <div id="menuTreegridToolbar">
        <a class="easyui-linkbutton addMenuBtn" data-options="iconCls:'icon-add'">添加</a>
    </div>

<#--  菜单表-->
    <table id="menuTreegrid" class="easyui-treegrid dataTable"
           data-options="idField:'menuId',treeField:'menuName',toolbar:'menuTreegridToolbar',fitColumns:true,striped:true,animate:true">
        <thead>
        <tr>
            <th data-options="field:'menuId',hidden:'true'">菜单编号</th>
            <th data-options="field:'menuName',halign:'center',align:'left'" style="width: 15%">菜单名称</th>
            <th data-options="field:'menuDes',halign:'center',align:'left'" style="width: 30%">菜单描述</th>
            <th data-options="field:'sortNo',halign:'center',align:'right'" style="width: 15%">排序号</th>
            <th data-options="field:'status',halign:'center',align:'center',formatter:formatterMenuTreegridMenuIsShow"
                style="width: 15%">显示
            </th>
            <th data-options="field:'oper',halign:'center',align:'center',formatter:formatterMenuTreegridOper"
                style="width: 25%">操作
            </th>
        </tr>
        </thead>
    </table>
</div>

<#-- 菜单详情dialog -->
<#include '_dlg_menu_detail.ftl'/>

<script src="${basePath}/js/admin/sys/menu/index.js"></script>
</body>
</html>