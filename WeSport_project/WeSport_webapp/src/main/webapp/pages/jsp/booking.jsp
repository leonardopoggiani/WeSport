<%@ page import="it.unipi.dsmt.dto.FieldBookingDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.Month" %>
<%--
  Created by IntelliJ IDEA.
  User: poggiolinux
  Date: 12/03/22
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<FieldBookingDTO> bookings = (List<FieldBookingDTO>)request.getAttribute("bookings");
    boolean[] freeDays = (boolean[])request.getAttribute("freeDays");
%>
                  
<html>
    <head>
        <title>Booking</title>
        <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/CSS/booking.css" rel="stylesheet" type="text/css">
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
    </head>
<body>
    <h1>Booking portal</h1>

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

    <table>
        <% for(int i = 0; i < freeDays.length; i++) { %>
        <tr>
            <td><%=i + 1%>: <%=freeDays[i]%> </td>
        </tr>
        <%}%>
    </table>

    <label for="sports">Choose a sport:</label>
    <select form="<%=request.getContextPath() %>/booking" name="sports" id="sports">
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
