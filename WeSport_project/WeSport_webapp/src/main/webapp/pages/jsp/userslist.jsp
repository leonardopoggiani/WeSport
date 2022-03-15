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
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
</head>

<body>
<%
    List<UserDTO> users = (List<UserDTO>)request.getAttribute("users");
    String username_filter = (String) request.getParameter("filter");
%>
    List of users

    <br>
    <form method="get" action="<%= request.getContextPath()%>/userlist?action=filter">
        <label for="filter">Username filter:</label><br>
        <input type="text" id="filter" name="filter"><br>
        <input type="submit" value="Filter"/>
    </form>

    <br>

    <%
        if (users != null && !users.isEmpty()){ %>
    <ol>
        <%  for(UserDTO dto : users) { %>
        <li><a href="<%=request.getContextPath() %>/userlist?action=load&code=<%= dto.getUsername() %>"><%= dto.getUsername() %></a> - Name: <%= dto.getName() %> - Surname: <%= dto.getSurname() %></li>
        <%  } %>
    </ol>
    <%
        }
    %>

</body>
</html>


<