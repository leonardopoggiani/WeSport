<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page import="it.unipi.dsmt.interfaces.UserRemote" %>

<!-- %@ page import="it.unipi.dsmt.ejb.UserRemoteEJB" % -->
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.net.InetAddress" %>
<%@ page import="it.unipi.dsmt.dto.FieldBookingDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: poggiolinux
  Date: 12/03/22
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserDTO logged_user = (UserDTO)session.getAttribute("logged_user");
    ArrayList<UserDTO> friends = (ArrayList<UserDTO>) request.getAttribute("friends");
    Integer bookedID = (Integer) session.getAttribute("event");
    String actual_ip = InetAddress.getLocalHost().getHostAddress();

%>
<html>
<head>
    <title>Profile</title>
    <link href="${pageContext.request.contextPath}/CSS/profile.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
</head>
<body>





<div class="home">
    <div class="row">

        <div class="project-box-content-header" id="books">YOUR FRIENDS

            <% if(friends == null) {%>
            <p> Such empty! :( </p>
            <%} else {%>

            <% for(UserDTO friend : friends) { %>

            <a>
                <p class="booking"><%=friend.getName()%></p>
            </a>
            <%}%>

            <% } %>




        </div>

            <div class="project-box-content-header" id="score"> SCORE



            </div>




    </div>

</div>


</body>
</html>
