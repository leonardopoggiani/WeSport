<%@ page import="it.unipi.dsmt.dto.FieldBookingDTO" %><%--
  Created by IntelliJ IDEA.
  User: ZenBook Pro
  Date: 22/03/2022
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    FieldBookingDTO fb = (FieldBookingDTO) session.getAttribute("fieldBooking");
    String sport = fb.getSport();
    //String sport =request.getAttribute("sports").toString() ;
    /*int month = Integer.parseInt(request.getAttribute("month").toString());
    int year = Integer.parseInt(request.getAttribute("year").toString());
    int day = Integer.parseInt(request.getAttribute("day").toString());*/

%>

<html>
<head>
    <title>Book a Field</title>
    <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/bookField.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/navbar.css" rel="stylesheet" type="text/css">
    <script async src="${pageContext.request.contextPath}/js/booking.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/navbar.js"></script>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png"></title>
</head>
<body>
    <h1>Book a Field</h1>
    <nav id="menu" class="navbar navbar-default">
        <div class="container-nav">

            <div id="navbar">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/homepage">Homepage</a></li>
                    <li class="active"><a href="${pageContext.request.contextPath}/booking">Booking</a></li>
                    <li><a href="${pageContext.request.contextPath}/profile">Profile</a></li>
                    <li><a href="${pageContext.request.contextPath}/chat">Chat</a></li>
                </ul>
            </div>

        </div>
    </nav>
    <br><br>
    <form method="post" action="<%= request.getContextPath()%>/bookField">
        <div id="insert">

        <%
            int numPlayer = 0;
            if(sport.equals("tennis")) numPlayer=2;
            else if (sport.equals("basket")) numPlayer = 10;
            else if (sport.equals("futsbal")) numPlayer=10;
            else numPlayer=14;
        %>

        <label>Insert your players' usernames</label>
            <ul class="ulInsert">
                <br>
                <%for (int i=0; i<numPlayer; i++){
                    %>
                    <li><input class ="players" name="player<%=i%>" type="text"></li>

                <%}%>
            </ul>
            <br><br>
            <ul class="ulInsert">
                <li><label class="labelHour">Start hour</label><br><input class="hour" type="time" name="start"></li>
                <li><label class="labelHour">End hour</label><br><input class="hour" type="time" name="end"></li>
                <br><br>
            </ul>
        </div>
        <br><br>
        <input id="submit" type="submit" value="SUBMIT">
    </form>

</body>
</html>
