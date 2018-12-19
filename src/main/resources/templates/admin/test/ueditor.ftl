<!DOCTYPE>
<html>
<head>
    <meta>
    <#-- 项目根目录 -->
    <#include '../depend/_global.ftl'/>
    <link href="/plugin/ueditor/third-party/video-js/video-js.css" type="text/css" rel="stylesheet">
    <script src="${basePath!}/plugin/ueditor/ueditor.config.js"></script>
    <script src="${basePath!}/plugin/ueditor/ueditor.all.js"></script>
    <script src="${basePath!}/plugin/ueditor/third-party/video-js/video.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script th:src="/plugin/ueditor/lang/zh-cn/zh-cn.js"></script>

    <style type="text/css">
        div {
            width: 100%;
        }
    </style>
</head>
<body>
<div>
    <h4 style="margin: 3px">请输入内容</h4>
    <textarea id="editor" style="width:90%;height:60%;"></textarea>
</div>
<div id="btns">
    <div>
        <button onclick="getContent()">获得内容</button>
    </div>
</div>

<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');

    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {
        if (action == 'uploadimage' || action == 'uploadvideo' || action == 'uploadfile') {
            return '/common/ueditor/uploadImageByUE';//指定访问路径
        }else if (action == 'uploadscrawl') {
            return '/common/ueditor/uploadscrawl';//指定访问路径
        }
        else {
            return this._bkGetActionUrl.call(this, action);
        }
    }


    function getContent() {
        console.log(UE.getEditor('editor').getContent());
    }
</script>
</body>
</html>