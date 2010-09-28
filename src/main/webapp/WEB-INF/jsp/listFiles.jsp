<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>Playlist Generator</title>

        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">

        <script src="<c:url value="/script/toggleCheckboxes.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value="/themes/default/css/mail_blue_all.css"/>">

    </head>

    <body>
        <form method="POST" action="generate">
        <table cellSpacing=0 cellPadding=0 width="100%" border=1>
            <tr>
                <td colspan="2" align="center">Playlist Generator</td>
            </tr>
            <tr>
                <td width="25%" valign=top>
                    <table>
                        <c:forEach items="${dir}" var="items">
                            <tr>
                                <td>
                                    <a href="<c:url value="/play/browse?folder=${items.absoluteFileName}"/>">
                                        <img src="<c:url value="/themes/default/images/dir.gif"/>">&nbsp;${items.displayName}
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
                                    <td><img src="<c:url value="/themes/default/images/dir.gif"/>">&nbsp;${items.displayName}
                                        <c:if test="${items.recentUpdate}" >
                                            <span style="color:red;text-decoration:blink">New!</span>
                                        </c:if>
                                    </td>
                                    <td><input type="checkbox" name="dirList" value="${items.absoluteFileName}"></td>
                                </tr>
                            </c:forEach>
                            <c:forEach items="${files}" var="items">
                                <tr>
                                    <td ><img src="<c:url value="/themes/default/images/mp3.jpg"/>" height="15" width="15">&nbsp;${items.displayName}
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
    </body>
</html>
