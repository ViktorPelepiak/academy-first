<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ViktorPelepiak
  Date: 07.11.2019
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>ViPSchedule</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

      <script>
          function x() {
              document.getElementById('exampleModal').style.display = 'none';
              document.getElementById('exampleModal').className = 'modal fade';
          }
      </script>
  </head>
  <body style="background: lavender">
  <table>
    <!-- Fixed navbar -->
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

      <!-- Button trigger modal -->
      <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
          Launch demo modal
      </button>

      <!-- Modal -->
      <c:if test="${error!=null}">
          <div class="modal fade show" id="exampleModal" tabindex="-1" role="dialog"
               aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: block" data-show="true">
              <div class="modal-dialog" role="document" data-show="true">
                  <div class="modal-content">
                      <div class="modal-header">
                          <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                              <span aria-hidden="true">&times;</span>
                          </button>
                      </div>
                      <div class="modal-body">
                          ${error}
                      </div>
                      <div class="modal-footer">
                          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                          <button type="button" class="btn btn-primary" onclick="x();">Save changes</button>
                      </div>
                  </div>
              </div>
          </div>
      </c:if>


    <tr><th>Що саме ви шукаєте</th></tr>
    <tr><th><a href="${pageContext.request.contextPath}/for_group">Розклад по групах</a></th></tr>
    <tr><th><a href="${pageContext.request.contextPath}/for_teacher">Розклад по викладачах</a></th></tr>
    <tr><th><a href="${pageContext.request.contextPath}/for_auditory">Розклад по аудиторіях</a></th></tr>
    <tr><th><a href="${pageContext.request.contextPath}/group">Інформація про групи</a></th></tr>
    <tr><th><a href="${pageContext.request.contextPath}/teachers">Інформація про викладачів</a></th></tr>
    <tr><th><a href="${pageContext.request.contextPath}/subjects">Інформація про предмети</a></th></tr>
    <tr><th><a href="${pageContext.request.contextPath}/auditory">Інформація про аудиторії</a></th></tr>
    <tr><th><a href="${pageContext.request.contextPath}/lessons">Додавання пари</a></th></tr>
  </table>
  </body>
</html>
