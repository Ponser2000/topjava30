<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<!DOCTYPE HTML>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Meals</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<p>
    <a href="meals?action=add">Add Meal</a>
<p>
<table cellspacing="0" border="2">
    <thead>
    <tr>
        <th width="125">Date</th>
        <th width="200">Description</th>
        <th width="50">Calories</th>
        <th width="50">&nbsp;</th>
        <th width="50">&nbsp;</th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="mealList" scope="request" type="java.util.List"/>
    <c:forEach items="${mealList}" var="mealTo">
        <tr data-meal-excess="${mealTo.excess ? 'red' : 'green'}">
            <td><javatime:parseLocalDateTime value="${mealTo.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parseDate"/>
                <javatime:format value="${parseDate}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td><a href="meals?action=delete&id=${mealTo.id}">Delete</a></td>
            <td><a href="meals?action=edit&id=${mealTo.id}">Edit</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>