<%@ taglib prefix="pls"     tagdir="/WEB-INF/tags/pls" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<pls:page title="pls.error.title">
    <div class="error-page">
        <div class="errors">
            <c:out value="${errorStackTrace}" />
        </div>
    </div>
</pls:page>
