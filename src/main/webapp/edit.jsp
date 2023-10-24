<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<!DOCTYPE HTML>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <title>
        ${meal.id == null ? 'Add meal' : 'Edit meal'}
    </title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>${meal.id == null ? 'Add meal' : 'Edit meal'}</h2>
<div>
    <form action="meals" method="post">
        <input type="hidden" name="id" value="${meal.id}">
        <table cellspacing="0" border="0">
            <tr>
                <td width="200">DateTime:</td>
                <td width="300"><label for="datetime">dateLocal</label><input type="datetime-local" name="datetime" id="datetime" value="${meal.dateTime}"></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><input type="text" name="description" id="description" value="${meal.description}" size="30"></td>
            </tr>
            <tr>
                <td>Calories:</td>
                <td><input type="number" name="calories" id="calories" value="${meal.calories}" size="18"></td>
            </tr>
            <tr>
                <td colspan="2"><button type="submit" >Save</button>&nbsp;&nbsp;&nbsp;<button type="button" onclick="window.history.back();">Cancel</button></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>