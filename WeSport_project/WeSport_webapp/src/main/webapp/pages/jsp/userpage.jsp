<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%UserDTO user = (UserDTO)request.getAttribute("user");%>

<html>
    <meta charset="UTF-8">
    <title>Profile of <%=user.getUsername()%></title>
    <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
<body>

    <p>Username : <%=user.getUsername()%></p>
    <p>Name : <%=user.getName()%></p>
    <p>Surname : <%=user.getSurname()%></p>
    <p>Email : <%=user.getEmail()%></p>
    <p>Description : <%=user.getDescription()%></p>

</body>
</html>
