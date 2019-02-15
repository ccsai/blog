<!doctype html>
<html lang="en">
<head>
    <#include '../../home/depend/_head.ftl'/>
<#-- ueditor -->
    <#include '../../common/depend/_ueditor.ftl'/>
<#-- 文章详情 css -->
    <link href="${basePath!}/css/home/common/_article_detail.css" rel="stylesheet" type="text/css"/>
<#-- 文章详情 js -->
    <script src="${basePath!}/js/home/guest/article/_article_detail.js"></script>
    <title>浩瀚星尘的博客</title>
</head>
<body>
<div id="body" class="container">
<#-- 侧边栏 -->
    <#include '../../home/common/_side.ftl'/>
<#-- 网页头部导航部分 -->
    <header>
        <nav id="banner" class="container navbar navbar-expand-sm navbar-light bg-dark rounded-bottom fixed-top">
            <div class="container-fluid d-sm-flex justify-content-between">
            <#-- 导航 -->
                <ul id="banner-nav" class="nav nav-pills">
                    <li class="nav-item">
                        <a class="nav-link text-light link-index active" href="#">
                            <h5>首页</h5>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-light link-skill" href="#">
                            <h5>技术交流</h5>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-light link-life" href="#">
                            <h5>生活日志</h5>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-light link-hobby" href="#">
                            <h5>兴趣爱好</h5>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-light link-leave-message" href="#">
                            <h5>留言</h5>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-light link-about" href="#">
                            <h5>关于</h5>
                        </a>
                    </li>
                </ul>

            <#-- 收索 -->
                <div>
                    <form id="index-search-form" class="form-inline" action="javascript:return false">
                        <div class="input-group">
                            <input type="text" class="form-control keyword" placeholder="输入你想搜的内容">
                            <div class="input-group-append">
                                <button class="btn btn-success" type="submit">
                            <span class="oi oi-magnifying-glass" title="icon magnifying-glass"
                                  aria-hidden="true"></span>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>

            <#-- 登录与退出 -->
                <div id="nav-login-div" class="text-warning">
                    <a class="text-warning login-href" href="javascript:void(0)" data-toggle="modal"
                       data-target="#login">登录</a>&nbsp;|&nbsp;
                    <a class="text-warning register-href" href="javascript:void(0)" data-toggle="modal"
                       data-target="#register">注册</a>
                </div>
            </div>
        </nav>
    </header>

