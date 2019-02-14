<#-- 文章排行 -->
<div id="ranking" class="rounded p-sm-3 mt-sm-4">
    <h5 class="text-primary font-weight-bold">文章排行</h5>
    <hr class="module-hr">
    <#list rankingArticleList>
        <div>
            <#items as a>
                <div class="d-sm-inline-flex w-100 pb-sm-1 mb-sm-1 ranking-row">
                    <div class="rounded-circle bg-danger ranking-circle mr-sm-2"></div>
                    <a class="ranking-link" href="/g.a/detail/${a.articleId}">${a.title}</a>
                </div>
            </#items>
        </div>
    </#list>
</div>