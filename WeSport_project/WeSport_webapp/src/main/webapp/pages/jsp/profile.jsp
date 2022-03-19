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
    <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
</head>
<body>

<p>Username : <%=logged_user.getUsername()%></p>
<p>Name : <%=logged_user.getName()%></p>
<p>Surname : <%=logged_user.getSurname()%></p>
<p>Email : <%=logged_user.getEmail()%></p>
<p>Description : <%=logged_user.getDescription()%></p>

<h2>Your bookings:</h2>

<% if(bookings == null) {%>
<p> Such empty! :( </p>
<%} else {%>
<table>
    <% for(FieldBookingDTO booking : bookings) { %>
    <tr>
        <td> <%=booking.getBooking_id()%> </td>
        <td> <%=booking.getDay()%>  </td>
        <td> <%=booking.getBooker()%>  </td>
    </tr>
    <%}%>
</table>
<% } %>

</body>
</html>
