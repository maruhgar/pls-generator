<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title><tiles:insertAttribute name="title" ignore="true" />
        </title>
        <tiles:insertAttribute name="header"/>
    </head>
    <body class="text-color background-color">
        <tiles:insertAttribute name="head"/>
        <div class="spacer"></div>
        <div class="body">
            <tiles:insertAttribute name="body"/>
        </div>
        <tiles:insertAttribute name="footer"/>
    </body>
</html>
