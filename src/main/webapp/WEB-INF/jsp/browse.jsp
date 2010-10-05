<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="pls"     tagdir="/WEB-INF/tags/pls" %>

<spring:theme var="images" code="images"/>

<pls:page title="pls.title">
    <form method="POST" action="generate">
        <table cellSpacing=0 cellPadding=0 width="100%" border=1>
            <tr>
                <td width="25%" valign=top>
                    <table>
                        <c:forEach items="${dir}" var="items">
                            <tr>
                                <td>
                                    <a href="<c:url value="/play/browse?folder=${items.absoluteFileName}"/>">
                                        <img src="<c:url value="${images}/dir.gif"/>">&nbsp;${items.displayName}
                                        <c:if test="${items.recentUpdate}" >
                                            <span style="color:red;text-decoration:blink">New!</span>
                                        </c:if>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
                <td valign="top">
                    <table border=1>

                        <c:if test="${empty files && empty dir}">
                            <strong>No files found. Please select another Directory</strong>
                        </c:if>
                        <c:if test="${!empty files || !empty dir}">
                            <tr>
                                <th>Name</th>
                                <th>All&nbsp;<INPUT type="checkbox" name="all" value="Y" onclick="toggleCheckboxes(this);"></th>
                            </tr>
                            <c:forEach items="${dir}" var="items">
                                <tr>
                                    <td><img src="<c:url value="${images}/dir.gif"/>">&nbsp;${items.displayName}
                                        <c:if test="${items.recentUpdate}" >
                                            <span style="color:red;text-decoration:blink">New!</span>
                                        </c:if>
                                    </td>
                                    <td><input type="checkbox" name="dirList" value="${items.absoluteFileName}"></td>
                                </tr>
                            </c:forEach>
                            <c:forEach items="${files}" var="items">
                                <tr>
                                    <td ><img src="<c:url value="${images}/mp3.jpg"/>" height="15" width="15">&nbsp;${items.displayName}
                                        <c:if test="${items.recentUpdate}" >
                                            <span style="color:red;text-decoration:blink">New!</span>
                                        </c:if>
                                    </td>
                                    <td><input type="checkbox" name="mp3List" value="${items.absoluteFileName}"></input
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="2" align="right" >
                                    <input type="submit" name="play" value="Play" onclick="return(checkSelected(this));"></input>
                                </td>
                            </tr>
                        </c:if>

                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="2" bgColor=aquamarine></td>
            </tr>
        </table>
    </form>
</pls:page>
