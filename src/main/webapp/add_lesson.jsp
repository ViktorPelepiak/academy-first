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
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</head>
<body>
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

<form action="/lessons" method="post">
    <div class="container">
        <div class="row">
            <div class="col">
                Lesson time
            </div>
            <div class="col">
                <select name="lessonTime" id="lessonTime">
                    <c:forEach var="lessonT" items="${lessonTime}">
                        <option value="${lessonT.lessonNumber}"> ${lessonT.lessonNumber} | ${lessonT.beginTime} | ${lessonT.endTime}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="col">
                Group
            </div>
            <div class="col">
                <select name="group" id="group">
                    <c:forEach var="group" items="${groups}">
                        <option value="${group.id}"> ${group.faculty} | ${group.specialisation} | ${group.groupNumber} | ${group.course}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="col">
                Lesson type
            </div>
            <div class="col">
                <select name="type" id="type">
                    <option value="0">LECTURE</option>
                    <option value="1">PRACTICE</option>
                    <option value="2">LABORATORY</option>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="col">
                Teacher
            </div>
            <div class="col">
                <select name="teacher" id="teacher">
                    <c:forEach var="teacher" items="${teachers}">
                        <option value="${teacher.id}">${teacher.firstName} | ${teacher.fatherName} | ${teacher.lastName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="col">
                Auditory
            </div>
            <div class="col">
                <select name="auditory" id="auditory">
                    <c:forEach var="auditory" items="${auditories}">
                        <option value="${auditory.id}">${auditory.buildingNumber} | ${auditory.auditoryNumber}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        
        <div class="row">
            <div class="col">
                Week parity
            </div>
            <div class="col">
                <select name="weekParity" id="weekParity">
                    <option value="0">PAIR WEEK</option>
                    <option value="1">UNPAIR WEEK</option>
                    <option value="2">ALL WEEKS</option>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="col">
                Day of week
            </div>
            <div class="col">
                <select name="day" id="day">
                    <option value="1">Monday</option>
                    <option value="2">Tuesday</option>
                    <option value="3">Wednesday</option>
                    <option value="4">Thursday</option>
                    <option value="5">Friday</option>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="col">
                Subject
            </div>
            <div class="col">
                <select name="subject" id="subject">
                    <c:forEach var="subject" items="${subjects}">
                        <option value="${subject.id}">${subject.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <button type="submit" class="button">Add lesson</button>
            </div>
        </div>
    </div>

</form>
</body>
</html>
