<%@ page import="it.unipi.dsmt.dto.FieldBookingDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.Month" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean[] freeDays = (boolean[])request.getAttribute("freeDays");
%>

<html>
    <head>
        <title>Booking</title>
        <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/CSS/booking.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/CSS/navbar.css" rel="stylesheet" type="text/css">
        <script async src="${pageContext.request.contextPath}/js/booking.js"></script>
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
    </head>
<body onload="onload()">
<nav id="menu" class="navbar navbar-default">
    <div class="container-nav">

        <div id="navbar">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/homepage">Homepage</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/booking">Booking</a></li>
                <li><a href="${pageContext.request.contextPath}/profile"><%=((UserDTO)session.getAttribute("logged_user")).getUsername()%></a></li>
                <li><a href="${pageContext.request.contextPath}/chat">Chat</a></li>
                <li><a href="${pageContext.request.contextPath}/chatroom">Chatroom</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
            </ul>
        </div>

    </div>
</nav>

    <h1>Booking portal</h1>
    <form method="get" action="<%= request.getContextPath()%>/booking?sport=sports&month=month&year=year&day=day">
        <label for="sports">Choose a sport:</label>
        <select name="sports" id="sports">
            <option value="tennis" id="default" selected>Tennis</option>
            <option value="basket">Basket</option>
            <option value="futsal">Futsal</option>
            <option value="rugby">Rugby</option>
        </select>
    <br><br>

    <div class="monthClass">
        <input class="label" type="text" id="month" name="month">
        <input class="label" type="text" id="year" name="year"><br>

    </div>

    <ul class="days">
        <script>let j=1;</script>
        <%
            int month = Integer.parseInt(request.getAttribute("monthNumber").toString());
            int year = Integer.parseInt(request.getAttribute("year").toString());
            LocalDate now = LocalDate.now();
            int currentMonth = now.getMonthValue()-1;
            int currentYear = now.getYear();
            int day = LocalDate.now().getDayOfMonth();

            for( int i = 0; i < freeDays.length; i++){
                if (year < currentYear || (year==currentYear && month < currentMonth) || (year==currentYear && month==currentMonth && i<day)){ %>
                    <li id="passed"><button class="busy"><%= i+1%></button></li>
                <%}
                else if (!freeDays[i]){ %>
                    <li id="ilbusy"><button class="busy"><%= i+1%></button></li>
                <% } else{  %>
                    <li><input type="submit" class="submitDays" name="day" value="<%= i+1%>"></input></li>
                <%}
            }%>
    </ul>

    <ul class="changeMonth">
        <li id="ilPrevious"><button onclick="onclickPrevious()" id="previous"></button></li>
        <li id="ilSearch">
            <input type="submit" id="submit" value=" "/>
    </li>
        <li><button onclick="onclickNext()" id="next"></button></li>
    </ul>
    </form>


</body>
</html>
