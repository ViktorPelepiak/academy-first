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
    <title>SubjectEdit</title>

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
    <script>
        function x() {
            document.getElementById('exampleModal').style.display = 'none';
            document.getElementById('exampleModal').className = 'modal fade';
        }
    </script>
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

<!-- Modal -->
<c:if test="${error!=null}">
    <div class="modal fade show" id="exampleModal" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: block" data-show="true">
        <div class="modal-dialog" role="document" data-show="true">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Error</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="x()">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                        ${error}
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="x();">OK</button>
                </div>
            </div>
        </div>
    </div>
</c:if>

<br>

    <form action="/ViPSchedule/subject_change" method="post" style="width: 70%; margin: 0 auto">
        <div class="form-group row">
            <div class="col-sm-10">
                    <input name="id" id="id" type="text" hidden value="${id}" class="form-control form-control-lg">
            </div>
        </div>

        <div class="form-group row">
            <label for="name" class="col-sm-2 col-form-label">Subject name</label>
            <div class="col-sm-10">
                <input name="name" id="name" type="text" value="${name}" class="form-control form-control-lg">
            </div>
        </div>

        <div class="form-group row"  style="width: 100%;">
            <div class="col-sm-4" style="width: 33%;">
                <input class="btn btn-success" type="submit" value="Save changes">
            </div>

            <div class="col-sm-4" style="width: 33%;">
                <input type="reset" class="btn btn-secondary" onclick="/subjects" value="Cancel" style="width: 33%;">
            </div>
        </div>
    </form>
</body>
</html>
