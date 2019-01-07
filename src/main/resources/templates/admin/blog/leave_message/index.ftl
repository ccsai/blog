<!DOCTYPE>
<html>
<head>
    <meta>
    <title>留言管理</title>
    <#include '../../depend/_head.ftl'/>
    <#include '../../../common/depend/_jquery_serializejson.ftl'/>
    <#include '../../../common/depend/_ueditor.ftl'/>
    <script src="${basePath!}/js/common/allmayneed/Utils.js"></script>
    <script src="${basePath!}/js/admin/blog/leave_message/index.js"></script>
</head>
<body>
<div class="easyui-layout" style="height: 99.8%;width: 100%;margin-top: 3px;margin-left: 3px">
    <div data-options="region:'west'" style="height:100%;width: 20%">
        <@shiro.hasPermission name="/admin/blog/leaveMessage/findIsHaveNewLeaveMessageByManager:request">
        <#-- 用户搜索 -->
            <div id="lmUserDatagridToolbar" style="height: 3%">
                <div style="float: right;width: 100%">
                    <form id="lmUserDatagridSearchForm">
                        <input type="text" class="easyui-textbox" name="keyWord" data-options="prompt:'用户/姓名'"
                               style="width: 60%"/>
                        &nbsp;&nbsp;
                        <a class="easyui-linkbutton" id="lmUserDatagridSearchBtn"
                           data-options="iconCls:'icon-search'">查询</a>
                    </form>
                </div>
            </div>

        <#-- 用户选择列表 -->
            <table id="lmUserDatagrid" class="easyui-datagrid" style="width: 100%;height: 95.3%"
                   data-options="idField:'userId',toolbar:'lmUserDatagridToolbar',rownumbers:true,singleSelect:true,striped:true">
                <thead>
                <tr>
                    <th data-options="field:'userId',hidden:'true'"></th>
                    <th data-options="field:'leaveMessageNumber',halign:'center',align:'right'" style="width: 29%">未读
                    </th>
                    <th data-options="field:'loginName',halign:'center',align:'left'" style="width: 41%">用户</th>
                    <th data-options="field:'realName',halign:'center',align:'left'" style="width: 30%">姓名</th>
                </tr>
                </thead>
            </table>
        </@shiro.hasPermission>
    </div>
    <div data-options="region:'center',title:'留言板'" style="width: 80%;">
        <@shiro.hasPermission name="/admin/blog/leaveMessage/findLeaveMessageByUserId:request">
            <h1 id="leaveMessageNotice"
                style='font-size: 35px;height: 100%;display: flex; justify-content: center; align-items: center;'>
                请选择用户留言
            </h1>
        <#-- 留言区域 -->
            <div id="leaveMessageDiv" style="display: none;width: 100%;height: 100%;background: rgb(244, 244, 244)">
            <#-- 留言板头部 -->
                <div style="height: 8%;width:100%;background: rgb(149, 184, 231)">
                    <div style="margin: 0 8px;height: 100%;display: flex; align-items: center;float: left">
                        <span id="leaveMessageUserName" style="font-size: 20px"></span>
                    </div>
                    <div style="height: 100%;display: flex; align-items: center;float: right;margin-right: 8px">
                        <@shiro.hasPermission name="/admin/blog/leaveMessage/leaveMessage:request">
                            <a class="easyui-linkbutton" id="sendLeaveMessageBtn"
                               data-options="iconCls:'icon-add'">发留言</a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="/admin/blog/leaveMessage/removeLeaveMessagesByLeaveMessageIds:request">
                            <a class="easyui-linkbutton" id="removeLeaveMessageBtn"
                               data-options="iconCls:'icon-remove'">删除</a>
                        </@shiro.hasPermission>
                        <a class="easyui-linkbutton" id="reloadLeaveMessageBtn"
                           data-options="iconCls:'icon-reload'">刷新</a>
                        <label style="display: inline-block;background: white;border-radius: 10%;width: 54px;height: 24px;">
                            <div style="display: flex;justify-content: center; align-items: center;">
                                <input id="checkAllLeaveMessageBtn" type="checkbox">&nbsp;全选
                            </div>
                        </label>
                    </div>
                </div>
            <#-- 留言 -->
                <div id="leaveMessageList" style="overflow: auto;height: 92%;width: 100%"></div>
            </div>
        </@shiro.hasPermission>
    </div>

<#-- 发送留言（ueditor）的对话框 -->
<#include '_leave_message_editor_dlg.ftl'/>
</div>

</body>
</html>