<#-- 主体内容部分 -->
    <section class="container pt-sm-3">
        <div class="row">
        <#-- 左侧 -->
            <div class="col-sm-8 pl-sm-3 pr-sm-3">

                <div id="main-list" class="pt-sm-3 rounded">
                    <h4 class="font-weight-bold text-dark">文章详情</h4>
                <#-- 列表标题分隔线 -->
                    <hr class="module-hr">

                <#-- 文章详情页面 -->
                    <article id="article-detail" class="d-sm-flex flex-column mt-sm-3 mb-sm-3 pl-sm-2 pr-sm-2">
                        <h3 class="text-warning">文章标题为空</h3>
                        <div class="d-sm-inline-flex justify-content-sm-between w-50">
                            <div>
                                <span class="oi oi-clock text-dark" title="发表时间" aria-hidden="true"></span>
                                <span class="text-dark">0000-00-00 00:00:00</span>
                            </div>
                            <div>
                                <span class="oi oi-eye text-dark" title="浏览" aria-hidden="true"></span>
                                <span class="text-dark">0</span>
                            </div>
                            <div class="info-icon">
                                <span class="oi oi-thumb-up text-dark" title="赞" aria-hidden="true"></span>
                                <span class="text-dark article-support-number">0</span>
                            </div>
                        </div>
                        <hr>

                    <#-- 文章内容 -->
                        <div class="article-content">
                        ${content!'文章内容为空'}
                        </div>

                    <#-- 标签、赞 -->
                        <div class="d-sm-inline-flex justify-content-between">
                            <div class="d-sm-flex flex-sm-wrap">
                                <div class="mr-sm-3">
                                    <h4>
                                        <span class="oi oi-tags text-info" title="标签" aria-hidden="true"></span>
                                    </h4>
                                </div>
                            </div>

                            <div class="input-group-append">
                                <button class="btn btn-outline-dark" role="button">
                                    赞（<span class="article-support-number">0</span>）
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
                                        <img class="w-100 h-100 rounded"
                                             src="${basePath!}/img/home/default/article-list.png">
                                    </div>
                                    <span class="font-weight-bold">微信</span>
                                </div>
                                <div class="text-sm-center ml-sm-3">
                                    <div class="qr-code">
                                        <img class="w-100 h-100 rounded"
                                             src="${basePath!}/img/home/default/article-list.png">
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
                            <form>
                                <input type="hidden" name="articleId" value="">
                                <div class="comment-ue-div">
                                    <textarea id="comment-ue"></textarea>
                                </div>
                                <button class="btn btn-sm btn-success float-sm-right mr-sm-2" disabled>畅言一下
                                </button>
                            </form>
                        </div>
                        <hr>

                    <#-- 评论列表 -->
                        <div>
                            <div class="d-sm-flex flex-column">
                                <h6 class="font-weight-bold">评论（0）</h6>
                                <div class="d-sm-flex flex-column">
                                    <div class="border border-warning rounded p-sm-2 mt-sm-1 mb-sm-2 text-center">
                                        <h6>暂无评论信息</h6>
                                    </div>
                                </div>
                            </div>
                    </article>

                <#-- 展示原图模态框 -->
                    <div class="modal fade" id="source-img-modal">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
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
                <div id="busi-cart" class="rounded p-sm-3">
                    <h5 class="text-primary font-weight-bold">个人名片</h5>
                    <hr class="module-hr">
                    <div>
                        <p>
                            &emsp;&emsp;欢迎来到“浩瀚星尘”的个人博客！<br>
                            &emsp;&emsp;首先，该博客用于分享本人的生活事迹与兴趣爱好；
                            此外，该博客的主要作用便是与广大的小伙伴一起分享探讨开发技术，
                            希望大家多多关照。
                        </p>
                        <div class="d-sm-inline-flex justify-content-sm-between w-100">
                            <div>
                                <span>网名：</span>
                                <span>浩瀚星尘</span>
                            </div>
                            <div>
                                <span>城市：</span>
                                <span>重庆</span>
                            </div>
                            <div>
                                <span>工作：</span>
                                <span>java</span>
                            </div>
                        </div>
                        <div class="d-sm-inline-flex justify-content-sm-between w-100">
                            <div class="icon-div rounded-circle">
                                <a href="http://wpa.qq.com/msgrd?uin=2509635377&amp;site=qq&amp;menu=yes"
                                   rel="external nofollow" target="_blank" title="联系QQ">
                                    <img class="w-100 h-100" src="${basePath!}/img/home/icon/qq.png" title="2509635377">
                                </a>
                            </div>
                            <div class="icon-div rounded-circle">
                                <a href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=2509635377@qq.com"
                                   target="_blank" title="2509635377@qq.com">
                                    <img class="w-100 h-100" src="${basePath!}/img/home/icon/email.png">
                                </a>
                            </div>
                            <div class="icon-div rounded-circle">
                                <a href="https://github.com/ccsai" target="_blank">
                                    <img class="w-100 h-100" src="${basePath!}/img/home/icon/github.png"
                                         title="https://github.com/ccsai">
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            <#-- 标签列表 -->
                <div id="label" class="rounded p-sm-3 mt-sm-4">
                    <h5 class="text-primary font-weight-bold">本站标签</h5>
                    <hr class="module-hr">
                </div>
            <#-- 文章排行 -->
                <div id="ranking" class="rounded p-sm-3 mt-sm-4">
                    <h5 class="text-primary font-weight-bold">文章排行</h5>
                    <hr class="module-hr">
                </div>
            <#-- 友情链接 -->
                <div id="friendly-link" class="rounded p-sm-3 mt-sm-4">
                    <h5 class="text-primary font-weight-bold">友情链接</h5>
                    <hr class="module-hr">
                </div>
            </div>
        </div>
    </section>

    <footer id="footer">
        <nav id="footer-nav" class="container d-sm-flex flex-column bg-dark rounded-top fixed-bottom">
            <div class="text-sm-center text-light">
                本站声明：本站所有内容由“浩瀚星尘”个人学习交流使用，未经许可不可用于商业用途。
                如果网站内容有侵权行为，请及时通知本人删除！
            </div>
            <div class="text-sm-center text-light">
                @备案。。。。
            </div>
        </nav>
    </footer>
</div>
</body>
</html>