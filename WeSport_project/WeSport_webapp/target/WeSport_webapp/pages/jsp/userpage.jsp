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
    <title>User page</title>
</head>
<body>
<%
    String requested_user = request.getParameter("username");
    UserRemote userRemoteEJB = null;
    List<UserDTO> target_user = null;
    userRemoteEJB = new UserRemoteEJB();
    try {
        target_user = userRemoteEJB.listUsers(requested_user);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    String actual_ip = InetAddress.getLocalHost().getHostAddress();
%>
Hello world!
</body>
</html>