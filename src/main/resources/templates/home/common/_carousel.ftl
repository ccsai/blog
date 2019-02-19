<#-- 首页轮播 -->
<#if carousel?size &gt; 0>
    <div id="carousel">
        <div id="index-carousel" class="carousel slide" data-ride="carousel">
        <#-- 指示符 -->
            <ul class="carousel-indicators">
                <#list 1..carousel?size as i>
                    <li data-target="#index-carousel" data-slide-to="${i-1}" <#if i == 0>class="active"</#if>></li>
                </#list>
            </ul>
        <#-- 轮播图片 -->
            <div class="carousel-inner rounded">
                <#list carousel as c>
                    <div class="carousel-item w-100 <#if c?index == 0>active</#if>">
                        <a href="/g.a/detail/${c.articleId}">
                            <img src="${c.carousel}" class="w-100">
                            <div class="carousel-caption">
                                <h2>${c.title}</h2>
                            </div>
                        </a>
                    </div>
                </#list>
            </div>
        <#-- 左右切换按钮 -->
            <a class="carousel-control-prev" href="#index-carousel" data-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </a>
            <a class="carousel-control-next" href="#index-carousel" data-slide="next">
                <span class="carousel-control-next-icon"></span>
            </a>
        </div>
    </div>
</#if>