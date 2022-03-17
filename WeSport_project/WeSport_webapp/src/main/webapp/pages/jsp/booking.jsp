<%@ page import="it.unipi.dsmt.dto.FieldBookingDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.Month" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
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
    String[] monthsArray;
    monthsArray = new String[]{"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
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
    <label for="sports">Choose a sport:</label>
    <select form="<%=request.getContextPath() %>/booking" name="sports" id="sports">
        <option value="tennis">Tennis</option>
        <option value="basket">Basket</option>
        <option value="futsal">Futsal</option>
        <option value="rugby">Rugby</option>
    </select>



    <div class="month">
        <%
             DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
             LocalDate now = LocalDate.now();
             String month = monthsArray[now.getMonthValue()-1];
             int year = now.getYear();
             int day = now.getDayOfMonth();
        %>
        <p><i><b><%= month + " " + year%></b></i></p>
    </div>

    <ul class="days">
        <%
            //int i;
            for( int i = 0; i < freeDays.length; i++){
                if (i<day){ %>
                    <li id="passed"><button class="busy"><%= i%></button></li>
                    <%}
                else if (!freeDays[i]){ %>
                    <li id="ilbusy"><button class="busy"><%= i%></button></li>
                <% } else{  %>
                    <li><button><%= i%></button></li>
                <%}
            }%>
    </ul>

    <ul class="changeMonth">
        <li id="ilPrevious"><button id="previous"></button></li>
        <li><button id="next"></button></li>
    </ul>


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
            <% if (freeDays[i] !=true && freeDays[i] !=false){ %>
                <td><%=i + 1%>: <%=freeDays[i]%> </td>
            <%}%>
        </tr>
        <%}%>
    </table>

</body>
</html>
