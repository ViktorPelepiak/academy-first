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
    <title>TeacherEdit</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</head>
<body style="width: 100%; background: lavender">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="index.jsp">ViPSchedule</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto">
            <%--          <li class="nav-item active">--%>
            <%--            <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>--%>
            <%--          </li>--%>
        </ul>
        <span class="navbar-text">
          Developed by Viktor Pelepiak
        </span>
    </div>
</nav>

<br>

<form action="/ViPSchedule/teacher_change" method="post" style="width: 70%; margin: 0 auto">
    <div class="form-group row">
        <div class="col-sm-10">
            <input name="id" id="id" type="text" hidden value="${id}" class="form-control form-control-lg">
        </div>
    </div>

    <div class="form-group row">
        <label for="f_name" class="col-sm-2 col-form-label">First name</label>
        <div class="col-sm-10">
            <input name="f_name" id="f_name" type="text" value="${f_name}" class="form-control form-control-lg">
        </div>
    </div>

    <div class="form-group row">
        <label for="l_name" class="col-sm-2 col-form-label">Last name</label>
        <div class="col-sm-10">
            <input name="l_name" id="l_name" type="text" value="${l_name}" class="form-control form-control-lg">
        </div>
    </div>

    <div class="form-group row">
        <label for="fth_name" class="col-sm-2 col-form-label">Father name</label>
        <div class="col-sm-10">
            <input name="fth_name" id="fth_name" type="text" value="${fth_name}" class="form-control form-control-lg">
        </div>
    </div>

    <div class="form-group row">
        <label for="dob" class="col-sm-2 col-form-label">Date of birth</label>
        <div class="col-sm-10">
            <input name="dob" id="dob" type="date" value="${dob}" class="form-control form-control-lg">
        </div>
    </div>

    <div class="form-group row">
        <label for="info" class="col-sm-2 col-form-label">Info</label>
        <div class="col-sm-10">
            <input name="info" id="info" type="text" value="${info}" class="form-control form-control-lg">
        </div>
    </div>

    <div class="form-group row"  style="width: 100%;">
        <div class="col-sm-4" style="width: 33%;">
            <input class="btn btn-success" type="submit" value="Save changes">
        </div>

        <div class="col-sm-4" style="width: 33%;">
            <input type="reset" class="btn btn-secondary" onclick="/teachers" value="Cancel" style="width: 33%;">
        </div>
    </div>
</form>
</body>
</html>
