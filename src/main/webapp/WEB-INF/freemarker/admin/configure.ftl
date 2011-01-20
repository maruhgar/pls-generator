<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html lang="en">
    <head>
        <title><@spring.message 'pls.configure.title'/></title>
        <#include "../header.ftl">
    </head>
    <body class="text-color background-color">
        <#include "../head.ftl">
        <div class="spacer"></div>
        <div class="body">
            <form method="POST">
                <fieldset>
                    <ol>
                        <li>
                            <@spring.bind "settings.contentFolder"/>
                            <label for="contentFolder"><@spring.message 'pls.configure.folder'/></label>
                            <@spring.showErrors ' ', 'errors'/>
                        </li>
                        <li><@spring.formInput 'settings.contentFolder', 'size="30" maxlength="80"'/>
                        </li>
                    </ol>
                    <ol>
                        <li>
                             <@spring.bind "settings.playerUrl"/>
                            <label for="playerUrl"><@spring.message 'pls.configure.url'/></label>
                            <@spring.showErrors ' ', 'errors'/>
                        </li>
                        <li><@spring.formInput 'settings.playerUrl', 'size="30" maxlength="80"'/>
                        </li>
                    </ol>
                    <ol>
                        <li>
                            <@spring.bind "settings.modifiedDays"/>
                            <label for="modifiedDays"><@spring.message 'pls.configure.modified'/></label>
                            <@spring.showErrors ' ', 'errors'/>
                        </li>
                        <li><@spring.formInput 'settings.modifiedDays', 'size="5"'/>
                        </li>
                    </ol>
                </fieldset>
                <fieldset>
                    <ol>
                        <li><input type="submit" value="Update Configuration"/>
                        </li>
                    </ol>
                </fieldset>
            </form>
        </div>
        <#include "../footer.ftl">
    </body>
</html>
