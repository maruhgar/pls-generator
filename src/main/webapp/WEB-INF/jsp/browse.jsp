<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="pls"     tagdir="/WEB-INF/tags/pls" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<spring:theme var="images" code="images"/>

<pls:page title="pls.title">
    <form method="post" action="generate">
        <div class="folder">
            <c:forEach items="${dir}" var="items">
                <div class="item">
                    <a href="<c:url value="/play/browse?folder=${items.absoluteFileName}"/>">
                        <img src="<c:url value="/images/dir.gif"/>" alt="Folder"/>&nbsp;${items.displayName}
                        <c:if test="${items.recentUpdate}" >
                            <span><fmt:message key="pls.browse.new"/></span>
                        </c:if>
                    </a>
                </div>
            </c:forEach>
        </div>
        <div class="contents">
            <c:if test="${empty files && empty dir}">
                <div class="errors"><fmt:message key="error.no.content"/>
                </div>
            </c:if>
            <c:if test="${!empty files || !empty dir}">
                <div class="item">
                    <input type="checkbox" name="all" value="Y" onclick="toggleCheckboxes(this);"></input>
                    All&nbsp;Name
                </div>
                <c:forEach items="${dir}" var="items">
                    <div class="item">
                        <input type="checkbox" name="dirList" value="${items.absoluteFileName}"></input>
                        <img src="<c:url value="/images/dir.gif"/>" alt="Folder"/>&nbsp;${items.displayName}
                        <c:if test="${items.recentUpdate}" >
                            <span><fmt:message key="pls.browse.new"/></span>
                        </c:if>
                    </div>
                </c:forEach>
                <c:forEach items="${files}" var="items">
                    <div class="item">
                        <input type="checkbox" name="mp3List" value="${items.absoluteFileName}"></input>
                        <img src="<c:url value="/images/mp3.jpg"/>" alt="Media" height="15" width="15"/>&nbsp;${items.displayName}
                        <c:if test="${items.recentUpdate}" >
                            <span><fmt:message key="pls.browse.new"/></span>
                        </c:if>
                    </div>
                </c:forEach>
                <input type="submit" name="play" value="Play" onclick="return(checkSelected(this));"></input>
            </c:if>
        </div>
    </form>
</pls:page>
