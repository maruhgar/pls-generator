<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Playlist Generator</title>
    </head>

<body>

    <h2>Exception Information</h2>

        <table border="1" width="100%">

            <tr>
                <td colspan="2"><font color="#FF0000"><h2>Error</h2></font></td>
            </tr>
            <tr>
                <td colspan="2">
                <p align="center"><textarea rows="11" name="S1" cols="57"><c:out value="${errorStackTrace}" /></textarea></p>
                &nbsp;</td>
            </tr>

        </table>

    </body>
</html>
