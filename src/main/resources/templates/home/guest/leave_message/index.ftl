<!doctype html>
<html lang="en">
<head>
    <#include '../../depend/_head.ftl'/>
<#-- ueditor -->
    <#include '../../../common/depend/_ueditor.ftl'/>
<#-- 留言主页 css -->
    <link href="${basePath!}/css/home/guest/leave_message/index.css" rel="stylesheet" type="text/css"/>
<#-- 留言主页 js -->
    <script src="${basePath!}/js/home/guest/leave_message/index.js"></script>
    <title>浩瀚星尘的博客</title>
</head>
<body>
<div id="body" class="container">
<#-- 侧边栏 -->
    <#include '../../common/_side.ftl'/>
<#-- 导航栏 -->
    <#include '../../common/_nav.ftl'/>

<#-- 主体内容部分 -->
    <section class="container pt-sm-3">
        <div class="row">
        <#-- 左侧 -->
            <div class="col-sm-8 pl-sm-3 pr-sm-3">

                <div id="main-list" class="pt-sm-3 rounded">
                    <h4 class="font-weight-bold text-dark">留言</h4>
                <#-- 列表标题分隔线 -->
                    <hr class="list-hr module-hr">

                <#-- 留言主页部分 -->
                    <div id="leave-message" class="d-sm-flex flex-column mt-sm-3 mb-sm-3 pl-sm-2 pb-sm-2 pr-sm-2">
                        <div>
                            <p class="text-warning">
                                各位访客，如果你有甚么话想对博主说，请在下方留言吧！
                            </p>
                        </div>

                    <#-- ueditor评论框 -->
                        <div>
                            <form id="leaveMessageForm" method="post" action="/b.lm/leaveMessage">
                                <div class="leave-message-ue-div">
                                    <textarea id="leave-message-ue"></textarea>
                                </div>
                                <button type="submit" class="btn btn-sm btn-success float-sm-right mr-sm-2">留言</button>
                            </form>
                        </div>

                    <#-- 留言列表 -->
                        <#if leaveMessageList?? && leaveMessageList?size &gt; 0 >
                            <div>
                                <div class="d-sm-flex flex-column">
                                    <h6 class="font-weight-bold">历史记录</h6>
                                    <#list leaveMessageList as lm>
                                        <div class="d-sm-flex flex-column">
                                        <#-- 一条留言开始 -->
                                            <div class="border border-warning rounded p-sm-2 mt-sm-1 mb-sm-2">
                                                <div class="d-sm-flex justify-content-sm-between">
                                                    <h6 class="font-weight-bold text-primary">${(lm.sendUserName)!'该留言用户已过期'}</h6>
                                                    <div>
                                                        <span class="oi oi-clock text-dark" title="留言时间"
                                                              aria-hidden="true"></span>
                                                        <span class="text-dark">${((lm.createTime)!'0000-00-00 00:00:00')?datetime}</span>
                                                    </div>
                                                </div>
                                            <#-- 留言内容 -->
                                                <div class="leave-message-comtent">
                                                    ${(lm.message)!'该评论无内容'}
                                                </div>
                                            </div>
                                        </div>
                                    </#list>
                                </div>
                            </div>
                        </#if>
                    </div>

                <#-- 展示原图模态框 -->
                    <div class="modal fade" id="source-img-modal">
                        <div class="modal-dialog modal-lg w-100 h-75">
                            <div class="modal-content w-100 h-100">
                                <!-- 模态框主体 -->
                                <div class="modal-body mx-auto">
                                    <img class="source-img" src=""/>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

        <#--  右侧-->
            <div class="d-sm-flex flex-sm-column col-sm-4 pl-sm-3 pr-sm-3">
            <#-- 个人名片 -->
                <#include '../../common/_busi_card.ftl'/>
            <#-- 本站标签 -->
                <#include '../../common/_label.ftl'/>
            <#-- 文章排行 -->
                <#include '../../common/_ranking.ftl'/>
            <#-- 友情链接 -->
                <#include '../../common/_friendly_link.ftl'/>
            </div>
        </div>
    </section>

<#-- 网页底部 -->
    <#include '../../common/_footer.ftl'/>

<#-- 登录注册相关页面 -->
    <#include '../../common/_login.ftl'/>
</div>
</body>
</html>