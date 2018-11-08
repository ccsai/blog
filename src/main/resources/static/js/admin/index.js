$(function () {
    $(document.body).layout({
        onCollapse: function () {
            $('#contentTabs').tabs({width: 'auto'});
        },
        onExpand: function () {
            $('#contentTabs').tabs({width: 'auto'});
        }
    });

    $('#tree').tree({
        data: [{
            "id": 1,
            "text": "Node 1",
            "state": "closed",
            "children": [{
                "id": 11,
                "text": "跳转",
                "href": "/getVisitedSuccessNum"
            }, {
                "id": 12,
                "text": "Node 12"
            }]
        }, {
            "id": 2,
            "text": "Node 2",
            "state": "closed"
        }],
        onSelect: function (node) {
            if ($(this).tree('isLeaf', node.target)) {
                if ($('#contentTabs').tabs('exists', node.text)) {
                    $('#contentTabs').tabs('select', node.text);
                } else {
                    $('#contentTabs').tabs('add', {
                        title: node.text,
                        href: node.href,
                        closable: true,
                        //toolPosition:'left',//始终不生效
                        tools: [{
                            iconCls: 'icon-reload',
                            handler: function () {
                                var curTab = $('#contentTabs').tabs('getSelected');
                                curTab.panel('refresh')
                            },
                        }]
                    });
                }
            }
        }

    });

    $('#dd').tooltip({
        content: '<a href="javascript:void(0)" style="color: black;text-decoration-line: none " >[注销]</a>',
        onShow: function () {
            var t = $(this);
            t.tooltip('tip').unbind().bind('mouseenter', function () {
                t.tooltip('show');
            }).bind('mouseleave', function () {
                t.tooltip('hide');
            });
        }
    });
});