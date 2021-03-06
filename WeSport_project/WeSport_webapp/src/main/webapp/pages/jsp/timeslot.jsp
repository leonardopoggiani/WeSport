<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    boolean[] freeTimeslot = (boolean[])request.getAttribute("freeTimeslot");
%>
<html>
<head>
    <title>Booktimeslot</title>
    <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/booktimeslot.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/navbar.css" rel="stylesheet" type="text/css">
    <script async src="${pageContext.request.contextPath}/js/booking.js"></script>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
</head>
<body>
    <nav id="menu" class="navbar navbar-default">
        <div class="container-nav">

            <div id="navbar">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/homepage">Homepage</a></li>
                    <li class="active"><a href="${pageContext.request.contextPath}/booking">Booking</a></li>
                    <li><a href="${pageContext.request.contextPath}/profile"><%=((UserDTO)session.getAttribute("logged_user")).getUsername()%></a></li>
                    <li><a href="${pageContext.request.contextPath}/chat">Chat</a></li>
                    <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
                </ul>
            </div>

        </div>
    </nav>
    <h1>Book your time slot</h1>
    <br><br>

    <div class="timeslot">
        <br><br>
        <form method="get" action="<%= request.getContextPath()%>/timeslot?slot=timeslot">
        <ul id="ultime">
            <li>
                <%
                    for (int i=0; i<12; i++){
                        if(!freeTimeslot[i]){%>
                        <button class="busy"><%= i+7%></button><br>
                        <%}else{%>
                        <input type="submit" class="timeslotbox" name="timeslot" value="<%= i+7%>"><br>
                <%
                        }
                    }
                %>
            </li>
        </ul>
        </form>
        <br><br>
    </div>

</body>
</html>
