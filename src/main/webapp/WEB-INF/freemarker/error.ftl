<#import "spring.ftl" as spring />

<!DOCTYPE html>
<html lang="en">
    <head>
        <title><@spring.message 'pls.error.title'/></title>
        <#include "header.ftl">
    </head>
    <body class="text-color background-color">
        <#include "head.ftl">
        <div class="spacer"></div>
        <div class="body">
            <div class="error-page">
                <div class="errors">
                    ${errorStackTrace}
                </div>
            </div>
        </div>
        <#include "footer.ftl">
    </body>
</html>
