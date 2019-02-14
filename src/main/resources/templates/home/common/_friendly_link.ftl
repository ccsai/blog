<#-- 友情链接 -->
<div id="friendly-link" class="rounded p-sm-3 mt-sm-4">
    <h5 class="text-primary font-weight-bold">友情链接</h5>
    <hr class="module-hr">
    <#list friendlyLinkList>
        <div class="w-100">
            <#items as fl>
                <div class="w-50 float-sm-left">
                    <h4>
                        <a href="${fl.url}" target="_blank"
                           class="badge badge-secondary friendly-link-a">${fl.linkName}</a>
                    </h4>
                </div>
            </#items>
        </div>
    </#list>
</div>