<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html lang="en">
    <head>
        <title><@spring.message 'pls.title'/></title>
        <#include "header.ftl">
    </head>
    <body class="text-color background-color">
        <#include "head.ftl">
        <div class="spacer"></div>
        <div class="body">
            <#assign images = "<@spring:theme 'images'/>">
            <form method="post" action="generate">
                <div class="folder">
                    <#list dir as item>
                        <div class="item">
                            <a href="<@spring.url "/browse/${item.absoluteFileName}"/>">
                                <img src="<@spring.url "/resources/images/dir.gif"/>" alt="Folder"/>&nbsp;${item.displayName}
                                <#if item.recentUpdate >
                                    <span><@spring.message "pls.browse.new"/></span>
                                </#if>
                            </a>
                        </div>
                    </#list>
                </div>
                <div class="contents">
                    <#if (files?size == 0) && (dir?size == 0)>
                        <div class="errors"><@spring.message "error.no.content"/>
                        </div>
                    </#if>
                    <#if (files?size > 0) || (dir?size > 0)>
                        <div class="item">
                            <input type="checkbox" name="all" value="Y" onclick="toggleCheckboxes(this);">
                            All&nbsp;Name
                        </div>
                        <#list dir as item>
                            <div class="item">
                                <input type="checkbox" name="dirList" value="${item.absoluteFileName}">
                                <img src="<@spring.url '/resources/images/dir.gif'/>" alt="Folder"/>&nbsp;${item.displayName}
                                <#if item.recentUpdate>
                                    <span><@spring.message "pls.browse.new"/></span>
                                </#if>
                            </div>
                        </#list>
                        <#list files as item>
                            <div class="item">
                                <input type="checkbox" name="mp3List" value="${item.absoluteFileName}">
                                <img src="<@spring.url '/resources/images/dir.gif'/>"  alt="Media" height="15" width="15"/>&nbsp;${item.displayName}
                                <#if item.recentUpdate>
                                    <span><@spring.message "pls.browse.new"/></span>
                                </#if>
                            </div>
                        </#list>
                        <input type="submit" name="play" value="Play" onclick="return(checkSelected(this));">
                    </#if>
                </div>
            </form>
        </div>
        <#include "footer.ftl">
    </body>
</html>
