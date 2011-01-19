<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>

<form:form modelAttribute="profile" method="POST">
    <form:hidden path="userInfo"/>
    <form:errors cssClass="errors"/>
    <fieldset>
        <ol>
            <li><label for="oldPassword"><fmt:message key="pls.profile.password.old"/></label><form:errors path="oldPassword" cssClass="errors"/>
            </li>
            <li><form:password path="oldPassword" size="14" maxlength="14"/>
            </li>
        </ol>
        <ol>
            <li><label for="newPassword"><fmt:message key="pls.profile.password.new"/></label><form:errors path="newPassword" cssClass="errors"/>
            </li>
            <li><form:password path="newPassword" size="14" maxlength="14"/>
            </li>
        </ol>
        <ol>
            <li><label for="confirmPassword"><fmt:message key="pls.profile.password.confirm"/></label><form:errors path="confirmPassword" cssClass="errors"/>
            </li>
            <li><form:password path="confirmPassword" size="14" maxlength="14"/>
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

