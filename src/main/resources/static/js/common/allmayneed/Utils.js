/**
 * 工具函数
 */
Utils = {

    //七牛cdn_prefix
    cdnPrefix: 'http://pmsw67kco.bkt.clouddn.com/',


    /**
     * 将表单数据转为json对象
     * @param formObject 表单节点对象
     * @return 表单数据对象
     */
    serializeFormObject: function (formObject) {
        //console.log(formObject[0])
        //数组形式的数据
        var dataArr = formObject.serializeArray();
        //要返回的表单对象
        var formDataObject = {};
        $.each(dataArr, function (index, obj) {
            formDataObject[obj.name] = obj.value;
        });
        return formDataObject;
    },

    /**
     * 从ueditor中获取oss的key
     * @param UEContent Ueditor的内容
     * @return {Array} 所有oss的key
     */
    getOssKeyFromUE: function (UEContent) {
        //内容转为dom对象
        domObj = $(UEContent);
        //获取文件上传到七牛的img标签
        var imgs = domObj.find('img[src^="' + this.cdnPrefix + '"]');
        //获取文件上传到七牛的video标签
        var videos = domObj.find('video[src^="' + this.cdnPrefix + '"]');
        //获取文件上传到七牛的a标签
        var as = domObj.find('a[href^="' + this.cdnPrefix + '"]');
        //所有oss的key
        var ossKeys = [];
        //七牛cdn_prefix字符串长度
        var startIndex = this.cdnPrefix.length;
        if (imgs.length > 0) {
            $.each(imgs, function (i, img) {
                //获取src
                var src = img.src;
                var ossKey = src.substring(startIndex, src.length);
                if ($.inArray(ossKey, ossKeys) == -1) {
                    ossKeys.push(ossKey);
                }
            });
        }
        if (videos.length > 0) {
            $.each(videos, function (j, video) {
                //获取src
                var src = video.src;
                var ossKey = src.substring(startIndex, src.length);
                if ($.inArray(ossKey, ossKeys) == -1) {
                    ossKeys.push(ossKey);
                }
            });
        }
        if (as.length > 0) {
            $.each(as, function (k, a) {
                //获取href
                var href = a.href;
                var ossKey = href.substring(startIndex, href.length);
                if ($.inArray(ossKey, ossKeys) == -1) {
                    ossKeys.push(ossKey);
                }
            });
        }
        //去除重复的ossKey
        if (ossKeys.length > 0) {
            //去重后的ossKeys数组
            var ossKeysToHeavy = [];
            $.each(ossKeys, function (i, o) {
                if (ossKeysToHeavy.indexOf(o) == -1) {
                    ossKeysToHeavy.push(o);
                }
            });
            ossKeys = ossKeysToHeavy;
        }
        return ossKeys;
    },

    /**
     * 获取url相对路径
     * @return {string} url相对路径
     */
    getUrlRelativePath: function () {
        var url = document.location.toString();
        var arrUrl = url.split("//");
        var start = arrUrl[1].indexOf("/");
        var relUrl = arrUrl[1].substring(start);//stop省略，截取从start开始到结尾的所有字符
        if (relUrl.indexOf("?") != -1) {
            relUrl = relUrl.split("?")[0];
        }
        return relUrl;
    }
}