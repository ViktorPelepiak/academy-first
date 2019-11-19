<%--
  Created by IntelliJ IDEA.
  User: ViktorPelepiak
  Date: 12.11.2019
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Subjects</title>

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
<nav class="navbar navbar-expand-lg navbar-default navbar-fixed-top navbar-dark bg-dark">
    <a class="navbar-brand" href="index.jsp">ViPSchedule</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
            aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active" style="width: 100%">
                <form method="post" id="test" action="/for_teacher">
                    <input type="text" hidden>
                </form>
            </li>
        </ul>
        <span class="navbar-text">
          Developed by Viktor Pelepiak
        </span>
    </div>
</nav>
<br>
<table class="table table-striped table-hover" style="width: 80%; margin: 0 auto; text-align: center;">
    <thead>
    <tr>
        <th scope="col">Subject name</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="s" items="${subjects}">
        <tr id="group_${s.id}">
            <td scope="row">
                    ${s.name}
            </td>
            <td>
                <form action="/ViPSchedule/subject_change" method="get" style="float: left;">
                    <input type="text" value="${s.id}" name="id" hidden>
                    <button type="submit" class="btn btn-primary">Edit</button>
                </form>
<%--            </td>--%>
<%--            <td>--%>
                <form action="/ViPSchedule/subjects" method="post" style="float: right;">
                    <input type="number" value="${s.id}" hidden name="del_id">
                    <div class="modal fade" id="exampleModal${s.id}" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Delete item</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    Are you sure?
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Delete</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal${s.id}"
                       href="#exampleModal${s.id}">
                        Delete
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="/ViPSchedule/subject_change" id="sdf" style="width: 80%; margin: 0 auto;">
    <button type="submit" class="btn btn-success">Add subject</button>
</form>
</body>
</html>
