<!DOCTYPE>
<html>
<head>
    <meta>
    <title>模板</title>
    <#include '../depend/_head.ftl'/>
</head>
<body>
<div style="width: 100%;height: 100%">
    <div style="height: 20%;width: 100%">
        <h2>导航</h2>
    </div>
    <div style="height: 80%;width: 100%;overflow: auto">
        <div style="width: 20%;height: 100%;float: left;">
            <h2>友链。。。</h2>
        </div>
        <div style="height: 100%;width: 80%;float: right">
            <div style="height: 20px;">
                <h3>标题等</h3>
            </div>
            <div id="content" style="width: 100%;height: auto;">
                ${content!}
            </div>
        </div>
    </div>
</div>

</body>
</html>