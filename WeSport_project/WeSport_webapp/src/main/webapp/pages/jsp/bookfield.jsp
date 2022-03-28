<%@ page import="it.unipi.dsmt.dto.FieldBookingDTO" %>
<%@ page import="it.unipi.dsmt.dto.UserDTO" %><%--
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
    <title>Bookfield</title>
    <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/bookField.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/CSS/navbar.css" rel="stylesheet" type="text/css">
    <script async src="${pageContext.request.contextPath}/js/booking.js"></script>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
</head>
<body>
    <h1>Book a Field</h1>
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
                    <li><input class ="players" name="<%=i%>" type="text"></li>

                <%}%>
            </ul>
            <br><br>
            <script>
                function mostra(){
                    var input = document.getElementById("hour");
                    window.alert(input.value);
                }

            </script>
            <br><br>
        </div>
        <br><br>
        <input id="submit" type="submit" value="SUBMIT">
    </form>

</body>
</html>
