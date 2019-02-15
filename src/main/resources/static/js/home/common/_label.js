/*
* 标签 js
* */
$(function () {
    //获取所有标签项
    var labelList = $('#label .label-href');
    //标签数量
    if (labelList != null && labelList.length > 0) {
        //改标签随机添加背景色样式
        $.each(labelList, function (i, l) {
            $(l).addClass(labelColorStyle[Math.floor(Math.random() * labelColorStyle.length)]);
        });
    }
});
