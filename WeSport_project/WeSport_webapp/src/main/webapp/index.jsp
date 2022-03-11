<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page import="it.unipi.dsmt.interfaces.UserRemote" %>
<%@ page import="it.unipi.dsmt.ejb.UserRemoteEJB" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.net.InetAddress" %>
<html>
<head>
    <title>WeSport</title>
    <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
</head>

<%
    UserDTO logged_user = (UserDTO)request.getAttribute("logged_user");
    UserRemote userRemoteEJB = null;

    try {
        userRemoteEJB = new UserRemoteEJB();
    } catch (NamingException e) {
        e.printStackTrace();
    }
    String actual_ip = InetAddress.getLocalHost().getHostAddress();
%>
<body>
    <h2>Welcome to WeSport!</h2>

    <div class="login">
        <h1>Login</h1>
        <form method="post" name="login" action="http://<%= actual_ip %>:8080/WeSport_webapp/login">
            <label><b>Username</b></label>
            <input type="text" placeholder="Insert Username" name="username" class="input" required>
            <label><b>Password</b></label>
            <input type="password" placeholder="Insert Password" name="password" class="input" required>
            <button type="submit"  class="button">Login</button>
        </form>
    </div>

</body>
</html>

