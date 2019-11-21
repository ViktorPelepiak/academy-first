<%--
  Created by IntelliJ IDEA.
  User: ViktorPelepiak
  Date: 07.11.2019
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html style="width: 100%">
<head>
    <title>Розклоад для груп</title>

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
<body style="width: 100%; background: lavender;">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="index.jsp">ViPSchedule</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
            aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto">
                      <li class="nav-item active" style="width: 100%">
                          <form method="post" id="test" action="/ViPSchedule/for_group">
                              <select class="form-control" style="width: 100px" size="1" name="groupId"
                                  onchange="document.getElementById('test').submit()">
                              <c:forEach var="group" items="${groups}">
                                  <option value="${group.id}"
                                          <c:if test="${group.id==groupId}">selected</c:if> >${group.groupNumber}</option>
                              </c:forEach>
                          </select></form>
                      </li>
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
<div class="container">
    <ul id="myTab" class="nav nav-pills nav-justified" role="tablist">
        <li class="nav-item" data-toggle="pill" role="tab">
            <a class="nav-link active" href="#today" role="tab" data-toggle="tab">
                TODAY
            </a>
        </li>
        <li class="nav-item" data-toggle="pill" role="tab">
            <a class="nav-link" href="#tomorrow" role="tab" data-toggle="tab">
                TOMORROW
            </a>
        </li>
        <li class="nav-item" data-toggle="pill" role="tab">
            <a class="nav-link" href="#first" role="tab" data-toggle="tab">
                UNPAIR WEEK
            </a>
        </li>
        <li class="nav-item" data-toggle="pill" role="tab">
            <a class="nav-link" href="#second" role="tab" data-toggle="tab">
                PAIR WEEK
            </a>
        </li>
    </ul>
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="today">
            <table>
                <tr>
                    <td colspan="2">${todayDay}</td>
                </tr>
                <c:forEach var="i" items="1,2,3,4,5">
                    <tr>
                        <td>${i}.</td>
                        <td>
                            <c:set var="flag" value="false"/>
                            <c:forEach var="l" items="${today}">
                                <c:if test="${i == l.lessonTime}">
                                    <table>
                                        <tr>
                                            <td>${l.subject}</td>
                                        </tr>
                                        <tr>
                                            <td>${l.teacher}</td>
                                        </tr>
                                        <tr>
                                            <td>${l.auditory}</td>
                                        </tr>
                                    </table>
                                    <c:set var="flag" value="true"/>
                                </c:if>
                            </c:forEach>
                            <c:if test="${flag=='false'}">
                                <table>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>-----------</td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>
                                </table>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div role="tabpanel" class="tab-pane" id="first">
            <p style="margin: 0 auto">І week</p>
            <div class="container">
                <div class="row">
                    <c:forEach var="d_i" items="1,2,3,4,5">
                        <div class="col">
                            <table>
                                <tr>
                                    <td colspan="2">
                                        <c:if test="${d_i==1}">MONDAY</c:if>
                                        <c:if test="${d_i==2}">TUESDAY</c:if>
                                        <c:if test="${d_i==3}">WEDNESDAY</c:if>
                                        <c:if test="${d_i==4}">THURSDAY</c:if>
                                        <c:if test="${d_i==5}">FRIDAY</c:if>
                                    </td>
                                </tr>
                                <c:forEach var="i" items="1,2,3,4,5">
                                    <tr>
                                        <td>${i}.</td>
                                        <td>
                                            <c:set var="flag" value="false"/>
                                            <c:forEach var="l" items="${I_week}">
                                                <c:if test="${i == l.lessonTime && d_i==l.day}">
                                                    <table>
                                                        <tr>
                                                            <td>${l.subject}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>${l.teacher}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>${l.auditory}</td>
                                                        </tr>
                                                    </table>
                                                    <c:set var="flag" value="true"/>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${flag=='false'}">
                                                <table>
                                                    <tr>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <td>-----------</td>
                                                    </tr>
                                                    <tr>
                                                        <td></td>
                                                    </tr>
                                                </table>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </c:forEach>
                </div>
            </div>

        </div>
        <div role="tabpanel" class="tab-pane" id="tomorrow">
            <table>
                <tr>
                    <td colspan="2">${tomorrowDay}</td>
                </tr>
                <c:forEach var="i" items="1,2,3,4,5">
                    <tr>
                        <td>${i}.</td>
                        <td>
                            <c:set var="flag" value="false"/>
                            <c:forEach var="l" items="${tomorrow}">
                                <c:if test="${i == l.lessonTime}">
                                    <table>
                                        <tr>
                                            <td>${l.subject}</td>
                                        </tr>
                                        <tr>
                                            <td>${l.teacher}</td>
                                        </tr>
                                        <tr>
                                            <td>${l.auditory}</td>
                                        </tr>
                                    </table>
                                    <c:set var="flag" value="true"/>
                                </c:if>
                            </c:forEach>
                            <c:if test="${flag=='false'}">
                                <table>
                                    <tr>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td>-----------</td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                    </tr>
                                </table>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div role="tabpanel" class="tab-pane" id="second">
            <p style="margin: 0 auto">ІІ week</p>
            <div class="container">
                <div class="row">
                    <c:forEach var="d_i" items="1,2,3,4,5">
                        <div class="col">
                            <table>
                                <tr>
                                    <td colspan="2">
                                        <c:if test="${d_i==1}">MONDAY</c:if>
                                        <c:if test="${d_i==2}">TUESDAY</c:if>
                                        <c:if test="${d_i==3}">WEDNESDAY</c:if>
                                        <c:if test="${d_i==4}">THURSDAY</c:if>
                                        <c:if test="${d_i==5}">FRIDAY</c:if>
                                    </td>
                                </tr>
                                <c:forEach var="i" items="1,2,3,4,5">
                                    <tr>
                                        <td>${i}.</td>
                                        <td>
                                            <c:set var="flag" value="false"/>
                                            <c:forEach var="l" items="${II_week}">
                                                <c:if test="${i == l.lessonTime && d_i==l.day}">
                                                    <table>
                                                        <tr>
                                                            <td>${l.subject}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>${l.teacher}</td>
                                                        </tr>
                                                        <tr>
                                                            <td>${l.auditory}</td>
                                                        </tr>
                                                    </table>
                                                    <c:set var="flag" value="true"/>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${flag=='false'}">
                                                <table>
                                                    <tr>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <td>-----------</td>
                                                    </tr>
                                                    <tr>
                                                        <td></td>
                                                    </tr>
                                                </table>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $('#myTab a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })
</script>

<script>
    $('a[data-toggle="pill"]').on('hidden.bs.tab', function (e) {
        console.log(e.target); // вкладка, которая стала активной
        console.log(e.relatedTarget); // предыдущая активная вкладка
    })
</script>

<table>
    <tr></tr>
</table>


</body>
</html>
