<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>

<div class="heading">

    <form method="post" action="<c:url value="/theme"/>">
        <div class="theme">
            <select name="theme">
                <option value="default"><fmt:message key="pls.theme.default"/></option>
                <option value="black"><fmt:message key="pls.theme.black"/></option>
            </select>
            <input type="submit" name="submit" value="<fmt:message key="pls.theme.change"/>"/>
        </div>
    </form>

    <div class="header-title">
        <header>
            <fmt:message key="pls.header"/>
        </header>
    </div>
</div>

<div class="menu">
    <nav>
        <ul>
            <li><a href="<c:url value="/"/>"><fmt:message key="pls.menu.home"/></a></li>
            <li><a href="<c:url value="/admin/configure"/>"><fmt:message key="pls.menu.configure"/></a></li>
            <li><a href="<c:url value="/admin/profile"/>"><fmt:message key="pls.menu.profile"/></a></li>
        </ul>
    </nav>
</div>
