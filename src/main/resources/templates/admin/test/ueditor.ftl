<!DOCTYPE>
<html>
<head>
    <meta>
    <script src="${basePath!}/plugin/jquery/jquery-3.1.1.min.js"></script>
    <#include '../../common/depend/_ueditor.ftl'/>
</head>
<body>
<div class="ueditorDiv">
    <h4 style="margin: 3px">请输入内容</h4>
    <textarea id="editor" style="width:90%;height:60%;"></textarea>
</div>
<#--<div class="ueditorDiv">
    <h4 style="margin: 3px">test</h4>
    <textarea id="test" style="width:90%;height:60%;"></textarea>
</div>-->
<div id="btns">
    <div>
        <button onclick="getContent()">获得内容</button>
    </div>
</div>

<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
    /*var ue = UE.getEditor('test',{
        toolbars: [
            ['fullscreen', 'source', 'undo', 'redo', 'bold']
        ]
    });*/

    function getContent() {
        console.log(UE.getEditor('editor').getContent());
    }
</script>
</body>
</html>