/*
* 留言 js
* */

$(function () {
    //留言banner选中
    $('#banner .link-leave-message').addClass('active');

    //ueditor加载
    var lmUE = UE.getEditor('leave-message-ue', {
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

    //留言图片过大处理
    resizeLeaveMessageImg();

    /**
     * 留言原图展示
     */
    $(document).on('click', '#leave-message .leave-message-comtent img', function () {
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

    //用户留言
    $('#leaveMessageForm').ajaxForm({
        beforeSubmit: function ($form) {
            //禁用按钮
            $('#leaveMessageForm button[type=submit]').attr('disabled', true);
            //判断用户是否登录
            if (userInfo == null) {
                layer.msg('请登录后发表留言！', {icon: 2});
                //启用按钮
                $('#leaveMessageForm button[type=submit]').attr('disabled', false);
                return false;
            }
            //获取要发送的内容
            var leaveMessageContent = lmUE.getContent();
            if (leaveMessageContent == '') {
                layer.msg('留言内容不能为空！', {icon: 2});
                //启用按钮
                $('#leaveMessageForm button[type=submit]').attr('disabled', false);
                return false;
            }
            $form.push({
                name: 'message',
                value: leaveMessageContent,
            });
            //获取所有oss的key
            var ossKeys = Utils.getOssKeyFromUE(leaveMessageContent);
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
                $('#leaveMessageForm button[type=submit]').attr('disabled', false);
                layer.msg('留言失败！', {icon: 2});
            } else if (data.resultCode == 1) {
                window.location.reload();
            }
        },
        error: function () {
            //启用按钮
            $('#leaveMessageForm button[type=submit]').attr('disabled', false);
            layer.msg('留言出错！', {icon: 2});
        }
    });
});

/**
 * 监听窗口大小变化
 */
$(window).resize(function () {
    //留言图片过大处理
    resizeLeaveMessageImg();
    //源文件自适应
    $('#source-img-modal .source-img').css({'width': $('#source-img-modal>.modal-dialog').width() * 0.94});
});


/**
 * 留言图片过大处理
 */
function resizeLeaveMessageImg() {
    //留言论图片最大高度
    var lmImgMaxHeight = 350;
    //留言容器宽
    var lmDivWidth = $('#leave-message .leave-message-comtent').width();
    //所有留言图片
    var lmImgs = $("#leave-message .leave-message-comtent img");
    //留言图片处理
    if (lmImgs != null && lmImgs.length > 0) {
        $.each(lmImgs, function (i, lm) {
            if ($(lm).attr('width') > lmDivWidth) {
                $(lm).css({'width': lmDivWidth + 'px'});
            }
            if ($(lm).height() > lmImgMaxHeight) {
                $(lm).css({'height': lmImgMaxHeight + 'px'});
            }
        });
    }
}