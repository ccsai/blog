<#-- 网页头部导航部分 -->
<header>
    <nav id="banner" class="container navbar navbar-expand-sm navbar-light bg-dark rounded-bottom fixed-top">
        <div class="container-fluid d-sm-flex justify-content-between">
        <#-- 导航 -->
            <ul id="banner-nav" class="nav nav-pills">
                <li class="nav-item">
                    <a class="nav-link text-light link-index" href="/">
                        <h5>首页</h5>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light link-skill <#if bannerMark?? && bannerMark == "sc">active</#if>"
                       href="/g.a/list/sc/page/1">
                        <h5>技术交流</h5>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light link-life <#if bannerMark?? && bannerMark == "ll">active</#if>"
                       href="/g.a/list/ll/page/1">
                        <h5>生活日志</h5>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light link-hobby <#if bannerMark?? && bannerMark == "hl">active</#if>"
                       href="/g.a/list/hl/page/1">
                        <h5>兴趣爱好</h5>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light link-leave-message" href="/g.lm/list">
                        <h5>留言</h5>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light link-about <#if bannerMark?? && bannerMark == "a">active</#if>"
                       href="/g.a/list/a/page/1">
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
                <@shiro.guest>
                    <a class="text-warning login-href" href="javascript:void(0)" data-toggle="modal"
                       data-target="#login">登录</a>&nbsp;|&nbsp;
                    <a class="text-warning register-href" href="javascript:void(0)" data-toggle="modal"
                       data-target="#register">注册</a>
                </@shiro.guest>
                <@shiro.user>
                    <span>欢迎&nbsp;<@shiro.principal property="loginName" /></span>&nbsp;|&nbsp;
                    <a class="text-warning logout-href" href="javascript:void(0)">注销</a>&nbsp;|&nbsp;
                    <a class="text-warning modify-href" href="javascript:void(0)" data-toggle="modal"
                       data-target="#modify-user-info">修改</a>
                </@shiro.user>
            </div>
        </div>
    </nav>
</header>