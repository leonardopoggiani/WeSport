<%--
  Created by IntelliJ IDEA.
  User: ZenBook Pro
  Date: 25/03/2022
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <h1>Book your time slot</h1>
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

    <div class="timeslot">
        <br><br>
        <form method="get" action="<%= request.getContextPath()%>/timeslot?slot=timeslot">
            <li>
        <%
            for (int i=0; i<6; i++){%>
                <input type="submit" class="timeslotbox" name="timeslot" value="<%= i+7%>"><br>
        <%
            }
        %>
            </li>
            <li>
                <%
                    for (int i=0; i<6; i++){%>
                <input type="submit" class="timeslotbox" name="timeslot" value="<%= i+13%>"><br>
                <%
                    }
                %>
            </li>
        </ul>
        </form>
        <br><br>
    </div>

</body>
</html>
