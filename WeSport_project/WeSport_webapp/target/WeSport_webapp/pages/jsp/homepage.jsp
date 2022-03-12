<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Homepage</title>
    <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
</head>
<body>
    <%if(session.getAttribute("logged_user") == null) { %>
        <p>You need to login first!</p>
        <a href="../../index.jsp">Login</a>
    <% } else { %>
        <h1>Welcome to your homepage</h1>

        <div class="container">
            <div class="row">

                <div class="project-box-content-header">
                    <a class="box-content-header"
                       href="${pageContext.request.contextPath}/pages/jsp/userslist.jsp"> List of user
                    </a>
                </div>

                <div class="project-box-content-header">
                    <a class="box-content-header"
                       href="${pageContext.request.contextPath}/pages/jsp/booking.jsp">Book a field</a>
                </div>
            </div>

            <div class="row">
                <div class="project-box-content-header">
                    <a class="box-content-header"
                       href="${pageContext.request.contextPath}/pages/jsp/profile.jsp">Personal profile</a>
                </div>

                <div class="project-box-content-header">
                    <a class="box-content-header"
                       href="${pageContext.request.contextPath}/pages/jsp/chat.jsp">Chat</a>
                </div>
            </div>
        </div>

    <% } %>
</body>
</html>
