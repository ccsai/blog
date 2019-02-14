<#-- 标签列表 -->
<div id="label" class="rounded p-sm-3 mt-sm-4">
    <h5 class="text-primary font-weight-bold">本站标签</h5>
    <hr class="module-hr">
    <#list labelList>
        <div class="d-sm-flex flex-sm-wrap">
            <#items as l>
                <h5 class="mr-sm-3">
                    <a href="/g.a/list/label/${l.labelId}/page/1" class="badge badge-primary">${l.label}</a>
                </h5>
            </#items>
        </div>
    </#list>
</div>