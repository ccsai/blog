<div id="articleDetailDlg" class="easyui-dialog" title="标签详情"
     data-options="buttons:'#articleDetailDlgToolBar',closed:true,modal:true,inline:true"
     style="padding: 10px;width: 90%;height: 90%;overflow: auto">
<#-- 标签详情表单 -->
    <form id="articleDetailForm">
    <#-- 判断标签修改或添加 -->
        <input type="hidden" name="operation">
    <#-- 标签编号 -->
        <input type="hidden" name="articleId">
        <div style="width: 100%;height: 35px">
            <div style="float: left">
                文章标题&nbsp;
                <input type="text" class="easyui-textbox" name="title" data-options="required:true,prompt:'请输入文章标题'"/>
            </div>
            <div id="articleStatistical" style="float: right;">
                赞&nbsp;<input name="supportNumber" class="easyui-textbox" disabled>
                &nbsp;&nbsp;
                访问量&nbsp;<input name="visitedNumber" class="easyui-textbox" disabled>
            </div>
        </div>
        <textarea id="articleUE" style="width: 100%;height: 250px;"></textarea>
        <div style="height: 18px;padding: 10px 0">
            <div style="float: left">
                排序&nbsp;<input class="easyui-textbox" name="sortNo"
                               data-options="required:true,validType:'checkSortNo'">
                &nbsp;&nbsp;
            </div>
            <div style="float: left">
                banner&nbsp;
                <select class="easyui-combobox" name="banner"
                        data-options="required:true,panelHeight:'auto',editable:false,width:175">
                    <option value=""></option>
                    <option value="1">技术交流</option>
                    <option value="2">生活日志</option>
                    <option value="3">兴趣爱好</option>
                    <option value="4">关于自己</option>
                </select>
                &nbsp;&nbsp;
            </div>
            <div style="float: left">
                私有&nbsp;
                <select class="easyui-combobox" name="isPrivate"
                        data-options="required:true,panelHeight:'auto',editable:false,width:75">
                    <option value="0">否</option>
                    <option value="1">是</option>
                </select>
            </div>
        </div>
        <div>
            <fieldset>
                <legend>标签</legend>
                <@shiro.hasPermission name="/admin/blog/article/findLabelInArticle:request">
                    <div id="labelListInArticle"></div>
                </@shiro.hasPermission>
                <@shiro.lacksPermission name="/admin/blog/article/findLabelInArticle:request">
                    <h5 style="color: red">你没有文章管理的标签选择权限！</h5>
                </@shiro.lacksPermission>
            </fieldset>
        </div>
    </form>
</div>

<#-- 标签详情工具栏 -->
<div id="articleDetailDlgToolBar">
    <a class="easyui-linkbutton save-btn" data-options="iconCls:'icon-save'">保存</a>
    <@shiro.hasPermission name="/admin/blog/article/previewArticle:request">
        <a class="easyui-linkbutton previe-btn" data-options="iconCls:'icon-search'">预览</a>
    </@shiro.hasPermission>
    <a class="easyui-linkbutton cancel-btn" data-options="iconCls:'icon-cancel'">关闭</a>
</div>