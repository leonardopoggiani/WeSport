<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page import="java.net.InetAddress" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unipi.dsmt.dto.UserBookingDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserDTO logged_user = (UserDTO)session.getAttribute("logged_user");
    ArrayList<UserDTO> friends = (ArrayList<UserDTO>) request.getAttribute("friends");
    Integer bookedID = (Integer) session.getAttribute("event");
    Integer userID = (Integer) session.getAttribute("userID");
    String actual_ip = InetAddress.getLocalHost().getHostAddress();
    UserBookingDTO userBookingDTO = (UserBookingDTO) request.getAttribute("userBooking");
    Integer i=0;
    Integer j=0;
    Integer friendclick = null;
    Integer rating2;
%>
<html>

<head>
    <title>Profile</title>
    <link href="${pageContext.request.contextPath}/CSS/profile.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bookedEvent.js"></script>
</head>
<body>

<div class="home">
    <div class="row">

        <div class="project-box-content-header" id="books">YOUR FRIENDS

            <% if(friends == null) {%>
                <p> Such empty! :( </p>
            <% } else { %>
                <% i=0;j=0; %>
                <% for(UserDTO friend : friends) { %>
                    <a onclick="handleClick('<%=friend.getName()%>','<%=friend.getSurname()%>','<%=friend.getUsername()%>','<%=friend.getId()%>','<%=i%>')" id="customerId" >
                        <p class="booking" id="<%=i%>"><%=friend.getName()%><%=friend.getId()%><%=i%></p>
                    </a>
                    <%i++;%>
                <% } %>
            <% } %>
        </div>

            <div class="project-box-content-header" id="score">

                <p type="hidden" id="tableText" ></p>
                <p type="hidden" id="tableText2" ></p>
                <p type="hidden" id="tableText3" ></p>
                <p type="hidden" id="tableText4" ></p>


                <form method="post" action="${pageContext.request.contextPath}/bookedEvent">
                    <div id="input">
                    </div>
                </form>

            </div>
                <div class="project-box-content-header" id="score">

                    <p type="hidden" id="tableText" ></p>
                    <p type="hidden" id="tableText2" ></p>
                    <p type="hidden" id="tableText3" ></p>
                    <div id="input">
                    </div>

                </div>
        </div>

    </div>

    </body>
</html>
