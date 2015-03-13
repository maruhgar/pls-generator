<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>

<meta name="pragma"        content="no-cache"/>
<meta name="cache-control" content="no-cache"/>
<meta name="expires"       content="0"/>
<meta charset="utf-8"/>

<script type="text/javascript" src="<c:url value="/resources/script/toggleCheckboxes.js"/>"></script>

<link rel="stylesheet" href="<c:url value="/resources/css/pls-default.css"/>"/>

<spring:theme var="css" code="css"/>

<c:if test="${not empty css}">
    <link rel="stylesheet" type="text/css" href="<c:url value="${css}/style.css"/>"/>
</c:if>


