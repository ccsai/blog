/*
* 文章列表js
* */
$(function () {
    //跳转到指定页面
    $(document).on('keyup', '#article-list-pagination .page-number', function (e) {
        //目标页码
        var targetPage = $(this).val();
        //最大页码
        var lastPage = $(this).data('lastPage');
        if (e.keyCode == 13) {
            //判断是否位数字，不是数字跳转到第一页
            if (/\d+/.test(targetPage)) {
                if (targetPage < 1) {
                    targetPage = 1;
                } else if (targetPage > lastPage) {
                    targetPage = lastPage;
                }
            } else {
                targetPage = 1;
            }
            window.location.href = $(this).data('urlPrefix') + targetPage;
        }
    });

    //文章点赞
    $(document).on('click', '.support-article', function () {
        var that = $(this);
        //判断用户信息
        if (userInfo == null) {
            layer.msg('请登录后点赞', {icon: 2});
            return false;
        }
        //点赞参数
        var data = {};
        //文章编号
        data.articleId = $(this).data('articleId');
        $.ajax({
            url: '/b.a/supportArticle',
            type: 'post',
            data: data,
            success: function (data) {
                if (data.resultCode == -1 || data.resultCode == 0) {
                    layer.msg(data.notice, {icon: 2});
                } else if (data.resultCode == 1) {
                    layer.msg('<span style="color: red">赞 +1</span>', {icon: 1});
                    that.find('.support-number').html(data.articleInfo.supportNumber);
                }
            },
            error: function () {
                layer.msg('点赞异常', {icon: 2});
            }
        });
    });
});