<%@ tag body-content="scriptless" %>
<%@ attribute name="title" required="true" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pls"  tagdir="/WEB-INF/tags/pls" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title><fmt:message key="${title}"/></title>
        <pls:header/>
    </head>
    <body>
        <div>
            <jsp:doBody/>
        </div>
        <pls:footer/>
    </body>
</html>
