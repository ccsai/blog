<#-- 文章列表展示 -->
<#list articlePageInfo.list>
    <#items as a>
        <div class="d-inline-flex justify-content-sm-around pt-sm-3 pb-sm-3 w-100 article-list-div">
            <div class="h-100 w-25">
                <a href="/g.a/detail/${a.articleId}">
                    <#assign listPicture=(a.listPicture)!''>
                    <img class="h-100 w-100 rounded"
                         src="<#if listPicture == ''>/img/home/default/article-list.png<#else>${listPicture}</#if>"/>
                </a>
            </div>
            <div class="w-75 pl-sm-4 article-list-right">
                <a href="/g.a/detail/${a.articleId}" class="text-dark">
                    <h5 class="font-weight-bold">${a.title}</h5>
                </a>
                <div class="pt-sm-1 article-list-content">
                    ${a.content?replace('<[^>]+>','','ri')}
                </div>
                <div class="d-inline-flex w-75 justify-content-sm-between rticle-list-right-bottom">
                    <div>
                        <span class="oi oi-clock text-secondary" title="发表时间" aria-hidden="true"></span>
                        <span class="text-secondary">${((a.createTime)!0)?datetime}</span>
                    </div>
                    <div class="info-icon">
                        <a href="/g.a/detail/${(a.articleId)!}">
                            <span class="oi oi-comment-square text-secondary" title="评论" aria-hidden="true"></span>
                            <span class="text-secondary">${(a.commentNumber)!0}</span>
                        </a>
                    </div>
                    <div>
                        <span class="oi oi-eye text-secondary" title="浏览" aria-hidden="true"></span>
                        <span class="text-secondary">${(a.visitedNumber)!0}</span>
                    </div>
                    <div class="info-icon support-article" data-article-id="${(a.articleId)!}">
                        <span class="oi oi-thumb-up text-secondary" title="赞" aria-hidden="true"></span>
                        <span class="text-secondary support-number">${(a.supportNumber)!0}</span>
                    </div>
                </div>
            </div>
        </div>
    </#items>

<#-- 分页 -->
<ul id="article-list-pagination" class="pagination justify-content-sm-end mt-sm-1">
    <li class="page-item <#if (articlePageInfo.isFirstPage)?? && articlePageInfo.isFirstPage>disabled</#if>"><a
            class="page-link"
            href="/g.a/list<#if bannerMark??>/${bannerMark}</#if><#if searchType??>/${searchType}</#if><#if param??>/${param}</#if>/page/${(articlePageInfo.firstPage)!1}">首页</a>
    </li>
    <li class="page-item <#if (articlePageInfo.isFirstPage)?? && articlePageInfo.isFirstPage>disabled</#if>"><a
            class="page-link"
            href="/g.a/list<#if bannerMark??>/${bannerMark}</#if><#if searchType??>/${searchType}</#if><#if param??>/${param}</#if>/page/${(articlePageInfo.prePage)!1}">上一页</a>
    </li>
    <li class="page-item disabled"><a class="page-link">第</a></li>
    <li class="page-item"><a class="page-link"><input class="form-control pagination-input page-number" type="number"
                                                      data-last-page="${(articlePageInfo.lastPage)!1}"
                                                      data-url-prefix="/g.a/list<#if bannerMark??>/${bannerMark}</#if><#if searchType??>/${searchType}</#if><#if param??>/${param}</#if>/page/"
                                                      value="${(articlePageInfo.pageNum)!''}"></a></li>
    <li class="page-item disabled"><a class="page-link">页&nbsp;共${(articlePageInfo.pages)!' '}
        页${(articlePageInfo.total)!' '}条</a></li>
    <li class="page-item <#if (articlePageInfo.isLastPage)?? && articlePageInfo.isLastPage>disabled</#if>"><a
            class="page-link"
            href="/g.a/list<#if bannerMark??>/${bannerMark}</#if><#if searchType??>/${searchType}</#if><#if param??>/${param}</#if>/page/${(articlePageInfo.nextPage)!1}">下一页</a>
    </li>
    <li class="page-item <#if (articlePageInfo.isLastPage)?? && articlePageInfo.isLastPage>disabled</#if>"><a
            class="page-link"
            href="/g.a/list<#if bannerMark??>/${bannerMark}</#if><#if searchType??>/${searchType}</#if><#if param??>/${param}</#if>/page/${(articlePageInfo.lastPage)!1}">末页</a>
    </li>
</ul>

<#else>
    <div class="text-center text-warning pb-sm-1">
        <h5 class="font-weight-bold">主人很懒，没有留下文章</h5>
    </div>
</#list>