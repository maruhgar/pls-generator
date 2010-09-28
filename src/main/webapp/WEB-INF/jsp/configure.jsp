<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Configure</title>
    </head>
    <body>
        <form:form modelAttribute="settings" method="POST">
          <table>
            <tr>
              <th>
                Content Folder: <form:errors path="contentFolder" cssClass="errors"/>
                <br/>
                <form:input path="contentFolder" size="30" maxlength="80"/>
              </th>
            </tr>
            <tr>
              <th>
                Player Url: <form:errors path="playerUrl" cssClass="errors"/>
                <br/>
                <form:input path="playerUrl" size="30" maxlength="80"/>
              </th>
            </tr>
            <tr>
              <th>
                Modified Days: <form:errors path="modifiedDays" cssClass="errors"/>
                <br/>
                <form:input path="modifiedDays" size="5"/>
              </th>
            </tr>
            <tr>
              <td>
                <p class="submit"><input type="submit" value="Update Configuration"/></p>
              </td>
            </tr>
          </table>
        </form:form>
    </body>
</html>
