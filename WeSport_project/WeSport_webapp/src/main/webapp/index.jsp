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
    String actual_ip = InetAddress.getLocalHost().getHostAddress();
%>
<body>
    <h1>Welcome to WeSport!</h1>

        <div class="login-page">
            <h2>Login</h2>
            <div class="form">
                <form method="post" class="login-form" action="http://<%= actual_ip %>:8080/WeSport_webapp/login">
                    <input type="text" name="username" placeholder="username" required />
                    <input type="password" name="password" placeholder="password"required />
                    <button type="submit" class="button">login</button>
                </form>
            </div>
        </div>

</body>
</html>

