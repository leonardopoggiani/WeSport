<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page import="it.unipi.dsmt.interfaces.UserRemote" %>
<%@ page import="it.unipi.dsmt.ejb.UserRemoteEJB" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.net.InetAddress" %><%--
  Created by IntelliJ IDEA.
  User: poggiolinux
  Date: 12/03/22
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserDTO logged_user = (UserDTO)session.getAttribute("logged_user");
    UserRemote userRemoteEJB = null;

    try {
        userRemoteEJB = new UserRemoteEJB();
    } catch (NamingException e) {
        e.printStackTrace();
    }
    String actual_ip = InetAddress.getLocalHost().getHostAddress();
%>
<html>
<head>
    <title>Profile</title>
    <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
</head>
<body>

<td><a href="UserListServlet?username=<%=((UserDTO)session.getAttribute("logged_user")).getUsername()%>"><i class="fas fa-user"></i>&nbsp;<%=((UserDTO)session.getAttribute("logged_user")).getUsername()%></a></td>

<p>Name : <%=logged_user.getName()%></p>
<p>Surname : <%=logged_user.getSurname()%></p>
<p>Email : <%=logged_user.getEmail()%></p>


</body>
</html>
