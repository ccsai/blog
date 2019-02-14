$(function () {
    //body下内边距
    var bodypb = $('#footer-nav').height() * 0.0625;
    $(body).css({
        'padding-bottom': bodypb + 'rem'
    });
});

/**
 * 监听窗口大小变化
 */
$(window).resize(function () {
    //body下内边距
    var bodypb = $('#footer-nav').height() * 0.0625;
    $(body).css({
        'padding-bottom': bodypb + 'rem'
    });
});