<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Homepage</title>
    <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/navbar.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/navbar.js"></script>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
</head>
<body>
    <nav id="menu" class="navbar navbar-default">
        <div class="container-nav">

            <div id="navbar">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="${pageContext.request.contextPath}/homepage">Homepage</a></li>
                    <li><a href="${pageContext.request.contextPath}/booking">Booking</a></li>
                    <li><a href="${pageContext.request.contextPath}/profile">Profile</a></li>
                    <li><a href="${pageContext.request.contextPath}/chat">Chat</a></li>
                </ul>
            </div>

        </div>
    </nav>
    <%if(session.getAttribute("logged_user") == null) { %>
        <a class="goback" href="${pageContext.request.contextPath}/index.jsp">
            <div> Need to login first! </div>
        </a>
    <% } else { %>
        <h1>Welcome to your homepage</h1>

        <div class="home">
            <div class="row">

                <a class="box-content-header" href="${pageContext.request.contextPath}/userlist">
                    <div class="project-box-content-header" id="users"> List of user </div>
                </a>

                <a class="box-content-header"  href="${pageContext.request.contextPath}/booking">
                    <div class="project-box-content-header" id="booking"> Book a field </div>
                </a>

            </div>

            <div class="row">
                <a class="box-content-header" href="${pageContext.request.contextPath}/profile">
                    <div class="project-box-content-header" id="profile"> Personal profile </div>
                </a>

                <a class="box-content-header" href="${pageContext.request.contextPath}/chat">
                    <div class="project-box-content-header" id="chat"> Chat </div>
                </a>
            </div>

            <a class="goback" href="${pageContext.request.contextPath}/index.jsp">
                <div> Logout </div>
            </a>
        </div>

    <% } %>

</body>
</html>
