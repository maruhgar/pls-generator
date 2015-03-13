<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="error-page">
    <div class="errors">
        <c:out value="${errorStackTrace}" />
    </div>
</div>
