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

            <% for(UserDTO friend : friends) { %>



            <a onclick="javascript:handleClick('<%=friend.getName()%>','<%=friend.getSurname()%>','<%=friend.getUsername()%>')" id="customerId" >
                <p class="booking"><%=friend.getName()%></p>
            </a>
            <%}%>

            <% } %>




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
