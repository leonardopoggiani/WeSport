<%--
  Created by IntelliJ IDEA.
  User: ZenBook Pro
  Date: 10/04/2022
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
    <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/booking.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/navbar.css" rel="stylesheet" type="text/css">
    <script async src="${pageContext.request.contextPath}/js/booking.js"></script>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
</head>
<body>
    <nav id="menu" class="navbar navbar-default">
        <div class="container-nav">
            <div id="navbar">
                <ul class="nav navbar-nav">
                    <li  class="active"><a href="${pageContext.request.contextPath}/admin">Homepage</a></li>
                    <!--li><a href="${pageContext.request.contextPath}/removebooking">Remove Booking</a></li>
                    <li><a href="${pageContext.request.contextPath}/removeuser">Remove User</a></li!-->
                    <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
                </ul>
            </div>

        </div>
    </nav>

    <h1>Admin's Page</h1>

    <div class="home">
        <div class="row">

            <a class="box-content-header" href="${pageContext.request.contextPath}/removebooking">
                <div class="project-box-content-header" id="booking"> Remove Booking</div>
            </a>

            <a class="box-content-header"  href="${pageContext.request.contextPath}/removeuser">
                <div class="project-box-content-header" id="profile"> Remove User </div>
            </a>

        </div>

        <div class="row-last">
            <a class="box-content-header" href="${pageContext.request.contextPath}/chat">
                <div class="project-box-content-header" id="chat"> Chat </div>
            </a>
        </div>


</body>
</html>
