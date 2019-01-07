<div id="articleCommentDetailDlg" class="easyui-dialog" title="文章评论详情"
     data-options="buttons:'#articleCommentDetailDlgToolBar',closed:true,modal:true,width:'90%',height:'500px'"
     style="padding: 10px">
<#-- 文章评论详情表单 -->
    <form id="articleCommentDetailForm">
    <#-- 文章评论编号 -->
        <input type="hidden" name="commentId">
        <div style="height: 15%">
            <h1 style="font-size: 20px;margin: 0 0 5px 0">文章：<span class="articleTitle" style="font-size: 20px"></span></h1>
            <div style="float: left">
                <span>评论者：</span><span class="commentUser" style=";display: inline-block;width: 100px"></span>&emsp;
                <span class="createTime" style="color: grey"></span>
            </div>
            <div style="float: right;color: grey">
                状态：<span class="isChecked" style="color: grey;display: inline-block;width: 80px"></span>
                赞：<span class="supportNumber" style="color: grey;display: inline-block;width: 50px"></span>
                踩：<span class="noSupportNumber" style="color: grey;display: inline-block;width: 50px"></span>
            </div>
        </div>
        <div class="comment" style="height: 82%;overflow: auto;border: 1px grey solid"></div>
    </form>
</div>

<#-- 文章评论详情工具栏 -->
<div id="articleCommentDetailDlgToolBar">
    <@shiro.hasPermission name="/admin/blog/articleComment/editArticleCommentIsCheckedByArticleCommentIds:request">
        <a class="easyui-linkbutton checked-btn" data-options="iconCls:'icon-ok'">过审</a>
        <a class="easyui-linkbutton no-checked-btn" data-options="iconCls:'icon-no'">拒审</a>
    </@shiro.hasPermission>
    <a class="easyui-linkbutton cancel-btn" data-options="iconCls:'icon-cancel'">关闭</a>
</div>