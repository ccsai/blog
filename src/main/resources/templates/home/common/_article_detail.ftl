<#-- 文章详情页面 -->
<article id="article-detail" class="d-sm-flex flex-column mt-sm-3 mb-sm-3 pl-sm-2 pr-sm-2">
    <h3 class="text-warning">${(articleDetail.title)!'文章标题为空'}</h3>
    <div class="d-sm-inline-flex justify-content-sm-between w-50">
        <div>
            <span class="oi oi-clock text-dark" title="发表时间" aria-hidden="true"></span>
            <span class="text-dark">${((articleDetail.createTime)!'0000-00-00 00:00:00')?datetime}</span>
        </div>
        <div>
            <span class="oi oi-eye text-dark" title="浏览" aria-hidden="true"></span>
            <span class="text-dark">${(articleDetail.visitedNumber)!0}</span>
        </div>
        <div class="info-icon support-article" data-article-id="${(articleDetail.articleId)!}">
            <span class="oi oi-thumb-up text-dark" title="赞" aria-hidden="true"></span>
            <span class="text-dark article-support-number">${(articleDetail.supportNumber)!0}</span>
        </div>
    </div>
    <hr>

<#-- 文章内容 -->
    <div class="article-content">
    ${(articleDetail.content)!'文章内容为空'}
    </div>

<#-- 标签、赞 -->
    <div class="d-sm-inline-flex justify-content-between">
        <div class="d-sm-flex flex-sm-wrap">
            <div class="mr-sm-3">
                <h4>
                    <span class="oi oi-tags text-info" title="标签" aria-hidden="true"></span>
                </h4>
            </div>
            <#list articleDetail.labelList as l>
                <h4 class="mr-sm-3">
                    <a href="/g.a/list/label/${(l.labelId)!' '}/page/1" class="badge label-href">${(l.label)!' '}</a>
                </h4>
            </#list>
        </div>

        <div class="input-group-append">
            <button class="btn btn-outline-dark support-article" data-article-id="${(articleDetail.articleId)!}"
                    role="button">
                赞（<span class="article-support-number">${(articleDetail.supportNumber)!0}</span>
                ）
                <span class="oi oi-thumb-up text-light" title="赞" aria-hidden="true"></span>
            </button>
        </div>
    </div>
    <hr>

<#-- 赏 -->
    <div class="d-sm-flex flex-column">
        <div class="text-center">
            <h6 class="text-dark font-weight-bold">如果你喜欢我的内容，就请打赏一下吧</h6>
        </div>
        <div class="d-sm-flex justify-content-sm-center">
            <div class="text-sm-center mr-sm-3">
                <div class="qr-code">
                    <img class="w-100 h-100 rounded" src="${basePath!}/img/home/pay/wechat-collection.jpg">
                </div>
                <span class="font-weight-bold">微信</span>
            </div>
            <div class="text-sm-center ml-sm-3">
                <div class="qr-code">
                    <img class="w-100 h-100 rounded" src="${basePath!}/img/home/pay/alipay-collection.jpg">
                </div>
                <span class="font-weight-bold">支付宝</span>
            </div>
        </div>
        <div class="text-center">
            <span class="text-danger">温馨提示：</span>
            <span>你的打赏金额会直接转入对方账户，不可退回。</span>
        </div>
    </div>
    <hr>

<#-- 评论专区 -->
    <div>
        <h5>评论专区</h5>
        <hr class="comment-hr">
        <form id="article-comment-form" method="post" action="/b.ac/commentArticle">
            <input type="hidden" name="articleId" value="${(articleDetail.articleId)!}">
            <div class="comment-ue-div">
                <textarea id="comment-ue"></textarea>
            </div>
            <button type="submit" class="btn btn-sm btn-success float-sm-right mr-sm-2">畅言一下</button>
        </form>
    </div>
    <hr>

<#-- 评论列表 -->
    <div>
        <div class="d-sm-flex flex-column">
            <h6 class="font-weight-bold">审核通过的评论（${(articleDetail.commentNumber)!0}）</h6>
            <div class="d-sm-flex flex-column">
                <#list articleDetail.articleCommentList as c>
                <#-- 一条评论开始 -->
                    <div class="border border-warning rounded p-sm-2 mt-sm-1 mb-sm-2">
                        <div class="d-sm-flex justify-content-sm-between">
                        <#--<#list c as key>${key}<br></#list>-->
                            <h6 class="font-weight-bold text-primary">${(c.commentUser)!'该评论者已被删除'}</h6>
                            <div>
                                <span class="oi oi-clock text-dark" title="发表时间" aria-hidden="true"></span>
                                <span class="text-dark">${((c.createTime)!'0000-00-00 00:00:00')?datetime}</span>
                            </div>
                        </div>
                    <#-- 评论内容 -->
                        <div class="comment">
                            ${(c.comment)!'该评论无内容'}
                        </div>
                        <div class="d-sm-flex justify-content-sm-end">
                            <div>
                                <a class="btn btn-sm btn-outline-info font-weight-bold article-comment-support"
                                   data-article-comment-id="${(c.commentId)!}">
                                    赞（<span class="article-comment-support-number">${(c.supportNumber)!0}</span>）
                                    <span class="oi oi-thumb-up text-dark" title="赞" aria-hidden="true"></span>
                                </a>
                                <a class="btn btn-sm btn-outline-danger font-weight-bold article-comment-no-support"
                                   data-article-comment-id="${(c.commentId)!}">
                                    踩（<span class="article-comment-no-support-number">${(c.noSupportNumber)!0}</span>）
                                    <span class="oi oi-thumb-down text-dark" title="踩" aria-hidden="true"></span>
                                </a>
                            </div>
                        </div>
                    </div>
                <#else>
                    <div class="border border-warning rounded p-sm-2 mt-sm-1 mb-sm-2 text-center">
                        <h6>暂无评论信息</h6>
                    </div>
                </#list>
            </div>
        </div>
</article>

<#-- 展示原图模态框 -->
<div class="modal fade" id="source-img-modal">
    <div class="modal-dialog modal-lg w-100 h-75">
        <div class="modal-content w-100 h-100">
            <!-- 模态框主体 -->
            <div class="modal-body mx-auto">
                <img class="mx-auto source-img" src=""/>
            </div>
        </div>
    </div>
</div>