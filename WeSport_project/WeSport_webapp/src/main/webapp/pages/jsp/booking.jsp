<%@ page import="it.unipi.dsmt.dto.FieldBookingDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: poggiolinux
  Date: 12/03/22
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%    List<FieldBookingDTO> bookings = (List<FieldBookingDTO>)request.getAttribute("bookings");%>
<html>
    <head>
        <title>Booking</title>
        <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
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

    <% if(bookings == null) {%>
        <p> There are no bookings! Be the first one! </p>
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

</body>
</html>
