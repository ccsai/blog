<!doctype html>
<html lang="en">
<head>
    <#include '../../depend/_head.ftl'/>
<#-- 文章列表css -->
    <link href="${basePath!}/css/home/common/_article_list.css" rel="stylesheet" type="text/css"/>
<#-- 文章列表js -->
    <script src="${basePath!}/js/home/common/_article_list.js"></script>
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
                    <h4 class="font-weight-bold text-dark">${bannerName!"最新文章"}</h4>
                <#-- 列表标题分隔线 -->
                    <hr class="list-hr">

                <#-- 文章列表展示 -->
                <#include '../../common/_article_list.ftl'/>
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