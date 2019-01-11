<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta>
    <title>垃圾数据管理</title>
    <#include '../../depend/_head.ftl'/>
    <script src="${basePath!}/js/admin/resource/junk_resource/index.js"></script>
</head>
<body>
<div style="padding: 5px;">
    <@shiro.hasPermission name="/admin/resource/junkResource/removeSecurityJunkData:request">
        <div style="padding: 0 5px 5px 0">
            <a id="clearSecurityJunkDataBtn" class="easyui-linkbutton clearBtn"
               data-options="iconCls:'icon-add'">清理安全管理数据</a>
        </div>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/admin/resource/junkResource/removeBlogBusiJunkData:request">
        <div>
            <div style="float: left;padding: 0 5px 5px 0">
                <a id="clearBlogBusiJunkDataBtn" class="easyui-linkbutton clearBtn" data-options="iconCls:'icon-add'">清理博客系统业务数据</a>
            </div>
        <@shiro.hasPermission name="/admin/resource/junkResource/removeJunkOssFromOssAndDB:request">
            <div style="padding: 0 5px 5px 0">
                <a id="clearJunkOssDataBtn" class="easyui-linkbutton clearBtn"
                   data-options="iconCls:'icon-add',disabled:true">清理对象存储及其数据</a>
            </div>
        </@shiro.hasPermission>
        </div>
    </@shiro.hasPermission>
</div>

<div id="junkDataClearNoticeDlg" class="easyui-dialog" title="等待提示" style="width:200px;height:100px;"
     data-options="iconCls:'icon-tip',closed:true,modal:true,closable:false">
    <div>
        <span style="font-weight: bold">数据清理中，请耐心等待...</span>
    </div>
</div>
</body>
</html>