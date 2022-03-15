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
        <a class="goback" href="${pageContext.request.contextPath}/index.jsp">
            <div> Need to login first! </div>
        </a>
    <% } else { %>
        <h1>Welcome to your homepage</h1>

        <div class="home">
            <div class="row">

                <a class="box-content-header" href="${pageContext.request.contextPath}/userlist">
                    <div class="project-box-content-header"> List of user </div>
                </a>

                <a class="box-content-header" href="${pageContext.request.contextPath}/booking">
                    <div class="project-box-content-header"> Book a field </div>
                </a>

            </div>

            <div class="row">
                <a class="box-content-header" href="${pageContext.request.contextPath}/pages/jsp/profile.jsp">
                    <div class="project-box-content-header"> Personal profile </div>
                </a>

                <a class="box-content-header" href="${pageContext.request.contextPath}/pages/jsp/chat.jsp">
                    <div class="project-box-content-header"> Chat </div>
                </a>
            </div>

            <a class="goback" href="${pageContext.request.contextPath}/index.jsp">
                <div> Go back </div>
            </a>
        </div>

    <% } %>

</body>
</html>
