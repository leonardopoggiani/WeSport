<%@ page import="it.unipi.dsmt.ejb.UserRemoteEJB" %>
<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unipi.dsmt.interfaces.UserRemote" %>
<%@ page import="java.net.InetAddress" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List of users</title>
    <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
</head>

<body>
<%
    List<UserDTO> users = (List<UserDTO>)request.getAttribute("users");
    UserRemote userRemoteEJB = null;

    try {
        userRemoteEJB = new UserRemoteEJB();
    } catch (NamingException e) {
        e.printStackTrace();
    }
    try {
        assert userRemoteEJB != null;
        try {
            users = userRemoteEJB.listUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    String actual_ip = InetAddress.getLocalHost().getHostAddress();
%>
    List of users

    <br>
    <form action="<%= request.getContextPath()%>/UserListServlet">
        <label for="username">Username filter:</label><br>
        <input type="text" id="username" name="username"><br>
        <input type="submit" value="Filter"/>
    </form>

    <br>

    <%
        if (users != null && !users.isEmpty()){ %>
    <ol>
        <%  for(UserDTO dto : users) { %>
        <li><a href="<%=request.getContextPath() %>/UserListServlet?action=load&code=<%= dto.getUser_id() %>"><%= dto.getUsername() %></a> - Name: <%= dto.getName() %> - Surname: <%= dto.getSurname() %></li>
        <%  } %>
    </ol>
    <%
        }
    %>

</body>
</html>


<