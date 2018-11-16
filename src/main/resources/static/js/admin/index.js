$(function () {

    //首页组件加载
    $.ajax({
        url: '/admin/loadIndexPageData',
        type: 'get',
        success: function (result) {
            if (result.resultCode == -1) {
                $.messager.alert('错误提示', '首页组件加载出错，请稍后重试或联系管理员！', 'error');
                return false;
            }
            //菜单树
            var menuTree = result.menuTree;
            if (!(menuTree && menuTree.length == 0)) {
                $.each(menuTree, function (index, topMenu) {
                    //添加顶级菜单面板
                    $('#menuAccordion').accordion('add', {
                        title: topMenu.menuName,
                        content: "<ul id='" + topMenu.menuId + "ChildrenMenu'></ul>"
                    });
                    if (topMenu.children.length > 0) {
                        //加载二级及以下菜单树
                        $('#' + topMenu.menuId + 'ChildrenMenu').tree({
                            data: topMenu.children,
                            onSelect: function (node) {
                                //如果是叶子菜单页面主体弹出tab
                                if ($(this).tree('isLeaf', node.target)) {
                                    //如果对应tab已经存在于主体就打开对应tab；否则在主体添加tab
                                    if ($('#contentTabs').tabs('exists', node.text)) {
                                        $('#contentTabs').tabs('select', node.text);
                                    } else {
                                        $('#contentTabs').tabs('add', {
                                            title: node.text,
                                            href: node.url,
                                            closable: true,
                                            tools: [{
                                                iconCls: 'icon-reload',
                                                handler: function () {
                                                    //刷新当前打开tab
                                                    var curTab = $('#contentTabs').tabs('getSelected');
                                                    curTab.panel('refresh')
                                                },
                                            }]
                                        });
                                    }
                                }
                            }
                        });
                    }
                });
                //顶级菜单滑块第一个默认选中打开
                $('#menuAccordion').accordion('select', 0);
            }
        },
        error: function () {
            $.messager.alert('错误提示', '首页组件加载出错，请稍后重试或联系管理员！', 'error');
        }
    });

    /**
     * 首页头部提示悬浮框
     */
    $('#tips').tooltip({
        content: '<a id="logOutBtn" href="javascript:void(0)" style="color: black;text-decoration-line: none " >[注销]</a>',
        //鼠标移开图标提示框不消失
        onShow: function () {
            var t = $(this);
            t.tooltip('tip').unbind().bind('mouseenter', function () {
                t.tooltip('show');
            }).bind('mouseleave', function () {
                t.tooltip('hide');
            });
        }
    });

    /**
     * 隐藏导航菜单伸缩按钮
     */
    $(document.body).layout('setRegionToolVisableState', {region: 'west', visible: false});

    /**
     * 登出操作
     */
    $(document).on('click', '#logOutBtn', function () {
        $.get('/logOut', function (result) {
            if (result.resultCode == 1) {
                window.location.href = '/admin/guest/login/index';
            } else if (result.resultCode == -1) {
                $.messager.alert('注销提示', '登录失败，请稍后重试或联系管理员！', 'error');
            }
        });
    });
});

/**
 * layout扩展方法
 */
$.extend($.fn.layout.methods, {
    /**
     * 设置region的收缩按钮是否可见
     * @param {[type]} jq     [description]
     * @param {[type]} params [description]
     */
    setRegionToolVisableState: function (jq, params) {//就是调用这个方法，其他方法也可以删掉
        return jq.each(function () {
            if (params.region == "center")
                return;
            var panels = $.data(this, 'layout').panels;
            var panel = panels[params.region];
            var tool = panel.panel('header').find('>div.panel-tool');
            tool.css({display: params.visible ? 'block' : 'none'});
            var first = params.region.substring(0, 1);
            var others = params.region.substring(1);
            var expand = 'expand' + first.toUpperCase() + others;
            if (panels[expand]) {
                panels[expand].panel('header').find('>div.panel-tool').css({display: params.visible ? 'block' : 'none'});
            }
        });
    }
});