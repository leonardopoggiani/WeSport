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
    monthsArray = new String[] {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};


%>
                  
<html>
    <head>
        <title>Booking</title>
        <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/CSS/booking.css" rel="stylesheet" type="text/css">
        <script async src="${pageContext.request.contextPath}/js/booking.js"></script>
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
    </head>
<body onload="onload()">
    <h1>Booking portal</h1>
        <label for="sports">Choose a sport:</label>
        <select form="<%=request.getContextPath()%>/booking?sport=sports" name="sports" id="sports">
            <option value="tennis" id="default" selected>Tennis</option>
            <option value="basket">Basket</option>
            <option value="futsal">Futsal</option>
            <option value="rugby">Rugby</option>
        </select>
    </ul>

    <br><br>


    <div class="monthClass">
        <%
            /*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
             LocalDate now = LocalDate.now();
             String month = monthsArray[now.getMonthValue()-1];
             int year = now.getYear();*/
             int day = LocalDate.now().getDayOfMonth();
        %>
        <!--p id="month-year"><i><b>MARCH 2022</b></i></p!-->
        <input class="label" form="<%=request.getContextPath()%>/booking" type="text" id="month" name="month" disabled>
        <input class="label" form="<%=request.getContextPath() %>/booking" type="text" id="year" name="year" disabled><br>
    </div>

    <ul class="days">
        <script>let j=1;</script>
        <%
            //int i;
            for( int i = 0; i < freeDays.length; i++){%>
                <!--script async type="text/javascript" src="${pageContext.request.contextPath}/js/booking.js">
                    createCalendar(j);
                    j=j+1;
                </script!-->
                <%
                if (!freeDays[i]){ %>
                    <li id="busy"><button class="busy"><%= i+1%></button></li>
                <% } else{  %>
                    <li><button><%= i+1%></button></li>
                <%}
            }%>
    </ul>

    <ul class="changeMonth">
        <li id="ilPrevious"><button onclick="onclickPrevious()" id="previous"></button></li>
        <li id="ilSearch"><form method="get" action="<%=request.getContextPath()%>/booking?sport=sports&month=month&year=year">
            <button type="submit" id="submit"></button>
        </form></li>
        <li><button onclick="onclickNext()" id="next"></button></li>
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
