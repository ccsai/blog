$(function () {

    //ueditor加载
    var commentUE = UE.getEditor('comment-ue', {
        toolbars: [
            [
                'emotion', //表情
                'simpleupload', //单图上传
                'insertcode', //代码语言
                'fullscreen'
            ]
        ],
        zIndex: 0, elementPathEnabled: false, initialFrameWidth: '100%'
    });

    //文章和文章评论图片过大处理
    resizeArticleAndCommentImg();

    /**
     * 文章评论图片原图展示
     */
    $(document).on('click', '#article-detail .article-content img, #article-detail .comment img', function () {
        $('#source-img-modal .source-img').attr('src', $(this).attr('src'));
        //获取当前图片宽度
        var tempImg = new Image();
        tempImg.src = $(this).attr('src');
        var curImgWidth = tempImg.width;
        $('#source-img-modal>.modal-dialog').css({'width': curImgWidth / 0.94 + 'px'});
        $('#source-img-modal').modal('show');
    });

    /**
     * 点击原图影藏原图
     */
    $(document).on('click', '#source-img-modal>.modal-dialog', function () {
        $('#source-img-modal').modal('hide');
    });

    //发表文章评论
    $('#article-comment-form').ajaxForm({
        beforeSubmit: function ($form) {
            //禁用按钮
            $('#article-comment-form button[type=submit]').attr('disabled', true);
            //判断用户是否登录
            if (userInfo == null) {
                layer.msg('请登录后发表评论！', {icon: 2});
                //启用按钮
                $('#article-comment-form button[type=submit]').attr('disabled', false);
                return false;
            }
            //获取要发送的内容
            var articleComment = commentUE.getContent();
            if (articleComment == '') {
                layer.msg('评论内容不能为空！', {icon: 2});
                //启用按钮
                $('#article-comment-form button[type=submit]').attr('disabled', false);
                return false;
            }
            $form.push({
                name: 'comment',
                value: articleComment,
            });
            //获取所有oss的key
            var ossKeys = Utils.getOssKeyFromUE(articleComment);
            if (ossKeys != null && ossKeys.length > 0) {
                $form.push({
                    name: 'ossKeys[]',
                    value: ossKeys
                })
            }
        },
        success: function (data) {
            if (data.resultCode == -1 || data.resultCode == 0) {
                //启用按钮
                $('#article-comment-form button[type=submit]').attr('disabled', false);
                layer.msg('评论失败！', {icon: 2});
            } else if (data.resultCode == 1) {
                window.location.reload();
            }
        },
        error: function () {
            //启用按钮
            $('#article-comment-form button[type=submit]').attr('disabled', false);
            layer.msg('评论出错！', {icon: 2});
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
        data.articleId = that.data('articleId');
        $.ajax({
            url: '/b.a/supportArticle',
            type: 'post',
            data: data,
            success: function (data) {
                if (data.resultCode == -1 || data.resultCode == 0) {
                    layer.msg(data.notice, {icon: 2});
                } else if (data.resultCode == 1) {
                    layer.msg('<span style="color: red">赞 +1</span>', {icon: 1});
                    $('.article-support-number').html(data.articleInfo.supportNumber);
                }
            },
            error: function () {
                layer.msg('点赞异常', {icon: 2});
            }
        });
    });
});

/**
 * 监听窗口大小变化
 */
$(window).resize(function () {
    //文章和文章评论图片过大处理
    resizeArticleAndCommentImg();
    //源文件自适应
    $('#source-img-modal .source-img').css({'width': $('#source-img-modal>.modal-dialog').width() * 0.94});
});

/**
 * 文章和文章评论图片过大处理
 */
function resizeArticleAndCommentImg() {
    //文章和文章评论图片最大高度
    var articleAndCommentImgMaxHeight = 350;
    //文章容器宽
    var articleDivWidth = $('#article-detail .article-content').width();
    //所有文章图片
    var articleImgs = $("#article-detail .article-content img");
    //文章图片处理
    if (articleImgs != null && articleImgs.length > 0) {
        $.each(articleImgs, function (i, ai) {
            if ($(ai).attr('width') > articleDivWidth) {
                $(ai).css({'width': articleDivWidth + 'px'});
            }
            if ($(ai).height() > articleAndCommentImgMaxHeight) {
                $(ai).css({'height': articleAndCommentImgMaxHeight + 'px'});
            }
        });
    }

    //文章容器评论宽度
    var articleCommentDivWidth = $('#article-detail .comment').width();
    //所有评论图片
    var articleCommentImgs = $('#article-detail .comment img');
    //文章评论图片处理
    if (articleCommentImgs != null && articleCommentImgs.length > 0) {
        $.each(articleCommentImgs, function (j, aci) {
            if ($(aci).attr('width') > articleCommentDivWidth) {
                $(aci).css({'width': articleCommentDivWidth});
            }
            if ($(aci).height() > articleAndCommentImgMaxHeight) {
                $(aci).css({'height': articleAndCommentImgMaxHeight});
            }
        });
    }
}