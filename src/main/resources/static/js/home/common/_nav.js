/*
* 网页顶部、导航js
* */
$(function () {
    //body上内边距
    var bodyPt = $('#banner').height() * 0.0625 + 1;
    $(body).css({
        'padding-top': bodyPt + 'rem'
    });

    /**
     * 收索
     */
    $(document).on('click', '#index-search-form button[type=submit]', function () {
        //收索关键字
        var keyword = $('#index-search-form .keyword').val();
        if (keyword != null && keyword != '') {
            window.location.href = '/g.a/list/key/' + keyword + '/page/1';
        } else {
            window.location.href = '/';
        }
    });
});

/**
 * 监听窗口大小变化
 */
$(window).resize(function () {
    //body上内边距
    var bodyPt = $('#banner').height() * 0.0625 + 1;
    $(body).css({
        'padding-top': bodyPt + 'rem'
    });
});