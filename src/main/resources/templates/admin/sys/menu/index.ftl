<!DOCTYPE>
<html>
<head>
    <meta>
    <title>菜单管理</title>
    <#include '../../depend/_head.ftl'/>
    <script src="${basePath}/js/admin/sys/menu/index.js"></script>
</head>
<body>
<@shiro.hasPermission name="/admin/sys/menu/findAllMenuTree:request">
    <div style="padding: 3px">
    <#-- 菜单表工具 -->
        <div id="menuTreegridToolbar" style="height: 26px">
            <@shiro.hasPermission name="/admin/sys/menu/addMenu:request">
                <a class="easyui-linkbutton addMenuBtn" data-options="iconCls:'icon-add'">添加</a>
            </@shiro.hasPermission>
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
</@shiro.hasPermission>

<#-- 菜单详情dialog -->
<#include '_dlg_menu_detail.ftl'/>

<script>
    /**
     * 格式化菜单操作栏
     * @param val
     * @param row
     * @return {string}
     */
    function formatterMenuTreegridOper(value, row) {
        var strArr = [];
        strArr.push("<@shiro.hasPermission name='/admin/sys/menu/editMenuByMenuId:request'>" +
                "<a href='javascript:void(0)' onclick='openMenuDetailDlgOfEdit(\"" + row.menuId + "\")'>修改</a>" +
                "</@shiro.hasPermission>");
        strArr.push("<@shiro.hasPermission name='/admin/sys/menu/findMenuByMenuId:request'>" +
                "<a href='javascript:void(0)' onclick='openMenuDetailDlgOfDetail(\"" + row.menuId + "\")'>详情</a>" +
                "</@shiro.hasPermission>");
        strArr.push("<@shiro.hasPermission name='/admin/sys/menu/removeMenuByMenuId:request'>" +
                "<a href='javascript:void(0)' onclick='removeMenuByMenuId(\"" + row.menuId + "\")'>删除</a>" +
                "</@shiro.hasPermission>");
        return strArr.join(' ');
    }
</script>
</body>
</html>