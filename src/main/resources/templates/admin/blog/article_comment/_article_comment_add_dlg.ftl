<div id="articleCommentAddDlg" class="easyui-dialog" title="文章评论添加"
     data-options="buttons:'#articleCommentAddDlgToolBar',closed:true,modal:true,width:'90%',height:'90%'"
     style="padding: 10px">
<#-- 文章评论添加表单 -->
    <form id="articleCommentAddForm">
        <div style="width: 100%;height: 10%">
            请选择文章&nbsp;
            <@shiro.hasPermission name="/admin/blog/articleComment/findArticleList:request">
                <select class="easyui-combobox" name="articleId" id="acArticleId"
                        data-options="panelHeight:'auto',limitToList:true,required:true,width:'50%'">
                </select>
            </@shiro.hasPermission>
            <@shiro.lacksPermission name="/admin/blog/articleComment/findArticleList:request">
                <input type="text" class="easyui-textbox" value="你没有文章评论管理的文章列表权限！" data-options="readonly:true"/>
            </@shiro.lacksPermission>
        </div>
        <div style="width: 100%;height: 76%">
            <textarea id="articleCommentUE" style="width: 100%;height: 80%;"></textarea>
        </div>
    </form>
</div>

<#-- 文章评论添加工具栏 -->
<div id="articleCommentAddDlgToolBar">
    <a class="easyui-linkbutton save-btn" data-options="iconCls:'icon-save'">保存</a>
    <a class="easyui-linkbutton cancel-btn" data-options="iconCls:'icon-cancel'">关闭</a>
</div>