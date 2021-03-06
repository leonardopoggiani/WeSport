<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page import="java.net.InetAddress" %>
<%@ page import="it.unipi.dsmt.dto.FieldBookingDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserDTO logged_user = (UserDTO)session.getAttribute("logged_user");
    List<FieldBookingDTO> bookings = (List<FieldBookingDTO>)request.getAttribute("bookings");
%>
<html>
<head>
    <title>Profile</title>
    <link href="${pageContext.request.contextPath}/CSS/profile.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/navbar.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
</head>
<body>

<nav id="menu" class="navbar navbar-default">
    <div class="container-nav">

        <div id="navbar">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/homepage">Homepage</a></li>
                <li><a href="${pageContext.request.contextPath}/booking">Booking</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/profile"><%=((UserDTO)session.getAttribute("logged_user")).getUsername()%></a></li>
                <li><a href="${pageContext.request.contextPath}/chat">Chat</a></li>
                <li><a href="${pageContext.request.contextPath}/chatroom">Chatroom</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
            </ul>
        </div>

    </div>
</nav>
<h1>Profile</h1><br><br>
<div class="home">


    <div class="row">

        <a class="box-content-header">
                <div class="project-box-content-header" id="users">PROFILE
                    <p>Username : <%=logged_user.getUsername()%></p>
                    <p>Name : <%=logged_user.getName()%></p>
                    <p>Surname : <%=logged_user.getSurname()%></p>
                    <p>Email : <%=logged_user.getEmail()%></p>
                    <p>Description : <%=logged_user.getDescription()%></p>
                </div>

        </a>
            <div class="project-box-content-header" id="books">YOUR BOOKINGS

                <% if(bookings == null) {%>
                <p> Such empty! :( </p>
                <%} else {%>

                    <% for(FieldBookingDTO booking : bookings) { %>

                        <a   class="box-content-header" href="${pageContext.request.contextPath}/bookedEvent?event=<%=booking.getBooking_id()%>">
                            <p class="booking"> ID: <%=booking.getBooking_id()%>, <%=booking.getDay()%>: <%=booking.getSport()%> </p>
                        </a>
                    <% } %>
                <% } %>
            </div>


    </div>
</div>

</body>
</html>
