<#-- 项目根目录 -->
<#include '_global.ftl'/>
<link href="${basePath!}/plugin/ueditor/third-party/video-js/video-js.css" type="text/css" rel="stylesheet">
<script src="${basePath!}/plugin/ueditor/ueditor.config.js"></script>
<script src="${basePath!}/plugin/ueditor/ueditor.all.js"></script>
<script src="${basePath!}/plugin/ueditor/third-party/video-js/video.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script src="${basePath!}/plugin/ueditor/lang/zh-cn/zh-cn.js"></script>

<style type="text/css">
    .ueditorDiv {
        width: 100%;
    }
</style>

<script>
    $(function () {
        UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
        UE.Editor.prototype.getActionUrl = function (action) {
            if (action == 'uploadimage' || action == 'uploadvideo' || action == 'uploadfile') {
                return '/common/ueditor/uploadFileByUE';//指定访问路径
            } else if (action == 'uploadscrawl') {
                return '/common/ueditor/uploadscrawlByUE';//指定访问路径
            }
            else {
                return this._bkGetActionUrl.call(this, action);
            }
        }
    })
</script>