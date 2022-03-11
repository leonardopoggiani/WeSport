<%@ page import="it.unipi.dsmt.dto.UserDTO" %><%--
  Created by IntelliJ IDEA.
  User: poggiolinux
  Date: 11/03/22
  Time: 00:27
  To change this template use File | Settings | File Templates.
--%>
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

        <p>You can now see the list of user registrated</p>
        <a href="${pageContext.request.contextPath}/pages/jsp/userpage.jsp">List of user</a>
    <% } %>
</body>
</html>
