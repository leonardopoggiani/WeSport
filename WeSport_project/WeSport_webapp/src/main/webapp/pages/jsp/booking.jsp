<%--
  Created by IntelliJ IDEA.
  User: poggiolinux
  Date: 12/03/22
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.Month" %>


<html>
    <head>
        <title>Booking</title>
        <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/CSS/booking.css" rel="stylesheet" type="text/css">
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
    </head>
<body>
    <h1>Booking portal</h1>
    <label for="sports">Choose a sport:</label>
    <select name="sports" id="sports">
        <option value="tennis">Tennis</option>
        <option value="basket">Basket</option>
        <option value="futsal">Futsal</option>
        <option value="rugby">Rugby</option>
    </select>



    <div class="month">
        <p><i><b>MARCH 2022</b></i></p>
            <% String month = "MARCH"; %>

    </div>

    <ul class="days">
        <%
            int i;
            for (i=1;i<=31; i++){

            %>
        <li><button><%= i%></button></li>
            <%
            }%>
    </ul>

    <ul class="changeMonth">
        <li id="ilPrevious"><button id="previous"></button></li>
        <li><button id="next"></button></li>
    </ul>

</body>
</html>
