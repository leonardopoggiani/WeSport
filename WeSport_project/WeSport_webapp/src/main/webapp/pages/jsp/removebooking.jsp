<%@ page import="it.unipi.dsmt.dto.FieldBookingDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Remove Booking</title>
  <link href="${pageContext.request.contextPath}/CSS/homepage.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/CSS/admin.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/CSS/navbar.css" rel="stylesheet" type="text/css">
  <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
</head>
<body>

  <script async>
  function changeColor(el){
    el.style.background = 'red';
    var id = 'booking'+ el.value();
    document.getElementById(id).style.background ='red';
  }
  </script>

  <nav id="menu" class="navbar navbar-default">
    <div class="container-nav">

      <div id="navbar">
        <ul class="nav navbar-nav">
          <li><a href="${pageContext.request.contextPath}/admin">Homepage</a></li>
          <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
        </ul>
      </div>

    </div>
  </nav>

    <h1>Remove a Booking</h1>

  <br><br>

  <div class = 'filter'>
    <br><br>
      <form method="get" action="<%= request.getContextPath()%>/showbooking?username=username&sport=sports">
        <ul>
          <li><input type="text" name="username" id="username"></li>
          <li><select name="sports" id="sports">
            <option value="tennis" id="default" selected>Tennis</option>
            <option value="basket">Basket</option>
            <option value="futsal">Futsal</option>
            <option value="rugby">Rugby</option>
          </select></li><br><br>
          <input type="submit" id ="filter" value="FILTER">
        </ul>
      </form>
    <br><br>
    </div>
    <br><br>
  <div class="showBookings">
    <br><br>
    <form method="get" action="<%= request.getContextPath()%>/removebooking?bookingid=bookingid">
      <ul>
        <li>
          <% List<FieldBookingDTO> bookings = (List<FieldBookingDTO>)request.getAttribute("list_bookings");
            if(bookings!=null && !bookings.isEmpty() ){
              int length = bookings.size();
              for (int i=0; i<length; i++){
                String print_str = bookings.get(i).getSport().toString() + ", " + bookings.get(i).getDay().toString();
          %>
                <input type="submit" class="bookingsid" name="bookingid" id="bookingid" onclick="changeColor(this)" value="<%=bookings.get(i).getBooking_id() %>" readonly>
                <input type="text" class="bookings" name="booking" id="booking<%=bookings.get(i).getBooking_id()%>" value="<%=print_str %>" readonly><br>
          <%  }
            }%>
        </li>
      </ul>
    </form>
  </div>


</body>
</html>
