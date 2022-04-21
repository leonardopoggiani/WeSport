<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    UserDTO user = (UserDTO)request.getAttribute("user");
    Double scoreUser = (Double) request.getAttribute("scoreUser");
%>
<html>
    <meta charset="UTF-8">
    <title>Profile of <%=user.getUsername()%></title>
    <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/userlist.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/navbar.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
<body>

<nav id="menu" class="navbar navbar-default">
    <div class="container-nav">

        <div id="navbar">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/homepage">Homepage</a></li>
                <li><a href="${pageContext.request.contextPath}/booking">Booking</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/profile"><%=((UserDTO)session.getAttribute("logged_user")).getUsername()%></a></li>
                <li><a href="${pageContext.request.contextPath}/chat">Chat</a></li>
                <li><a href="${pageContext.request.contextPath}/chatroom">Chatroom</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
            </ul>
        </div>

    </div>
</nav>

    <h1> User page </h1>

    <div class="userpage">
        <table class="table">
            <tbody>
            <tr>
                <td>
                    <label> Username: </label> <%=user.getUsername()%>
                </td>
            </tr>
            <tr>
                <td>
                    <label> Name: </label> <%=user.getName()%>
                </td>
            </tr>
            <tr>
                <td>
                    <label> Surname: </label> <%=user.getSurname()%>
                </td>
            </tr>
            <tr>
                <td>
                    <label> Email: </label> <%=user.getEmail()%>
                </td>
            </tr>
            <tr>
                <td>
                    <label> Description: </label> <%=user.getDescription()%>
                </td>
            </tr>
            <tr>
                <td>
                    <label> Score: </label> <%=scoreUser%>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

</body>
</html>
