<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Homepage</title>
    <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/navbar.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
</head>
<body>
    <nav id="menu" class="navbar navbar-default">
        <div class="container-nav">

            <div id="navbar">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="${pageContext.request.contextPath}/homepage">Homepage</a></li>
                    <li><a href="${pageContext.request.contextPath}/booking">Booking</a></li>
                    <li><a href="${pageContext.request.contextPath}/profile"><%=((UserDTO)session.getAttribute("logged_user")).getUsername()%></a></li>
                    <li><a href="${pageContext.request.contextPath}/chat">Chat</a></li>
                    <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
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
                    <div class="project-box-content-header" id="users"> Registered user</div>
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

            <div class="row-last">
                <a class="box-content-header" href="${pageContext.request.contextPath}/chatroom">
                    <div class="project-box-content-header" id="chatroom"> Chatroom </div>
                </a>
            </div>

        </div>

    <% } %>

</body>
</html>
