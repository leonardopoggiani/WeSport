<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page import="java.net.InetAddress" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unipi.dsmt.dto.UserBookingDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserDTO logged_user = (UserDTO)session.getAttribute("logged_user");
    ArrayList<UserDTO> friends = (ArrayList<UserDTO>) request.getAttribute("friends");
    Integer bookedID = (Integer) session.getAttribute("event");
    Integer userID = (Integer) session.getAttribute("userID");
    String actual_ip = InetAddress.getLocalHost().getHostAddress();
    UserBookingDTO userBookingDTO = (UserBookingDTO) request.getAttribute("userBooking");
    Integer i=0;
    Integer j=0;
    Integer friendclick = null;
    Integer rating2;
%>
<html>

<head>
    <title>Profile</title>
    <link href="${pageContext.request.contextPath}/CSS/profile.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/navbar.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bookedEvent.js"></script>
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
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
            </ul>
        </div>

    </div>
</nav>

<div class="home">
    <div class="row">

        <div class="project-box-content-header" id="books">YOUR FRIENDS

            <% if(friends == null) {%>
            <p> Such empty! :( </p>
            <% } else { %>
                <% for(UserDTO friend : friends) { %>
                    <div onclick="handleClick('<%=friend.getName()%>','<%=friend.getSurname()%>','<%=friend.getUsername()%>','<%=friend.getId()%>', '<%=actual_ip%>')" id="customerId" >
                        <p class="booking" id="<%=friend.getId()%>"> <%=friend.getUsername()%> </p>
                    </div>
                    <%i++;%>
                <% } %>
            <% } %>
        </div>

        <div class="project-box-content-header" id="score">
            <form method="post" id="form" style=" visibility:hidden;" >
                <p id="tableText" > </p>
                <p id="tableText2" > </p>
                <p id="usernamerating" name="usernamerating" > </p>
                <p id="tableText4" > </p>

                <div id="input" style=" visibility:hidden;">
                    <input type="text" id="rating" name="rating"><br>
                    <input type="submit" id="button" value="Rate!"/>
                </div>
            </form>
        </div>
    </div>

</div>

</body>
</html>
