<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html lang="en">
    <head>
        <title><@spring.message 'pls.profile.title'/></title>
        <#include "../header.ftl">
    </head>
    <body class="text-color background-color">
        <#include "../head.ftl">
        <div class="spacer"></div>
        <div class="body">
            <form method="POST">
                <@spring.bind "profile"/>
                <@spring.showErrors ' ', 'errors'/>
                <@spring.formHiddenInput "profile.userInfo"/>
                <fieldset>
                    <ol>
                        <li>
                            <@spring.bind "profile.oldPassword"/>
                            <label for="oldPassword"><@spring.message "pls.profile.password.old"/></label>
                            <@spring.showErrors ' ', 'errors'/>
                        </li>
                        <li><@spring.formPasswordInput "profile.oldPassword", 'size="14" maxlength="14"'/>
                        </li>
                    </ol>
                    <ol>
                        <li>
                            <@spring.bind "profile.newPassword"/>
                            <label for="newPassword"><@spring.message "pls.profile.password.new"/></label>
                            <@spring.showErrors ' ', 'errors'/>
                        </li>
                        <li><@spring.formPasswordInput "profile.newPassword", 'size="14" maxlength="14"'/>
                        </li>
                    </ol>
                    <ol>
                        <li>
                            <@spring.bind "profile.confirmPassword"/>
                            <label for="confirmPassword"><@spring.message "pls.profile.password.confirm"/></label>
                            <@spring.showErrors ' ', 'errors'/>
                        </li>
                        <li><@spring.formPasswordInput "profile.confirmPassword", 'size="14" maxlength="14"'/>
                        </li>
                    </ol>
                </fieldset>
                <fieldset>
                    <ol>
                        <li><input type="submit" value="Update Profile"/>
                        </li>
                    </ol>
                </fieldset>
            </form:form>
        </div>
        <#include "../footer.ftl">
    </body>
</html>
