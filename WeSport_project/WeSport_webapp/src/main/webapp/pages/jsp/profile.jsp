<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page import="it.unipi.dsmt.interfaces.UserRemote" %>

<!-- %@ page import="it.unipi.dsmt.ejb.UserRemoteEJB" % -->
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.net.InetAddress" %>
<%@ page import="it.unipi.dsmt.dto.FieldBookingDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: poggiolinux
  Date: 12/03/22
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserDTO logged_user = (UserDTO)session.getAttribute("logged_user");
    List<FieldBookingDTO> bookings = (List<FieldBookingDTO>)request.getAttribute("bookings");
    String actual_ip = InetAddress.getLocalHost().getHostAddress();
%>
<html>
<head>
    <title>Profile</title>
    <link href="${pageContext.request.contextPath}/CSS/profile.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
</head>
<body>





<div class="home">
    <div class="row">

        <a class="box-content-header" href="${pageContext.request.contextPath}/userlist">
            <div class="project-box-content-header" id="users">PROFILE
                <p>Username : <%=logged_user.getUsername()%></p>
                <p>Name : <%=logged_user.getName()%></p>
                <p>Surname : <%=logged_user.getSurname()%></p>
                <p>Email : <%=logged_user.getEmail()%></p>
                <p>Description : <%=logged_user.getDescription()%></p>
            </div>
        </a>

        <a class="box-content-header"  href="${pageContext.request.contextPath}/booking">
            <div class="project-box-content-header" id="books">Your bookings:


                <% if(bookings == null) {%>
                <p> Such empty! :( </p>
                <%} else {%>

                    <% for(FieldBookingDTO booking : bookings) { %>

                        <p><%=booking.getDay()%>
                        <%=booking.getBooker()%> </p>

                    <%}%>

                <% } %>

            </div>
        </a>

    </div>

</div>


</body>
</html>
