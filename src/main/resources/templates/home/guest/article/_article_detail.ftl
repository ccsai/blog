<!doctype html>
<html lang="en">
<head>
    <#include '../../depend/_head.ftl'/>
<#-- ueditor -->
    <#include '../../../common/depend/_ueditor.ftl'/>
<#-- 文章详情 css -->
    <link href="${basePath!}/css/home/common/_article_detail.css" rel="stylesheet" type="text/css"/>
<#-- 文章详情 js -->
    <script src="${basePath!}/js/home/guest/article/_article_detail.js"></script>
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
                    <h4 class="font-weight-bold text-dark">${bannerName!"文章详情"}</h4>
                <#-- 列表标题分隔线 -->
                    <hr class="module-hr">

                <#-- 文章详情展示 -->
                <#include '../../common/_article_detail.ftl'/>
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