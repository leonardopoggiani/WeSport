<%@ page import="it.unipi.dsmt.dto.UserDTO" %>
<%@ page import="it.unipi.dsmt.interfaces.UserRemote" %>

<!-- %@ page import="it.unipi.dsmt.ejb.UserRemoteEJB" % -->
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.net.InetAddress" %>
<<<<<<< HEAD
<%@ page import="it.unipi.dsmt.dto.FieldBookingDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<<<<<<< HEAD
<<<<<<< HEAD
<%@ page import="java.util.ArrayList" %><%--
=======
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unipi.dsmt.ejb.UserBookingEJB" %>
<%@ page import="it.unipi.dsmt.dto.UserBookingDTO" %>
<%@ page import="javax.persistence.criteria.CriteriaBuilder" %><%--
>>>>>>> parent of d110da1 (refactor)
=======
<%@ page import="java.util.ArrayList" %><%--
>>>>>>> parent of 3132b2f (Merge branch 'bookedEventServlet' of https://github.com/leonardopoggiani/WeSport into bookedEventServlet)
=======
<%@ page import="java.util.ArrayList" %><%--
>>>>>>> parent of 82cbc86 (bookedevent)
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> parent of 82cbc86 (bookedevent)

%>
<html>
<head>
    <script type="text/javascript">
        function setRating() {
            console.log("SETTANDO");
        }

        function handleClick(name,surname,username)
        {
            console.log("Dentro handle "+name);
            document.getElementById("tableText").textContent="NAME: "+name.toString();
            document.getElementById("tableText2").textContent="SURNAME: "+surname.toString();
            document.getElementById("tableText3").textContent="USERNAME: "+username.toString();
            document.getElementById("input").textContent="PLAYER RATING:";
            var inptext = document.createElement( "input" );

            var buttonElement = document.createElement( "button" );

            inptext.id="inp";
            document.getElementById("input").append(inptext);
            document.getElementById("input").append(buttonElement);


            console.log("Dentro handle2");

        }


    </script>
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
<<<<<<< HEAD
<<<<<<< HEAD

            <% for(UserDTO friend : friends) { %>

            <a>
                <p class="booking"><%=friend.getName()%></p>
            </a>
            <%}%>

            <% } %>
=======
            <%   i=0;j=0;%>
=======

>>>>>>> parent of 82cbc86 (bookedevent)
            <% for(UserDTO friend : friends) { %>



            <a onclick="javascript:handleClick('<%=friend.getName()%>','<%=friend.getSurname()%>','<%=friend.getUsername()%>')" id="customerId" >
                <p class="booking"><%=friend.getName()%></p>
            </a>
            <%}%>

            <% } %>




        </div>
>>>>>>> parent of d110da1 (refactor)



<<<<<<< HEAD

        </div>

            <div class="project-box-content-header" id="score"> SCORE


=======
                <p type="hidden" id="tableText" ></p>
                <p type="hidden" id="tableText2" ></p>
                <p type="hidden" id="tableText3" ></p>
                <div id="input">
                </div>
>>>>>>> parent of 82cbc86 (bookedevent)



=======
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

            <div class="project-box-content-header" id="books"> YOUR FRIENDS

                <% if(friends == null) {%>
                    <p> Such empty! :( </p>
                <% } else { %>
                    <% for(UserDTO friend : friends) { %>
                        <a onclick="handleClick('<%=friend.getName()%>','<%=friend.getSurname()%>','<%=friend.getUsername()%>')" id="customerId" >
                            <p class="booking"><%=friend.getName()%></p>
                        </a>
                    <% } %>
                <% } %>
>>>>>>> parent of 3132b2f (Merge branch 'bookedEventServlet' of https://github.com/leonardopoggiani/WeSport into bookedEventServlet)

            </div>




    </div>

</div>


</body>
</html>
