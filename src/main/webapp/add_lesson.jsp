<%--
  Created by IntelliJ IDEA.
  User: ViktorPelepiak
  Date: 13.11.2019
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add lesson</title>

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
    <script src="js/functions.js"></script>
</head>
<body style="width: 100%; align-content: center; background: lavender" onload="function errorAlert() { if (${error != null}) {alert(${error})}}">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="index.jsp">ViPSchedule</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
            aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
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

<form action="/ViPSchedule/lessons" method="post" style="width: 70%; align-self: center; margin: 0 auto;">
    <div class="form-group row">
        <label for="lessonTime" class="col-sm-2 col-form-label">Lesson time</label>
        <div class="col-sm-10">
            <select class="form-control form-control-lg" name="lessonTime" id="lessonTime">
                <c:forEach var="lessonT" items="${lessonTime}">
                    <option value="${lessonT.lessonNumber}"> ${lessonT.lessonNumber} | ${lessonT.beginTime}
                        | ${lessonT.endTime}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="form-group row">
        <label for="group" class="col-sm-2 col-form-label">Group</label>
        <div class="col-sm-10">
            <select class="form-control form-control-lg" name="group" id="group">
                <c:forEach var="group" items="${groups}">
                    <option value="${group.id}"> ${group.faculty} | ${group.specialisation}
                        | ${group.groupNumber}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="form-group row">
        <label for="type" class="col-sm-2 col-form-label">Lesson type</label>
        <div class="col-sm-10">
            <select class="form-control form-control-lg" name="type" id="type">
                <option value="0">LECTURE</option>
                <option value="1">PRACTICE</option>
                <option value="2">LABORATORY</option>
            </select>
        </div>
    </div>

    <div class="form-group row">
        <label for="teacher" class="col-sm-2 col-form-label">Teacher</label>
        <div class="col-sm-10">
            <select class="form-control form-control-lg" name="teacher" id="teacher">
                <c:forEach var="teacher" items="${teachers}">
                    <option value="${teacher.id}">${teacher.firstName} | ${teacher.fatherName}
                        | ${teacher.lastName}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="form-group row">
        <label for="auditory" class="col-sm-2 col-form-label">Auditory</label>
        <div class="col-sm-10">
            <select class="form-control form-control-lg" name="auditory" id="auditory">
                <c:forEach var="auditory" items="${auditories}">
                    <option value="${auditory.id}">${auditory.buildingNumber} | ${auditory.auditoryNumber}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="form-group row">
        <label for="weekParity" class="col-sm-2 col-form-label">Week parity</label>
        <div class="col-sm-10">
            <select class="form-control form-control-lg" name="weekParity" id="weekParity">
                <option value="0">PAIR WEEK</option>
                <option value="1">UNPAIR WEEK</option>
                <option value="2">ALL WEEKS</option>
            </select>
        </div>
    </div>

    <div class="form-group row">
        <label for="day" class="col-sm-2 col-form-label">Day of week</label>
        <div class="col-sm-10">
            <select class="form-control form-control-lg" name="day" id="day">
                <option value="1">Monday</option>
                <option value="2">Tuesday</option>
                <option value="3">Wednesday</option>
                <option value="4">Thursday</option>
                <option value="5">Friday</option>
            </select>
        </div>
    </div>

    <div class="form-group row">
        <label for="subject" class="col-sm-2 col-form-label">Subject</label>
        <div class="col-sm-10">
            <select class="form-control form-control-lg" name="subject" id="subject">
                <c:forEach var="subject" items="${subjects}">
                <option value="${subject.id}">${subject.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="form-group row">
        <div class="col-sm-4" style="align-self: auto">
            <input type="submit" class="btn btn-success" value="Add lesson">
            <%--            <button type="button" class="btn btn-success" onclick="createLesson()" name="submit" id="submit" style="width: 100%;">Add--%>
<%--                lesson--%>
<%--            </button>--%>
        </div>

        <div class="col-sm-4" style="align-self: auto">
            <input value="Cancel" class="btn btn-secondary" type="reset" name="cancel" id="cancel" style="width: 100%">
        </div>
    </div>


</form>
</body>
</html>
