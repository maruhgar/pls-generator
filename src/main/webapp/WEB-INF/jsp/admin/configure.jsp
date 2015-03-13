<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pls"  tagdir="/WEB-INF/tags/pls" %>

<pls:page title="pls.configure.title">
    <form:form modelAttribute="settings" method="POST">
        <fieldset>
            <ol>
                <li><label for="contentFolder"><fmt:message key="pls.configure.folder"/></label><form:errors path="contentFolder" cssClass="errors"/>
                </li>
                <li><form:input path="contentFolder" size="30" maxlength="80"/>
                </li>
            </ol>
            <ol>
                <li><label for="playerUrl"><fmt:message key="pls.configure.url"/></label><form:errors path="playerUrl" cssClass="errors"/>
                </li>
                <li><form:input path="playerUrl" size="30" maxlength="80"/>
                </li>
            </ol>
            <ol>
                <li><label for="modifiedDays"><fmt:message key="pls.configure.modified"/></label><form:errors path="modifiedDays" cssClass="errors"/>
                </li>
                <li><form:input path="modifiedDays" size="5"/>
                </li>
            </ol>
        </fieldset>
        <fieldset>
            <ol>
                <li><input type="submit" value="Update Configuration"/>
                </li>
            </ol>
        </fieldset>
    </form:form>
</pls:page>
