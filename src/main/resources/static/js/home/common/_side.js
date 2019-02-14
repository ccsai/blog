$(function () {
    /**
     * 回到顶部
     */
    $(document).on('click', '#side .to-top', function () {
        $(window).scrollTop(0);
    });
});

/**
 * //监听窗口滚动条时间
 */
$(window).scroll(function () {
    //返回顶部显示控制
    if ($(this).scrollTop() > 100) {
        $('#side .to-top').show();
    } else {
        $('#side .to-top').hide();
    }
});