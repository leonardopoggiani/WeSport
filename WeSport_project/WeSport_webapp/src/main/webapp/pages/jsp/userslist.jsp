<!-- %@ page import="it.unipi.dsmt.ejb.UserRemoteEJB" %-->
<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page import="it.unipi.dsmt.interfaces.UserRemote" %>
<%@ page import="java.net.InetAddress" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List of users</title>
    <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/navbar.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/userlist.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
</head>

<% List<UserDTO> users = (List<UserDTO>)request.getAttribute("users");%>

<body>

<nav id="menu" class="navbar navbar-default">
    <div class="container-nav">

        <div id="navbar">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/homepage">Homepage</a></li>
                <li><a href="${pageContext.request.contextPath}/booking">Booking</a></li>
                <li><a href="${pageContext.request.contextPath}/profile"><%=((UserDTO)session.getAttribute("logged_user")).getUsername()%></a></li>
                <li><a href="${pageContext.request.contextPath}/chat">Chat</a></li>
                <li><a href="${pageContext.request.contextPath}/chatroom">Chatroom</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
            </ul>
        </div>

    </div>
</nav>

<h1> List of users </h1>

    <br>
    <form method="get" action="<%= request.getContextPath()%>/userlist?action=filter">
        <label for="filter">Username filter:</label><br>
        <input type="text" id="filter" name="filter"><br>
        <input type="submit" value="Filter"/>
    </form>

    <br>

    <div class="userlist">
        <table class="table">
            <thead>
            <tr>
                <th colspan="3">Registered user list</th>
            </tr>
            </thead>
            <tbody>
            <% if (users != null && !users.isEmpty()){ %>
                <% for(UserDTO dto : users) { %>
                    <tr>
                        <td>
                            <a href="<%=request.getContextPath() %>/userlist?action=load&code=<%= dto.getUsername() %>"> <%= dto.getUsername() %> </a>
                        </td>

                        <td>
                            <%= dto.getName() %>
                        </td>

                        <td>
                            <%= dto.getSurname() %>
                        </td>

                        <td>
                            <%= %>
                        </td>
                    </tr>
                <% } %>
            <% } else { %>
                <tr>
                    <td>
                        No registered users
                    </td>
                </tr>
            <% } %>
            </tbody>
        </table>
    </div>

</body>
</html>
