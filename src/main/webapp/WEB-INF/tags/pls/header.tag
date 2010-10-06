<%@ tag body-content="empty" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>

<script src="<c:url value="/script/toggleCheckboxes.js"/>"></script>

<spring:theme var="css" code="css"/>
<c:if test="${not empty css}">
    <link rel="stylesheet" type="text/css" href="<c:url value="${css}/pls-default.css"/>">
</c:if>
<spring:theme var="images" code="images"/>

<div class="header">
    <fmt:message key="pls.header"/>
</div>
