<%--
  Created by IntelliJ IDEA.
  User: ViktorPelepiak
  Date: 12.11.2019
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>GroupEdit</title>
</head>
<body>
    <form action="/group_change" method="post">
        <input name="id" type="text" hidden value="${id}">
        <input name="faculty" type="text" value="${fac}">
        <input name="specialisation" type="text" value="${spec}">
        <input name="group_number" type="text" value="${num}">
        <input name="courses" type="number" min="1" max="6" value="${course}">
        <input type="submit">
    </form>
</body>
</html>
